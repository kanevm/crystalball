<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<spring:message code="text.gamepage.gameId" arguments="${game.id}"/>
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<strong>
						<spring:message code="text.gamepage.competition" arguments="${game.competitionCaption},${game.competitionMatchday}"/>
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<strong>
						<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${game.startedTime}" var="startedTime" />
						<spring:message code="text.gamepage.startedOn" arguments="${startedTime}"/>
					</strong>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<strong>
						<spring:message code="text.gamepage.game.status.${game.gameStatus}" var="gameStatus"/>
						<spring:message code="text.gamepage.status" arguments="${gameStatus}"/>
					</strong>
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
						<td><spring:message code="text.gamepage.points" arguments="${points}"/></td>
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
						<th><spring:message code="text.gamepage.fixture" /></th>
						<th><spring:message code="text.gamepage.result" /></th>
						<c:forEach items="${userPredictions}" var="userPredction">
							<c:set var="user" value="${userPredction.key}" />
							<th><spring:message code="text.gamepage.predictionOf" arguments="${user.name}"/></th>
						</c:forEach>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${predictions}" var="prediction" varStatus="counter">
						<c:set var="fixture" value="${prediction.fixture }" />
						<tr>
							<td>${fixture.homeTeamName } vs. ${fixture.awayTeamName }</td>
							<td>
								<c:if test="${not empty fixture.goalsHomeTeam and not empty fixture.goalsAwayTeam}">
									${fixture.goalsHomeTeam }:${fixture.goalsAwayTeam }
								</c:if>
							</td>
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