package by.piskunou.solvdlaba.domain;

import by.piskunou.solvdlaba.domain.event.Event;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Stream;

public abstract class AbstractFunctionalAggregate implements Aggregate {

	@Override
	public void apply(@NotNull Event event) {
		event.copyTo(this);
	}

	@Override
	public void apply(@NotNull Stream<Event> eventStream) {
		eventStream.forEach(this::apply);
	}

	@Override
	public void apply(@NotNull List<Event> events) {
		apply(events.stream());
	}

}