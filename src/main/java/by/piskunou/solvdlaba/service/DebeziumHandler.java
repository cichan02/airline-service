package by.piskunou.solvdlaba.service;

import java.util.Map;

public interface DebeziumHandler {

	void replicateData(Map<String, Object> payload);

}
