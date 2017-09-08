package org.football.repository;

import java.util.List;

import org.football.persistance.fixture.Fixture;

public interface FixtureRepository extends CustomJpaRepository<Fixture> {

	List<Fixture> findByCompetitionIdAndMatchdayOrderByDate(Long competitionId, Integer matchday);
	
}
