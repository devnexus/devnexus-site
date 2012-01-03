<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">
    <h2>Add Speaker</h2>
    <form:form id="form" method="post" modelAttribute="presentation" cssClass="cleanform" enctype="multipart/form-data">
        <div class="required">
            <label for="event">Event</label>
            <form:select path="event" id="event" tabindex="1" cssStyle="width: 300px;">
                <form:option value="" label="Please Select an Event"/>
                <form:options items="${events}" itemValue="id" itemLabel="eventKey"/>
            </form:select>
            <form:errors path="event" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="speaker">Speaker</label>
            <form:select path="speaker.id" id="speaker" tabindex="1" cssStyle="width: 300px;">
                <form:option value="" label="Please Select Speaker"/>
                <c:forEach items="${speakers}" var="s">
            <form:option value="${s.id}" label="${s.lastName}, ${s.firstName}" />
        </c:forEach>
            </form:select>
            <form:errors path="event" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="lastName">Title</label>
            <form:input  path="title" id="title" maxlength="255" tabindex="2" cssStyle="width: 300px;"/>
            <form:errors path="title" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="bio">Description</label>
            <form:textarea  path="description" id="description" tabindex="3" cssStyle="width: 300px;"/>
            <form:errors path="description" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="firstName">Presentation Link</label>
            <form:input  path="presentationLink" id="presentationLink" maxlength="255" tabindex="4" cssStyle="width: 300px;"/>
            <form:errors path="presentationLink" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="uploadedFile">Presentation</label>
            <input id="uploadedFile" type="file" name="uploadedFile" />
        </div>
        <div class="required">
            <label for="picture">Audio Link</label>
            <form:input  path="audioLink" id="audioLink" maxlength="255" tabindex="5" cssStyle="width: 300px;"/>
            <form:errors path="audioLink" cssClass="fieldError"/>
        </div>
        <div class="submit">
            <input type="submit" class="button" name="save"   value="Add"/>
            <input type="submit" class="button" name="cancel" value="Cancel"/>
        </div>
    </form:form>
</div>