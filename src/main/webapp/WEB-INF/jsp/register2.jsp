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

    <h1>Register for <c:out value="${event.title}"/></h1>

    <div class="row">


        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerFormPageTwo"  enctype="multipart/form-data" action="/s/registerPageTwo">

            <form:hidden path="couponCode"/>
            <form:hidden path="ticketGroup"/>
            <form:hidden path="ticketCount"/>

            <c:forEach items="${registerFormPageTwo.orderDetails}" varStatus="orderStatus">
                <div style="border: 1px solid  #008F9C; border-radius: 5px;margin: 26px; padding: 13px;">
                    <h4>Registrant Information - Required - </h4>
                    <br/>

                    <spring:bind path="orderDetails[${orderStatus.index}].firstName">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].firstName" class="col-lg-2 control-label">First Name*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].firstName" id="orderDetails[${orderStatus.index}].firstName" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].firstName" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].lastName" >
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].lastName" class="col-lg-2 control-label">Last Name*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].lastName" id="orderDetails[${orderStatus.index}].lastName" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].lastName" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].emailAddress">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].emailAddress" class="col-lg-2 control-label">Email Address*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].emailAddress" id="orderDetails[${orderStatus.index}].emailAddress" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].emailAddress" cssClass="fieldError"/>
                        </div>
                    </div>

                    <h4>Location Information - Appreciated - </h4>
                    <br/>

                    <spring:bind path="orderDetails[${orderStatus.index}].city">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].city" class="col-lg-2 control-label">City </label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].city" id="orderDetails[${orderStatus.index}].city" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].city" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].state">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].state" class="col-lg-2 control-label">State</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].state" id="orderDetails[${orderStatus.index}].state" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].state" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].country">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].country" class="col-lg-2 control-label">Country</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].country" id="orderDetails[${orderStatus.index}].country" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].country" cssClass="fieldError"/>
                        </div>
                    </div>

                    <h4>Employment Information - Just Curious - </h4>
                    <br/>

                    <spring:bind path="orderDetails[${orderStatus.index}].jobTitle">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].jobTitle" class="col-lg-2 control-label">Job Title</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].jobTitle" id="orderDetails[${orderStatus.index}].jobTitle" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].jobTitle" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].company">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].company" class="col-lg-2 control-label">Company</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].company" id="orderDetails[${orderStatus.index}].company" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].company" cssClass="fieldError"/>
                        </div>
                    </div>

                    <h4>Amenities Information - For your comfort - </h4>
                    <br/>

                    <spring:bind path="orderDetails[${orderStatus.index}].tShirtSize">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].tShirtSize" class="col-lg-2 control-label">T-Shirt Size</label>
                        <div class="col-lg-10">
                            <form:select cssClass="form-control" path="orderDetails[${orderStatus.index}].tShirtSize" id="orderDetails[${orderStatus.index}].tShirtSize" >
                                <form:option value="Men's XXXL"></form:option>
                                <form:option value="Men's XXL"></form:option>
                                <form:option value="Men's XL"></form:option>
                                <form:option value="Men's L"></form:option>
                                <form:option value="Men's M"></form:option>
                                <form:option value="Men's S"></form:option>

                                <form:option value="Women's XXXL"></form:option>
                                <form:option value="Women's XXL"></form:option>
                                <form:option value="Women's XL"></form:option>
                                <form:option value="Women's L"></form:option>
                                <form:option value="Women's M"></form:option>
                                <form:option value="Women's S"></form:option>


                            </form:select>
                            <form:errors path="orderDetails[${orderStatus.index}].tShirtSize" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].vegetarian">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].vegetarian" class="col-lg-2 control-label">Request Vegetarian Meal</label>
                        <div class="col-lg-10">
                            <form:checkbox cssClass="form-control" path="orderDetails[${orderStatus.index}].vegetarian" id="orderDetails[${orderStatus.index}].vegetarian" value="true"/>
                            <form:errors path="orderDetails[${orderStatus.index}].vegetarian" cssClass="fieldError"/>
                        </div>
                    </div>
                </div>
            </c:forEach>
            
            <div class="form-group">
                <div class="col-lg-offset-4 col-lg-10">
                        <input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" align="left" style="margin-right:7px;" />
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-4 col-lg-10">
                    <button type="submit" class="btn btn-link" lang="save" tabindex="19" value="invoice" name="invoice">I can't use PayPal.</button>
                </div>
            </div>
            
            </form:form>


        </div>

    </div>
    <jsp:include page="includes/questions.jsp"/>

    <content tag='bottom'>
    </content>
