package by.piskunou.solvdlaba.service.impl;

import by.piskunou.solvdlaba.service.AirlineAggregateService;
import by.piskunou.solvdlaba.service.DebeziumHandler;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DebeziumHandlerImpl implements DebeziumHandler {

	private final AirlineAggregateService aggregateService;

	@Override
	public void replicateData(@NotNull Map<String, Object> payload) {
		UUID id = UUID.fromString((String) payload.get("aggregate_id"));
		String type = (String) payload.get("type");
		String data = (String) payload.get("payload");
		switch ( type ) {
			case "airline-create" -> aggregateService.create(id, data);
			case "airline-update" -> aggregateService.update(id, data);
			case "airline-remove" -> aggregateService.remove(id);
		}
	}

}
