package cto.iamskrai.apexmatch.dto;

import cto.iamskrai.apexmatch.model.Order;

import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class OrderBookResponseDTO {

    private TreeMap<Integer, List<String>> buyOrderBook;
    private TreeMap<Integer, List<String>> sellOrderBook;

    public OrderBookResponseDTO(TreeMap<Integer, List<String>> buyOrderBook, TreeMap<Integer, List<String>> sellOrderBook) {
        this.buyOrderBook = buyOrderBook;
        this.sellOrderBook = sellOrderBook;
    }

    public TreeMap<Integer, List<String>> getBuyOrderBook() {
        return buyOrderBook;
    }

    public TreeMap<Integer, List<String>> getSellOrderBook() {
        return sellOrderBook;
    }
}
