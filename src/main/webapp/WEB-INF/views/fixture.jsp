<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<c:set var="fixture" value="${fixtureWithH2H.fixture }"/>
	<fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${fixture.date }" />
	&nbsp;(round: ${fixture.matchday})
	&emsp;
	<c:url var="fixtureUrl" value="/fixture/${fixture.id}" />
	<a href="${fixtureUrl}">${fixture.homeTeamName } vs. ${fixture.awayTeamName }</a><br/>
	Status: ${fixture.status }<br/>
	<hr/>
	
	<c:set var="h2h" value="${fixtureWithH2H.head2head }"/>
	Previous encounters: ${h2h.count }<br/>
	Home team wins: ${h2h.homeTeamWins }<br/>
	Away team wins: ${h2h.awayTeamWins }<br/>
	Draws: ${h2h.draws }<br/><br/>
	
	<c:forEach items="${h2h.fixtures }" var="fixture">
		<fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${fixture.date }" />
		&nbsp;(round: ${fixture.matchday})
		&emsp;
		${fixture.homeTeamName } vs. ${fixture.awayTeamName }<br/>
		<c:if test="${not empty fixture.result }">
			<c:set var="result" value="${fixture.result }"/>
			Result: ${result.goalsHomeTeam }:${result.goalsAwayTeam }<br/>
		</c:if>
		<hr/>
	</c:forEach>
	
</template:page>