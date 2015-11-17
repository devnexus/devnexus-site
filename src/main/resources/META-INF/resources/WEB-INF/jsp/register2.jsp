<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Register</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
    <div class="container header">
        <div class="row centered">
            <div class="col-md-10 col-md-offset-1">
                <div class="top-intro travel">
                    <h4 class="section-white-title decorated"><span>Register for ${event.title}</span></h4>
                    <h5 class="intro-white-lead">1600+ attendees, 90+ Speakers, 12+ tracks, 2 Conference Days + 1 Workshop Day.</h5>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- /intro -->

<div class="container">

    <h1>Register for <c:out value="${event.title}"/></h1>

    <div class="row">

        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registrationDetails"  enctype="multipart/form-data" action="/s/registerPageTwo">

            <%@include file="registration_details.jsp" %>

            <div class="form-group">
                <label for="total-cost" class="col-lg-2 control-label">Final Price: </label>
                <div class="col-lg-9">
                    <input class="form-control" disabled="disabled" value="$<c:out value="${registrationDetails.finalCost}"/>"/>
                </div>
            </div>



            <div class="form-group">
                <div class="col-lg-offset-4 col-lg-10">
                    <input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" align="left" style="margin-right:7px;" />
                </div>
            </div>

        </form:form>
    </div>

</div>
<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
</content>
