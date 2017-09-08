package org.football.persistance.fixture;

public class FixtureWithH2H {
	private Fixture fixture;
	private Head2head head2head;

	public Fixture getFixture() {
		return fixture;
	}

	public void setFixture(final Fixture fixture) {
		this.fixture = fixture;
	}

	public Head2head getHead2head() {
		return head2head;
	}

	public void setHead2head(final Head2head head2head) {
		this.head2head = head2head;
	}

	@Override
	public String toString() {
		return "FixtureWrapper [fixture=" + fixture + ", head2head=" + head2head + "]";
	}
}
