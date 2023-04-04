package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import by.piskunou.solvdlaba.domain.event.CreateAirlineEvent;
import by.piskunou.solvdlaba.domain.event.UpdateAirlineEvent;
import by.piskunou.solvdlaba.persistence.AirlineAggregateRepository;
import by.piskunou.solvdlaba.service.DebeziumHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebeziumHandlerImpl implements DebeziumHandler {

	private final AirlineQueryServiceImpl queryService;
	private final AirlineAggregateRepository repository;

	@Override
	public void replicateData(@NotNull Map<String, Object> payload) {
		UUID id = UUID.fromString((String) payload.get("aggregate_id"));
		String type = (String) payload.get("type");
		String data = (String) payload.get("payload");
		switch ( type ) {
			case "airline-create" -> createAirlineAggregate(id, data);
			case "airline-update" -> updateAirlineAggregate(id, data);
			case "airline-remove" -> removeAirlineAggregate(id);
		}
	}

	@SneakyThrows
	private void createAirlineAggregate(UUID id, String data) {
		AirlineAggregate airlineAggregate = AirlineAggregate.airlineAggregateBuilder()
						.id(id)
						.build();
		airlineAggregate.apply( new CreateAirlineEvent( data ) );
		repository.save(airlineAggregate);
	}

	@SneakyThrows
	private void updateAirlineAggregate(UUID id, String data) {
		AirlineAggregate airlineAggregate = queryService.findById(id);
		airlineAggregate.apply( new UpdateAirlineEvent( data ));
		repository.save(airlineAggregate);
	}

	private void removeAirlineAggregate(UUID id) {
		repository.deleteById(id);
	}

}
