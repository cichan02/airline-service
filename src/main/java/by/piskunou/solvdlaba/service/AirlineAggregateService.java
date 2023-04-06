package by.piskunou.solvdlaba.service;

import java.util.UUID;

public interface AirlineAggregateService {

	void create(UUID id, String data);

	void update(UUID id, String data);

	void remove(UUID id);

}
