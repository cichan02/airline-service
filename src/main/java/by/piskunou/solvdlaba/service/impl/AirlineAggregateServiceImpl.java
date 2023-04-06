package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import by.piskunou.solvdlaba.domain.event.CreateAirlineEvent;
import by.piskunou.solvdlaba.domain.event.UpdateAirlineEvent;
import by.piskunou.solvdlaba.persistence.AirlineAggregateRepository;
import by.piskunou.solvdlaba.service.AirlineAggregateService;
import by.piskunou.solvdlaba.service.AirlineQueryService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirlineAggregateServiceImpl implements AirlineAggregateService {

	private final AirlineQueryService queryService;
	private final AirlineAggregateRepository repository;

	@Override
	@SneakyThrows
	public void create(UUID id, String data) {
		AirlineAggregate airlineAggregate = AirlineAggregate.airlineAggregateBuilder()
				.id(id)
				.build();
		airlineAggregate.apply( new CreateAirlineEvent( data ) );
		repository.save(airlineAggregate);
	}

	@Override
	@SneakyThrows
	public void update(UUID id, String data) {
		AirlineAggregate airlineAggregate = queryService.findById(id);
		airlineAggregate.apply( new UpdateAirlineEvent( data ));
		repository.save(airlineAggregate);
	}

	@Override
	public void remove(UUID id) {
		repository.deleteById(id);
	}

}
