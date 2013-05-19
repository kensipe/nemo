<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>

</head>

<body>


<div id="wrapper">

    <div id="contentWrap">

        <div id="content">

            <decorator:getProperty property="page.contentHeader"/>

            <div id="contentBody">
                <div id="main">
                    <decorator:body/>
                </div>
                <!-- /main -->
                <decorator:getProperty property="page.side-bar"/>
            </div>
            <!-- /contentBody -->

        </div>
        <!-- /content -->
    </div>
    <!-- /contentWrap -->
</div>
<!-- /wrapper -->

</body>
</html>