package by.piskunou.solvdlaba.domain.event;

import by.piskunou.solvdlaba.domain.Aggregate;

public interface Event {

	void copyTo(Aggregate aggregate);

}