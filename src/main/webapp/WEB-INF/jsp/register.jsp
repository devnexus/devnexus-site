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

    <div class="row">
        <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">
            <table>
                <thead>
                <td>Label</td>
                <td>Description</td>
                <td>Start Date</td>
                <td>End Date</td>
                <td>Cost per Ticket</td>
                <td>Ticket Quantity</td>
                </thead>

                <c:forEach items="${eventSignup.groups}" var="group" varStatus="status">
                    <tr>
                        <td><c:out value="${group.label}"></c:out></td>
                        <td><c:out value="${group.description}"></c:out></td>
                        <td><c:out value="${group.openDate}"></c:out></td>
                        <td><c:out value="${group.closeDate}"></c:out></td>
                        <td><c:out value="${group.price}"></c:out></td>
                        <td><form:select path="ticketCount">
                                <form:option value="0" >0</form:option>
                                <form:option value="1" >1</form:option>
                                <form:option value="2" >2</form:option>
                                <form:option value="3" >3</form:option>
                                <form:option value="4" >4</form:option>
                                <form:option value="5" >5</form:option>
                                <form:option value="6" >6</form:option>
                                <form:option value="7" >7</form:option>
                                <form:option value="8" >8</form:option>
                                <form:option value="9" >9</form:option>
                                <form:option value="10" >10</form:option>
                                <form:option value="11" >11</form:option>
                                <form:option value="12" >12</form:option>
                                <form:option value="13" >13</form:option>
                                <form:option value="14" >14</form:option>
                                <form:option value="15" >15</form:option>
                                <form:option value="16" >16</form:option>
                            </form:select></td>
                    </tr>
                </c:forEach>
            </table>
        </form:form>


    </div>

</div>
<jsp:include page="includes/questions.jsp"/>

<content tag='bottom'>
    <script type="text/javascript">
        $(document).ready(function () {

            var s = skrollr.init({
                forceHeight: false,
                mobileCheck: function () {
                    return false;
                }
            });
        });
    </script>
</content>
