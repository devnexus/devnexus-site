<%@include file="/WEB-INF/jsp/includes/taglibs-decorators.jsp"%>
<!DOCTYPE html>
<html>
    <head>
    <title>DevNexus</title>

    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=yes"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">

  <!-- For iPhone 4 -->
  <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx}/apple-touch-icon.png">
  <!-- For iPad 1-->
  <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx}/apple-touch-icon.png">
  <!-- For iPhone 3G, iPod Touch and Android -->
  <link rel="apple-touch-icon-precomposed" href="${ctx}/apple-touch-icon-precomposed.png">
  <!-- For Nokia -->
  <link rel="shortcut icon" href="${ctx}/apple-touch-icon.png">
  <!-- For everything else -->
  <link rel="shortcut icon" href="${ctx}/favicon.ico">

  <!--iOS. Delete if not required -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
  <link rel="apple-touch-startup-image" href="${ctx}/img/splash.png">

  <!--Microsoft. Delete if not required -->
  <meta http-equiv="cleartype" content="on">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <link rel="stylesheet" href="${ctx}/css/mobile-min.css" />
    <link rel="stylesheet" href="${ctx}/css/rateit/rateit.css" media="screen, projection" />
    <script src="${ctx}/js/lib/jquery-1.7.0.js"></script>
    <script src="${ctx}/js/mobile.js"></script>
    <sitemesh:write property='head'/>
</head>
<body>
    <sitemesh:write property='body'/>
    <sitemesh:write property='page.bottom'/>
</body>
</html>
