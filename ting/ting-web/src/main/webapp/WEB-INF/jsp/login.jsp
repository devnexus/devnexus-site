<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">

  <c:if test="${param.status == 'error'}">
    <div class="error"><spring:message code="jsp.login.error.errorMessage"/></div>
  </c:if>

	  <form  name="j_spring_security_check" id="j_acegi_security_check" method="POST" action="${ctx}/j_spring_security_check">
	      <h2>Login</h2>
			<div class="required">
                <label for="j_username"><spring:message code="class.user.email" /></label>
                <input class="title" type="text" id="j_username" name="j_username" maxlength="50" tabindex="1"/>
			</div>
            <div class="required">
                <label for="j_password"><spring:message code="class.user.password" /></label>
                <input class="title" type="password" name="j_password" maxlength="120" tabindex="2"/>
            </div>
	        <div class="submit">
                <input type="submit" value="<spring:message code="jsp.login.button.login"/>"/>
                <input type="submit" onClick="location.href='<c:url value='/'/>'; return false;" value="<spring:message code="jsp.login.button.cancel"/>"/>
	        </div>
	  </form>
</div>
<script type="text/JavaScript" language="JavaScript">
<!--
$(function() {

    $('#j_username').focus();
    $('table.errorMessages').width($('.content').width());

});
//-->
</script>
