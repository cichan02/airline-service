package by.piskunou.solvdlaba.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "airline_aggregates")
public class AirlineAggregate extends BaseAggregate {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String name;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String iata;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String icao;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String callsign;

	@Builder(builderMethodName = "airlineAggregateBuilder")
	public AirlineAggregate(UUID id, String name, String iata, String icao, String callsign) {
		super(id, LocalDateTime.now());
		this.name = name;
		this.iata = iata;
		this.icao = icao;
		this.callsign = callsign;
	}

}
