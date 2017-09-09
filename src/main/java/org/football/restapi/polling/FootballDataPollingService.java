package org.football.restapi.polling;

public interface FootballDataPollingService {

	void scheduleFootballDataPolling();

	void schedulePredictionsEvaluation();

}
