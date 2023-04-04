package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import by.piskunou.solvdlaba.domain.exception.ResourceNotExistsException;
import by.piskunou.solvdlaba.persistence.AirlineAggregateRepository;
import by.piskunou.solvdlaba.service.AirlineQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirlineQueryServiceImpl implements AirlineQueryService {

	private final AirlineAggregateRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<AirlineAggregate> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public AirlineAggregate findById(UUID id) {
		return repository.findById(id)
						 .orElseThrow(() -> new ResourceNotExistsException("There's no airline with such id"));
	}

	@Override
	@Transactional(readOnly = true)
	public List<AirlineAggregate> search(String inquiry) {
		return repository.search('%' + inquiry + '%');
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistsById(UUID id) {
		return repository.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistsByName(String name, UUID id) {
		return repository.existsByNameAndIdNot(name, id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistsByIata(String iata, UUID id) {
		return repository.existsByIataAndIdNot(iata, id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistsByIcao(String icao, UUID id) {
		return repository.existsByIcaoAndIdNot(icao, id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean isExistsByCallsign(String callsign, UUID id) {
		return repository.existsByCallsignAndIdNot(callsign, id);
	}

}
