package by.piskunou.solvdlaba.service;

import by.piskunou.solvdlaba.domain.AirlineAggregate;

import java.util.List;
import java.util.UUID;

public interface AirlineQueryService {

	List<AirlineAggregate> findAll();

	AirlineAggregate findById(UUID id);

	List<AirlineAggregate> search(String inquiry);

	boolean isExistsById(UUID id);

	boolean isExistsByName(String name, UUID id);

	boolean isExistsByIata(String iata, UUID id);

	boolean isExistsByIcao(String icao, UUID id);

	boolean isExistsByCallsign(String callsign, UUID id);

}

