/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.model;

import by.bsu.kisel.command.*;
import by.bsu.kisel.constants.CommandConstants;
import by.bsu.kisel.constants.ParameterConstants;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kisel Anastasia
 */
public class RequestHelper {
    /**
     * instance of the RequestHelper
     */
    private static RequestHelper instance = null;
    /**
     * container of the command
     */
    HashMap<String, Command> commands = new HashMap<String, Command>();
    /**
     * constructor
     */
    private RequestHelper() {        
        commands.put(CommandConstants.COMMAND_LOGIN, new LoginCommand());
        commands.put(CommandConstants.COMMAND_VIEW, new ViewCommand());
        commands.put(CommandConstants.COMMAND_LOGOUT, new LogOutCommand());
        commands.put(CommandConstants.COMMAND_SIGNUP, new SignUpCommand());
        commands.put(CommandConstants.COMMAND_DELETE, new DeleteCommand());
        commands.put(CommandConstants.COMMAND_LOCALE, new LocaleCommand());
    }
    /**
     * gets command from the container
     * @param request
     * @return the command from the container
     */
    public Command getCommand(HttpServletRequest request) {
        //gets command from the request
        String action = request.getParameter(ParameterConstants.PARAMETER_COMMAND);
        //get object appropriate to the command
        Command command = commands.get(action);
        if (command == null) {
        //if connamd doesn't exist in the current object
            command = new NoCommand();
        }
        return command;
    }
     /**
     * @return instance of the RequestHelper
     */
    public static synchronized RequestHelper getInstance() {
        if (instance == null) {
            instance = new RequestHelper();
        }
        return instance;
        }
}
