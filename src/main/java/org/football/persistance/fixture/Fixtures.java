package org.football.persistance.fixture;

import java.util.ArrayList;
import java.util.List;

import org.football.persistance.meta.Resource;

public class Fixtures extends Resource {
	private List<Fixture> fixtures = new ArrayList<>();
	private int count;

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "FixturesWrapper [fixtures=" + fixtures + ", count=" + count + "]";
	}

}
