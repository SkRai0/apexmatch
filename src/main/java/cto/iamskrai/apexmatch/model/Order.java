package cto.iamskrai.apexmatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Integer id;
    private Integer price;
    private Integer qty;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    public Order(){}

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
