package org.football.restapi.polling;

public interface FootballDataPollingService {

	void scheduleFixedDelayDataPolling();

	void schedulePredictionsEvaluation();

}
