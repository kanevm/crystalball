package org.football.persistance.fixture;

public class Result {

	private Short goalsHomeTeam, goalsAwayTeam;

	public Short getGoalsHomeTeam() {
		return goalsHomeTeam;
	}

	public void setGoalsHomeTeam(final Short goalsHomeTeam) {
		this.goalsHomeTeam = goalsHomeTeam;
	}

	public Short getGoalsAwayTeam() {
		return goalsAwayTeam;
	}

	public void setGoalsAwayTeam(final Short goalsAwayTeam) {
		this.goalsAwayTeam = goalsAwayTeam;
	}

	@Override
	public String toString() {
		return "Result [goalsHomeTeam=" + goalsHomeTeam + ", goalsAwayTeam=" + goalsAwayTeam + "]";
	}
}
