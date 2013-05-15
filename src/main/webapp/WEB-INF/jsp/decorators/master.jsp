<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title><decorator:title default="Nemo Search"/></title><decorator:head/>


    <!-- Fav and touch icons -->
    <link rel="shortcut icon" href="/images/favicon.ico">

</head>

<body>

<!-- /wrapper -->
<div id="pageBttm">
    <div id="pageBttmContent">
        <p id="pageLegal"><spring:message code="app.copyright"/></p>
        <ul id="pageBttmNav">
            <li id="termsUse"></li>
            <li id="privPol"></li>
        </ul>
    </div>
    <!-- /pageBttmContent -->
</div>
<!-- /pageBttm -->

</body>
</html>