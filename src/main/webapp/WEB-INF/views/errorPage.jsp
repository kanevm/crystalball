<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-12">
			<h1><spring:message code="text.errorpage.header" /></h1>
			<p><spring:message code="text.errorpage.paragraph" /></p>

			<!--
			    Failed URL: ${url}
			    Exception:  ${exception.message}
			        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
			    </c:forEach>
			  -->
		</div>
	</div>
</template:page>