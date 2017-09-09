<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ attribute name="competition" required="true" type="org.football.persistance.competition.Competition"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th><spring:message code="text.leaguetable.standing.position" /></th>
			<th><spring:message code="text.leaguetable.standing.team" /></th>
			<th><spring:message code="text.leaguetable.standing.played" /></th>
			<th><spring:message code="text.leaguetable.standing.wins" /></th>
			<th><spring:message code="text.leaguetable.standing.draws" /></th>
			<th><spring:message code="text.leaguetable.standing.losses" /></th>
			<th><spring:message code="text.leaguetable.standing.points" /></th>
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