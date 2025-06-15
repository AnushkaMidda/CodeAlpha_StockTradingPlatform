// Portfolio.java

import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double balance;

    public Portfolio(double initialBalance) {
        this.balance = initialBalance;
    }

    public void buy(String stock, int quantity, double price) {
        double cost = price * quantity;
        if (cost > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        holdings.put(stock, holdings.getOrDefault(stock, 0) + quantity);
        balance -= cost;
        System.out.println("Bought " + quantity + " shares of " + stock);
    }

    public void sell(String stock, int quantity, double price) {
        if (!holdings.containsKey(stock) || holdings.get(stock) < quantity) {
            System.out.println("Not enough shares to sell!");
            return;
        }
        holdings.put(stock, holdings.get(stock) - quantity);
        if (holdings.get(stock) == 0) holdings.remove(stock);
        balance += price * quantity;
        System.out.println("Sold " + quantity + " shares of " + stock);
    }

    public void viewPortfolio(Map<String, Stock> market) {
        System.out.println("\n--- Portfolio ---");
        System.out.printf("Balance: $%.2f\n", balance);
        double totalValue = balance;
        for (String stock : holdings.keySet()) {
            int qty = holdings.get(stock);
            double value = qty * market.get(stock).price;
            totalValue += value;
            System.out.printf("%s: %d shares ($%.2f)\n", stock, qty, value);
        }
        System.out.printf("Total Portfolio Value: $%.2f\n", totalValue);
    }
}
