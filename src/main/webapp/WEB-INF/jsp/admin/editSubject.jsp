<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 12.11.2021
  Time: 01:12
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
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Edit subject</title>
    <style>
        <%@ include file="/css/home.css" %>
    </style>

</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=updateSubject&subjectId=${subjectEn.id}" class="login-form" method="post">
    <h2 class="navigation-h3" style="padding-left: 75px"><fmt:message key="msg.edit-subject"/> </h2>
    <div class="row">
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label"><fmt:message key="msg.subject-name-english"/> </label>
                <input type="text" name="name_en" class="form-control" value="${subjectEn.name}">
            </div>
            <div class="login-form">
                <label  class="form-label"><fmt:message key="msg.subject-description-english"/></label>
                <textarea class="form-control" name="description_en" rows="3" >${subjectEn.description}</textarea>
            </div>
        </div>
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label"><fmt:message key="msg.subject-name-ukrainian"/></label>
                <input type="text" name="name_ua" class="form-control" value="${subjectUa.name}">
            </div>
            <div class="login-form">
                <label  class="form-label"><fmt:message key="msg.subject-description-ukrainian"/></label>
                <textarea class="form-control" name="description_ua" rows="3">${subjectUa.description}</textarea>
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
