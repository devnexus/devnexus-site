<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>


<title>${contextEvent.title} - Register</title>

<section class="container-fluid register" >


    <h1 class="featured-header">Confirm Registration for <c:out value="${event.title}"/></h1>
    <div class="row">

        <c:choose>
            <c:when test="${not empty payPalError}">
                <div class="col-lg-offset-1 col-lg-10 bg-danger">
                    <h3><p class="center">
                            <span>The transaction did not complete with PayPal.  PayPal has returned the following information :</span>
                        <ul>
                            <li>
                                <div class="label-danger">Error Message from PayPal</div>
                                <div class="col-sm-offset-1"><c:out value="${payPalError.message}"/></div>
                            </li>
                        </ul>
                        <span>You may try to resolve the problem with PayPal and retry registration, or contact us at <a href="mailto:info@ajug.org">info@ajug.org</a> for assistance.</span>
                        </p></h3>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-lg-offset-1 col-lg-10 bg-info ">
                    <h3><p class="center">Please review your registration details and click &QUOT;Purchase and complete registration&QUOT; to complete your registration.</p></h3>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="row">
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registrationDetails"  enctype="multipart/form-data" action="/s/executeRegistration/${registrationKey}?payerId=${payerId}&paymentId=${paymentId}">

            <%@include file="registration_details.jsp" %>
            <div class="row">
                <div class="form-group">
                    <div class="col-lg-12">
                        <button type="submit" class="center-block btn btn-lg btn-warning" style="margin-left: auto; margin-right: auto;border: none;border-radius: 0; background-color: black" lang="save" tabindex="19">Purchase and complete registration</button>
                    </div>
                </div>
            </div>
        </form:form>
    </div>

</section>
<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
    <script type="text/javascript">
        $("#form input").prop("disabled", true);
        $("#form select").prop("disabled", true);
    </script>
</content>
