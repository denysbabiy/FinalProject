package com.epam.finalproject.web.command;

import com.epam.finalproject.Path;
import com.epam.finalproject.db.entity.*;
import com.epam.finalproject.exception.ServiceException;
import com.epam.finalproject.service.AnswerService;
import com.epam.finalproject.service.QuestionService;
import com.epam.finalproject.service.QuizResultService;
import com.epam.finalproject.service.QuizService;
import com.epam.finalproject.web.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


public class SubmitQuizCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.removeAttribute("timeStart");
        session.removeAttribute("timeEnd");
        session.removeAttribute("timerId");
        double score = 0;
        double correct = 0;
        Quiz quiz = QuizService.getInstance().getQuizById(Integer.parseInt(request.getParameter("quizId")));

        List<List<Answer>> userAnswers = new ArrayList<>();
        List<Question> questionList = QuestionService.getInstance().getAllQuestionsByQuizId(quiz.getId());

        for (int i = 0; i < questionList.size(); i++) {
            if(request.getParameterValues("question-"+i+"-answer")!=null){
                String[] ids = request.getParameterValues("question-"+i+"-answer");
                List<Answer> answers = new ArrayList<>();
                for (String id : ids) {
                   answers.add(AnswerService.getInstance().getAnswerById(Integer.parseInt(id)));
                }
                userAnswers.add(answers);
            }else {
                userAnswers.add(null);
            }
        }

        for (List<Answer> userAnswer : userAnswers) {
            if (userAnswer == null) {
                continue;
            }
            List<Answer> questionRightAnswers = AnswerService.getInstance().getAllCorrectAnswersByQuestionId(userAnswer.get(0).getQuestionId());
            if (userAnswer.equals(questionRightAnswers)) {
                correct++;
            }
        }

        score = correct * 100. / questionList.size();

        QuizResult quizResult = new QuizResult();
        quizResult.setResult(round(score, 2));
        quizResult.setQuizId(quiz.getId());
        quizResult.setUserId(((User) session.getAttribute("user")).getId());
        QuizResultService.getInstance().insertNewQuizResult(quizResult);
        router.setPage(Path.COMMAND_SHOW_QUIZ_RESULT + "&quizId=" + quiz.getId());
        router.setType(Router.TransactionType.REDIRECT);

        return router;

    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
