<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
    <div class="container">
        <div id="banner">
            <h1><strong>Add/Edit Purchase Group</strong></h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">

        <spring:bind path="purchaseGroup.*">
            <c:if test="${not empty status.errorMessages}">
                <div class="alert alert-danger fade in"
                     ><a href="#" data-dismiss="alert" class="close">&times;</a>
                    <c:forEach var="error" items="${status.errorMessages}"
                               ><c:out value="${error}" escapeXml="false"/><br/>
                    </c:forEach>
                </div>
            </c:if>
        </spring:bind>
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="purchaseGroup" enctype="multipart/form-data">
            <form:hidden path="event.id"/>

            <spring:bind path="purchaseGroup.label">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="firstName" class="col-lg-2 control-label">Label*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="label" id="label" maxlength="255" tabindex="1"/>
                    <form:errors path="label" cssClass="fieldError"/>
                </div>
            </div>



            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-default" lang="save" tabindex="19">Add/Save</button>
                    <button type="submit" class="btn btn-default" lang="delete" tabindex="20">Delete</button>
                    <button type="submit" class="btn btn-default" name="cancel" tabindex="21">Cancel</button>
                </div>
            </div>
        </form:form>
    </div>
</div>

<content tag='bottom'>

    <script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $("input[type='text']:visible:enabled:first", document.forms['cfpForm']).focus();

            $('textarea').maxlength({
                alwaysShow: true
            });

        });
    </script>
</content>
