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

    <div class="row">
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">
            <table>
                <thead>
                <td>Label</td>
                <td>Description</td>
                <td>Start Date</td>
                <td>End Date</td>
                <td>Cost per Ticket</td>
                <td>Ticket Quantity</td>
                </thead>

                <c:forEach items="${eventSignup.groups}" var="group" varStatus="status">
                    <tr>
                        <td><c:out value="${group.label}"></c:out></td>
                        <td><c:out value="${group.description}"></c:out></td>
                        <td><c:out value="${group.startDate}"></c:out></td>
                        <td><c:out value="${group.endDate}"></c:out></td>
                        <td><form:select path="ticketCount">
                                <form:option value="0" >0</form:option>
                                <form:option value="1" >1</form:option>
                                <form:option value="2" >2</form:option>
                                <form:option value="3" >3</form:option>
                                <form:option value="4" >4</form:option>
                                <form:option value="5" >5</form:option>
                                <form:option value="6" >6</form:option>
                                <form:option value="7" >7</form:option>
                                <form:option value="8" >8</form:option>
                                <form:option value="9" >9</form:option>
                                <form:option value="10" >10</form:option>
                                <form:option value="11" >11</form:option>
                                <form:option value="12" >12</form:option>
                                <form:option value="13" >13</form:option>
                                <form:option value="14" >14</form:option>
                                <form:option value="15" >15</form:option>
                                <form:option value="16" >16</form:option>
                            </form:select></td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>

        <div class="col-md-10 col-md-offset-1" style="padding-top: 4em;">
            <h2>Tell us about you.</h2>
            <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">
                <spring:bind path="registerForm.firstName">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                </spring:bind>
                <div class="form-group${errorClass}">
                    <label for="firstName" class="col-lg-2 control-label">First Name *</label>
                    <div class="col-lg-10">
                        <form:input cssClass="form-control" path="firstName" id="firstName" maxlength="255" tabindex="1"/>
                        <form:errors path="firstName" cssClass="fieldError"/>
                    </div>
                </div>
                <spring:bind path="registerForm.lastName">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                </spring:bind>
                <div class="form-group${errorClass}">
                    <label for="lastName" class="col-lg-2 control-label">Last Name *</label>
                    <div class="col-lg-10">
                        <form:input cssClass="form-control" path="lastName" id="lastName" maxlength="255" tabindex="1"/>
                        <form:errors path="lastName" cssClass="fieldError"/>
                    </div>
                </div>
                <spring:bind path="registerForm.emailAddress">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                </spring:bind>
                <div class="form-group${errorClass}">
                    <label for="emailAddress" class="col-lg-2 control-label">Email Address *</label>
                    <div class="col-lg-10">
                        <form:input cssClass="form-control" path="emailAddress" id="emailAddress" maxlength="255" tabindex="1"/>
                        <form:errors path="emailAddress" cssClass="fieldError"/>
                    </div>
                </div>
                <spring:bind path="registerForm.phoneNumber">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                </spring:bind>
                <div class="form-group${errorClass}">
                    <label for="phoneNumber" class="col-lg-2 control-label">Phone Number *</label>
                    <div class="col-lg-10">
                        <form:input cssClass="form-control" path="phoneNumber" id="phoneNumber" maxlength="255" tabindex="1"/>
                        <form:errors path="phoneNumber" cssClass="fieldError"/>
                    </div>
                </div>

                <div class="row">

                    <div class="col-md-12 text-right">

                        <button type="button" class="btn btn-info" name="addTicket" id="addTicket" tabindex="20" title="Add Ticket">
                            Add A Ticket <span class="glyphicon glyphicon-plus"></span>
                        </button>
                    </div>
                </div>

                <a name="ticketStart"></a>    
                <c:forEach items="${tickets}" var="ticket" varStatus="status">
                    <spring:bind path="tickets[${status.index}].firstName">
                        <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                    </spring:bind>
                    <div class="form-group${errorClass}">
                        <label for="tickets[${status.index}].firstName" class="col-lg-2 control-label">First Name*</label>
                        <div class="col-lg-10">
                            <form:input cssClass="form-control" path="tickets[${status.index}].firstName" id="speakers[${status.index}].firstName" maxlength="255"/>
                            <form:errors path="tickets[${status.index}].firstName" cssClass="fieldError"/>
                        </div>
                    </div>
                </c:forEach>
            </form:form>

        </div>
    </div>
</div>
<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
    <script type="text/javascript">
        $(document).ready(function () {

            var s = skrollr.init({
                forceHeight: false,
                mobileCheck: function () {
                    return false;
                }
            });
        });
    </script>
</content>
