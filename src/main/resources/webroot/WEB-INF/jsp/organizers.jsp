<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<title>${contextEvent.title} | Organizers</title>

<section class="container-fluid speakers" >
        <h1 class="featured-header">Organizers</h1>
        <div class="row">
            <c:forEach items="${organizerList.organizers}" var="organizer" varStatus="status">

                <div class="col-sm-6 col-md-4 col-lg-3">
                    <div class="thumbnail" id="${organizer.firstName}_${organizer.lastName}">
                        <img class="img-responsive" src="${organizer.pictureSerialized}" alt="${organizer.firstName} ${speaker.lastName}">
                        <div class="caption">
                            <h3><a href="${ctx}/s/organizers/${organizer.id}">${organizer.firstName} ${organizer.lastName}</a></h3>
                            <p>${organizer.company}&nbsp;</p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
</section>


