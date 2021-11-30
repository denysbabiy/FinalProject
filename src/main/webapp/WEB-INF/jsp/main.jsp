<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 08.11.2021
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>

<html>
<head>
    <title><fmt:message key="msg.home"/></title>

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
        <h2><fmt:message key="msg.choose-subject"/> </h2>
        <div class="row">
            <c:if test="${sessionScope.user.isAdmin==1}">
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box">
                        <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                        <div class="box-title">
                            <h3><fmt:message key="msg.add-new-subject"/></h3>
                        </div>
                        <div class="box-text">
                            <span><fmt:message key="msg.add-new-subject-description"/></span>
                        </div>
                        <div class="box-btn">
                            <a href="${pageContext.request.contextPath}/controller?command=showSubjectEditForm"><fmt:message key="msg.add"/></a>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:forEach items="${requestScope.subjectList}" var="subject">
                <div class="col-lg-4 col-xs-12 text-center">
                    <div class="box">
                        <i class="fa fa-behance fa-3x" aria-hidden="true"></i>
                        <div class="box-title">
                            <h3>${subject.name}</h3>
                        </div>
                        <div class="box-text">
                            <span>${subject.description}</span>
                        </div>
                            <div class="box-btn" style="display: flex; justify-content: space-around;">
                                <a href="${pageContext.request.contextPath}/controller?command=showQuizzes&subjectId=${subject.id}"><fmt:message key="msg.select"/> </a>

                                    <c:if test="${sessionScope.user.isAdmin==1}">
                                        <a href="${pageContext.request.contextPath}/controller?command=showSubjectEditForm&subjectId=${subject.id}"><fmt:message key="msg.edit"/></a>
                                        <a href="${pageContext.request.contextPath}/controller?command=deleteSubject&subjectId=${subject.id}"><fmt:message key="msg.delete"/></a>
                                    </c:if>
                            </div>
                    </div>
                </div>

            </c:forEach>

        </div>
    </div>
</div>
</body>
</html>
