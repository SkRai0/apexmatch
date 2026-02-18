package cto.iamskrai.apexmatch.engine;

import cto.iamskrai.apexmatch.model.OrderType;
import cto.iamskrai.apexmatch.model.Order;
import cto.iamskrai.apexmatch.model.Trade;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

@Component
public class OrderBook {
    TreeMap<Integer, Queue<Order>> buyOrderBook = new TreeMap<>(Collections.reverseOrder());
    TreeMap<Integer, Queue<Order>> sellOrderBook = new TreeMap<>();
    List<Trade> tradeList = new ArrayList<>();

    public TreeMap<Integer, Queue<Order>> getBuyOrderBook() {
        return buyOrderBook;
    }

    public TreeMap<Integer, Queue<Order>> getSellOrderBook() {
        return sellOrderBook;
    }

    public List<Trade> getTradeList() {
        return tradeList;
    }

    public void addBuyOrder(Order order){
        int price = order.getPrice();
        buyOrderBook.computeIfAbsent(price, k->new LinkedList<>())
                .add(order);
    }

    public void addSellOrder(Order order){
        int price = order.getPrice();
        sellOrderBook.computeIfAbsent(price, k->new LinkedList<>())
                .add(order);
    }

    public synchronized void addOrder(Order order){
        if(order.getType().equals(OrderType.BUY)){
            matchBuyOrder(order);
        }else{
            matchSellOrder(order);
        }
    }

    private void matchBuyOrder(Order buyOrder){
        while(buyOrder.getQty()>0 && !sellOrderBook.isEmpty()){
            int bestSellPrice = sellOrderBook.firstKey();

            if(bestSellPrice>buyOrder.getPrice()) break;

            Queue<Order> sellQueue = sellOrderBook.get(bestSellPrice);
            Order sellOrder = sellQueue.peek();

            int tradeQty = Math.min(sellOrder.getQty(), buyOrder.getQty());

            Trade trade = new Trade(buyOrder.getId(), sellOrder.getId(), bestSellPrice, tradeQty);
            System.out.println("Trade Executed: " + trade);
            tradeList.add(trade);

            sellOrder.setQty(sellOrder.getQty()-tradeQty);
            buyOrder.setQty(buyOrder.getQty()-tradeQty);

            if(sellOrder.getQty() == 0){
                sellQueue.poll();
            }

            if(sellQueue.isEmpty()){
                sellOrderBook.remove(bestSellPrice);
            }
        }
        if(buyOrder.getQty()>0){
            addBuyOrder(buyOrder);
        }
    }

    private void matchSellOrder(Order sellOrder){
        while(sellOrder.getQty()>0 && !buyOrderBook.isEmpty()){
            int bestBuyPrice = buyOrderBook.firstKey();

            if(bestBuyPrice<sellOrder.getPrice()) break;

            Queue<Order> buyQueue = buyOrderBook.get(bestBuyPrice);
            Order buyOrder = buyQueue.peek();

            int tradeQty = Math.min(sellOrder.getQty(), buyOrder.getQty());

            Trade trade = new Trade(buyOrder.getId(), sellOrder.getId(), bestBuyPrice, tradeQty);
            System.out.println("Trade Executed: " + trade);
            tradeList.add(trade);

            sellOrder.setQty(sellOrder.getQty()-tradeQty);
            buyOrder.setQty(buyOrder.getQty()-tradeQty);

            if(buyOrder.getQty() == 0){
                buyQueue.poll();
            }

            if(buyQueue.isEmpty()){
                buyOrderBook.remove(bestBuyPrice);
            }
        }
        if(sellOrder.getQty()>0){
            addSellOrder(sellOrder);
        }
    }

    public void printOrderBook(){
        System.out.println("\nBUY BOOK:");
        for (Map.Entry<Integer, Queue<Order>> entry : buyOrderBook.entrySet()) {
            System.out.println("Price: " + entry.getKey());
            for (Order o : entry.getValue()) {
                System.out.println("   " + o.getQty());
            }
        }

        System.out.println("\nSELL BOOK:");
        for (Map.Entry<Integer, Queue<Order>> entry : sellOrderBook.entrySet()) {
            System.out.println("Price: " + entry.getKey());
            for (Order o : entry.getValue()) {
                System.out.println("   " + o.getQty());
            }
        }
    }

    public List<Trade> drainTrades(){
        List<Trade> currentTrades = new ArrayList<>(tradeList);
        tradeList.clear();
        return currentTrades;
    }

}
