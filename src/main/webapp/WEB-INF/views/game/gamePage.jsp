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
				<spring:message code="text.gamepage.players" />
				<c:forEach items="${game.users}" var="user" varStatus="counter">
					${user.name} (${user.email})
					<c:if test="${counter.count != game.users.size()}">
					 vs. 
					</c:if>
				</c:forEach>
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
	<hr>
	<div class="row">
		<div class="col-md-6">
			<c:url var="predictionUrl" value="/game/predict" />
			<form:form commandName="predictionForm" action="${predictionUrl }" method="POST">
				<c:forEach items="${predictionForm.predictions}" var="prediction" varStatus="counter">
					
					<c:set var="index" value="${counter.index }" />
					<c:set var="fixture" value="${prediction.fixture }" />
					<c:set var="cannotPredict" value="${fixture.status == 'FINISHED' or fixture.status == 'IN_PLAY'}" />
					<form:input value="${prediction.id}" path="predictions[${index }].id" type="hidden" required="required"/>
					<form:input value="${game.id}" path="predictions[${index }].gameId" type="hidden" required="required"/>
					<form:input value="${prediction.fixtureId}" path="predictions[${index }].fixtureId" type="hidden" required="required"/>
					<form:input value="${currentUser.id}" path="predictions[${index }].userId" type="hidden" required="required"/>
					<div class="row top10">
						<div class="col-md-3 col-sm-3 col-xs-3">
							<fmt:formatDate pattern="dd.MM HH:mm" value="${fixture.date}" />
						</div>
						<div class="col-md-5 col-sm-5 col-xs-5">
							${fixture.homeTeamName} vs. ${fixture.awayTeamName}&nbsp;
							<c:if test="${not empty fixture.goalsHomeTeam and not empty fixture.goalsAwayTeam}">
								<strong>
									<spring:message code="text.gamepage.fixture.status.${fixture.status}" />
									&nbsp;${fixture.goalsHomeTeam }:${fixture.goalsAwayTeam }
								</strong>
							</c:if>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-2">
							<form:errors path="predictions[${index }].goalsHomeTeam" cssClass="alert alert-danger" element="div"/>
							<form:input class="form-control input-sm"  path="predictions[${index }].goalsHomeTeam" type="number" required="required" min="0" max="15" disabled="${cannotPredict}"/>
						</div>
						<div class="col-md-2 col-sm-2 col-xs-2">
							<form:errors path="predictions[${index }].goalsAwayTeam" cssClass="alert alert-danger" element="div"/>
							<form:input class="form-control input-sm"  path="predictions[${index }].goalsAwayTeam" type="number" required="required" min="0" max="15" disabled="${cannotPredict}" />
						</div>
					</div>
				</c:forEach>
				<c:if test="${game.gameStatus != 'FINISHED'}">
					<form:button type="submit" class="btn btn-default btn-primary"><spring:message code="text.gamepage.predict" /></form:button>
				</c:if>
			</form:form>
		</div>
		<div class="col-md-6">
			<football:leagueTable competition="${competition}" />
		</div>
	</div>
</template:page>