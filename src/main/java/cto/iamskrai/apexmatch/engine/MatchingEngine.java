package cto.iamskrai.apexmatch.engine;

import java.util.List;

import org.springframework.stereotype.Component;

import cto.iamskrai.apexmatch.repository.TradeRepository;

import cto.iamskrai.apexmatch.model.Order;
import cto.iamskrai.apexmatch.model.Trade;

@Component
public class MatchingEngine {
    
    private final OrderBook orderBook;
    private final OrderQueue orderQueue;
    private final TradeRepository tradeRepository;

    public MatchingEngine(OrderBook orderBook, OrderQueue orderQueue, TradeRepository tradeRepository) {
        this.orderBook = orderBook;
        this.orderQueue = orderQueue;
        this.tradeRepository = tradeRepository;
    }

        public void start() {
            Thread worker = new Thread(() -> {
                while (true) {
                    try {
                        Order order = orderQueue.take();
                        System.out.println("Matching order: " + order.getId());
                        orderBook.addOrder(order);
                        List<Trade> trades = orderBook.drainTrades();
                        trades.forEach(tradeRepository::save);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            worker.setDaemon(true);
            worker.start();
        }
}
