package org.football.restapi.service;

import java.util.List;

import org.football.persistance.competition.Competition;
import org.football.persistance.competition.Competitions;
import org.football.persistance.fixture.Fixture;
import org.football.persistance.fixture.FixtureWithH2H;
import org.football.persistance.leaguetable.LeagueTable;

public interface FootballOperations {

	List<Fixture> getFixtures(long competitionId, int matchday);

	FixtureWithH2H getFixture(long fixtureId);

	Competition getCompetition(long competitionId);

	Competitions getCompetitions();

	LeagueTable getLeagueTable(long competitionId);

	LeagueTable getLeagueTable(long competitionId, int matchday);

}
