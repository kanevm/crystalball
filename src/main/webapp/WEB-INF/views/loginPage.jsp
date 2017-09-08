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
					Invalid username and password.
				</div>
			</c:if>
			<c:if test="${param.logout ne null}">
				<div class="alert alert-info" role="alert">
					You have been logged out.
				</div>
			</c:if>
			<c:url var="loginUrl" value="/login"/>
			<form role="form" action="${loginUrl }" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			    <div class="form-group">
			        <label for="email">Email address</label>
			        <input class="form-control" type="email" name="email" id="email" required autofocus>
			    </div>
			    <div class="form-group">
			        <label for="password">Password</label>
			        <input class="form-control" type="password" name="password" id="password" required>
			    </div>
			    <button class="btn btn-default btn-primary" type="submit">Sign in</button>
			</form>
		</div>
		<div class="col-md-6">
			"Lorem ipsum dolor sit amet, consectetur
			adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore
			magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation
			ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
			irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
			fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
			proident, sunt in culpa qui officia deserunt mollit anim id est
			laborum."
		</div>
	</div>
</template:page>