<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
			<c:url var="registerUrl" value="/register"/>
			<form:form id="register-form" commandName="registerForm" action="${registerUrl}" method="POST">
				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
				<div class="form-group">
					<form:label path="email"><spring:message code="text.registerpage.email" /></form:label>
					<form:input path="email" type="email" class="form-control" required="required" autofocus="autofocus"/>
				</div>
				<div class="form-group">
					<form:label path="name"><spring:message code="text.registerpage.name" /></form:label>
					<form:input path="name" class="form-control" required="required"/>
				</div>
				<div class="form-group">
					<form:label path="name"><spring:message code="text.registerpage.password" /></form:label>
					<form:password path="password" class="form-control" required="required"/>
				</div>
				<div class="form-group">
					<form:label path="name"><spring:message code="text.registerpage.passwordRepeated" /></form:label>
					<form:password path="passwordRepeated" class="form-control" required="required"/>
				</div>
				<div>
					<form:button type="submit" class="btn btn-default btn-primary"><spring:message code="text.registerpage.register" /></form:button>
				</div>
			</form:form>
		</div>
		<div class="col-md-6">
			<spring:message code="text.registerpage.info" />
		</div>
	</div>
</template:page>