<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><decorator:title default="DevNexus 2011 Atlanta" /></title>


    <decorator:head/>


    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

    <meta name="author"      content="Gunnar Hillert" />
    <meta name="keywords"    content="AJUG, Java, Conference, Atlanta, 2009, 2010, 2011, March" />
    <meta name="description" content="The professional developer conference of the Atlanta Java Users Group" />

    <link rel="icon"          href="<c:url value='/favicon.ico'/>"  type="image/x-icon" />
    <link rel="shortcut icon" href="<c:url value='/favicon.ico'/>"  type="image/x-icon" />

    <jwr:style src="/bundles/screen.css" media="screen, projection"/>
    <jwr:style src="/bundles/print.css"  media="print"/>
    <jwr:style src="/bundles/ie.css"     media="screen, projection"/>

    <jwr:script src="/bundles/lib.js"/>

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

</head>

<body id="eventwax" class="ajug devcon-2009">

<div class="container">
    <div id="header" class="span-24 last">
        <p style="margin-top: 5px; text-align: center;"><img src="${ctx}/images/logo_devnexus_600x150.png" alt="DevNexus Logo"/></p>
    </div>
    <div class="span-24 last menubar">
        <ul id="menu">

                        <c:url var="homeUrl"          value="/"/>
                        <c:url var="speakersUrl"      value="/s/speakers"/>
                        <c:url var="presentationsUrl" value="/s/presentations"/>
                        <c:url var="scheduleUrl"      value="/s/schedule"/>
                        <c:url var="organizersUrl"    value="/s/organizers"/>

                        <li><a href="${homeUrl}"><span>Home</span></a></li>
                        <li><a href="${speakersUrl}"><span>Speakers</span></a></li>
                        <li><a href="${presentationsUrl}"><span>Presentations</span></a></li>
                        <li><a href="${scheduleUrl}"><span>Schedule</span></a></li>
                        <li class="mega">
                            <h2>
                              <a href="#">Past Conferences...</a>
                            </h2>
                            <div style="width: 450px; z-index: 20000;">
                                <table style="width: 400px;">
                                    <c:forEach items="${eventsForMenu}" var="event">
                                        <tr>
	                                       <td><c:out value="${event.title}"/></td>
	                                       <td><a href="<c:url value='/s/${event.eventKey}/speakers'/>">Speakers</a></td>
	                                       <td><a href="<c:url value='/s/${event.eventKey}/presentations'/>">Presentations</a></td>
	                                    </tr>
	                                </c:forEach>
                                </table>
                            </div>
                        </li>
                        <li><a href="${organizersUrl}"><span>Your Organizers</span></a></li>
                        <li><a href="http://ajug.eventwax.com/devnexus-2011/register" style="color: #F7941E"><span>SIGN UP!</span></a></li>
                        <li><a class="icon-facebook" href="http://www.facebook.com/devnexus">&nbsp;<span>&nbsp;</span></a></li>
                        <li><a class="icon-twitter"  href="http://twitter.com/devnexus">&nbsp;<span>&nbsp;</span></a></li>
                        <li style="margin-right: 0;"><a href="${currentUrl}?site_preference=mobile">&nbsp;<span>Mobile</span></a></li>
                    </ul>
    </div>
    <div id="content" class="span-22 prepend-1 append-1 prepend-top last">
								<security:authorize ifAnyGranted="ADMIN">
									<div style="text-align: right" class="span-22 append-bottom last">You are logged in as
									   <security:authentication property="principal.firstName"/> <security:authentication property="principal.lastName"/> (<security:authentication property="principal.email"/>) |
									   <a href="<c:url value='/s/logout'/>" >Logout</a>
									   <a href="<c:url value='/s/admin/index'/>">Admin Area</a>
									</div>
								</security:authorize>
				                <c:if test="${not empty message}">
				                    <div id="message" class="${message.type} span-22 last">${message.text}</div>
				                </c:if>
                                <decorator:body />
    </div>
    <div id="footer" class="span-24 last">
        &copy; 2008-2011 Atlanta Java Users Group (AJUG), powered by <a href="http://code.google.com/p/devnexus/">Ting</a> <spring:message code="ting.build.version"/>.<spring:message code="ting.build.number"/>
    </div>
</div>



    <script type="text/javascript">

    $(function() {
        $( "button, input:submit").button();
    });

    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
    </script>
    <script type="text/javascript">
    try {
    var pageTracker = _gat._getTracker("UA-177507-7");
    pageTracker._trackPageview();
    } catch(err) {}</script>
</body>
</html>
