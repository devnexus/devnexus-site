<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
    <div class="container">
        <div id="banner">
            <h1><strong>Edit Registrations</strong></h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">


    </div>
</div>


    <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registrationSearchForm" enctype="multipart/form-data">

        <spring:bind path="email">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="label" class="col-lg-2 control-label">Search By Email</label>
            <div class="col-lg-10">
                <form:input cssClass="form-control" path="email" id="email" maxlength="255" tabindex="1"/>
                <form:errors path="email" cssClass="fieldError"/>
            </div>
        </div>


        <spring:bind path="name">
            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
        </spring:bind>
        <div class="form-group${errorClass}">
            <label for="price" class="col-lg-2 control-label">Search by Name</label>
            <div class="col-lg-10">
                <form:input cssClass="form-control" path="name" id="name" tabindex="5" maxlength="255"/>
                <form:errors path="name" cssClass="fieldError"/>
            </div>
        </div>

        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default" lang="save" tabindex="19">Search</button>
            </div>
        </div>
    </form:form>

    <c:if test="${not empty registrations}">
        <h2>Registration Purchases found</h2>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Action</th><th>Contact Name</th><th>Contact Email</th><th>Payment Status</th>
                </tr>
            </thead>

            <c:forEach items="${registrations}" var="reg">
                <tr>
                    <td><a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/editRegistration/${reg.registrationFormKey}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><c:out value="${reg.contactName}"/></td>
                    <td><c:out value="${reg.contactEmailAddress}"/></td>
                    <td><c:out value="${reg.paymentState}"/></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${not empty tickets}">
        <h2>Tickets found</h2>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Action</th><th>Name on Ticket</th><th>Email on Ticket</th><th></th>
                </tr>
            </thead>

            <c:forEach items="${tickets}" var="reg">
                <tr>
                    <td><a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/editRegistration/${reg.registration.registrationFormKey}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td><c:out value="${reg.firstName}"/> <c:out value="${reg.lastName}"/></td>
                    <td><c:out value="${reg.emailAddress}"/></td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

<content tag='bottom'>
    <script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>

</content>
