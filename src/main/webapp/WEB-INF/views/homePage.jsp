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
			<a href="<c:url value="/game/start" />" class="btn btn-primary" role="button"><spring:message code="text.header.startGame" /></a>
			<a href="<c:url value="/games" />" class="btn btn-primary" role="button"><spring:message code="text.header.games" /></a>
			<a href="<c:url value="/games#stats" />" class="btn btn-primary" role="button"><spring:message code="text.header.gamesStats" /></a>
		</div>
		<div class="col-md-6">
			<spring:message code="text.homepage.info" />
		</div>
	</div>
	<div class="row">&nbsp;</div>
	<c:forEach items="${competitions}" var="competitionCurrentFixtures">
		<c:set var="competition" value="${competitionCurrentFixtures.key}" />
		<c:set var="fixtures" value="${competitionCurrentFixtures.value}" />
		<div class="row">
			<div class="col-md-12">
				<h4>${competition.caption}</h4><hr>
			</div>
			<div class="col-md-6">
				<table class="table table-striped">
					<thead>
						<tr>
							<th><spring:message code="text.homepage.fixture.date" /></th>
							<th><spring:message code="text.homepage.fixture.round" /></th>
							<th><spring:message code="text.homepage.fixture.caption" /></th>
							<th><spring:message code="text.homepage.fixture.result" /></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${fixtures}" var="fixture">
						<tr>
							<td><fmt:formatDate pattern="dd.MM HH:mm" value="${fixture.date}" /></td>
							<td>${fixture.matchday}</td>
							<td>
								<c:set var="maxNumberChars" value="15" />
								<c:set var="homeTeamName" value="${fixture.homeTeamName}" />
								<c:set var="awayTeamName" value="${fixture.awayTeamName}" />
								<c:if test="${fn:length(fixture.homeTeamName) gt maxNumberChars}">
									<c:set var="homeTeamName" value="${fn:trim(fn:substring(fixture.homeTeamName, 0, maxNumberChars))}..." />
								</c:if>
								<c:if test="${fn:length(fixture.awayTeamName) gt maxNumberChars}">
									<c:set var="awayTeamName" value="${fn:trim(fn:substring(fixture.awayTeamName, 0, maxNumberChars))}..." />
								</c:if>
								${homeTeamName} vs. ${awayTeamName }
							</td>
							<td>
								<spring:message code="text.gamepage.fixture.status.${fixture.status}" />
								<c:if test="${not empty fixture.goalsHomeTeam and not empty fixture.goalsAwayTeam}">
									&nbsp;${fixture.goalsHomeTeam }:${fixture.goalsAwayTeam }
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