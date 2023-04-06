package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import by.piskunou.solvdlaba.domain.event.BaseEvent;
import by.piskunou.solvdlaba.domain.event.CreateAirlineEvent;
import by.piskunou.solvdlaba.domain.event.RemoveAirlineEvent;
import by.piskunou.solvdlaba.domain.event.UpdateAirlineEvent;
import by.piskunou.solvdlaba.domain.exception.ResourceAlreadyExistsException;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.EventRepository;
import by.piskunou.solvdlaba.service.AirlineCommandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirlineCommandServiceImpl implements AirlineCommandService {

	private final ObjectMapper objectMapper;
	private final EventRepository repository;
	private final AirlineQueryServiceImpl queryService;

	@Override
	@SneakyThrows
	@Transactional
	public void create(AirlineAggregate airline)  {
		checkIsEntityValid(airline);
		String payload = objectMapper.writeValueAsString(airline);
		BaseEvent event = CreateAirlineEvent.createAirlineEventBuilder()
				.aggregateId( UUID.randomUUID() )
				.payload( payload )
				.build();
		repository.save(event);
	}

	@Override
	@SneakyThrows
	@Transactional
	public void updateById(UUID id, AirlineAggregate airline) {
		if( !queryService.isExistsById(id) ) {
			throw new ResourceNotExistsException("There's no airline with such id");
		}
		String payload = objectMapper.writeValueAsString(airline);
		BaseEvent event = UpdateAirlineEvent.updateAirlineEventBuilder()
				.aggregateId( id )
				.payload( payload )
				.build();
		repository.save(event);
	}

	@Override
	@Transactional
	public void removeById(UUID id) {
		BaseEvent event = RemoveAirlineEvent.airlineRemoveEventBuilder()
				.aggregateId( id )
				.build();
		repository.save(event);
	}

	private void checkIsEntityValid(@NotNull AirlineAggregate airline) {
		UUID id = airline.getId();
		if( queryService.isExistsByName(airline.getName(), id) ) {
			throw new ResourceAlreadyExistsException("Airline with such name has already exists");
		}
		if( queryService.isExistsByIata(airline.getIata(), id) ) {
			throw new ResourceAlreadyExistsException("Airline with such IATA has already exists");
		}
		if( queryService.isExistsByIcao(airline.getIcao(), id) ) {
			throw new ResourceAlreadyExistsException("Airline with such ICAO has already exists");
		}
		if( queryService.isExistsByCallsign(airline.getCallsign(), id) ) {
			throw new ResourceAlreadyExistsException("Airline with such callsign has already exists");
		}
	}

}
