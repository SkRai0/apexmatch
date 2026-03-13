package cto.iamskrai.apexmatch.engine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import cto.iamskrai.apexmatch.model.Order;

@Component
public class OrderQueue {
    
    private final BlockingQueue<Order> queue = new LinkedBlockingQueue<>();

    public void submit(Order order) {
        queue.offer(order);
    }

    public Order take() throws InterruptedException {
        return queue.take();
    }
}
