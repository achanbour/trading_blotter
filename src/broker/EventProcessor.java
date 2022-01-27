package src.broker;

import java.util.Objects;

/**
 * The event processor is identified by its name and register to the broker to receive events
 */
public class EventProcessor {
    @Override
    public boolean equals(Object o) {
        return (o != null && getClass() == o.getClass());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass());
    }
}