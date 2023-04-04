package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.AirlineAggregate;

import java.util.UUID;

public interface AirlineCommandService {

	void create(AirlineAggregate airline);

	void updateById(UUID id, AirlineAggregate airline);

	void removeById(UUID id);

}
