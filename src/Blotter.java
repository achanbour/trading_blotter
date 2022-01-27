package src;

import src.broker.EventBroker;
import src.broker.EventProcessor;
import src.eventprocessor.QuantityProcessor;

public class Blotter {

    public static void main(String[] args) {

        EventBroker broker = new EventBroker();
        EventProcessor quantity = new QuantityProcessor();

        broker.registerChannel(quantity);
    }
}
