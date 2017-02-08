<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
    <title>${contextEvent.title} |  DevNexus 2017</title>
</head>
<body>
    <section class="container-fluid register" id="admin-group-register" >


        <h1 class="featured-header">
            REGISTER
        </h1>
        <section>

            <div class="row">
                <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">

                    <h4 class="form-group">Purchaser Information </h4>

                    <spring:bind path="contactName">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="contactName" >* Contact Name</label>
                        <form:input cssClass="form-control" path="contactName" type="text" id="contactName" maxlength="255"/>
                        <form:errors path="contactName" cssClass="fieldError"/>

                    </div>

                    <spring:bind path="contactEmailAddress">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="contactEmailAddress" >* Email Address</label>

                        <form:input cssClass="form-control" type="email" path="contactEmailAddress" id="contactEmailAddress" maxlength="255"/>
                        <form:errors path="contactEmailAddress" cssClass="fieldError"/>

                    </div>

                    <spring:bind path="contactPhoneNumber">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="contactPhoneNumber" >* Phone Number</label>
                        <form:input cssClass="form-control" type="tel" path="contactPhoneNumber" id="contactPhoneNumber" maxlength="255"/>
                        <form:errors path="contactPhoneNumber" cssClass="fieldError"/>
                    </div>
                    
                    <div class="row hidden-xs">
                        <div class="text-center">
                            <button class="btn download" onclick="form.submit()">download new group form</button>
                        </div>
                    </div>
                    
                </form:form>
            </div>
        </section>
    </section>
    <br/>
    <br/>
</body>