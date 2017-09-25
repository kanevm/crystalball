package org.football.persistance.competition;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.football.persistance.fixture.Fixture;
import org.football.persistance.leaguetable.Standing;
import org.football.persistance.meta.Resource;

@Entity
@Table(name = "competitions")
public class Competition extends Resource {

	@Id
	private Long id;
	private String caption;
	private String league;
	private String year;
	private Integer currentMatchday;
	private Integer numberOfMatchdays;
	private Integer numberOfTeams;
	private Integer numberOfGames;
	private Date lastUpdated;
	@OrderBy("date")
	@OneToMany(mappedBy = "competition", cascade = { CascadeType.ALL })
	private List<Fixture> fixtures = new ArrayList<>();
	@OrderBy("position")
	@OneToMany(mappedBy = "competition", cascade = { CascadeType.ALL })
	private List<Standing> standings = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getCurrentMatchday() {
		return currentMatchday;
	}

	public void setCurrentMatchday(Integer currentMatchday) {
		this.currentMatchday = currentMatchday;
	}

	public Integer getNumberOfMatchdays() {
		return numberOfMatchdays;
	}

	public void setNumberOfMatchdays(Integer numberOfMatchdays) {
		this.numberOfMatchdays = numberOfMatchdays;
	}

	public Integer getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(Integer numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public Integer getNumberOfGames() {
		return numberOfGames;
	}

	public void setNumberOfGames(Integer numberOfGames) {
		this.numberOfGames = numberOfGames;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void setFixtures(List<Fixture> fixtures) {
		this.fixtures = fixtures;
	}

	public List<Standing> getStandings() {
		return standings;
	}

	public void setStandings(List<Standing> standings) {
		this.standings = standings;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caption == null) ? 0 : caption.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((league == null) ? 0 : league.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Competition other = (Competition) obj;
		if (caption == null) {
			if (other.caption != null)
				return false;
		} else if (!caption.equals(other.caption))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (league == null) {
			if (other.league != null)
				return false;
		} else if (!league.equals(other.league))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", caption=" + caption + ", league=" + league + ", year=" + year
				+ ", currentMatchday=" + currentMatchday + ", numberOfMatchdays=" + numberOfMatchdays
				+ ", numberOfTeams=" + numberOfTeams + ", numberOfGames=" + numberOfGames + "]";
	}
}
