package by.piskunou.solvdlaba.persistence;

import by.piskunou.solvdlaba.service.DebeziumHandler;
import io.debezium.config.Configuration;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static io.debezium.data.Envelope.FieldName.OPERATION;
import static io.debezium.data.Envelope.FieldName.BEFORE;
import static io.debezium.data.Envelope.FieldName.AFTER;
import static io.debezium.data.Envelope.Operation;
import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class DebeziumListener {

	private final Executor executor = Executors.newSingleThreadExecutor();
	private final DebeziumEngine<RecordChangeEvent<SourceRecord>> debezium;
	private final DebeziumHandler handler;

	public DebeziumListener(@NotNull Configuration customerConnectorConfiguration, DebeziumHandler handler) {
		this.debezium = DebeziumEngine.create( ChangeEventFormat.of(Connect.class) )
				.using( customerConnectorConfiguration.asProperties() )
				.notifying(this::handleChangeEvent)
				.build();
		this.handler = handler;
	}

	private void handleChangeEvent(@NotNull RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
		SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();

		log.info("Key = '" + sourceRecord.key() + "' value = '" + sourceRecord.value() + "'");

		Struct sourceRecordChangeValue= (Struct) sourceRecord.value();

		if (sourceRecordChangeValue == null) { return; }

		Operation operation = Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));

		if( operation == Operation.READ) { return; }

		String record = operation == Operation.DELETE ? BEFORE : AFTER;

		Struct struct = (Struct) sourceRecordChangeValue.get(record);
		Map<String, Object> payload = struct.schema()
				.fields()
				.stream()
				.map(Field::name)
				.filter(fieldName -> struct.get(fieldName) != null)
				.map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
				.collect(toMap(Pair::getKey, Pair::getValue));

		this.handler.replicateData(payload);
		log.info("Updated Data: {} with Operation: {}", payload, operation.name());
	}

	@PostConstruct
	private void start() {
		this.executor.execute(debezium);
	}

	@PreDestroy
	private void stop() throws IOException {
		if (this.debezium != null) {
			this.debezium.close();
		}
	}

}
