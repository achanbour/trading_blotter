package src.trade;

import java.sql.Timestamp;

enum TradeDirection {BUY, SELL}

/**
 * This dictionary object represents a unique trade entry in the blotter
 */
public class TradeEntry {
    public Timestamp timestamp;
    public Trader trader;
    public String product;
    public String counterparty;
    public TradeDirection direction;
    public int quantity;
    public double price;

    public TradeEntry(Trader trader,
                      String product,
                      String counterparty,
                      TradeDirection direction,
                      int quantity,
                      double price) {
        this.trader = trader;
        this.product = product;
        this.counterparty = counterparty;
        this.direction = direction;
        this.quantity = quantity;
        this.price = price;
    }
}

