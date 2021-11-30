<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 22.11.2021
  Time: 00:48
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
    <title>Quiz-result</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <style>
        <%@ include file="/css/testing.css" %>
    </style>
</head>
<body>
<div class="result-nav">
    <h1>${requestScope.subject.name}/${requestScope.quiz.name}</h1>
</div>
<div class="resultPage">
    <c:if test="${requestScope.quizResultList.get(0).result<70}">
        <div class="resultBox">
            <div class="negative _2D8K8"></div>
            <h3 class="h3-result"><fmt:message key="msg.dont-pass-quiz"/>
                <br><fmt:message key="msg.your-result-is"/> ${requestScope.quizResultList.get(0).result}%</h3>
        </div>
    </c:if>
    <c:if test="${requestScope.quizResultList.get(0).result>=70}">
        <div class="resultBox">
            <div class="positive _2D8K8"></div>
            <h3 class="h3-result"><fmt:message key="msg.pass-quiz"/>
                <br><fmt:message key="msg.your-result-is"/> ${requestScope.quizResultList.get(0).result}%</h3>
        </div>
    </c:if>
    <div class="container">
        <h2><fmt:message key="msg.previous-results"/> </h2>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><fmt:message key="msg.result"/> </th>
                <th><fmt:message key="msg.create-time"/> </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.quizResultList}" var="result" varStatus="loop">
                <tr>
                    <td>${result.result} %</td>
                   <td><date:PrettyDateTimeFormatTag value="${result.create_time}" locale="${sessionScope.lang}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>
