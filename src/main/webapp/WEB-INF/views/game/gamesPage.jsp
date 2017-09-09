<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<h3>Your games</h3><hr>
	<c:if test="${empty games}">
		<div class="row">
			<div class="col-md-12">
				You have no started games<br><br>
				<a href="<c:url value="/game/start" />" class="btn btn-primary" role="button">Start a game</a>
			</div>
		</div>
	</c:if>
	<div class="row">
		<c:forEach items="${games}" var="game">
			<div class="col-md-4 col-sm-6 well" style="height: 210px; position:relative;">
				<div class="row">
					<div class="col-md-12">
						Game id: 
						${game.id}
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						Competition: 
						${game.competitionCaption} - Round ${game.competitionMatchday}
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						Started on: 
						<fmt:formatDate pattern="dd.MM HH:mm" value="${game.startedTime}" />
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						Players: 
						<c:forEach items="${game.users}" var="user" varStatus="counter">
							${user.name} (${user.email})
							<c:if test="${counter.count != game.users.size()}">
							 VS 
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						Status: ${game.gameStatus}
					</div>
				</div>
				<div class="row" style="position: absolute; bottom:20px;">
					<div class="col-md-12">
						<c:choose>
							<c:when test="${game.gameStatus == 'FINISHED'}">
								<a href="<c:url value="/game/${game.id}/summary" />" class="btn btn-primary" role="button">Display summary</a>
							</c:when>
							<c:otherwise>
								<a href="<c:url value="/game/${game.id}" />" class="btn btn-primary" role="button">Predict scores</a>
							</c:otherwise>						
						</c:choose>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
	
</template:page>