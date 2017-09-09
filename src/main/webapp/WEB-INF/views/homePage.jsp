<%@ page trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="football" tagdir="/WEB-INF/tags/football" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
			<a href="<c:url value="/game/start" />" class="btn btn-primary" role="button">Start a game</a>
			<a href="<c:url value="/games" />" class="btn btn-primary" role="button">Display started games</a>
		</div>
		<div class="col-md-6">
			Lorem Ipsum is simply dummy text of the printing and
			typesetting industry. Lorem Ipsum has been the industry's standard
			dummy text ever since the 1500s, when an unknown printer took a
			galley.
		</div>
	</div>
	<div class="row">&nbsp;</div>
	<c:forEach items="${competitions}" var="competition">
		<div class="row">
			<div class="col-md-12">
				<h4>${competition.caption}</h4><hr>
			</div>
			<div class="col-md-6">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>Date</th>
							<th>Round</th>
							<th>Fixture</th>
							<th>Result</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${competition.fixtures}" var="fixture">
						<tr>
							<td><fmt:formatDate pattern="dd.MM HH:mm" value="${fixture.date }" /></td>
							<td>${fixture.matchday}</td>
							<td>
								${fixture.homeTeamName } vs. ${fixture.awayTeamName }&nbsp;
							</td>
							<td>
								<c:if test="${not empty fixture.goalsHomeTeam and not empty fixture.goalsAwayTeam}">
									${fixture.status }&nbsp;${fixture.goalsHomeTeam }:${fixture.goalsAwayTeam }
								</c:if>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<football:leagueTable competition="${competition}" />
			</div>
		</div>
		<div class="row">&nbsp;</div>
	</c:forEach>
</template:page>