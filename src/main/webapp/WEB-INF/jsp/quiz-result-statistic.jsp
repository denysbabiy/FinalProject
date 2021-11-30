<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 24.11.2021
  Time: 02:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="date" uri="/WEB-INF/tld/pretty-date-time-tag.tld" %>
<jsp:include page="header.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Statistic</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <style>
        <%@ include file="/css/testing.css" %>
    </style>
</head>
<body>
<div class="container">
    <h2><fmt:message key="msg.statistic"/> </h2>
    <table class="table table-hover">
        <thead>
        <tr>
            <th><fmt:message key="msg.result"/></th>
            <th><fmt:message key="msg.create-time"/> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.quizResults}" var="result" varStatus="loop">
            <tr>
                <td>${result.result} %</td>
                <td><date:PrettyDateTimeFormatTag value="${result.create_time}" locale="${sessionScope.lang}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
