//Stock.java

public class Stock {
    String name;
    double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void updatePrice() {
        double change = (Math.random() * 1000) - 500; // -500 to +500
        price = Math.max(1, price + change); // Price can't go below $1
    }
}
