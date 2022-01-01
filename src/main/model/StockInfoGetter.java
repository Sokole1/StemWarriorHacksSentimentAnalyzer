package main.model;

public interface StockInfoGetter {
    // EFFECTS: produce Stock with the given ticker
    Stock getStock(String ticker);
}
