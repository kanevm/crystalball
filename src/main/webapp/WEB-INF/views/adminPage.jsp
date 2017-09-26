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
			<div class="row">
			<c:forEach items="${users}" var="user">
				<div class="col-md-12 well">
					<div class="row">
						<div class="col-md-8">
							<strong>
								${user.name } (${user.email})
							</strong>
							<c:if test="${not empty user.games}">
								<br>
								<strong>
									<spring:message code="text.adminpage.games" />:
									<c:forEach items="${user.games}" var="game" varStatus="counter">
										${game.id }
										<c:if test="${counter.count != user.games.size()}">
											,
										</c:if>
									</c:forEach>
								</strong>
							</c:if>
						</div>
						<div class="col-md-4">
							<c:if test="${user.role != 'ADMIN' && empty user.games}">
								<c:url var="deleteUserUrl" value="/admin/user" />
								<form action="${deleteUserUrl}" method="POST" style="float:right; ">
									<input type="hidden" name="id" value="${user.id}" /> 
									<input class="btn btn-primary" type="submit" value="<spring:message code="text.adminpage.users.delete" />" />
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="col-md-6">
			<div class="row">
			<c:forEach items="${games}" var="game">
				<div class="col-md-10 col-md-offset-2 well" style="height: 210px; position:relative;">
				
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
				<div class="row" style="position: absolute; bottom:20px;">
					<div class="col-md-12">
						<a href="<c:url value="/game/${game.id}/summary" />" class="btn btn-primary" role="button"><spring:message code="text.adminpage.games.details" /></a>&nbsp;
						<c:url var="deleteGameUrl" value="/admin/game" />
						<form action="${deleteGameUrl}" method="POST" style="float:right; ">
							<input type="hidden" name="id" value="${game.id}" /> 
							<input class="btn btn-primary" type="submit" value="<spring:message code="text.adminpage.games.delete" />" />
						</form>
					</div>
				</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</div>
</template:page>