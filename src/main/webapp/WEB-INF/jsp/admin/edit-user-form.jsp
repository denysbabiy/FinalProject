<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 25.11.2021
  Time: 13:53
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
    <title>Edit user</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">


    <!-- Bootstrap CSS -->
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
                    <div class="card-header"><fmt:message key="msg.edit-user"/> (id - ${requestScope.user.id})</div>
                    <div class="card-body">
                        <form name="my-form"
                              action="${pageContext.request.contextPath}/controller?command=editUser" method="post">
                            <div class="form-group row">
                                <input type="hidden" name="userId" value="${requestScope.user.id}">
                                <label for="full_name" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.name"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="full_name" value="${requestScope.user.name}" class="form-control" name="name" required autofocus>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="full_name" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.surname"/></label>
                                <div class="col-md-6">
                                    <input type="text" id="sur_name" value="${requestScope.user.surname}" class="form-control" name="surname" required>
                                </div>
                            </div>

                            <div class="form-group row">
                                <label for="email_address" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.e-mail"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="email_address" value="${requestScope.user.email}" class="form-control" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required>
                                    <c:if test="${emialAlreadyExist==true}">
                                        <label style="color: red"><fmt:message key="msg.user-with-email-exist"/> </label>
                                    </c:if>
                                </div>
                            </div>


                            <div class="form-group row">
                                <label for="user_login" class="col-md-4 col-form-label text-md-right"><fmt:message key="msg.login-lable"/> </label>
                                <div class="col-md-6">
                                    <input type="text" id="user_login" value="${requestScope.user.login}" class="form-control" name="login" required>
                                    <c:if test="${loginAlreadyExist==true}">
                                        <label style="color: red"><fmt:message key="msg.user-with-login-exist"/> </label>
                                    </c:if>
                                    <c:if test="${invalidData==true}">
                                        <label style="color: red"><fmt:message key="msg.invalid-data"/> </label>
                                    </c:if>
                                </div>
                            </div>

                            <div class="col-md-6 offset-md-4">
                                <button type="submit" class="btn btn-primary">
                                    <fmt:message key="msg.edit"/>
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
