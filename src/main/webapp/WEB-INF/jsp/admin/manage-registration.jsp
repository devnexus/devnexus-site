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
       
        
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/registration/ticketGroup" role="button">Add Event Ticket Type</a>
        <a class="btn btn-default" href="${ctx}${baseSiteUrl}/admin/${event.eventKey}/index" role="button">Main Menu</a>
    </div>
</div>

