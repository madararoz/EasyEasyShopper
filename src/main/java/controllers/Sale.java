package controllers;

public class Sale {

    private final int productId;
    private final double total;
    private final int quantity;

    public Sale(int productId, double total, int quantity) {
        this.productId = productId;
        this.total = total;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public double getTotal() {
        return total;
    }

    public int getQuantity() {
        return quantity;
    }
}
