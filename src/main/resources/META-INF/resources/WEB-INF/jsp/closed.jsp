<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>

<title>${contextEvent.title} | Registration Closed</title>

    <section id="speaker" class="bg-light-gray" >
        <div class="container subsuccess">
            <div class="row" style="margin-top: 50px;">
                <div class="col-md-2 col-md-offset-2">
                    <div class="glyphicon glyphicon-exclamation-sign text-center" aria-hidden="true" style="font-size: 150px;"></div>
                </div>
                <div class="col-md-6 "  >
                    <h3 style="margin-bottom: 2em;">We are sorry, registration is not open.</h3>

                    <p><button id="goBackButton" class="btn btn-default"><span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span> Return to DevNexus.com</button></p>
                </div>
            </div>
        </div>
    </section>

<content tag='bottom'>
    <script src="${ctx}/assets/js/other/bootstrap-maxlength.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#goBackButton').click(function () {
                window.location.href = "https://www.devnexus.com";
            });
        });
    </script>
    <style>
        .footer {
            position: absolute;
            width: 100%;
            bottom: 0px;
        }
    </style>
</content>