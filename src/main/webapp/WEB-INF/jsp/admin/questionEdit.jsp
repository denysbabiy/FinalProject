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
        var answer = ${requestScope.question.answers.size()};

        function add_fields() {
            answer++;
            var objTo = document.getElementById('answer_fileds')
            var divtest = document.createElement("div");
            divtest.innerHTML = '<div id="' + answer + '"><div class="label">Answer ' + answer + ':</div><div class="content"><span>Text: <input class="form-control" type="text" name="answer-text" value="" /><button type="button" onclick="delete_fields(' + answer + ')">Delete</button>' +
                '</span><div class="form-check"> <input class="form-check-input" type="checkbox" value="1" name="is-correct"><input type="hidden" value="0" name="is-correct"> <label class="form-check-label" >Correct </label> </div></div></div>';

            objTo.appendChild(divtest)
        }

        function delete_fields(answerFieldsId) {
            answer--;
            var div = document.getElementById(answerFieldsId);
            div.parentNode.removeChild(div);
        }

        function delete_answer(id) {


        }
    </script>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller?command=updateQuestion&quizId=${requestScope.question.quizId}&questionId=${requestScope.question.id}"
      class="login-form" method="post" id="update">
    <div class="row">
        <div class="col-md-5">
            <div class="login-form">
                <label class="form-label">Question text:</label>
                <input type="text" name="question-text" value="${requestScope.question.question}" class="form-control">
            </div>

            <input type="button" id="more_fields" onclick="add_fields();" value="Add More"/>


            <div id="answer_fileds" class="login-form">
                <div>
                    <div class='label'>Answer 1:</div>
                    <input hidden name="answerId" value="${requestScope.question.answers.get(0).id}">
                    <div class="content">
                        <span>Text: <input class="form-control" type="text" name="answer-text"
                                           value="${requestScope.question.answers.get(0).answer}"/></span>
                        <div class="form-check">
                            <input class="form-check-input"
                                   <c:if test="${requestScope.question.answers.get(0).isCorrect==1}">checked</c:if>
                                   type="checkbox" disabled >
                            <input type="hidden" value="1" name="is-correct">
                            <input type="hidden" value="0" name="is-correct">
                            <label class="form-check-label">
                                Correct
                            </label>
                        </div>
                    </div>
                    <c:if test="${requestScope.question.answers.size()>1}">
                        <c:forEach items="${requestScope.question.answers}" var="answer" varStatus="loop" begin="1">

                            <div class='label'>Answer ${loop.index+1}:</div>
                            <input hidden name="answerId" value="${answer.id}">
                            <div class="content">
                            <span>Text: <input class="form-control" type="text" name="answer-text"
                                               value="${answer.answer}"/></span>
                                <div class="form-check">
                                    <input class="form-check-input"
                                           <c:if test="${answer.isCorrect==1}">checked</c:if> type="checkbox" value="1"
                                           name="is-correct">
                                    <input type="hidden" value="0" name="is-correct">
                                    <form>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/controller?command=deleteAnswer&answerId=${answer.id}&questionId=${requestScope.question.id}"
                                          method="post" id="delete${answer.id}">
                                        <button type="submit" form="delete${answer.id}">Delete answer</button>
                                    </form>

                                    <label class="form-check-label">
                                        Correct
                                    </label>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </div>


        </div>
    </div>


</form>
<div class="row justify-content-center">
    <button type="submit" class="btn btn-primary" form="update">
        Update
    </button>
</div>


</body>
</html>
