<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
			<c:if test="${param.error ne null}">
				<div class="alert alert-danger" role="alert">
					<spring:message code="text.loginpage.invalidUsernameOrPassword" />
				</div>
			</c:if>
			<c:if test="${param.logout ne null}">
				<div class="alert alert-info" role="alert">
					<spring:message code="text.loginpage.loggedOut" />
				</div>
			</c:if>
			<c:url var="loginUrl" value="/login"/>
			<form role="form" action="${loginUrl }" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    <div class="form-group">
			        <label for="email"><spring:message code="text.loginpage.email" /></label>
			        <input class="form-control" type="email" name="email" id="email" required autofocus>
			    </div>
			    <div class="form-group">
			        <label for="password"><spring:message code="text.loginpage.password" /></label>
			        <input class="form-control" type="password" name="password" id="password" required>
			    </div>
			    <button class="btn btn-default btn-primary" type="submit"><spring:message code="text.loginpage.signIn" /></button>
			</form>
		</div>
		<div class="col-md-6">
			<spring:message code="text.loginpage.info" />
		</div>
	</div>
</template:page>