<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<div id="content" class="span-22 prepend-top last ">

    <c:if test="${param.status == 'error'}">
        <div class="error"><spring:message code="jsp.login.error.errorMessage"/></div>
    </c:if>

    <form class="form-group" name="j_spring_security_check" id="j_acegi_security_check" method="POST"
          action="${ctx}/login" role="form" style="margin: 15px">
        <div class="form-group">
            <h2>Login</h2>

            <div class="required form-group">
                <label for="j_username"><spring:message code="class.user.email"/></label>
                <input class="title form-control" type="text" id="j_username" name="username" maxlength="50"
                       tabindex="1"/>
            </div>
            <div class="required form-group">
                <label for="j_password"><spring:message code="class.user.password"/></label>
                <input class="title form-control" type="password" name="password" maxlength="120" tabindex="2"/>
            </div>

            <button type="submit" class="btn btn-default" value="<spring:message code="jsp.login.button.login"/>">
                <spring:message code="jsp.login.button.login"/></button>
        </div>
    </form>
</div>
<script type="text/JavaScript" language="JavaScript">
    <!--
    $(function () {

    $('#j_username').focus();
    $('table.errorMessages').width($('.content').width());

    });
    //-->
</script>
