package org.football.restapi.polling.impl;

import java.util.List;

import org.football.persistance.competition.Competition;
import org.football.persistance.fixture.Fixture;
import org.football.persistance.leaguetable.Standing;
import org.football.repository.CompetitionRepository;
import org.football.restapi.polling.FootballDataPollingService;
import org.football.restapi.service.FootballOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class FootballDataPollingServiceImpl implements FootballDataPollingService {
	
	private static final int THIRTY_SECONDS = 30_000;
	
	@Autowired
	private FootballOperations footballOperations;
	@Autowired
	private List<String> supportedCompetitionIds;
	@Autowired
	private CompetitionRepository competitionRepository;

	@Override
	@Scheduled(fixedDelay = THIRTY_SECONDS)
	public void scheduleFixedDelayDataPolling() {
		supportedCompetitionIds.forEach(this::pollCompetitionDetails);
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
