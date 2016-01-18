/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.command;

import by.bsu.kisel.constants.PageConstants;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Kisel Anastasia
 */
public class NoCommand implements Command{
    /**
     * no command
     * @param request
     */
    @Override
    public String execute(HttpServletRequest request){
        //in case simple order to controller we are getting to the login page
        String page = PageConstants.INDEX_PAGE_PATH;
        return page;
    }
    
}
