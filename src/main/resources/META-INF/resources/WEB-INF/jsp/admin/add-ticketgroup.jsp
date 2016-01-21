<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
    <div class="container">
        <div id="banner">
            <h1><strong>Add/Edit Ticket Group</strong></h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">

        <spring:bind path="ticketGroup.*">
            <c:if test="${not empty status.errorMessages}">
                <div class="alert alert-danger fade in"
                     ><a href="#" data-dismiss="alert" class="close">&times;</a>
                    <c:forEach var="error" items="${status.errorMessages}"
                               ><c:out value="${error}" escapeXml="false"/><br/>
                    </c:forEach>
                </div>
            </c:if>
        </spring:bind>
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="ticketGroup" enctype="multipart/form-data">
            <form:hidden path="event.id"/>

            <spring:bind path="ticketGroup.label">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="label" class="col-lg-2 control-label">Label*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="label" id="label" maxlength="255" tabindex="1"/>
                    <form:errors path="label" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="ticketGroup.description">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="label" class="col-lg-2 control-label">Description *</label>
                <div class="col-lg-10">
                    <form:textarea cssClass="form-control" path="description" id="description" maxlength="10000" rows="10" tabindex="1"/>
                    <form:errors path="description" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="ticketGroup.openDate">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="openDate" class="col-lg-2 control-label">Open Date(inclusive)*</label>
                <div class="col-lg-10">
                    <fmt:formatDate value="${ticketGroup.openDate}" var="openDateString" type="both" pattern="MM/dd/yyyy" />
                    <form:input cssClass="form-control" path="openDate" id="openDate" value="${openDateString}" tabindex="3" maxlength="255"/>
                    <form:errors path="openDate" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="ticketGroup.closeDate">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="closeDate" class="col-lg-2 control-label">Close Date(exclusive)*</label>
                <div class="col-lg-10">
                    <fmt:formatDate value="${ticketGroup.closeDate}" var="closeDateString" type="both" pattern="MM/dd/yyyy" />
                    <form:input cssClass="form-control" path="closeDate" id="closeDate" value="${closeDateString}" tabindex="4" maxlength="255"/>
                    <form:errors path="closeDate" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="ticketGroup.price">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="price" class="col-lg-2 control-label">Price*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="price" id="price" tabindex="5" maxlength="255"/>
                    <form:errors path="price" cssClass="fieldError"/>
                </div>
            </div>


            <spring:bind path="ticketGroup.registerFormUrl">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="registerFormUrl" class="col-lg-2 control-label">Register Form URL*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="registerFormUrl" id="registerFormUrl" tabindex="5" maxlength="255"/>
                    <form:errors path="registerFormUrl" cssClass="fieldError"/>
                </div>
            </div>

            <fieldset>
                <div id="couponCodes">
                    <c:forEach items="${ticketGroup.couponCodes}" var="couponCode"  varStatus="status">
                        <spring:bind path="couponCodes[${status.index}]">
                            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                        </spring:bind>

                        <div class="form-group${errorClass}">
                            <label for="couponCodes[${status.index}].code" class="col-lg-2 control-label">Coupon Code</label>
                            <div class="col-lg-10">
                                <form:input cssClass="form-control" path="couponCodes[${status.index}].code" id="couponCodes[${status.index}].code" tabindex="5" maxlength="255"/>
                                <form:errors path="couponCodes[${status.index}].code" cssClass="fieldError"/>
                            </div>
                            <label for="couponCodes[${status.index}].price" class="col-lg-2 control-label">Price</label>
                            <div class="col-lg-10">
                                <form:input cssClass="form-control" path="couponCodes[${status.index}].price" id="couponCodes[${status.index}].price" tabindex="5" maxlength="255"/>
                                <form:errors path="couponCodes[${status.index}].price" cssClass="fieldError"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <button type="button" class="btn btn-default" lang="save" id="addCouponButton">Add Coupon</button>
            </fieldset>


            <spring:bind path="ticketGroup.minPurchase">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="minPurchase" class="col-lg-2 control-label">Min Purchase*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="minPurchase" id="minPurchase" tabindex="5" maxlength="255" />
                    <form:errors path="minPurchase" cssClass="fieldError"/>
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


            -$("#openDate").datepicker();
            -
                    -$("#closeDate").datepicker();
            -
                    $('textarea').maxlength({
                alwaysShow: true
            });

            $('#addCouponButton').click(function (event) {
                size = $('#couponCodes').children().size();
                $('#couponCodes').append('<div class="form-group$"> <label for="couponCodes[' + size + '].code" class="col-lg-2 control-label">Coupon Code</label> <div class="col-lg-10"> <input class="form-control" name="couponCodes[' + size + '].code" id="couponCodes[' + size + '].code" tabindex="5" maxlength="255"/></div><label for="couponCodes[' + size + '].price" class="col-lg-2 control-label">New Price</label><div class="col-lg-10"><input class="form-control" name="couponCodes[' + size + '].price" id="couponCodes[' + size + '].price" maxlength="255"/></div></div>');
            })


        });
    </script>
</content>
