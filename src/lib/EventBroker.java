package src.lib;

import java.util.Hashtable;

/**
 * The event broker maintains a one-to-one mapping between an event processor and an event channel
 */
final public class EventBroker {
    Hashtable<EventProcessor, EventChannel> channels = new Hashtable<>();

    public void registerChannel(EventProcessor processor) {
        channels.put(processor, new EventChannel());
    }

    public void registerEvent(EventProcessor processor, Event e) {
        channels.get(processor).addEvent(e);
    }
}
