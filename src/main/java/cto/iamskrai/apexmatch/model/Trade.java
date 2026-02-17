package cto.iamskrai.apexmatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int buyOrderId;
    private int sellOrderId;
    private int price;
    private int qty;

    public Trade(){}

    public Trade(int buyOrderId, int sellOrderId, int price, int qty) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.qty = qty;
    }

    public int getBuyOrderId() {
        return buyOrderId;
    }

    public int getSellOrderId() {
        return sellOrderId;
    }

    public int getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "buyOrderId=" + buyOrderId +
                ", sellOrderId=" + sellOrderId +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
