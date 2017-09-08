<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<c:forEach items="${fixtures }" var="fixture">
		<fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${fixture.date }" />
		&nbsp;(round: ${fixture.matchday})
		&emsp;
		<c:url var="fixtureUrl" value="/fixture/${fixture.id}" />
		<a href="${fixtureUrl}">${fixture.homeTeamName } vs. ${fixture.awayTeamName }</a><br/>
		<c:if test="${not empty fixture.odds }">
			<br/>
			Home win: ${fixture.odds.homeWin }<br/>
			Draw: ${fixture.odds.draw }<br/>
			Away win: ${fixture.odds.awayWin }<br/>
		</c:if>
		<hr/>
	</c:forEach>
	
</template:page>