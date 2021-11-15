package com.epam.finalproject.web.command.admin;

import com.epam.finalproject.db.dao.DAOFactory;
import com.epam.finalproject.db.entity.Answer;
import com.epam.finalproject.db.entity.Question;
import com.epam.finalproject.web.Router;
import com.epam.finalproject.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AddNewQuestionCommand extends Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {

        Question question = new Question();
        question.setQuestion(request.getParameter("question-text"));
        question.setQuizId(Integer.parseInt(request.getParameter("quizId")));
        DAOFactory.getInstance().getQuestionDAO().insert(question);
        String[] answers = request.getParameterValues("answer-text");
        String[] is_correct_from_html = request.getParameterValues("is-correct");
        List<Integer> is_correct = filtringIsCorrect(is_correct_from_html);

        for (int i = 0; i < answers.length; i++) {
            Answer answer = new Answer();
            answer.setAnswer(answers[i]);
            answer.setIsCorrect(is_correct.get(i));
            answer.setQuestionId(question.getId());

        }

        Router router = new Router();
        router.setType(Router.TransactionType.REDIRECT);
        return router;
    }

    private List<Integer> filtringIsCorrect(String[] is_correct_from_html) {
        List<Integer> is_correct = new ArrayList<>();
        for (int i = 0; i < is_correct_from_html.length; i++) {
            if(is_correct_from_html[i].equals("1")){
                is_correct.add(Integer.valueOf(is_correct_from_html[i]));
                i++;
            }else {
                is_correct.add(Integer.valueOf(is_correct_from_html[i]));
            }
        }
        return is_correct;
    }
}
