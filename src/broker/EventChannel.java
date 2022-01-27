package src.broker;

import java.util.ArrayList;
import java.util.List;

/**
 * The event channel stores events waiting to be picked up by an event processor for processing
 */
public class EventChannel {
    List<Event> events = new ArrayList<>();

    public void addEvent(Event e) {
        events.add(e);
    }

    public Event popEvent() {
        return events.remove(0);
    }
}
