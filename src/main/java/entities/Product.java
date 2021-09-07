package entities;

public class Product {

    int id;
    String name;
    double price;
    int quantity;
    double cost;

    public Product(int id, String name, double price, int quantity, double cost) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.cost = cost;
    }


    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return id + "\t" +  name + "\t" + price + "\t" + quantity + "\t"+ cost;
    }
}
