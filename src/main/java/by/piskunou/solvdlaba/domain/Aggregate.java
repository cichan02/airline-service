package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.event.Event;

import java.util.List;
import java.util.stream.Stream;

public interface Aggregate {

	void apply(Event event);

	void apply(Stream<Event> eventStream);

	void apply(List<Event> events);

}
