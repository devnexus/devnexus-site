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
                    <th>Name</th><th>Start Date</th><th>End Date</th><th>Price</th>
                </tr>
            </thead>
            <tbody>
                
                <c:if test="${empty eventSignup.groups}">
                    <tr>
                        <td colspan="4">Nothing to see here</td>
                    </tr>
                </c:if>
                <c:if test="${not empty eventSignup.groups}">
                    <c:forEach items="${eventSignup.groups}" var="purchaseGroup" >
                        <tr>
                            <td colspan="4"><c:out value="${purchaseGroup.label}"/></td>
                        </tr>
                        <c:forEach items="${purchaseGroup.items}" var="purchaseItem">
                            <tr>
                                <td><c:out value="${purchaseItem.label}"/></td>
                                <td><c:out value="${purchaseItem.openDate}"/></td>
                                <td><c:out value="${purchaseItem.closeDate}"/></td>
                                <td><c:out value="${purchaseItem.value}"/></td>
                            </tr>
                        </c:forEach>
                    </c:forEach>    
                </c:if>

            </tbody>
        </table>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/purchaseItem" role="button">Add Event Signup Item</a>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/purchaseGroup" role="button">Add Event Signup Item Group</a>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
    </div>
</div>

