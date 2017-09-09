<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<br>
<footer>
  <div class="container">
    <div class="row">
      <div class="col-md-4 col-sm-6 footerleft ">
        <div class="logofooter"> <spring:message code="text.footer.logo" /></div>
		<p><spring:message code="text.footer.info" /></p>
		<p><i class="fa fa-map-pin"></i> <spring:message code="text.footer.location" /></p>
        <p><i class="fa fa-phone"></i> <spring:message code="text.footer.phone" /></p>
        <p><i class="fa fa-envelope"></i> <spring:message code="text.footer.mail" /></p>
        
      </div>
      <div class="col-md-2 col-sm-6 paddingtop-bottom">
        <h6 class="heading7"><spring:message code="text.footer.generalLinks" /></h6>
        <ul class="footer-ul">
          <li><a href="#"> <spring:message code="text.footer.careers" /></a></li>
          <li><a href="#"> <spring:message code="text.footer.termsAndConditions" /></a></li>
          <li><a href="#"> <spring:message code="text.footer.ranking" /></a></li>
          <li><a href="#"> <spring:message code="text.footer.faq" /></a></li>
          <li><a href="#"> <spring:message code="text.footer.aboutUs" /></a></li>
          <li><a href="#"> <spring:message code="text.footer.contactUs" /></a></li>
        </ul>
      </div>
      <div class="col-md-3 col-sm-6 paddingtop-bottom">
        <h6 class="heading7"><spring:message code="text.footer.latestPosts" /></h6>
        <div class="post">
          <p>post 1 <span>August 13,2017</span></p>
          <p>post 2 <span>August 23,2017</span></p>
          <p>post 3 <span>August 3,2017</span></p>
        </div>
      </div>
      <div class="col-md-3 col-sm-6 paddingtop-bottom">
        <div class="fb-page" data-href="https://www.facebook.com/mkaneff" data-tabs="timeline" data-height="300" data-small-header="false" style="margin-bottom:15px;" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true">
          <div class="fb-xfbml-parse-ignore">
            <blockquote cite="https://www.facebook.com/mkaneff"><a href="https://www.facebook.com/mkaneff">Facebook</a></blockquote>
          </div>
        </div>
      </div>
    </div>
  </div>
</footer>
<!--footer start from here-->

<div class="copyright">
  <div class="container">
    <div class="col-md-12">
      <p><spring:message code="text.footer.copyright" /></p>
    </div>
  </div>
</div>
