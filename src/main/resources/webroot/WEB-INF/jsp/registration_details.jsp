<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<form:hidden path="contactName"/>
<form:hidden path="contactEmailAddress"/>
<form:hidden path="contactPhoneNumber"/>

<c:forEach items="${registrationDetails.orderDetails}" varStatus="orderStatus" var="order">

    <div style="margin: 26px; padding: 13px;">
        <h3 ><span class="checkout">Details for ticket</span> : <c:out value="${order.label}"/></h3>

        <br/>

        <spring:bind path="orderDetails[${orderStatus.index}].firstName">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].firstName" class="control-label">* First Name</label>

            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].firstName" id="orderDetails[${orderStatus.index}].firstName" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].firstName" cssClass="fieldError"/>

        </div>

        <spring:bind path="orderDetails[${orderStatus.index}].lastName" >
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].lastName" class="control-label">* Last Name</label>

            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].lastName" id="orderDetails[${orderStatus.index}].lastName" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].lastName" cssClass="fieldError"/>

        </div>

        <spring:bind path="orderDetails[${orderStatus.index}].emailAddress">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].emailAddress" class="control-label">* Email Address</label>
            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].emailAddress" id="orderDetails[${orderStatus.index}].emailAddress" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].emailAddress" cssClass="fieldError"/>

        </div>

        <h4>Location Information</h4>
        <br/>

        <spring:bind path="orderDetails[${orderStatus.index}].city">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].city" class="control-label">* City</label>
            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].city" id="orderDetails[${orderStatus.index}].city" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].city" cssClass="fieldError"/>

        </div>
        <form:hidden path="orderDetails[${orderStatus.index}].couponCode"/>
        <form:hidden path="orderDetails[${orderStatus.index}].ticketGroup"/>
        <form:hidden path="orderDetails[${orderStatus.index}].label"/>
        <spring:bind path="orderDetails[${orderStatus.index}].state">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].state" class="control-label">* State</label>
            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].state" id="orderDetails[${orderStatus.index}].state" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].state" cssClass="fieldError"/>

        </div>

        <spring:bind path="orderDetails[${orderStatus.index}].country">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].country" class="control-label">* Country</label>
            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].country" id="orderDetails[${orderStatus.index}].country" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].country" cssClass="fieldError"/>

        </div>

        <h4>Employment Information</h4>
        <br/>

        <spring:bind path="orderDetails[${orderStatus.index}].jobTitle">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].jobTitle" class="control-label">* Job Title</label>

            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].jobTitle" id="orderDetails[${orderStatus.index}].jobTitle" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].jobTitle" cssClass="fieldError"/>

        </div>

        <spring:bind path="orderDetails[${orderStatus.index}].company">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].company" class="control-label">* Company</label>
            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].company" id="orderDetails[${orderStatus.index}].company" maxlength="255"/>
            <form:errors path="orderDetails[${orderStatus.index}].company" cssClass="fieldError"/>

        </div>

        <h4>Other Information </h4>
        <br/>

        <spring:bind path="orderDetails[${orderStatus.index}].tShirtSize">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].tShirtSize" class="control-label">T-Shirt Size</label>

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

        <spring:bind path="orderDetails[${orderStatus.index}].vegetarian">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].vegetarian" class="control-label">I would like a vegetarian lunch</label>

            <form:select cssClass="form-control" path="orderDetails[${orderStatus.index}].vegetarian" id="orderDetails[${orderStatus.index}].vegetarian" >
                <form:option value="true">Yes</form:option>
                <form:option value="false">No</form:option>                
            </form:select>
            <form:errors path="orderDetails[${orderStatus.index}].vegetarian" cssClass="fieldError"/>

        </div>
        <spring:bind path="orderDetails[${orderStatus.index}].sponsorMayContact">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>

        <div class="form-group${errorClass}">
            <label for="orderDetails[${orderStatus.index}].sponsorMayContact" class="control-label">I would like to receive information from DevNexus sponsors</label>

            <form:select cssClass="form-control" path="orderDetails[${orderStatus.index}].sponsorMayContact" id="orderDetails[${orderStatus.index}].sponsorMayContact" value="true">
                <form:option value="true">Yes</form:option>
                <form:option value="false">No</form:option>                
            </form:select>
            <form:errors path="orderDetails[${orderStatus.index}].sponsorMayContact" cssClass="fieldError"/>

        </div>

    </div>
</c:forEach>
