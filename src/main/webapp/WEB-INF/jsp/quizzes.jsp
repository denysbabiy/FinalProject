<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 08.11.2021
  Time: 17:06
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
    <title>Home</title>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        <%@ include file="/css/home.css" %>
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">
</head>
<body>


<div class="social-box">
    <div class="container">

        <h3 class="navigation-h3"><fmt:message key="msg.choose-quiz"/></h3>
        <div class="btn-group" style="display: flex;justify-content: flex-end">
            <h4 style="font-family: 'Poppins', sans-serif"><fmt:message key="msg.sort-by"/></h4>
            <a href="${pageContext.request.contextPath}/controller?command=showQuizzes&subjectId=${subjectId}&sortBy=name"
               class="btn btn-primary" style="background-color: #5bc0de"><fmt:message key="msg.sort-name"/> </a>
            <a href="${pageContext.request.contextPath}/controller?command=showQuizzes&subjectId=${subjectId}&sortBy=complexity"
               class="btn btn-primary" style="background-color: #5bc0de"><fmt:message key="msg.sort-complexity"/> </a>
            <a href="${pageContext.request.contextPath}/controller?command=showQuizzes&subjectId=${subjectId}&sortBy=popular"
               class="btn btn-primary" style="background-color: #5bc0de"><fmt:message key="msg.sort-popular"/> </a>
        </div>
        <div class="row">
            <c:if test="${sessionScope.user.isAdmin==1}">
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box">
                        <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                        <div class="box-title">
                            <h3><fmt:message key="msg.add-new-quiz"/></h3>
                        </div>
                        <div class="box-text">
                            <span><fmt:message key="msg.add-new-quiz-description"/> </span>
                        </div>
                        <div class="box-btn">
                            <a href="${pageContext.request.contextPath}/controller?command=showQuizEditForm&subjectId=${subjectId}"><fmt:message
                                    key="msg.add"/></a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:forEach items="${requestScope.quizList}" var="quiz">
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box">
                        <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                        <div class="box-title">
                            <h3>${quiz.name}</h3>
                        </div>
                        <div class="box-text">
                            <span><fmt:message key="msg.complexity"/> ${quiz.complexity}/3</span>
                            <br>
                            <span><fmt:message key="msg.time"/> ${quiz.time} <fmt:message key="msg.minutes"/></span>
                        </div>
                        <div class="box-btn" style="display: flex; justify-content: space-around">

                            <a href="${pageContext.request.contextPath}/controller?command=startQuiz&quizId=${quiz.id}"><fmt:message
                                    key="msg.start"/></a>

                            <c:if test="${sessionScope.user.isAdmin==1}">

                                <a href="${pageContext.request.contextPath}/controller?command=showQuizEditForm&quizId=${quiz.id}&subjectId=${subjectId}"><fmt:message
                                        key="msg.edit"/></a>
                                <a href="${pageContext.request.contextPath}/controller?command=deleteQuiz&quizId=${quiz.id}&subjectId=${subjectId}"><fmt:message
                                        key="msg.delete"/></a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>

        <ul class="pagination">
            <c:forEach begin="1" end="${requestScope.numberOfPages}" varStatus="loop">
                <c:if test="${currentPage==loop.index}">
                    <li class="page-item active"><a class="page-link"
                                                     href="${pageContext.request.contextPath}/controller?command=showQuizzes&subjectId=${requestScope.subjectId}&page=${loop.index}&sortBy=${sortBy}">${loop.index}</a>
                    </li>
                </c:if>
                <c:if test="${currentPage!=loop.index}">
                    <li class="page-item"><a class="page-link"
                                              href="${pageContext.request.contextPath}/controller?command=showQuizzes&subjectId=${requestScope.subjectId}&page=${loop.index}&sortBy=${sortBy}">${loop.index}</a>
                    </li>
                </c:if>
            </c:forEach>
        </ul>

    </div>
</div>
</body>
</html>
