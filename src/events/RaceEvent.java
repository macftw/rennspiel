package events;

import javafx.event.Event;
import javafx.event.EventType;

public class RaceEvent extends Event {

    public static final EventType<RaceEvent> CRASH = new EventType(ANY, "CRASH");
    public static final EventType<RaceEvent> START = new EventType(ANY, "START");
    public static final EventType<RaceEvent> CHECKPOINT = new EventType(ANY, "CHECKPOINT");
    public static final EventType<RaceEvent> FINISH = new EventType(ANY, "FINISH");

    public RaceEvent(EventType<RaceEvent> type) {
        super(type);
    }

}
