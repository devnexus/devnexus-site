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

            <%@include file="registration_details.jsp" %>

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
