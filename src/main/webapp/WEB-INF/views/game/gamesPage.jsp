<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<h3><spring:message code="text.gamepage.yourGames" /></h3><hr>
	<c:if test="${empty games}">
		<div class="row">
			<div class="col-md-12">
				<spring:message code="text.gamepage.noStartedGames" /><br><br>
				<a href="<c:url value="/game/start" />" class="btn btn-primary" role="button"><spring:message code="text.header.startGame" /></a>
			</div>
		</div>
	</c:if>
	<div class="row">
		<c:forEach items="${games}" var="game">
			<div class="col-md-4 col-sm-6 well" style="height: 210px; position:relative;">
				<div class="row">
					<div class="col-md-12">
						<spring:message code="text.gamepage.gameId" arguments="${game.id}"/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<spring:message code="text.gamepage.competition" arguments="${game.competitionCaption},${game.competitionMatchday}"/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${game.startedTime}" var="startedTime" />
						<spring:message code="text.gamepage.startedOn" arguments="${startedTime}"/>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<spring:message code="text.gamepage.players" />
						<c:forEach items="${game.users}" var="user" varStatus="counter">
							${user.name} (${user.email})
							<c:if test="${counter.count != game.users.size()}">
							 vs. 
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<spring:message code="text.gamepage.game.status.${game.gameStatus}" var="gameStatus"/>
						<spring:message code="text.gamepage.status" arguments="${gameStatus}"/>
					</div>
				</div>
				<div class="row" style="position: absolute; bottom:20px;">
					<div class="col-md-12">
						<c:choose>
							<c:when test="${game.gameStatus == 'FINISHED'}">
								<a href="<c:url value="/game/${game.id}/summary" />" class="btn btn-primary" role="button"><spring:message code="text.gamepage.displaySummary" /></a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value="/game/${game.id}" />" class="btn btn-primary" role="button"><spring:message code="text.gamepage.predict" /></a>
							</c:otherwise>						
						</c:choose>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	<c:if test="${not empty games }">
		<h3><spring:message code="text.gamepage.stats" text="Stats"/></h3><hr>
		<div class="row">
			<div class="col-md-6">
				<table class="table table-striped">
				<thead>
					<tr><th colspan="2"><spring:message code="text.gamepage.games" /></th></tr>
					<tr>
						<th><spring:message code="text.gamepage.games.status" /></th>
						<th><spring:message code="text.gamepage.games.count" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${gamesCountForStatus}" var="gameCount">
						<tr>
							<td><spring:message code="text.gamepage.game.status.${gameCount.key}" /></td>
							<td>${gameCount.value}</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<div class="col-md-6">
				<table class="table table-striped">
				<thead>
					<tr><th colspan="3"><spring:message code="text.gamepage.predictions" /></th></tr>
					<tr>
						<th><spring:message code="text.gamepage.predictions.points" /></th>
						<th><spring:message code="text.gamepage.predictions.count" /></th>
						<th><spring:message code="text.gamepage.predictions.percent"  /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${predictionsCountForPoints}" var="predictionCount">
						<tr>
							<td>${predictionCount.key}</td>
							<td>${predictionCount.value }</td>
							<td><fmt:formatNumber type = "percent" value = "${(predictionCount.value / predictionsCount)}" /></td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
		</div>
	</c:if>
</template:page>