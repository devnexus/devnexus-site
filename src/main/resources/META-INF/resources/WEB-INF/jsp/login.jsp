<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Login</title>

<section class="container-fluid conference-information" >
    <h1 class="featured-header">LOGIN</h1>
    <div class="row">
        <c:if test="${param.status == 'error'}">
            <div class="error"><spring:message code="jsp.login.error.errorMessage"/></div>
        </c:if>

        <form class="form-horizontal" role="form" name="j_spring_security_check" id="j_acegi_security_check" method="POST"
              action="${ctx}/s/login" role="form" style="margin: 15px">
            <div class="form-group">
                <label for="j_username" class="col-lg-2 control-label"><spring:message code="class.user.email"/></label>
                <div class="col-lg-10">
                    <input class="form-control" type="text" id="j_username" name="username" maxlength="50"
                           tabindex="1" autofocus/>
                </div>
            </div>
            <div class="form-group">
                <label for="j_password" class="col-lg-2 control-label"><spring:message code="class.user.password"/></label>
                <div class="col-lg-10">
                    <input class="form-control" type="password" name="password" maxlength="120" tabindex="2"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-default" value="<spring:message code="jsp.login.button.login"/>">
                        <spring:message code="jsp.login.button.login"/></button>
                </div>
            </div>
        </form>
    </div>
    <div class="row">
        <hr/>
        <form class="form-horizontal" role="form" action="<c:url value="/auth/google" />" method="POST">
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-default">Sign in with Google</button>
                    <input type="hidden" name="scope"
                           value="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"/>
                </div>
            </div>
        </form>

    </div>

</section>