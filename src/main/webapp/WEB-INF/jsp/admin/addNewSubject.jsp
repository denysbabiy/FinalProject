<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 11.11.2021
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Title</title>
    <style>
        <%@ include file="/css/home.css" %>
    </style>

</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=addNewSubject" class="login-form" method="post">
    <h2 class="navigation-h3" style="padding-left: 75px">Add new subject</h2>
    <div class="row">

        <div class="col-md-5">

            <div class="login-form">
                <label class="form-label">Subject name (English)</label>
                <input type="text" name="name_en" class="form-control">
            </div>
            <div class="login-form">
                <label  class="form-label">Subject description (English)</label>
                <textarea class="form-control" name="description_en" rows="3"></textarea>
            </div>
        </div>
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label">Subject name (Ukraine)</label>
                <input type="text" name="name_ua" class="form-control" >
            </div>
            <div class="login-form">
                <label  class="form-label">Subject description (Ukraine)</label>
                <textarea class="form-control" name="description_ua" rows="3"></textarea>
            </div>
        </div>
    </div>
    <div class="row justify-content-center">
            <button type="submit" class="btn btn-primary">
                ADD
            </button>
    </div>

</form>
</body>
</html>
