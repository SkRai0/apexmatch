package cto.iamskrai.apexmatch.service;

import cto.iamskrai.apexmatch.dto.OrderBookResponseDTO;
import cto.iamskrai.apexmatch.engine.OrderBook;
import cto.iamskrai.apexmatch.model.Order;
import cto.iamskrai.apexmatch.model.Trade;
import cto.iamskrai.apexmatch.repository.OrderRepository;
import cto.iamskrai.apexmatch.repository.TradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;

@Service
public class MatchingService {

    public final OrderBook orderBook;
    public final OrderRepository orderRepository;
    public final TradeRepository tradeRepository;

    public MatchingService(OrderBook orderBook, OrderRepository orderRepository, TradeRepository tradeRepository) {
        this.orderBook = orderBook;
        this.orderRepository = orderRepository;
        this.tradeRepository = tradeRepository;
    }

    @Transactional
    public void addOrder(Order order){
        orderRepository.save(order);
        orderBook.addOrder(order);
        List<Trade> newTrades = orderBook.drainTrades();
        newTrades.forEach(tradeRepository::save);
    }

    public OrderBookResponseDTO getOrderBookSnapshot() {

        TreeMap<Integer, List<String>> buySnapshot = new TreeMap<>();
        TreeMap<Integer, List<String>> sellSnapshot = new TreeMap<>();

        orderBook.getBuyOrderBook().forEach((price, queue) ->
                buySnapshot.put(price,
                        queue.stream()
                                .map(order -> "OrderId:" + order.getId() + " Qty:" + order.getQty())
                                .toList())
        );

        orderBook.getSellOrderBook().forEach((price, queue) ->
                sellSnapshot.put(price,
                        queue.stream()
                                .map(order -> "OrderId:" + order.getId() + " Qty:" + order.getQty())
                                .toList())
        );

        return new OrderBookResponseDTO(buySnapshot, sellSnapshot);
    }
}
