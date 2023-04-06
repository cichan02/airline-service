package by.piskunou.solvdlaba.persistence;

import io.debezium.config.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class DebeziumConfig {

	@Value("${MAIN_HOST}")
	private String customerDbHost;

	@Value("${POSTGRES_POST}")
	private String customerDbPort;

	@Value("${spring.datasource.username}")
	private String customerDbUsername;

	@Value("${spring.datasource.password}")
	private String customerDbPassword;

	@Value("${POSTGRES_DB}")
	private String customerDbName;

	@Value("${POSTGRES_SCHEMA}")
	private String customerDbSchema;

	@Bean
	public Configuration customerConnector() {
		return Configuration.create()
				.with("name", "postgres-connector")
				.with("connector.class", "io.debezium.connector.postgresql.PostgresConnector")
				.with("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore")
				.with("offset.storage.file.filename", "/tmp/offsets/events-offset.dat")
				.with("offset.flush.interval.ms", "60000")
				.with("database.hostname", customerDbHost)
				.with("database.port", customerDbPort)
				.with("database.user", customerDbUsername)
				.with("database.password", customerDbPassword)
				.with("database.dbname", customerDbName)
				.with("table.include.list", customerDbSchema + ".events")
				.with("include.schema.changes", "false")
				.with("database.server.name", "postgres-db-server")
				.with("topic.prefix", "test")
				.with("plugin.name", "pgoutput")
				.build();
	}

}
