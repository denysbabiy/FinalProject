<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 09.11.2021
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">


    <link rel="icon" href="Favicon.png">

    <!-- Bootstrap CSS -->
    <style>
        <%@ include file="/css/style.css" %>
    </style>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light navbar-laravel">
    <div class="container">
        <a class="navbar-brand" href="#">Quiz</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <div class="dropdown">
                        <button type="submit" class="nav-link"><fmt:message key="msg.language"/></button>
                        <div class="dropdown-content">
                            <a href="?lang=en&${pageContext.request.queryString}">English</a>
                            <a href="?lang=ua&${pageContext.request.queryString}">Українська</a>
                        </div>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=showMainPage"><fmt:message
                            key="msg.home"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=showMyProfile"><fmt:message
                            key="msg.profile"/></a>
                </li>
                <c:if test="${sessionScope.user.isAdmin==1}">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/controller?command=showUserManager"><fmt:message
                                key="msg.user-manager"/></a>
                    </li>
                </c:if>
                <li class="nav-item">
                    <c:if test="${sessionScope.timerId==null}"><a class="nav-link"
                                                                  href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
                            key="msg.logout"/> <b><c:out value="${sessionScope.user.login}"/></b></a>
                    </c:if>

                    <c:if test="${sessionScope.timerId!=null}">
                        <a class="nav-link" data-toggle="modal" data-target="#myModalLogout">
                            <fmt:message
                                    key="msg.logout"/> <b><c:out value="${sessionScope.user.login}"/></b>
                        </a>
                        <div class="modal" id="myModalLogout">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">You already have started quiz!!!</h4>
                                        <button type="button" class="close" data-dismiss="modal">&times;
                                        </button>
                                    </div>

                                    <!-- Modal body -->
                                    <div class="modal-body">
                                        To continue started quiz, press the button.
                                        <br>
                                        If you exit your score will be 0.0% !!!
                                    </div>

                                    <!-- Modal footer -->
                                    <div class="modal-footer">
                                        <a href="${pageContext.request.contextPath}/controller?command=startQuiz&quizId=${sessionScope.timerId}"
                                           class="btn btn-primary" style="color: white">
                                            Continue
                                        </a>
                                        <a href="${pageContext.request.contextPath}/controller?command=logout"
                                           class="btn btn-primary" style="color: white">
                                            Logout
                                        </a>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:if>
                </li>


            </ul>

        </div>
    </div>
</nav>
</body>
</html>
