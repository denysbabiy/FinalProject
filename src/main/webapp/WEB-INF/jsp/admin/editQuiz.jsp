<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 14.11.2021
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Title</title>
    <style>
        <%@ include file="/css/home.css" %>
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=updateQuiz&subjectId=${requestScope.subjectId}&quizId=${requestScope.quiz.id}" class="login-form" method="post">
    <h2 class="navigation-h3" style="padding-left: 75px"><fmt:message key="msg.edit-quiz"/> </h2>
    <div class="row">
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label"><fmt:message key="msg.quiz-name"/> </label>
                <input type="text" name="name" class="form-control" value="${requestScope.quiz.name}" required>
            </div>
            <div class="login-form">
                <label  class="form-label"><fmt:message key="msg.quiz-complexity"/> </label>
                <select name="complexity" class="form-select" required >
                    <option disabled>Open this select menu</option>
                    <option <c:if test="${requestScope.quiz.complexity==1}">selected</c:if> style="background-color: rgba(35,225,50,0.5)" value="1"><fmt:message key="msg.easy"/> </option>
                    <option <c:if test="${requestScope.quiz.complexity==2}">selected</c:if> style="background-color: rgba(224,112,40,0.5)" value="2"><fmt:message key="msg.medium"/> </option>
                    <option <c:if test="${requestScope.quiz.complexity==3}">selected</c:if> style="background-color: rgba(246,39,39,0.5)" value="3"><fmt:message key="msg.hard"/> </option>
                </select>
            </div>
            <div class="login-form">
                <label class="form-label"><fmt:message key="msg.quiz-duration"/> </label>
                <input class="form-control" type="text" pattern="\d{1,3}" name="time" value="${requestScope.quiz.time}" required>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="msg.edit"/>
        </button>
    </div>

</form>

</body>
</html>
