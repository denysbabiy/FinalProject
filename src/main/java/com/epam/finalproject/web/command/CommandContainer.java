package com.epam.finalproject.web.command;

import com.epam.finalproject.web.command.admin.*;

import java.util.Map;
import java.util.TreeMap;

public class CommandContainer {
    private static Map<String,Command> commands = new TreeMap<>();
    static {
        commands.put("login", new LoginCommand());
        commands.put("showMainPage",new ShowMainPageCommand());
        commands.put("registration",new RegistrationCommand());
        commands.put("showQuizzes",new ShowQuizzesCommand());
        commands.put("logout",new LogoutCommand());
        commands.put("showSubjectEditForm",new ShowSubjectEditFormCommand());
        commands.put("addNewSubject",new AddNewSubjectCommand());
        commands.put("updateSubject",new UpdateSubjectCommand());
        commands.put("showQuizEditForm",new ShowQuizEditFormCommand());
        commands.put("addNewQuiz",new AddNewQuizCommand());
        commands.put("showQuestionManager",new ShowQuestionManager());
        commands.put("showQuestionEditForm",new ShowQuestionEditFormCommand());
        commands.put("addNewQuestion",new AddNewQuestionCommand());
        commands.put("updateQuiz",new UpdateQuizCommand());
        commands.put("updateQuestion",new UpdateQuestionCommand());
        commands.put("deleteAnswer",new DeleteAnswerCommand());
        commands.put("startQuiz",new StartQuizCommand());
        commands.put("submitQuiz",new SubmitQuizCommand());
        commands.put("showQuizResult",new ShowQuizResultCommand());
        //commands.put("lang",new ChangeLangCommand());
    }



    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
//            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
