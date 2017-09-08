package org.football.persistance.fixture;

public class Odds {
	private float homeWin, draw, awayWin;

	public float getHomeWin() {
		return homeWin;
	}

	public void setHomeWin(final float homeWin) {
		this.homeWin = homeWin;
	}

	public float getDraw() {
		return draw;
	}

	public void setDraw(final float draw) {
		this.draw = draw;
	}

	public float getAwayWin() {
		return awayWin;
	}

	public void setAwayWin(final float awayWin) {
		this.awayWin = awayWin;
	}

	@Override
	public String toString() {
		return "Odds [homeWin=" + homeWin + ", draw=" + draw + ", awayWin=" + awayWin + "]";
	}
}
