<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>
<%@ taglib prefix="football" tagdir="/WEB-INF/tags/football" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-12">
					<strong>
						Game id: 
						${game.id}
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<strong>
						Competition: 
						${competition.caption} - Round ${game.competitionMatchday}
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<strong>
						Started on: 
						<fmt:formatDate type="date" dateStyle="medium" timeStyle="short" value="${game.startedTime}" />
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<strong>Status: ${game.gameStatus}</strong>
				</div>
			</div>
		</div>
		<div class="col-md-6">
			<table class="table table-striped">
				<tbody>
					<c:forEach items="${userPoints}" var="userPoint">
					<c:set var="user" value="${userPoint.key }" />
					<c:set var="points" value="${userPoint.value }" />
					<tr>
						<td>${user.name} (${user.email})</td>
						<td>${points} points</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<hr>
	<div class="row">
		<div class="col-md-12">
			<c:set var="predictions" value="${0}" />
			<c:forEach items="${userPredictions}" var="userPredction">
				<c:set var="predictions" value="${userPredction.value}" />
			</c:forEach>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Fixture</th>
						<th>Result</th>
						<c:forEach items="${userPredictions}" var="userPredction">
							<c:set var="user" value="${userPredction.key }" />
							<th>${user.name}'s prediction</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${predictions}" var="prediction" varStatus="counter">
						<c:set var="fixture" value="${prediction.fixture }" />
						<tr>
							<td>${fixture.homeTeamName } vs. ${fixture.awayTeamName }</td>
							<td>${fixture.goalsHomeTeam }:${fixture.goalsAwayTeam }</td>
							<c:forEach items="${userPredictions}" var="userPredction">
								<c:set var="user" value="${userPredction.key }" />
								<c:set var="currentPrediction" value="${userPredction.value[counter.index] }" />
								<td>${currentPrediction.goalsHomeTeam }:${currentPrediction.goalsAwayTeam } - ${currentPrediction.points} points</td>
							</c:forEach>
						</tr>
					</c:forEach>
					
				</tbody>
			</table>
		</div>
	</div>
</template:page>