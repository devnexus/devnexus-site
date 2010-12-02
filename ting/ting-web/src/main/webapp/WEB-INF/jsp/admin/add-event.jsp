<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">

<div class="quote"><span>What the community says:</span> "great networking opportunity, excellent technical knowledge transfer"</div>

    <h2>Add/Edit Event</h2>
    <form:form id="form" method="post" modelAttribute="eventForm" cssClass="cleanform">
        <form:hidden  path="event.id" id="eventKey"/>
	    <div class="required">
	        <label for="eventKey">Event Key*</label>
	        <form:input  path="event.eventKey" id="eventKey" maxlength="25" tabindex="1"/>
	        <form:errors path="event.eventKey" cssClass="fieldError"/>
	    </div>
        <div class="required">
            <label for="title">Title</label>
            <form:input  path="event.title" id="title" maxlength="255" tabindex="2"/>
            <form:errors path="event.title" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="speakers">Speakers</label>
            <form:select multiple="multiple" path="event.speakers" id="speakers" tabindex="3" size="10" cssStyle="width: 200px;">
                <c:forEach items="${speakers}" var="s">
                    <form:option value="${s.id}" label="${s.lastName}, ${s.firstName}"/>
                </c:forEach>
            </form:select>
            <form:errors path="event.speakers" cssClass="fieldError"/>
        </div>
	    <div class="submit">
	        <input type="submit" class="button" name="save"   value="Save"/>
	        <input type="submit" class="button" name="cancel" value="Cancel"/>

	        <c:if test="${not empty eventForm.event.id}">
	            <input type="submit" class="button" name="delete" value="Delete"/>
	        </c:if>
	    </div>
    </form:form>

</div>