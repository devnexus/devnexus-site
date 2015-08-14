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
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">
            <spring:bind path="ticketGroup">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="skill-level" class="col-lg-2 control-label">Registration Type*</label>
                <div class="col-lg-10">
                    <form:select cssClass="form-control" path="ticketGroup" id="ticket-group" tabindex="10">
                        <form:option value="" label="Please Select a Registration Type" />
                        <form:options items="${signupRegisterView.groups}" itemLabel="label" itemValue="id"/>
                    </form:select>
                    <form:errors path="ticketGroup" cssClass="fieldError" />
                </div>
            </div>
            <span id="signup_details" >
                <div class="form-group">
                    <label for="ticket-cost" class="col-lg-2 control-label">Price per Ticket: </label>
                    <div class="col-lg-10">
                        <input class="form-control" id="ticket-cost" name="ticket-cost"  disabled="disabled" value="$0.00"/>
                    </div>
                </div>
                <spring:bind path="ticketCount">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                </spring:bind>
                <div class="form-group${errorClass}">
                    <label for="ticketCount" class="col-lg-2 control-label">Number of Tickets: *</label>
                    <div class="col-lg-10">
                        <form:select cssClass="form-control" path="ticketCount" id="ticket-count" tabindex="10">
                            <form:option value="1" label="1" />
                            <form:option value="2" label="2" />
                            <form:option value="3" label="3" />
                            <form:option value="4" label="4" />
                            <form:option value="5" label="5" />
                            <form:option value="6" label="6" />
                            <form:option value="7" label="7" />
                            <form:option value="8" label="8" />
                            <form:option value="9" label="9" />
                            <form:option value="10" label="10" />
                            <form:option value="11" label="11" />
                            <form:option value="12" label="12" />
                            <form:option value="13" label="13" />
                            <form:option value="14" label="14" />
                            <form:option value="15" label="15" />
                            <form:option value="16" label="16" />
                            <form:option value="-1" label="Need more? Email info@ajug.org" />
                        </form:select>
                        <form:errors path="ticketCount" cssClass="fieldError" />
                    </div>
                </div>

                <spring:bind path="couponCode">
                    <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                </spring:bind>
                <div class="form-group${errorClass}" id="coupon-code-group">
                    <label for="coupon-code" class="col-lg-2 control-label">Coupon Code: </label>
                    <div class="col-lg-10">
                        <form:input cssClass="form-control" path="couponCode" id="coupon-code" tabindex="10"/>
                        <form:errors path="couponCode" cssClass="fieldError" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="total-cost" class="col-lg-2 control-label">Final Price: </label>
                    <div class="col-lg-10">
                        <input class="form-control" id="total-cost" name="total-cost"  disabled="disabled" value="$0.00"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-offset-4 col-lg-10">
                        <button type="submit" class="btn btn-default right btn-primary" lang="save" tabindex="19">Enter Ticket Information</button>
                    </div>
                </div>
            </span>

        </form:form>


    </div>

</div>
<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
    <script type="text/javascript">
        $(document).ready(function () {

            var price = "";

            var s = skrollr.init({
                forceHeight: false,
                mobileCheck: function () {
                    return false;
                }
            });
            $('#ticket-count').change(function () {
                var signupId = $('#ticket-group option:selected').val();
                $('#total-cost').val($('#ticket-count option:selected').val() * price);
            });

            $('#ticket-group').change(function () {
                updateInfo()
            });
            
            $('#coupon-code').change(function() {
                jQuery.ajax("/s/lookupCouponCode/" + $('#ticket-group option:selected').val() + "/" + $('#coupon-code').val(), {
                    success : function(data) { price = data;$('#total-cost').val($('#ticket-count option:selected').val() * price);}
                });
            });

            function updateInfo() {
                var signupId = $('#ticket-group option:selected').val();
                if (signupId) {
                    var signupData = data['group_' + signupId];
                    $('#coupon-code-group').show();
                    $('#coupon-code').val("");
                    price = signupData.price;
                    $('#ticket-cost').val(signupData.price);
                    $('#ticket-count option').each(function (index, option) {
                        option = $(option);
                        if (option.val() >= signupData.minPurchase) {
                            option.show();
                        } else {
                            option.hide();
                        }
                    });
                    $('#ticket-count option:selected').prop('selected', false);
                    $('#ticket-count option').filter(function () {
                        return $(this).val() == signupData.minPurchase;
                    }).prop('selected', true);

                    $('#total-cost').val($('#ticket-count option:selected').val() * signupData.price);

                    $('#signup_details').show();
                } else {
                    $('#signup_details').hide();
                }
            }

            var data = {
        <c:forEach items="${signupRegisterView.groups}" var="group">
                group_<c:out value="${group.id}"/>: {
                                    price: <c:out value="${group.price}"/>,
                                    minPurchase: <c:out value="${group.minPurchase}"/>
                                },
        </c:forEach>
                            };
                            updateInfo();
                        });
    </script>
</content>
