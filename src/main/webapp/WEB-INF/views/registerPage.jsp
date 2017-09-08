<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
			<c:url var="registerUrl" value="/register"/>
			<form:form id="register-form" commandName="registerForm" action="${registerUrl }" method="POST">
				<form:errors path="*" cssClass="alert alert-danger" element="div"/>
				<div class="form-group">
					<form:label path="email">Email</form:label>
					<form:input path="email" type="email" class="form-control" required="required" autofocus="autofocus"/>
				</div>
				<div class="form-group">
					<form:label path="name">Name</form:label>
					<form:input path="name" class="form-control" required="required"/>
				</div>
				<div class="form-group">
					<form:label path="name">Password</form:label>
					<form:password path="password" class="form-control" required="required"/>
				</div>
				<div class="form-group">
					<form:label path="name">Repeat Password</form:label>
					<form:password path="passwordRepeated" class="form-control" required="required"/>
				</div>
				<div>
					<form:button type="submit" class="btn btn-default btn-primary">Register</form:button>
				</div>
			</form:form>
		</div>
		<div class="col-md-6">
			"Sed ut perspiciatis unde omnis iste natus error
			sit voluptatem accusantium doloremque laudantium, totam rem aperiam,
			eaque ipsa quae ab illo inventore veritatis et quasi architecto
			beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia
			voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur
			magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro
			quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur,
			adipisci velit, sed quia non numquam eius modi tempora incidunt ut
			labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima
			veniam, quis nostrum exercitationem ullam corporis suscipit
			laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel
			eum iure reprehenderit qui in ea voluptate velit esse quam nihil
			molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas
			nulla pariatur?"
		</div>
	</div>
</template:page>