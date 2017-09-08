package org.football.form;

import java.util.HashSet;
import java.util.Set;

public class GameForm {

	private String competitionId;
	private Set<String> userIds = new HashSet<>();

	public String getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(final String competitionId) {
		this.competitionId = competitionId;
	}

	public Set<String> getUserIds() {
		return userIds;
	}

	public void setUserIds(final Set<String> userIds) {
		this.userIds = userIds;
	}
}
