package org.football.persistance.fixture;

import java.util.Date;
import java.util.List;

public class Head2head {
	private int count, homeTeamWins, awayTeamWins, draws;
	private Date timeFrameStart, timeFrameEnd;
	private Fixture lastHomeWinHomeTeam, lastWinHomeTeam, lastAwayWinAwayTeam, lastWinAwayTeam;
	private List<Fixture> fixtures;

	public int getCount() {
		return count;
	}

	public void setCount(final int count) {
		this.count = count;
	}

	public int getHomeTeamWins() {
		return homeTeamWins;
	}

	public void setHomeTeamWins(final int homeTeamWins) {
		this.homeTeamWins = homeTeamWins;
	}

	public int getAwayTeamWins() {
		return awayTeamWins;
	}

	public void setAwayTeamWins(final int awayTeamWins) {
		this.awayTeamWins = awayTeamWins;
	}

	public int getDraws() {
		return draws;
	}

	public void setDraws(final int draws) {
		this.draws = draws;
	}

	public Date getTimeFrameStart() {
		return timeFrameStart;
	}

	public void setTimeFrameStart(final Date timeFrameStart) {
		this.timeFrameStart = timeFrameStart;
	}

	public Date getTimeFrameEnd() {
		return timeFrameEnd;
	}

	public void setTimeFrameEnd(final Date timeFrameEnd) {
		this.timeFrameEnd = timeFrameEnd;
	}

	public Fixture getLastHomeWinHomeTeam() {
		return lastHomeWinHomeTeam;
	}

	public void setLastHomeWinHomeTeam(final Fixture lastHomeWinHomeTeam) {
		this.lastHomeWinHomeTeam = lastHomeWinHomeTeam;
	}

	public Fixture getLastWinHomeTeam() {
		return lastWinHomeTeam;
	}

	public void setLastWinHomeTeam(final Fixture lastWinHomeTeam) {
		this.lastWinHomeTeam = lastWinHomeTeam;
	}

	public Fixture getLastAwayWinAwayTeam() {
		return lastAwayWinAwayTeam;
	}

	public void setLastAwayWinAwayTeam(final Fixture lastAwayWinAwayTeam) {
		this.lastAwayWinAwayTeam = lastAwayWinAwayTeam;
	}

	public Fixture getLastWinAwayTeam() {
		return lastWinAwayTeam;
	}

	public void setLastWinAwayTeam(final Fixture lastWinAwayTeam) {
		this.lastWinAwayTeam = lastWinAwayTeam;
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(final List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	@Override
	public String toString() {
		return "Head2head [count=" + count + ", homeTeamWins=" + homeTeamWins + ", awayTeamWins=" + awayTeamWins
				+ ", draws=" + draws + ", timeFrameStart=" + timeFrameStart + ", timeFrameEnd=" + timeFrameEnd
				+ ", lastHomeWinHomeTeam=" + lastHomeWinHomeTeam + ", lastWinHomeTeam=" + lastWinHomeTeam
				+ ", lastAwayWinAwayTeam=" + lastAwayWinAwayTeam + ", lastWinAwayTeam=" + lastWinAwayTeam
				+ ", fixtures=" + fixtures + "]";
	}

}
