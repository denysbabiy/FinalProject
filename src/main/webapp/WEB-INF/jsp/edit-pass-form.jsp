<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 24.11.2021
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">
    <style>
        <%@ include file="/css/style.css" %>
    </style>
</head>
<body>
<main class="my-form">
    <div class="cotainer">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header"><fmt:message key="msg.change-pass"/> </div>
                    <div class="card-body">
                        <form name="my-form"
                              action="${pageContext.request.contextPath}/controller?command=editPass" method="post">
                            <div class="form-group row">
                                <label for="old_pass" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.enter-old-pass"/> </label>
                                <div class="col-md-6">
                                    <input type="password" id="old_pass" class="form-control" name="old-pass" required autofocus>
                                    <c:if test="${incorrectPassword==true}">
                                        <label style="color: red"><fmt:message key="msg.incorrect-pass"/> </label>
                                    </c:if>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="new-pass" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.enter-new-pass"/> </label>
                                <div class="col-md-6">
                                    <input type="password" id="new-pass" class="form-control" name="new-pass" required>
                                    <c:if test="${invalidPassword==true}">
                                        <label style="color: red"><fmt:message key="msg.invalid-pass"/> </label>
                                    </c:if>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="new-pass-rep" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.repeat-new-pass"/> </label>
                                <div class="col-md-6">
                                    <input type="password" id="new-pass-rep" class="form-control" name="new-pass-rep" required>
                                    <c:if test="${incorrectPassRep==true}">
                                        <label style="color: red"><fmt:message key="msg.pass-not-match"/> </label>
                                    </c:if>
                                    <c:if test="${invalidData==true}">
                                        <label style="color: red"><fmt:message key="msg.invalid-data"/> </label>
                                    </c:if>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="msg.change"/>
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</main>

</body>
</html>
