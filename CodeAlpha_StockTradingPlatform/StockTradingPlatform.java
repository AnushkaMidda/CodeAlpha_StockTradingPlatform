// Stock Trading Platform (main class)

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Map<String, Stock> market = new HashMap<>();
            
            // Predefined stock list
            market.put("AAPL", new Stock("AAPL", 150));
            market.put("GOOG", new Stock("GOOG", 2800));
            market.put("TSLA", new Stock("TSLA", 700));
            market.put("MSFT", new Stock("MSFT", 310));
            
            // Get starting balance from user
            System.out.print("Enter Amount to Invest: ");
            double startingBalance = sc.nextDouble();
            Portfolio portfolio = new Portfolio(startingBalance);
            
            int choice;
            do {
                for (Stock stock : market.values()) {
                    stock.updatePrice(); // update each stock's price
                }
                
                System.out.println("\n--- Stock Trading Platform ---");
                System.out.println("1. View Market");
                System.out.println("2. Buy Stock");
                System.out.println("3. Sell Stock");
                System.out.println("4. View Portfolio");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1 -> {
                        System.out.println("\n--- Market Data ---");
                        for (Stock s : market.values()) {
                            System.out.printf("%s: $%.2f\n", s.name, s.price);
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter stock symbol to buy (AAPL, GOOG, TSLA, MSFT): ");
                        String buySymbol = sc.next().toUpperCase();
                        if (!market.containsKey(buySymbol)) {
                            System.out.println("Stock not found.");
                            break;
                        }
                        System.out.print("Enter quantity: ");
                        int buyQty = sc.nextInt();
                        portfolio.buy(buySymbol, buyQty, market.get(buySymbol).price);
                    }
                    case 3 -> {
                        System.out.print("Enter stock symbol to sell (AAPL, GOOG, TSLA, MSFT): ");
                        String sellSymbol = sc.next().toUpperCase();
                        if (!market.containsKey(sellSymbol)) {
                            System.out.println("Stock not found.");
                            break;
                        }
                        System.out.print("Enter quantity: ");
                        int sellQty = sc.nextInt();
                        portfolio.sell(sellSymbol, sellQty, market.get(sellSymbol).price);
                    }
                    case 4 -> portfolio.viewPortfolio(market);
                    case 5 -> System.out.println("Exiting. Goodbye!");
                    default -> System.out.println("Invalid choice!");
                }
            } while (choice != 5);
        }
    }
}
