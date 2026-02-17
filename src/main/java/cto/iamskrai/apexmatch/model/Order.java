package cto.iamskrai.apexmatch.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Order {
    @Min(1)
    private Integer id;
    @Min(1)
    private Integer price;
    @Min(1)
    private Integer qty;
    @NotNull
    private OrderType type;

    public Order(Integer id, Integer price, Integer qty, OrderType type) {
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", qty=" + qty +
                ", type=" + type +
                '}';
    }
}
