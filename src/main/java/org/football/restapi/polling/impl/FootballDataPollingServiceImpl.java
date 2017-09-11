package org.football.restapi.polling.impl;

import java.util.List;

import org.football.persistance.competition.Competition;
import org.football.persistance.fixture.Fixture;
import org.football.persistance.fixture.Status;
import org.football.persistance.game.Game;
import org.football.persistance.game.GameStatus;
import org.football.persistance.leaguetable.Standing;
import org.football.persistance.prediction.Prediction;
import org.football.repository.CompetitionRepository;
import org.football.repository.GameRepository;
import org.football.repository.PredictionRepository;
import org.football.restapi.polling.FootballDataPollingService;
import org.football.restapi.service.FootballOperations;
import org.football.service.GameService;
import org.football.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FootballDataPollingServiceImpl implements FootballDataPollingService {
	
	private static final int ONE_SECONDS = 1_000;
	private static final int THIRTY_SECONDS = 30 * ONE_SECONDS;
	private static final int ONE_MINUTE = 2 * THIRTY_SECONDS;
	
	@Autowired
	private FootballOperations footballOperations;
	@Autowired
	private List<String> supportedCompetitionIds;
	@Autowired
	private CompetitionRepository competitionRepository;
	@Autowired
	private PredictionRepository predictionRepository;
	@Autowired
	private PredictionService predictionService;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private GameService gameService;

	@Override
	@Scheduled(fixedDelay = ONE_MINUTE)
	public void scheduleFootballDataPolling() {
		supportedCompetitionIds.forEach(this::pollCompetitionDetails);
	}
	
	@Override
	@Scheduled(fixedDelay = ONE_MINUTE)
	public void schedulePredictionsEvaluation() {
		final List<Prediction> unevaluatedPredictions = predictionRepository.findByPointsAndFixtureStatus(null, Status.FINISHED);
		unevaluatedPredictions.forEach(predictionService::evaluatePrediction);
		final List<Prediction> predictionsWithPostponedFixtures = predictionRepository.findByPointsAndFixtureStatus(null, Status.POSTPONED);
		predictionsWithPostponedFixtures.forEach(prediction -> predictionService.evaluatePrediction(prediction, (short) 0));
		
		final List<Game> inProgressGames = gameRepository.findByGameStatus(GameStatus.IN_PROGRESS);
		inProgressGames.forEach(gameService::tryEndGame);
	}
	
	private void pollCompetitionDetails(final String competitionId) {
		final Long competitionIdAsLong = Long.valueOf(competitionId);
		final Competition competition = footballOperations.getCompetition(competitionIdAsLong);
		
		final List<Fixture> fixtures = footballOperations.getFixtures(competitionIdAsLong, competition.getCurrentMatchday());
		fixtures.forEach(fixture -> fixture.setCompetition(competition));
		competition.setFixtures(fixtures);
		
		final List<Standing> standings = footballOperations.getLeagueTable(competitionIdAsLong, competition.getCurrentMatchday()).getStanding();
		standings.forEach(standing -> standing.setCompetition(competition));
		competition.setStandings(standings);
		
		competitionRepository.save(competition);
	}
}
