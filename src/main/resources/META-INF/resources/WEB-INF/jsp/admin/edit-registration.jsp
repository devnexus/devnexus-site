<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
    <title>${event.title} - Register</title>
</head>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
    <div class="container header">
        <div class="row centered">
            <div class="col-md-10 col-md-offset-1">
                <div class="top-intro travel">
                    <h4 class="section-white-title decorated"><span>Register for ${event.title}</span></h4>
                    <h5 class="intro-white-lead">${scheduleItemList.numberOfSpeakersAssigned} Speakers, ${scheduleItemList.numberOfSessions} Presentations, ${scheduleItemList.days.size()} Days.</h5>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /intro -->

<div class="container">

    <h1>Registration for <c:out value="${event.title}"/></h1>

    <div class="row">
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registrationDetails"  enctype="multipart/form-data" >

            <h4>Purchaser Information - Required - </h4>
            <br/>

            <spring:bind path="contactName">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactName" class="col-lg-2 control-label">Contact Name*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="contactName" id="contactName" maxlength="255"/>
                    <form:errors path="contactName" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="contactEmailAddress">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactEmailAddress" class="col-lg-2 control-label">Email Address*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="contactEmailAddress" id="contactEmailAddress" maxlength="255"/>
                    <form:errors path="contactEmailAddress" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="contactPhoneNumber">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactPhoneNumber" class="col-lg-2 control-label">Phone Number*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="contactPhoneNumber" id="contactPhoneNumber" maxlength="255"/>
                    <form:errors path="contactPhoneNumber" cssClass="fieldError"/>
                </div>
            </div>

                <div class="form-group${errorClass}">
                <label for="skill-level" class="col-lg-2 control-label">Payment State*</label>
                <div class="col-lg-10">
                    <form:select cssClass="form-control" path="paymentState" id="payment-state" tabindex="10">
                        <form:options items="${paymentStates}" />
                    </form:select>
                    <form:errors path="paymentState" cssClass="fieldError" />
                </div>
            </div>


            <%@include file="registration_details.jsp" %>

            <div class="form-group">
                <div class="col-lg-offset-4 col-lg-10">
                    <button type="submit" class="btn btn-default right btn-primary" lang="save" tabindex="19">Update Form</button>
                </div>
            </div>
        </form:form>
    </div>

</div>

<content tag='bottom'>
</content>

