package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.domain.AirlineAggregate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AirlineAggregateRepository extends JpaRepository<AirlineAggregate, UUID> {

	@Query("""
		select a from AirlineAggregate a
		where a.name like :inquiry or
			  a.iata like :inquiry or
			  a.icao like :inquiry or
			  a.callsign like :inquiry""")
	List<AirlineAggregate> search(@Param("inquiry") String inquiry);

	boolean existsByNameAndIdNot(String name, UUID id);

	boolean existsByIataAndIdNot(String iata, UUID id);

	boolean existsByIcaoAndIdNot(String icao, UUID id);

	boolean existsByCallsignAndIdNot(String callsign, UUID id);

}
