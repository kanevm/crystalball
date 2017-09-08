<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="metaDescription" required="false"%>
<%@ attribute name="metaKeywords" required="false"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${not empty pageTitle ? pageTitle : 'Football'}</title>


<%-- Meta Content --%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<%-- Additional meta tags --%>
<c:forEach var="metatag" items="${metatags}">
	<c:if test="${not empty metatag.content}">
		<meta name="${metatag.name}" content="${metatag.content}" />
	</c:if>
</c:forEach>

<%-- Favourite Icon --%>

<%-- CSS Files Are Loaded First as they can be downloaded in parallel --%>
<template:styleSheets />

<%-- Inject any additional CSS required by the page --%>
<jsp:invoke fragment="pageCss" />
</head>

<body class="${pageBodyCssClasses} language-en">

	<%-- Inject the page body here --%>
	<jsp:doBody />

	<%-- Load JavaScript required by the site --%>
	<template:javaScript />

	<%-- Inject any additional JavaScript required by the page --%>
	<jsp:invoke fragment="pageScripts" />

</body>
</html>
