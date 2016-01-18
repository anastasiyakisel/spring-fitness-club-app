/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.manager;

import by.bsu.kisel.constants.PageConstants;
import by.bsu.kisel.exception.MyTechnicalException;
import by.bsu.kisel.parser.DBProperty;
import by.bsu.kisel.parser.DOMAnalyzer;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Kisel Anastasia
 */
public class DatabaseManager{
    private static DatabaseManager instance;
    
    private DBProperty property;

    public DBProperty getProperty() {
        return property;
    }
       
    private DatabaseManager(){}
    
    public static synchronized DatabaseManager getInstance() throws MyTechnicalException {
        if (instance == null) {           
                instance = new DatabaseManager();
                instance.property=parseDOM();
        }
        return instance;
    }
    
    public static DBProperty parseDOM() throws MyTechnicalException {
        try {
            DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            //recognizing XML-document
            InputStream iSTREAM=DatabaseManager.class.getResourceAsStream(PageConstants.DB_CONFIG_PATH);
            Document document = db.parse(iSTREAM);
            Element root=document.getDocumentElement();
            DBProperty propertyDB=DOMAnalyzer.propertyBuilder(root);
            
            return propertyDB;
        } catch (SAXException ex) {
              throw  new MyTechnicalException(MessageManager.SAX_ERROR_MESSAGE, ex);
        } catch (IOException ex) {
                throw new MyTechnicalException(MessageManager.IO_EXCEPTION_ERROR_MESSAGE, ex);
        } catch (ParserConfigurationException ex) {
                throw new MyTechnicalException(MessageManager.PARSE_ERROR_MESSAGE, ex);
        }
    }
    
    @Override
    public String toString() {
        return getClass().getName() +" has  "+ property;
    }
}
