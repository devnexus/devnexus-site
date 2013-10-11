<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<div id="content" class="span-22 prepend-top last ">

	<h2>Call for Papers 2014!</h2>
	<p>
		Thank you for your interest in DevNexus 2014! We would love to review your
		session proposals for the South-East's largest developer conference. We are
		planning to cover a wide variety of topics around:
	</p>
	<ul>
		<li>Java/JavaEE/Spring</li>
		<li>HTML5 + JavaScript</li>
		<li>Data + Integration</li>
		<li>User Experience</li>
		<li>Alternative Languages on the JVM</li>
		<li>Cloud</li>
		<li>Agile + Tools</li>
		<li>Mobile</li>
	</ul>

	<form:form id="form" method="post" modelAttribute="cfpSubmission" enctype="multipart/form-data"
		cssClass="cleanform">
		<form:errors/>
		<div class="required">
			<label for="firstName">First Name</label>
			<form:hidden path="event.id"/>
			<form:input  path="firstName" id="firstName" maxlength="255" tabindex="1" cssStyle="width: 300px;"/>
			<form:errors path="firstName" cssClass="fieldError"/>
		</div>
		<div class="required">
			<label for="lastName">Last Name</label>
			<form:input  path="lastName" id="lastName" maxlength="255" tabindex="2" cssStyle="width: 300px;"/>
			<form:errors path="lastName" cssClass="fieldError"/>
		</div>
		<p style="clear: left;">Please provide contact some information. We will use primarily email between
		now and the conference. However, on the days of the conference, it is important
		that we can reach you via cell phone, just in case emergencies arise</p>
		<div class="required">
			<label for="email">Email</label>
			<form:input path="email" id="email" tabindex="3" cssStyle="width: 300px;"/>
			<form:errors path="email" cssClass="fieldError"/>
		</div>
		<div class="required">
			<label for="phone">Phone</label>
			<form:input path="phone" id="phone" tabindex="4" cssStyle="width: 300px;"/>
			<form:errors path="phone" cssClass="fieldError"/>
		</div>
		<p style="clear: left;">Please submit a decent sized picture. We will add it to the conference site.</p>
        <div class="required">
            <label for="pictureFile">Picture</label>
            <input id="pictureFile" type="file" name="pictureFile" tabindex="5"/>
        </div>
        <p style="clear: left;"><a href="http://daringfireball.net/projects/markdown/" target="_blank">Markdown</a> is supported for the bio.</p>
		<div class="required">
			<label for="bio">Bio</label>
			<form:textarea  path="bio" id="bio" tabindex="6" cssStyle="width: 300px;"/>
			<form:errors path="bio" cssClass="fieldError"/>
		</div>
		<div class="required" style="margin-top: 1em;">
			<label for="title">Presentation Title</label>
			<form:input path="title" id="title" tabindex="7" cssStyle="width: 300px;"/>
			<form:errors path="title" cssClass="fieldError"/>
		</div>
		<p style="clear: left;"><a href="http://daringfireball.net/projects/markdown/" target="_blank">Markdown</a> is supported for the abstract.</p>
		<div class="required" style="margin-bottom: 1em;">
			<label for="bio">Abstract</label>
			<form:textarea  path="description" id="description" tabindex="8" cssStyle="width: 300px;"/>
			<form:errors path="description" cssClass="fieldError"/>
		</div>
		<p style="clear: left;">For example: Java/JavaEE/Spring, Data, HTML5, Agile, Mobile, Cloud ...</p>
		<div class="required">
			<label for="topic">Topic</label>
			<form:input path="topic" id="topic" tabindex="9" cssStyle="width: 300px;"/>
			<form:errors path="topic" cssClass="fieldError"/>
		</div>
		<p style="clear: left;">Please help us classify your presentation</p>
		<div class="required">
			<label for="skill-level">Skill Level</label>
			<form:select path="skillLevel" id="skill-level" tabindex="10"
				cssStyle="width: 300px;">
				<form:option value="" label="Please Select a Skill Level" />
				<c:forEach items="${skillLevels}" var="s">
					<form:option value="${s.id}" label="${s.name}" />
				</c:forEach>
			</form:select>
			<form:errors path="skillLevel" cssClass="fieldError" />
		</div>
		<div class="required">
			<label for="skill-level">Presentation Type</label>
			<form:select path="presentationType" id="presentation-type"
				tabindex="11" cssStyle="width: 300px;">
				<form:option value="" label="Please Select a Presentation Type" />
				<c:forEach items="${presentationTypes}" var="s">
					<form:option value="${s.id}" label="${s.name}" />
				</c:forEach>
			</form:select>
			<form:errors path="presentationType" cssClass="fieldError" />
		</div>
		<h3>Your social links</h3>
		<div class="optional">
			<label for="twitterId">Twitter Id</label>
			<form:input  path="twitterId" id="twitterId" maxlength="255" tabindex="12" cssStyle="width: 300px;"/>
			<form:errors path="twitterId" cssClass="fieldError"/>
		</div>
		<div class="optional">
			<label for="googlePlusId">Google Plus Id</label>
			<form:input  path="googlePlusId" id="googlePlusId" maxlength="255" tabindex="13" cssStyle="width: 300px;"/>
			<form:errors path="googlePlusId" cssClass="fieldError"/>
		</div>
		<div class="optional">
			<label for="linkedInId">LinkedIn Id</label>
			<form:input  path="linkedInId" id="linkedInId" maxlength="255" tabindex="14" cssStyle="width: 300px;"/>
			<form:errors path="linkedInId" cssClass="fieldError"/>
		</div>
		<h3 style="clear: left;">Miscellaneous</h3>
		<p style="clear: left;">Please let us know your T-shirt size, you we can ensure we have the right size.</p>
 		<div class="required">
			<label for="tshirtSize">T-Shirt Size</label>
			<form:input path="tshirtSize" id="tshirtSize" tabindex="15" cssStyle="width: 300px;"/>
			<form:errors path="tshirtSize" cssClass="fieldError"/>
		</div>
		<div class="required">
			<label for="sessionRecordingApproved">Can we record your session?</label>
			<form:checkbox path="sessionRecordingApproved" id="sessionRecordingApproved" tabindex="16" cssStyle="width: 300px;"/>
			<form:errors path="sessionRecordingApproved" cssClass="fieldError"/>
		</div>

		<p style="clear: left;">
			Please tell us if you have any preferences/requirements for your
			presentation, e.g. if you can only speak on specific days, morning/afternoon
			etc. Also, if you have any comments, please let us know.
		</p>
		<div class="required" style="margin-bottom: 1em;">
			<label for="slotPreference">Slot Preference/Comments</label>
			<form:textarea  path="slotPreference" id="bio" tabindex="17" cssStyle="width: 300px;"/>
			<form:errors path="slotPreference" cssClass="fieldError"/>
		</div>
		<c:if test="${reCaptchaEnabled}">
			<div class="required" style="width: 300px; margin-bottom: 1em; margin-left: auto; margin-right: auto;">
				<c:out value="${reCaptchaHtml}" escapeXml="false"/>
			</div>
		</c:if>
		<div class="submit">
			<input type="submit" class="button" name="save"   value="Add" tabindex="19"/>
			<input type="submit" class="button" name="cancel" value="Cancel"/>
		</div>
	</form:form>
	<content tag='bottom'>

	</content>
</div>
