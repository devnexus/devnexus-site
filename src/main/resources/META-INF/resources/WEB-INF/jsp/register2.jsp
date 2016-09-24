<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Register</title>

<section class="container-fluid register" >
    <h1 class="featured-header">
        CHECKOUT
    </h1>

    <div class="row">

        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registrationDetails"  enctype="multipart/form-data" action="/s/registerPageTwo">

            <%@include file="registration_details.jsp" %>

            <div class="form-group total">
                <span for="total-cost" style="font-weight: bold" class="col-lg-2 control-label">Order Total: </span>
                <span id="total-cost" style="font-weight: bold" name="total-cost"  >$<c:out value="${registrationDetails.finalCost}"/></span>
                <input class="paypal-image" type="image" src="https://www.paypalobjects.com/webstatic/en_US/i/btn/png/gold-rect-paypalcheckout-60px.png" align="right" style="margin-right:7px;" />
            </div>

        </form:form>
    </div>

</section>


<content tag='bottom'>
</content>
