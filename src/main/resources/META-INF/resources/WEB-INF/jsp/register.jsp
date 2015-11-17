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
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">

            <h4>Purchaser Information - Required - </h4>
            <br/>

            <spring:bind path="contactName">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactName" class="col-lg-2 control-label">Contact Name*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="contactName" id="contactName" maxlength="255"/>
                    <form:errors path="contactName" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="contactEmailAddress">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactEmailAddress" class="col-lg-2 control-label">Email Address*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="contactEmailAddress" id="contactEmailAddress" maxlength="255"/>
                    <form:errors path="contactEmailAddress" cssClass="fieldError"/>
                </div>
            </div>

            <spring:bind path="contactPhoneNumber">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactPhoneNumber" class="col-lg-2 control-label">Phone Number*</label>
                <div class="col-lg-10">
                    <form:input cssClass="form-control" path="contactPhoneNumber" id="contactPhoneNumber" maxlength="255"/>
                    <form:errors path="contactPhoneNumber" cssClass="fieldError"/>
                </div>
            </div>
            <div class="col-lg-offset-2 col-lg-10">
                <table class="table table-striped">

                    <c:forEach items="${signupRegisterView.groups}" var="ticketGroup" varStatus="ticketGroupStatus">
                        <form:hidden path="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketGroupId" id="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketGroupId" />
                                     <tr>
                                         <td>
                                             <h4><c:out value="${ticketGroup.label}"/> </h4><br/>
                                             <c:out value="${ticketGroup.descriptionAsHtml}" escapeXml="false"/>
                                         </td>
                                         <td style="width: 300px">
                                             <div class="form-group">
                                                 <label for="ticketCost" class="col-lg-5 control-label">Cost per Ticket </label>
                                                 <input type="hidden" id="ticket-cost-${ticketGroup.id}" class="ticket-cost" value="<c:out value="${ticketGroup.price}"/>" disabled />
                                                 <div class="col-lg-6"><input value="<c:out value="${ticketGroup.formattedPrice}"/>" Class="form-control" disabled /></div>
                                             </div>
                                             <spring:bind path="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketCount">
                                                 <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                                             </spring:bind>
                                             <div class="form-group${errorClass}">
                                                 <label for="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketCount" class="col-lg-5 control-label">Number of Tickets: </label>
                                                 <div class="col-lg-6">
                                                     <form:select cssClass="form-control ticket-count" path="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketCount" id="ticket-count" tabindex="10">
                                                         <form:option value="0" label="0" />
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
                                                         <form:option value="17" label="17" />
                                                         <form:option value="18" label="18" />
                                                         <form:option value="19" label="19" />
                                                         <form:option value="20" label="20" />
                                                         <form:option value="21" label="21" />
                                                         <form:option value="22" label="22" />
                                                         <form:option value="23" label="23" />
                                                         <form:option value="24" label="24" />
                                                         <form:option value="25" label="25" />
                                                         <form:option value="26" label="26" />
                                                         <form:option value="27" label="27" />
                                                         <form:option value="28" label="28" />
                                                         <form:option value="29" label="29" />
                                                         <form:option value="30" label="30" />
                                                         <form:option value="-1" label="Need more? Email info@ajug.org" />
                                                     </form:select>
                                                     <form:errors path="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketCount" cssClass="fieldError" />
                                                 </div>
                                             </div>

                                             <spring:bind path="ticketGroupRegistrations[${ticketGroupStatus.index}].couponCode">
                                                 <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                                             </spring:bind>
                                             <div class="form-group${errorClass}" id="coupon-code-group">
                                                 <label for="ticketGroupRegistrations[${ticketGroupStatus.index}].coupon-code" class="col-lg-5 control-label">Coupon Code: </label>
                                                 <div class="col-lg-6">
                                                     <input type="hidden" value="<c:out value="${registerForm.ticketGroupRegistrations[ticketGroupStatus.index].ticketGroupId}" />"/>
                                                     <form:input cssClass="form-control coupon-code"  path="ticketGroupRegistrations[${ticketGroupStatus.index}].couponCode" id="ticketGroupRegistrations[${ticketGroupStatus.index}].coupon-code" tabindex="10"/>
                                                     <form:errors path="ticketGroupRegistrations[${ticketGroupStatus.index}].couponCode" cssClass="fieldError" />
                                                 </div>
                                             </div>

                                         </td>
                                     </tr>
                    </c:forEach>
                </table>
            </div>


            <div class="form-group">
                <label for="total-cost" class="col-lg-2 control-label">Final Price: </label>
                <div class="col-lg-10">
                    <input class="form-control" id="total-cost" name="total-cost"  disabled="disabled" value="$0.00"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-4 col-lg-10">
                    <button type="submit" class="btn btn-default right btn-primary" lang="save" tabindex="19">Continue to Complete Registration</button>
                </div>
            </div>

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
            $('.ticket-count').change(function () {
                var cost = 0;
                $('.ticket-count').each(function(i, el){cost += (el.selectedIndex * $('.ticket-cost')[i].value);});
                $('#total-cost').val("$" + cost + ".00");
            });


            $('.coupon-code').change(function (el) {
                couponCode = $(el.target).val();
                ticketGroupId = $(el.target).siblings().val();
                console.log(couponCode + " " + ticketGroupId);
                jQuery.ajax("/s/lookupCouponCode/" + ticketGroupId + "/" + couponCode, {
                    success: function (data) {
                        $('#ticket-cost-' + ticketGroupId).val(data);
                        var cost = 0;
                        $('.ticket-count').each(function(i, el){cost += (el.selectedIndex * $('.ticket-cost')[i].value);});
                        $('#total-cost').val("$" + cost + ".00");
                    }
                });
            });



            $('.coupon-code').each(function (i, el) {
                couponCode = $(el).val();
                ticketGroupId = $(el).siblings().val();
                console.log(couponCode + " " + ticketGroupId);
                jQuery.ajax("/s/lookupCouponCode/" + ticketGroupId + "/" + couponCode, {
                    success: function (data) {
                        $('#ticket-cost-' + ticketGroupId).val(data);
                        var cost = 0;
                        $('.ticket-count').each(function(i, el){cost += (el.selectedIndex * $('.ticket-cost')[i].value);});
                        $('#total-cost').val("$" + cost + ".00");
                    }
                });
            });
        });
    </script>
</content>
