<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 14.11.2021
  Time: 20:15
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
    <title>Title</title>
    <style>
        <%@ include file="/css/home.css" %>
    </style>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type='text/javascript'>
        var answer = 1;
        function add_fields() {
            answer++;
            var objTo = document.getElementById('answer_fileds')
            var divtest = document.createElement("div");
            divtest.innerHTML = '<div class="label">Answer ' + answer +':</div><div class="content"><span>Text: <input class="form-control" type="text" name="answer-text" value="" />' +
                '</span><div class="form-check"> <input class="form-check-input" type="checkbox" value="1" name="is-correct"><input type="hidden" value="0" name="is-correct"> <label class="form-check-label" >Correct </label> </div></div>';

            objTo.appendChild(divtest)
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=addNewQuestion&quizId=${requestScope.quizId}" class="login-form" method="post">
    <div class="row">
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label">Question text:</label>
                <input type="text" name="question-text" class="form-control">
            </div>

            <input type="button" id="more_fields" onclick="add_fields();" value="Add More" />

            <div id="answer_fileds" class="login-form">
                <div>
                    <div class='label'>Answer 1:</div>
                    <div class="content">
                        <span>Text: <input class="form-control" type="text"  name="answer-text" value=""/></span>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="1" name="is-correct">
                            <input type="hidden" value="0" name="is-correct">
                            <label class="form-check-label">
                                Correct
                            </label>
                        </div>
                    </div>
                </div>
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
