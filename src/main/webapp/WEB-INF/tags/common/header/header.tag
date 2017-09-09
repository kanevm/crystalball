<%@ tag body-content="empty" trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/common/nav"%>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/" />"><spring:message code="text.header.logo" /></a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      	<c:if test="${currentUser.role == 'ANONYMOUS'}">
	        <li class="active"><a href="<c:url value="/login" />"><spring:message code="text.header.login" /></a></li>
	        <li><a href="<c:url value="/register" />"><spring:message code="text.header.register" /></a></li>
		</c:if>
		<c:if test="${currentUser.role != 'ANONYMOUS'}">
	        <li class="active">
	        	<a style="cursor: default;" href="javascript:;">
	        		<spring:message code="text.header.greeting" arguments="${currentUser.name}"/>
	        		</a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false"><spring:message code="text.header.actions" />&nbsp;<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="<c:url value="/game/start" />"><spring:message code="text.header.startGame" /></a></li>
					<li><a href="<c:url value="/games" />"><spring:message code="text.header.games" /></a></li>
					<li role="separator" class="divider"></li>
					<li><a href="<c:url value="/logout" />"><spring:message code="text.header.logout" /></a></li>
				</ul>
			</li>
		</c:if>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="text.header.language.change" />&nbsp;<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="<c:url value="${request.contextPath }?lang=bg" />"><spring:message code="text.header.language.bg" /></a></li>
            <li><a href="<c:url value="${request.contextPath }?lang=en" />"><spring:message code="text.header.language.en" /></a></li>
          </ul>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>