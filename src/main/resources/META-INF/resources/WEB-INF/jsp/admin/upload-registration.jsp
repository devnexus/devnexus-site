<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp" %>

<head>
    <title>${contextEvent.title} |  DevNexus 2017</title>
</head>
<body>
    <section class="container-fluid register" id="admin-group-register" >


        <h1 class="featured-header">
            UPLOAD REGISTRATION
        </h1>
        <section>

            <div class="row">
                <form:form id="form" class="form-horizontal" role="form" method="post" modelAttribute="registerForm" enctype="multipart/form-data">

                    <form:errors path="*" cssClass="fieldError"/>
                    
                    <div class="form-group">
			<label for="registrationFile" class="col-lg-2 control-label">Registration File*</label>
			<div class="col-lg-10">
				<input id="registrationFile" type="file" cssClass="form-control" name="registrationFile" />
				<span class="help-block">The maximum permissible file-size is ${maxFileSize}.</span>
			</div>
                    </div>
                    
                    <div class="form-group">
			<label for="overrideRegistration" class="col-lg-2 control-label">Replace Registrations</label>
                        <form:select cssClass="" path="overrideRegistration" id="overrideRegistration" >
                            <form:option value="true">Yes</form:option>
                            <form:option value="false">No</form:option>                
                        </form:select>
			<div class="col-lg-10">                        
				<span class="help-block">If the field "Registration Reference" in the uploaded file matches an existing registration, replace the old values with these values.</span>
			</div>
                    </div>
                        
                    <div class="row hidden-xs">
                        <div class="text-center">
                            <button class="btn customize" onclick="form.submit()">upload group form</button>
                        </div>
                    </div>
                    
                </form:form>
            </div>
        </section>
    </section>
    <br/>
    <br/>
</body>