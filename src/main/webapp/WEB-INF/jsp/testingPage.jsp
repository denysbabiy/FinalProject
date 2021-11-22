<%--
  Created by IntelliJ IDEA.
  User: denys
  Date: 19.11.2021
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp"/>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <style>
        <%@ include file="/css/testing.css" %>
    </style>

</head>
<body>
<form method="post"
      action="${pageContext.request.contextPath}/controller?command=submitQuiz&quizId=${requestScope.questionList.get(0).quizId}">
    <div class="row">
        <span id="remainingTime"
              style="position: fixed;top:90px;left: 80%;font-size: 23px;background: rgba(255,0,77,0.38);border-radius: 5px;padding: 10px;box-shadow: 2px -2px 6px 0px;">
        </span>
        <div class="col-md-4">

            <c:forEach items="${requestScope.questionList}" var="question" varStatus="q">
                <div class="box">
                    <h3 class="navigation-h3">${question.question}</h3>
                    <input type="hidden" name="questionId" value="${question.id}">

                    <c:forEach items="${question.answers}" var="answer" varStatus="a">
                        <div class="funkyradio">
                            <div class="funkyradio-primary">
                                <input type="checkbox" name="question-${q.index}-answer"
                                       value="${answer.id}" id="question-${q.index}-answers-${a.index}"/>
                                <label for="question-${q.index}-answers-${a.index}">${answer.answer} </label>
                            </div>
                        </div>

                    </c:forEach>
                </div>


            </c:forEach>

            <div class="panel-footer">
                <div class="row">
                    <button class="btn btn-outline-success btn-lg" type=submit id="submit">
                        <span>Submit</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <script>
        //it calls fuction after specific time again and again
        var x = window.setInterval(timerFunction, 1000);

        function timerFunction() {

            if (new Date(${sessionScope.timeEnd.time}).getTime() < new Date().getTime()) {
                clearInterval(x);
                document.getElementById("remainingTime").innerHTML = "00 : 00";
                document.getElementById("submit").click();
            }
            document.getElementById("remainingTime").innerHTML = millisToMinutesAndSeconds(new Date(${sessionScope.timeEnd.time}).getTime() - new Date().getTime());

        }

        function millisToMinutesAndSeconds(millis) {
            var minutes = Math.floor(millis / 60000);
            var seconds = ((millis % 60000) / 1000).toFixed(0);
            return minutes + ":" + (seconds < 10 ? '0' : '') + seconds;
        }
    </script>
</form>

</body>
</html>
