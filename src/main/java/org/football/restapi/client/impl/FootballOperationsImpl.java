package org.football.restapi.client.impl;

import static org.football.restapi.util.UrlUtils.safeUrl;
import static org.football.restapi.util.UrlUtils.uriFrom;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.football.persistance.competition.Competition;
import org.football.persistance.competition.Competitions;
import org.football.persistance.fixture.Fixture;
import org.football.persistance.fixture.FixtureWithH2H;
import org.football.persistance.fixture.Fixtures;
import org.football.persistance.leaguetable.LeagueTable;
import org.football.restapi.client.FootballOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FootballOperationsImpl implements FootballOperations {

	@Autowired
	private RestTemplate restTemplate;

	private URI apiBaseUri;

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public FootballOperationsImpl(final String apiUrl) {
		try {
			this.apiBaseUri = new URI(safeUrl(apiUrl));
		} catch (final URISyntaxException e) {
			throw new RuntimeException("Could not initialize " + FootballOperationsImpl.class.getName(), e);
		}
	}

	@Override
	public FixtureWithH2H getFixture(final long fixtureId) {
		final URI uriFrom = uriFrom(apiBaseUri, "/fixtures/{id}", Collections.singletonMap("id", fixtureId));
		final ResponseEntity<FixtureWithH2H> fixtureResponseEntity = getRestTemplate().getForEntity(uriFrom, FixtureWithH2H.class);

		return fixtureResponseEntity.getBody();
	}

	@Override
	public List<Fixture> getFixtures(final long competitionId, final int matchday) {
		final URI uriFrom = uriFrom(apiBaseUri, "/competitions/{id}/fixtures", Collections.singletonMap("id", competitionId));
		final ResponseEntity<Fixtures> fixturesResponseEntity = getRestTemplate().getForEntity(uriFrom.toString() + "?matchday=" + matchday, Fixtures.class);

		return fixturesResponseEntity.getBody().getFixtures();
	}

	@Override
	public Competition getCompetition(final long competitionId) {
		final URI uriFrom = uriFrom(apiBaseUri, "/competitions/{id}", Collections.singletonMap("id", competitionId));
		final ResponseEntity<Competition> competitionResponseEntity = getRestTemplate().getForEntity(uriFrom, Competition.class);

		return competitionResponseEntity.getBody();
	}

	@Override
	public Competitions getCompetitions() {
		final URI uriFrom = uriFrom(apiBaseUri, "/competitions", Collections.emptyMap());
		final ResponseEntity<Competitions> competitionResponseEntity = getRestTemplate().getForEntity(uriFrom, Competitions.class);

		return competitionResponseEntity.getBody();
	}

	@Override
	public LeagueTable getLeagueTable(final long competitionId) {
		final URI uriFrom = uriFrom(apiBaseUri, "/competitions/{id}/leagueTable", Collections.singletonMap("id", competitionId));
		final ResponseEntity<LeagueTable> leagueTableResponseEntity = getRestTemplate().getForEntity(uriFrom, LeagueTable.class);

		return leagueTableResponseEntity.getBody();
	}

	@Override
	public LeagueTable getLeagueTable(final long competitionId, final int matchday) {
		final URI uriFrom = uriFrom(apiBaseUri, "/competitions/{id}/leagueTable", Collections.singletonMap("id", competitionId));
		final ResponseEntity<LeagueTable> leagueTableResponseEntity = getRestTemplate().getForEntity(uriFrom.toString() + "?matchday=" + matchday, LeagueTable.class);

		return leagueTableResponseEntity.getBody();
	}
}
