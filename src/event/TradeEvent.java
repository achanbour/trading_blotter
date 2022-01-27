package src.event;

import src.broker.Event;
import src.trade.TradeEntry;

public class TradeEvent extends Event {
    TradeEntry trade;

    public TradeEvent(TradeEntry trade) {
        this.trade = trade;
    }
}
