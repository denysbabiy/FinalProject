<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 17.11.2021
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="language"/>
<html>
<head>
    <title>Add question</title>
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
        var answer = 0;

        function add_fields() {
            answer++;
            var objTo = document.getElementById('answer_fileds')
            var divtest = document.createElement("div");
            divtest.innerHTML = '<div id="' + answer + '"> <div class="label"><fmt:message key="msg.answer"/> </div><div class="content"><span>Text: <input class="form-control" type="text" name="answer-text" value="" required /> <button type="button" onclick="delete_fiels(' + answer + ')"><fmt:message key="msg.delete"/> </button>' +
                '</span><div class="form-check"> <input class="form-check-input" type="checkbox" value="1" name="is-correct"><input type="hidden" value="0" name="is-correct"> <label class="form-check-label" ><fmt:message key="msg.correct"/> </label> </div></div></div>';

            objTo.appendChild(divtest)
        }

        function delete_fiels(answerFieldsId) {
            answer--;
            var div = document.getElementById(answerFieldsId);
            div.parentNode.removeChild(div);
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller?command=addNewQuestion&quizId=${requestScope.quizId}"
      class="login-form" method="post">
    <div class="row">
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label"><fmt:message key="msg.question-text"/> </label>
                <input type="text" name="question-text" class="form-control" required>
            </div>

            <input type="button" id="more_fields" onclick="add_fields();" value="<fmt:message key="msg.add-answer"/> "/>

            <div id="answer_fileds" class="login-form">
                <div>
                    <div class='label'><fmt:message key="msg.answer"/> </div>
                    <div class="content">
                        <span><fmt:message key="msg.text"/>:<input class="form-control" type="text" name="answer-text" required
                                           value=""/></span>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" checked disabled>
                            <input type="hidden" value="1" name="is-correct">
                            <input type="hidden" value="0" name="is-correct">
                            <label class="form-check-label">
                                <fmt:message key="msg.correct"/>
                            </label>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="row justify-content-center">
        <button type="submit" class="btn btn-primary">
            <fmt:message key="msg.add"/>
        </button>
    </div>

</form>
</body>
</html>

