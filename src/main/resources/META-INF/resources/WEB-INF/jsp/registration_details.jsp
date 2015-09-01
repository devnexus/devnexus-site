<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

            <form:hidden path="couponCode"/>
            <form:hidden path="ticketGroup"/>
            <form:hidden path="ticketCount"/>
            <form:hidden path="contactName"/>
            <form:hidden path="contactEmailAddress"/>
            <form:hidden path="contactPhoneNumber"/>
            
            <c:forEach items="${registerFormPageTwo.orderDetails}" varStatus="orderStatus">
                <c:out value="${status.toString()}"/>
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

                    <h4>Location Information - Required - </h4>
                    <br/>

                    <spring:bind path="orderDetails[${orderStatus.index}].city">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].city" class="col-lg-2 control-label">City*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].city" id="orderDetails[${orderStatus.index}].city" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].city" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].state">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].state" class="col-lg-2 control-label">State*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].state" id="orderDetails[${orderStatus.index}].state" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].state" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].country">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].country" class="col-lg-2 control-label">Country*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].country" id="orderDetails[${orderStatus.index}].country" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].country" cssClass="fieldError"/>
                        </div>
                    </div>

                    <h4>Employment Information - Required - </h4>
                    <br/>

                    <spring:bind path="orderDetails[${orderStatus.index}].jobTitle">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].jobTitle" class="col-lg-2 control-label">Job Title*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].jobTitle" id="orderDetails[${orderStatus.index}].jobTitle" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].jobTitle" cssClass="fieldError"/>
                        </div>
                    </div>

                    <spring:bind path="orderDetails[${orderStatus.index}].company">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].company" class="col-lg-2 control-label">Company*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="orderDetails[${orderStatus.index}].company" id="orderDetails[${orderStatus.index}].company" maxlength="255"/>
                            <form:errors path="orderDetails[${orderStatus.index}].company" cssClass="fieldError"/>
                        </div>
                    </div>

                    <h4>Other Information </h4>
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
                        <spring:bind path="orderDetails[${orderStatus.index}].sponsorMayContact">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="orderDetails[${orderStatus.index}].sponsorMayContact" class="col-lg-2 control-label">Sponsor May Contact</label>
                        <div class="col-lg-10">
                            <form:checkbox cssClass="form-control" path="orderDetails[${orderStatus.index}].sponsorMayContact" id="orderDetails[${orderStatus.index}].sponsorMayContact" value="true"/>
                            <form:errors path="orderDetails[${orderStatus.index}].sponsorMayContact" cssClass="fieldError"/>
                        </div>
                    </div>
                    
                </div>
            </c:forEach>
