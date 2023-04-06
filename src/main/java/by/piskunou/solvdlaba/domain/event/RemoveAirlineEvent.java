package by.piskunou.solvdlaba.domain.event;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
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
@DiscriminatorValue(RemoveAirlineEvent.EVENT_TYPE)
public class RemoveAirlineEvent extends BaseEvent {

	public static final String EVENT_TYPE = "airline-remove";

	@Builder(builderMethodName = "airlineRemoveEventBuilder")
	public RemoveAirlineEvent(Long id, UUID aggregateId) {
		super(id, aggregateId, LocalDateTime.now(), null);
	}

}
