<%@include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title><decorator:title default="DevNexus 2011 Atlanta" /></title>
    <link rel="apple-touch-icon" href="${ctx}/images/mobile/iphone-icon-57x57.png" />

    <link rel="stylesheet" href="http://code.jquery.com/mobile/1.0a2/jquery.mobile-1.0a2.min.css" />
    <script src="http://code.jquery.com/jquery-1.4.4.min.js"></script><script type="text/javascript" src="http://code.jquery.com/mobile/1.0a2/jquery.mobile-1.0a2.min.js"></script>
</head>
<body>
<div data-role="page" data-theme="b">
            <header data-role="header">
                <h1>DevNexus 2011</h1>
            </header>
    <div data-role="content">
        <decorator:body />
    </div>
            <footer data-role="footer">
                <h4>&copy; 2011 AJUG</h4>
            </footer>
</div>
</body>
</html>
