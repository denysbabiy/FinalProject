package com.epam.finalproject.web.command;

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
    }



    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
//            log.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }

}
