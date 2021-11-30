<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 23.11.2021
  Time: 20:57
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
    <title>My Profile</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <style>
        <%@ include file="/css/my-profile-style.css" %>
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Fonts -->
    <link rel="dns-prefetch" href="https://fonts.gstatic.com">

</head>
<body>
<div class="col-lg">
    <div class="personal-info">
        <div class="personal-photo" style="background-color: rgb(236, 212, 0);">
            <span><svg class="svg-icon" viewBox="0 0 20 20">
                <path fill="none"
                      d="M10,10.9c2.373,0,4.303-1.932,4.303-4.306c0-2.372-1.93-4.302-4.303-4.302S5.696,4.223,5.696,6.594C5.696,8.969,7.627,10.9,10,10.9z M10,3.331c1.801,0,3.266,1.463,3.266,3.263c0,1.802-1.465,3.267-3.266,3.267c-1.8,0-3.265-1.465-3.265-3.267C6.735,4.794,8.2,3.331,10,3.331z"></path>
                <path fill="none"
                      d="M10,12.503c-4.418,0-7.878,2.058-7.878,4.685c0,0.288,0.231,0.52,0.52,0.52c0.287,0,0.519-0.231,0.519-0.52c0-1.976,3.132-3.646,6.84-3.646c3.707,0,6.838,1.671,6.838,3.646c0,0.288,0.234,0.52,0.521,0.52s0.52-0.231,0.52-0.52C17.879,14.561,14.418,12.503,10,12.503z"></path>
            </svg></span>
        </div>
        <div class="personal-name">
            <span>${sessionScope.user.name} ${sessionScope.user.surname}</span>
        </div>
        <br>
        <div class="personal-email">
            <svg role="img" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
                 style="display: inline-block; color: inherit; fill: inherit; height: 24px; width: 24px; user-select: none;">
                <g>
                    <path fill="currentColor" transform="translate(-232 -328)"
                          d="M254 332h-20v16h20v-16zm-2 2l-8 5-8-5h16zm0 12h-16v-10l8 5 8-5v10z"></path>
                </g>
            </svg>
            <span>${sessionScope.user.email}</span>
        </div>
        <div class="personal-email">
            <svg class="svg-icon" viewBox="0 0 20 20">
                <path fill="none"
                      d="M10,10.9c2.373,0,4.303-1.932,4.303-4.306c0-2.372-1.93-4.302-4.303-4.302S5.696,4.223,5.696,6.594C5.696,8.969,7.627,10.9,10,10.9z M10,3.331c1.801,0,3.266,1.463,3.266,3.263c0,1.802-1.465,3.267-3.266,3.267c-1.8,0-3.265-1.465-3.265-3.267C6.735,4.794,8.2,3.331,10,3.331z"></path>
                <path fill="none"
                      d="M10,12.503c-4.418,0-7.878,2.058-7.878,4.685c0,0.288,0.231,0.52,0.52,0.52c0.287,0,0.519-0.231,0.519-0.52c0-1.976,3.132-3.646,6.84-3.646c3.707,0,6.838,1.671,6.838,3.646c0,0.288,0.234,0.52,0.521,0.52s0.52-0.231,0.52-0.52C17.879,14.561,14.418,12.503,10,12.503z"></path>
            </svg>
            <span>${sessionScope.user.login}</span>
        </div>
        <div class="personal-email">
            <a href="${pageContext.request.contextPath}/controller?command=showEditPersonalInformationForm"><fmt:message key="msg.edit-personal-information"/> </a>

        </div>
        <div class="personal-email">
            <a href="${pageContext.request.contextPath}/controller?command=showEditPassForm"><fmt:message key="msg.change-pass"/> </a>
        </div>


    </div>
    <div class="social-box">
        <div class="container">
            <div class="row">
                <div class="col-lg">
                    <section class="statistic-section">
                        <h2 class="h2-label">
                            <fmt:message key="msg.statistic"/>
                        </h2>
                    </section>

                    <div class="row">
                        <c:forEach items="${requestScope.quizResultBests}" var="result">
                            <c:forEach items="${requestScope.quizList}" var="quiz">
                                <c:if test="${result.quizId==quiz.id}">
                                    <div class="col-lg-4 col-xs-12 text-center">
                                        <div class="box">
                                            <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                                            <div class="box-title">
                                                <h3>${quiz.name}</h3>
                                            </div>
                                            <div class="box-text">
                                                <span><fmt:message key="msg.best-result"/> ${result.result}%</span>
                                                <br>
                                                <span><fmt:message key="msg.create-time"/> </span>
                                                <br>
                                                <span><date:PrettyDateTimeFormatTag value="${result.create_time}"
                                                                                    locale="${lang}"/></span>
                                            </div>
                                            <div class="box-btn">
                                                <a href="${pageContext.request.contextPath}/controller?command=showStatistic&quizId=${quiz.id}"><fmt:message key="msg.statistic"/> </a>
                                                <br>
                                                <a href="${pageContext.request.contextPath}/controller?command=startQuiz&quizId=${quiz.id}"><fmt:message key="msg.try-again"/> </a>
                                            </div>

                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
