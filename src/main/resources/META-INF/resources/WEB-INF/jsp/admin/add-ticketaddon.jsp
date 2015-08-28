<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div class="jumbotron call" style="margin-bottom:50px">
    <div class="container">
        <div id="banner">
            <h1><strong>Add/Edit Ticket Add On</strong></h1>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-8 col-md-offset-2">

        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="ticketAddOn" enctype="multipart/form-data">
            <form:hidden path="event.id"/>

            <spring:bind path="ticketAddOn.label">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="label" class="col-lg-2 control-label">Label*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="label" id="label" maxlength="255" tabindex="1"/>
                    <form:errors path="label" cssClass="fieldError"/>
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

            <spring:bind path="ticketGroup.maxAvailableTickets">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <label for="maxAvailableTickets" class="col-lg-2 control-label">Maximum Available</label>
            <div class="col-lg-10">
                <form:input cssClass="form-control" path="maxAvailableTickets" id="maxAvailableTickets" tabindex="5" maxlength="255"/>
                <form:errors path="maxAvailableTickets" cssClass="fieldError"/>
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

            $('#addAddOnButton').click(function (event) {
                size = $('#ticketAddOns').children().size();
                $('#ticketAddOns').append('<div class="form-group$"> <label for="ticketAddOns[' + size + '].label" class="col-lg-2 control-label">Label</label> <div class="col-lg-10"> <input class="form-control" name="ticketAddOns[' + size + '].label" id="ticketAddOns[' + size + '].label" tabindex="5" maxlength="255"/></div><label for="ticketAddOns[' + size + '].price" class="col-lg-2 control-label">Price</label><div class="col-lg-10"><input class="form-control" name="ticketAddOns[' + size + '].price" id="ticketAddOns[' + size + '].price" maxlength="255"/></div><label for="ticketAddOns[' + size + '].maxAvailableTickets" class="col-lg-2 control-label">Max Available</label><div class="col-lg-10"> <input class="form-control" name="ticketAddOns[' + size + '].maxAvailableTickets" id="ticketAddOns[' + size + '].maxAvailableTickets" tabindex="5" maxlength="255"/></div></div>');

            })

        });
    </script>
</content>
