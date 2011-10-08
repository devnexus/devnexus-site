<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">
    <h2>Add Speaker</h2>
    <form:form id="form" method="post" modelAttribute="speaker" cssClass="cleanform" enctype="multipart/form-data">
        <div class="required">
            <label for="firstName">First Name</label>
            <form:input  path="firstName" id="firstName" maxlength="255" tabindex="2" cssStyle="width: 300px;"/>
            <form:errors path="firstName" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="lastName">Last Name</label>
            <form:input  path="lastName" id="lastName" maxlength="255" tabindex="3" cssStyle="width: 300px;"/>
            <form:errors path="lastName" cssClass="fieldError"/>
        </div>
        <c:if test="${speaker.picture != null}">
           <img src="${ctx}/s/speaker/${speaker.id}.jpg"/>
        </c:if>
        <div class="required">
            <label for="picture">Picture</label>
            <form:input  path="picture" id="picture" maxlength="255" tabindex="3" cssStyle="width: 300px;"/>
            <form:errors path="picture" cssClass="fieldError"/>
        </div>
        <div class="required">
            <label for="pictureFile">Picture</label>
            <input id="pictureFile" type="file" name="pictureFile" />
        </div>
        <div class="required">
            <label for="bio">Bio</label>
            <form:textarea  path="bio" id="bio" tabindex="5" cssStyle="width: 300px;"/>
            <form:errors path="bio" cssClass="fieldError"/>
        </div>
        <div class="submit">
            <input type="submit" class="button" name="save"   value="Add"/>
            <input type="submit" class="button" name="cancel" value="Cancel"/>
        </div>
    </form:form>
</div>
