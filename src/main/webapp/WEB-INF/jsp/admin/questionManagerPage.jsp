<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 14.11.2021
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<html>
<head>
    <title>Title</title>
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
    <h2 class="navigation-h3" style="padding-left: 75px">Question Manager</h2>
    <div class="container">
        <div class="row">
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box">
                        <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                        <div class="box-title">
                            <h3>Add new question</h3>
                        </div>
                        <div class="box-text">
                            <span>Lorem ipsum dolor sit amet, id quo eruditi eloquentiam. Assum decore te sed. Elitr scripta ocurreret qui ad.</span>
                        </div>
                        <div class="box-btn">
                            <a href="${pageContext.request.contextPath}/controller?command=showQuestionEditForm&quizId=${quizId}">Add</a>
                        </div>
                    </div>
                </div>
            <c:forEach items="${requestScope.questionList}" var="question" varStatus="loop" >
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box">
                        <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                        <div class="box-title">
                            <h3>${loop.index}<br>${question.question}</h3>
                        </div>
                        <div class="box-btn">
                            <a href="${pageContext.request.contextPath}/controller?command=showQuestionEditForm&questionId=${question.id}">Edit</a>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </div>
    </div>
</div>


</body>
</html>
