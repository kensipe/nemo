<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nemo Search Service</title>
</head>
<body>

<form action="<%= request.getContextPath()%>/search/index.html" method="GET">
    <input type="text" name="q"/>
    <input type="submit">
</form>

<div id="SearchPage" class="SearchPage">

    <table>
        <tr>
            <td style="text-align: right">query:</td>
            <td>${searchForm.q}</td>
        </tr>
        <tr>
            <td style="text-align: right">strategy:</td>
            <td>${searchForm.strategy}</td>
        </tr>
        <tr>
            <td style="text-align: right">strategy list:</td>
            <td>
                <c:forEach var="strat" items="${searchForm.strategyList}">
                    <a href="/search/index.html?q=${searchForm.q}&strategy=${strat}">${strat}</a>
                </c:forEach>
            </td>
        </tr>
    </table>

    <h3> search results </h3>
    <table>
        <c:forEach var="searchLink" items="${searchForm.links}">
            <tr>
                <td style="text-align: right"> ${searchLink.source}:</td>
                <td><a href="${searchLink.url}"> ${searchLink.title}</a></td>
            </tr>
        </c:forEach>
    </table>


</div>
</body>
<content tag="contentHeader">

    <div id="contentHdr">
        <h1 class="UtilityPageHdr">Nemo Search Service</h1>
    </div>
    <!-- /contentHdr -->
</content>


</html>