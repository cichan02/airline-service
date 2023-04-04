package by.piskunou.solvdlaba.domain.event;

import by.piskunou.solvdlaba.domain.Aggregate;
import by.piskunou.solvdlaba.domain.AirlineAggregate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue(UpdateAirlineEvent.EVENT_TYPE)
public class UpdateAirlineEvent extends BaseEvent {

	public static final String EVENT_TYPE = "airline-update";

	@Transient
	private String name;

	@Transient
	private String iata;

	@Transient
	private String icao;

	@Transient
	private String callsign;

	public UpdateAirlineEvent (String payload) throws JsonProcessingException {
		JsonNode node = new ObjectMapper().readTree( payload );
		this.name = node.get("name").asText();
		this.iata = node.get("iata").asText();
		this.icao = node.get("icao").asText();
		this.callsign = node.get("callsign").asText();
	}

	@Override
	public void copyTo(Aggregate aggregate) {
		AirlineAggregate country = (AirlineAggregate) aggregate;
		country.setName(name);
		country.setIata(iata);
		country.setIcao(icao);
		country.setCallsign(callsign);
	}

	@Builder(builderMethodName = "updateAirlineEventBuilder")
	public UpdateAirlineEvent(Long id, UUID aggregateId, String payload) {
		super(id, aggregateId, LocalDateTime.now(), payload);
	}

}
