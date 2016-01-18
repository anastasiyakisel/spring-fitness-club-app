/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.command;

import javax.servlet.http.HttpServletRequest;

import by.bsu.kisel.exception.ResourceCreationException;


/**
 *
 * @author Kisel Anastasia
 */
public interface Command {
    
    public String execute(HttpServletRequest request)  throws ResourceCreationException ;
}
