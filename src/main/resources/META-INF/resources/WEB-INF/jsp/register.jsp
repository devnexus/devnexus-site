<%@page import="com.devnexus.ting.model.ScheduleItemType" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Register</title>


<section class="container-fluid register" >
    <h1 class="featured-header">
        REGISTER
    </h1>

    <div class="row">
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">

            <h4 class="form-group">Purchaser Information </h4>

            <spring:bind path="contactName">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactName" >* Contact Name</label>
                <form:input cssClass="form-control" path="contactName" type="text" id="contactName" maxlength="255"/>
                <form:errors path="contactName" cssClass="fieldError"/>

            </div>

            <spring:bind path="contactEmailAddress">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactEmailAddress" >* Email Address</label>

                <form:input cssClass="form-control" type="email" path="contactEmailAddress" id="contactEmailAddress" maxlength="255"/>
                <form:errors path="contactEmailAddress" cssClass="fieldError"/>

            </div>

            <spring:bind path="contactPhoneNumber">
                <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
            </spring:bind>
            <div class="form-group${errorClass}">
                <label for="contactPhoneNumber" >* Phone Number</label>
                <form:input cssClass="form-control" type="tel" path="contactPhoneNumber" id="contactPhoneNumber" maxlength="255"/>
                <form:errors path="contactPhoneNumber" cssClass="fieldError"/>
            </div>

            <div class="row ticket-selection hidden-xs hidden-sm">
                <div class="col-md-6 ticket-header form-group">
                    Ticket Type
                </div>
                <div class="col-md-2 ticket-header form-group">
                    Unit Price
                </div>
                <div class="col-md-2 ticket-header form-group">
                    Quantity
                </div>
                <div class="col-md-2 ticket-header form-group">
                    Coupon Code
                </div>
            </div>

            <div  class="row ticket-selection visible-xs visible-sm hidden-md">
                <div class="col-xs-12 ticket-header form-group">
                    &nbsp;
                </div>
            </div>
            
            <c:forEach items="${signupRegisterView.groups}" var="ticketGroup" varStatus="ticketGroupStatus">
                <form:hidden path="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketGroupId" id="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketGroupId" />
                <div class="row ticket-selection ">
                    
                    <div class="col-md-6 form-group${errorClass}">
                        <h4><c:out value="${ticketGroup.label}"/> </h4>
                        <c:out value="${ticketGroup.descriptionAsHtml}" escapeXml="false"/>
                    </div>
                    <div class="col-md-2 form-group${errorClass} cost" style="display:table-cell; vertical-align: middle">
                        <h4>&nbsp;</h4>
                        <input type="hidden" id="ticket-cost-${ticketGroup.id}" class="ticket-cost" value="<c:out value="${ticketGroup.price}"/>" disabled />
                        <span class="hidden-md visible-xs visible-sm">Price :</span><c:out value="${ticketGroup.formattedPrice}"/>

                    </div>
                    <div class="col-md-2 col-sm-12  form-group${errorClass}"><h4>&nbsp;</h4>
                        <span class="hidden-md visible-xs visible-sm">Quantity :</span><spring:bind path="ticketGroupRegistrations[${ticketGroupStatus.index}].ticketCount">
                            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                        </spring:bind>
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
                    <div class="col-md-2 form-group${errorClass}">
                        <h4>&nbsp;</h4>
                        <span class="hidden-md visible-xs visible-sm">Coupon Code :</span>
                        <spring:bind path="ticketGroupRegistrations[${ticketGroupStatus.index}].couponCode">
                            <c:set var="errorClass" value="${(not empty status.errorMessage) ? ' has-error' : ''}"/>
                        </spring:bind>
                        <input type="hidden" value="<c:out value="${registerForm.ticketGroupRegistrations[ticketGroupStatus.index].ticketGroupId}" />"/>
                        <form:input cssClass="form-control coupon-code"  path="ticketGroupRegistrations[${ticketGroupStatus.index}].couponCode" id="ticketGroupRegistrations[${ticketGroupStatus.index}].coupon-code" tabindex="10"/>
                        <form:errors path="ticketGroupRegistrations[${ticketGroupStatus.index}].couponCode" cssClass="fieldError" />                        
                    </div>
                </div>
            </c:forEach>


            <div class="form-group total row">

                <span for="total-cost" class="col-md-2 control-label">Order Total: </span>

                <span id="total-cost" name="total-cost"  >$0.00</span>

                <button type="submit" class="btn btn-default right btn-primary" lang="save" tabindex="19">Complete Registration</button>

            </div>

        </form:form> 


    </div>

</section>


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
                $('.ticket-count').each(function (i, el) {
                    cost += (el.selectedIndex * $('.ticket-cost')[i].value);
                });
                $('#total-cost').text("$" + cost + ".00");
            });


            $('.coupon-code').change(function (el) {
                couponCode = $(el.target).val();
                ticketGroupId = $(el.target).prev().val();
                console.log(couponCode + " " + ticketGroupId);
                jQuery.ajax("/s/lookupCouponCode/" + ticketGroupId + "/" + couponCode, {
                    success: function (data) {
                        $('#ticket-cost-' + ticketGroupId).val(data);
                        var cost = 0;
                        $('.ticket-count').each(function (i, el) {
                            cost += (el.selectedIndex * $('.ticket-cost')[i].value);
                        });
                        $('#total-cost').text("$" + cost + ".00");
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
                        $('.ticket-count').each(function (i, el) {
                            cost += (el.selectedIndex * $('.ticket-cost')[i].value);
                        });
                        $('#total-cost').text("$" + cost + ".00");
                    }
                });
            });
        });
    </script>
</content>
