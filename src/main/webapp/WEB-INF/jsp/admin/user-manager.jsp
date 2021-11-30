<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 25.11.2021
  Time: 02:24
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
    <title>User Manager</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <style>
        <%@ include file="/css/user-manager-style.css" %>
    </style>
</head>
<body>
<div class="container">
    <h2><fmt:message key="msg.user-manager"/> </h2>
    <div class="row">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col"><fmt:message key="msg.login-lable"/> </th>
                <th scope="col"><fmt:message key="msg.name"/> </th>
                <th scope="col"><fmt:message key="msg.surname"/></th>
                <th scope="col"><fmt:message key="msg.e-mail"/></th>
                <th scope="col"><fmt:message key="msg.role"/></th>
                <th scope="col"><fmt:message key="msg.blocked"/> </th>
                <th scope="col"><fmt:message key="msg.actions"/> </th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${requestScope.userList}" var="user">
                <tr>
                    <th scope="row">${user.id}</th>
                    <td>${user.login}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td><c:if test="${user.isAdmin==1}"><fmt:message key="msg.ADMIN"/> </c:if><c:if test="${user.isAdmin==0}"><fmt:message key="msg.STUDENT"/></c:if></td>
                    <td><c:if test="${user.isBlocked==1}"><fmt:message key="msg.YES"/></c:if><c:if test="${user.isBlocked==0}"><fmt:message key="msg.NO"/></c:if></td>
                    <c:if test="${user.isAdmin==0}">
                        <td style="display: flex;justify-content: space-between">
                            <c:if test="${user.isBlocked==0}">
                                <form action="${pageContext.request.contextPath}/controller?command=blockUser&userId=${user.id}"
                                      method="post">
                                    <button type="submit" class="btn btn-primary"><fmt:message key="msg.block"/></button>
                                </form>
                            </c:if>
                            <c:if test="${user.isBlocked==1}">
                                <form action="${pageContext.request.contextPath}/controller?command=unblockUser&userId=${user.id}"
                                      method="post">
                                    <button type="submit" class="btn btn-primary"><fmt:message key="msg.unblock"/></button>
                                </form>
                            </c:if>
                            <a style="display: block; margin-top: 0; margin-block-end: 1em"
                               href="${pageContext.request.contextPath}/controller?command=showEditUserForm&userId=${user.id}"
                               class="btn btn-success"><fmt:message key="msg.edit"/></a>
                            <form action="${pageContext.request.contextPath}/controller?command=deleteUser&userId=${user.id}"
                                  method="post">
                                <button type="submit" class="btn btn-danger"><fmt:message key="msg.delete"/></button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>

            </tbody>

        </table>
    </div>
</div>

</body>
</html>
