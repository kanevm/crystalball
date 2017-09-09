<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
		
			<c:url var="startGameUrl" value="/game/start" />
			<form:form id="game-form" commandName="gameForm" action="${startGameUrl}" method="POST">
				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
				
				<h4><spring:message code="text.gamepage.chooseCompetition" /></h4><hr>
				<c:forEach items="${competitions}" var="competition">
					<div class="radio">
						<label>
							<form:radiobutton path="competitionId" value="${competition.id }" />
							${competition.caption} - <spring:message code="text.gamepage.round" /> ${competition.currentMatchday}
						</label>
					</div>
				</c:forEach>

				<br>

				<h4><spring:message code="text.gamepage.choosePlayers" /></h4>
				<hr>
				<c:forEach items="${users}" var="user">
					<div class="checkbox">
						<label>
							<form:checkbox path="userIds" value="${user.email}" />
							${user.name} (${user.email})
						</label>
					</div>
				</c:forEach>

				<br>
				
				<input type="submit" class="btn btn-primary" value="Start game">
			</form:form>
			
		</div>
		<div class="col-md-6">
			<spring:message code="text.gamepage.info" />
		</div>
	</div>
</template:page>