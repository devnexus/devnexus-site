<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>Manage Registration</title>

<!-- intro -->
<section id="about" class="module parallax parallax-3">
    <div class="container header">
        <div class="row centered">
            <div class="col-md-10 col-md-offset-1">
                <div class="top-intro travel">
                    <h4 class="section-white-title decorated"><span>Manage ${event.eventKey} Registration</span></h4>

                </div>
            </div>
        </div>
    </div>
</section>
<!-- /intro -->

<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th ></th><th>Name</th><th/><th>Start Date</th><th>End Date</th><th>Price</th>
                </tr>
            </thead>
            <tbody>

                <c:if test="${empty eventSignup.groups}">
                    <tr>

                        <td colspan="6">Nothing to see here</td>
                    </tr>
                </c:if>
                <c:if test="${not empty eventSignup.groups}">
                    <c:forEach items="${eventSignup.groups}" var="ticketGroup" >
                        <tr>
                            <td><a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/ticketGroup/${ticketGroup.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
                            <td colspan="5"><c:out value="${ticketGroup.label}"/></td>

                            <td><c:out value="${ticketGroup.openDate}"/></td>
                            <td><c:out value="${ticketGroup.closeDate}"/></td>
                            <td><c:out value="${ticketGroup.price}"/></td>
                        </tr>

                    </c:forEach>    
                </c:if>
            </tbody>
        </table>
<<<<<<< HEAD:src/main/resources/META-INF/resources/WEB-INF/jsp/admin/manage-registration.jsp
        <table class="table table-hover">
            <thead>
                <tr>
                    <th ></th><th>Name</th><th>Coupon Code</th><th>Start Date</th><th>End Date</th><th>Price</th>
                </tr>
            </thead>
            <tbody>

                <c:if test="${empty eventSignup.coupons}">
                    <tr>

                        <td colspan="6">Nothing to see here</td>
                    </tr>
                </c:if>
        <c:if test="${not empty eventSignup.coupons}">
            <c:forEach items="${eventSignup.coupons}" var="coupon" >


                <tr>
                    <td><a href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/couponItem/${coupon.id}" class="btn btn-default"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <td ><c:out value="${coupon.label}"/></td>
                    <td><c:out value="${coupon.couponCode}"/></td>
                    <td><c:out value="${coupon.openDate}"/></td>
                    <td><c:out value="${coupon.closeDate}"/></td>
                    <td><c:out value="${coupon.price}"/></td>
                </tr>

            </c:forEach>    
        </c:if>

        </tbody>
        </table>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/purchaseItem" role="button">Add Event Signup Item</a>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/couponItem" role="button">Add Event Coupon Item</a>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/purchaseGroup" role="button">Add Event Signup Item Group</a>
=======
       
        
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/ticketGroup" role="button">Add Event Ticket Type</a>
>>>>>>> Massively changing how registraiton is done.  Much simpler now:src/main/webapp/WEB-INF/jsp/admin/manage-registration.jsp
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
    </div>
</div>

