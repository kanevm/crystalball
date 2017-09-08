<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="competition" required="true" type="org.football.persistance.competition.Competition"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Pos</th>
			<th>Team</th>
			<th>M</th>
			<th>W</th>
			<th>D</th>
			<th>L</th>
			<th>Pts</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${competition.standings}" var="standing">
		<tr>
			<td>${standing.position }</td>
			<td><img src="${empty standing.crestURI ? '/resources/custom/images/crestNotAvailable.jpg' : standing.crestURI}" width="28" height="28"/>&emsp;${standing.teamName }</td>
			<td>${standing.playedGames }</td>
			<td>${standing.wins }</td>
			<td>${standing.draws }</td>
			<td>${standing.losses }</td>
			<td>${standing.points }</td>
		</tr>
		</c:forEach>
	</tbody>
</table>