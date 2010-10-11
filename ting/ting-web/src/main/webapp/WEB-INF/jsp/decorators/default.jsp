<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title><decorator:title default="Welcome to the DevNexus 2011 conference in Atlanta" /></title>

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

    <meta name="author"      content="Gunnar Hillert" />
    <meta name="keywords"    content="AJUG, Java, Conference, Atlanta, 2009, 2010, 2011, March" />
    <meta name="description" content="The professional developer conference of the Atlanta Java Users Group" />

    <link rel="icon"          href="<c:url value='/favicon.ico'/>"  type="image/x-icon" />
    <link rel="shortcut icon" href="<c:url value='/favicon.ico'/>"  type="image/x-icon" />

    <jwr:style src="/bundles/all.css" />
    <jwr:style src="/bundles/all-IE.css" />

    <!-- Java Script Imports -->
    <jwr:script src="/bundles/lib.js"/>

    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma"        content="no-cache" />
    <meta http-equiv="Expires"       content="0" />
    <meta http-equiv="content-type"  content="text/html; charset=utf-8" />

</head>

<body id="eventwax" class="ajug devcon-2009">
    <div id="b2">
        <div id="b3">
            <div id="b4">

                <div id="header">
                    <div id="h2">
                    <div id="h3">
                    <div id="h4">
                        <p><img src="${ctx}/images/logo_devnexus_600x150.png" alt="DevNexus Logo" /></p>
                    </div>
                    </div>
                    </div>
                </div>
                <div class="menubar">
                    <ul id="menu">

                        <c:url var="homeUrl"          value="/"/>
                        <c:url var="speakersUrl"      value="/s/speakers"/>
                        <c:url var="presentationsUrl" value="/s/presentations"/>
                        <c:url var="scheduleUrl"      value="/s/schedule"/>
                        <c:url var="organizersUrl"    value="/s/organizers"/>

                        <li><a href="${homeUrl}">Home</a></li>
                        <li><a href="${speakersUrl}">Speakers</a></li>
                        <li><a href="${presentationsUrl}">Presentations</a></li>
                        <li><a href="${scheduleUrl}">Schedule</a></li>
                        <li class="mega">
                            <h2>
                              <a href="#">Past Conferences...</a>
                            </h2>
                            <div>
                            	  <a href="/2009/index.html">Devnexus 2010</a><br/>
                                  <a href="/2009/index.html">Devnexus 2009</a><br/>
                                  <a href="/2006/index.html">Devcon 2006</a><br/>
                                  <a href="/2005/index.html">Devcon 2005</a><br/>
                                  <a href="/2004/index.html">Devcon 2004</a><br/>
                            </div>
                        </li>
                        <li><a href="${organizersUrl}">Your Organizers</a></li>
                        <li><a href="http://ajug.eventwax.com/devnexus-2011/register" style="color: #F7941E">SIGN UP!</a></li>
                    </ul>
                </div>
                <div id="content">
                    <div id="c2">
                        <div id="c3">
                            <div id="c4">
if logged in
                                  <div>
                                   <a href="#">Admin Area</a> |
                                   <a href="#">My Account</a> |
                                   <a href="#">Logout</a>
                                  </div>
end if
                                <decorator:body />
                            </div>
                        </div>
                    </div>
                </div>

                <div id="footer">&nbsp;<!--ads--></div>

                <div id="extraDiv1"><span></span></div>
                <div id="extraDiv2"><span></span></div>
                <div id="extraDiv3"><span></span></div>
                <div id="extraDiv4"><span></span></div>
                <div id="extraDiv5"><span></span></div>
                <div id="extraDiv6"><span></span></div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
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