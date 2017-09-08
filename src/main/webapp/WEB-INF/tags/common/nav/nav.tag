<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url var="homeUrl" value="/" />
<a href="${homeUrl }">HOME</a> | 
<c:if test="${currentUser.role eq 'ANONYMOUS'}">
	<c:url var="registerUrl" value="/register" />
	<a class="btn btn-primary" role="button" href="${registerUrl }">REGISTER</a> | 
	<c:url var="loginUrl" value="/login" />
	<a class="btn btn-primary" role="button" href="${loginUrl }">LOGIN</a>
</c:if>
<c:if test="${currentUser.role eq 'USER' or currentUser.role eq 'ADMIN'}">
	<c:url var="logoutUrl" value="/logout" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
           <input class="btn btn-primary" role="button" type="submit" value="LOGOUT" />
	</form>
</c:if>