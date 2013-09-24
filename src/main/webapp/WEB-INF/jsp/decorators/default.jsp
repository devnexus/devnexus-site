<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="${ctx}/assets/ico/favicon.png">

        <!-- Bootstrap core CSS -->
        <link href="${ctx}/css/bootstrap.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="${ctx}/css/devnexus.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
        <script src="${ctx}/js/html5shiv.js"></script>
        <script src="${ctx}/js/respond.min.js"></script>
        <![endif]-->
        <sitemesh:write property='head'/>
    </head>


    <body>

        <c:url var="homeUrl" value="${baseSiteUrl}/index"/>
        <c:url var="speakersUrl" value="${baseSiteUrl}/speakers"/>
        <c:url var="presentationsUrl" value="${baseSiteUrl}/presentations"/>
        <c:url var="scheduleUrl" value="${baseSiteUrl}/schedule"/>
        <c:url var="organizersUrl" value="${baseSiteUrl}/organizers"/>
        <c:url var="travelUrl" value="${baseSiteUrl}/travel"/>

        <div id="logo" class="navbar-left">
            <a href="${homeUrl}"><img src="${ctx}/images/devnexus-logo.jpg" border="0"></a>
        </div>
        <!-- end logo -->
        <div id="topnav" class="navbar-right navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">About</a></li>
                <li><a href="${speakersUrl}">Speakers</a></li>
                <li><a href="${presentationsUrl}">Presentations</a></li>
                <li><a href="${scheduleUrl}">Schedule</a></li>
                <li class="mega">
                    <a href="#" data-target="#past-conferences-collapse" class="accordion-toggle" data-toggle="collapse">Past Conferences...</a>
                </li>
                <li><a href="https://ajug.eventwax.com/devnexus-2013/register">Register</a></li>
            </ul>
        </div>
        <div style="clear: both"></div>
        <div id="devnex" class="jumbotron">
            <div class="container">
                <div id="banner">
                    <h1 id="gray">DevNexus 2014</h1>

                    <h1 id="white"><c:out default="Atlanta, GA" value="${headerTitle}"/></h1>

                    <h3><c:out default="The professional developer conference." value="${tag}"/></h3>
                </div>
            </div>
        </div>
        <!-- end banner -->
        <sitemesh:write property='body'/>

        <div style="clear:both;"></div>

        <div id="footer" class="footer-inverse">
            <div class="container">
                <div class="row">
                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-3">
                                <h3>Learn More</h3>
                                <ul id="col">
                                    <li><a href="#">About DN</a></li>
                                    <li><a href="#">Organizers</a></li>
                                    <li><a href="#">Past Conferences</a></li>
                                </ul>
                            </div>
                            <div class="col-md-3">
                                <h3>DevNexus 2014</h3>
                                <ul id="col">
                                    <li><a href="#">Schedule</a></li>
                                    <li><a href="#">Speakers</a></li>
                                    <li><a href="#">Presentations</a></li>
                                </ul>
                            </div>
                            <div class="col-md-3">
                                <h3>Attend</h3>
                                <ul id="col">
                                    <li><a href="#">Registration Info</a></li>
                                    <li><a href="#">Travel</a></li>
                                </ul>
                            </div>
                            <div class="col-md-3">
                                <h3>DevNexus 2014</h3>
                                <ul id="col">
                                    <li><a href="#">Facebook</a></li>
                                    <li><a href="#">Twitter</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <img id="logo" src="images/devnexus-logo.jpg" border="0">
                        <p id="copy">&copy; 2008-2013 Atlanta Java Users Group (AJUG)</p>
                    </div>
                </div>

            </div>
        </div>
            <!-- Asynchronous Google Analytics snippet. Change UA-XXXXX-X to be your site's ID.
                 mathiasbynens.be/notes/async-analytics-snippet -->
            <script>
                var _gaq = [
                    ['_setAccount', 'UA-177507-7'],
                    ['_trackPageview']
                ];
                (function (d, t) {
                    var g = d.createElement(t), s = d.getElementsByTagName(t)[0];
                    g.src = ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js';
                    s.parentNode.insertBefore(g, s)
                }(document, 'script'));
            </script>

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${ctx}/js/jquery.js"></script>
        <script src="${ctx}/js/bootstrap.min.js"></script>
    </body>
</html>
