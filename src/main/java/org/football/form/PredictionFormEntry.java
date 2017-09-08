package org.football.form;

import org.football.persistance.fixture.Fixture;

public class PredictionFormEntry {

	private Long id;
	private Long userId, gameId, fixtureId;
	private Short goalsHomeTeam, goalsAwayTeam;
	private Fixture fixture;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Long getFixtureId() {
		return fixtureId;
	}

	public void setFixtureId(Long fixtureId) {
		this.fixtureId = fixtureId;
	}

	public Short getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(Short goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public Short getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(Short goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(Fixture fixture) {
		this.fixture = fixture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
