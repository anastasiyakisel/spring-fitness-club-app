/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.manager;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Kisel Anastasia
 */
@Component("MessageManager")
public class MessageManager {
    
	@Value("by.bsu.kisel.resources.messages")
    private String bundleName;
    
    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
        System.out.println("Bundle Name"+bundleName);
    } 
 
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String IO_EXCEPTION_ERROR_MESSAGE= "IO_EXCEPTION_ERROR_MESSAGE";
    public static final String PARSE_ERROR_MESSAGE= "PARSE_ERROR_MESSAGE";
    public static final String SAX_ERROR_MESSAGE= "SAX_ERROR_MESSAGE";
    public static final String RADIOBUTTON_ERROR_MESSAGE="RADIOBUTTON_ERROR_MESSAGE";
    public static final String NO_SUCH_ENUM_VALUE="NO_SUCH_ENUM_VALUE";
    public static final String DAO_ERROR_MESSAGE="DAO_ERROR_MESSAGE";
    public static final String ERROR_MESSAGE_POOL_IS_EMPTY="ERROR_MESSAGE_POOL_IS_EMPTY";
    
    public String getProperty(String key) {    	
        ResourceBundle resourceBundle=ResourceBundle.getBundle(bundleName);
        return (String)resourceBundle.getObject(key);
    }
}
