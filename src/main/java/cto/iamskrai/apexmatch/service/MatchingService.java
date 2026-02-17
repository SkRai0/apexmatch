package cto.iamskrai.apexmatch.service;

import cto.iamskrai.apexmatch.engine.OrderBook;
import cto.iamskrai.apexmatch.model.Order;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    public final OrderBook orderBook;

    public MatchingService(OrderBook orderBook) {
        this.orderBook = orderBook;
    }

    public void addOrder(Order order){
        orderBook.addOrder(order);
        System.out.println("Order Added:\n" + order);
    }
}
