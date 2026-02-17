package cto.iamskrai.apexmatch.model;

public class Trade {
    private int buyOrderId;
    private int sellOrderId;
    private int price;
    private int qty;

    public Trade(int buyOrderId, int sellOrderId, int price, int qty) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.price = price;
        this.qty = qty;
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
