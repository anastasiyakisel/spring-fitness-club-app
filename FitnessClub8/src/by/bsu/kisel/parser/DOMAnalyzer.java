/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * This class implements logic of parsing configuration file with the help of DOM Parser.
 * @author Anastasiya Kisel
 */
public class DOMAnalyzer {
	/**
	 * Constructs DBProperty object from the Element.
	 * @param root - DOM Element object
	 * @return DBProperty
	 */
    public static DBProperty propertyBuilder(Element root){
        //getting list of child elements <property>
        NodeList propertyNodes=root.getElementsByTagName("property");
        DBProperty property = new DBProperty();
        Element propertyElement=(Element)propertyNodes.item(0);
        //initialization of object property
        property.setUser(propertyElement.getAttribute("user"));
        property.setPassword(propertyElement.getAttribute("password"));
        property.setDriver(getChildValue(propertyElement, "database_driver_name"));
        property.setUrl(getChildValue(propertyElement, "database_url"));
        return property;
    }
    /**
     * Returns child element by it's name and parent element
     * @param parent - parent element
     * @param childName - name of the child
     * @return child element
     */
    private static Element getChild(Element parent, String childName){
        NodeList nList=parent.getElementsByTagName(childName);
        Element child = (Element)nList.item(0);
        return child;
    }
    /**
     * Returns text of the element.
     * @param parent - parent
     * @param childName - name of the child
     * @return text of the element
     */
    private static String  getChildValue(Element parent,  String childName){
        Element child = getChild(parent, childName);
        Node node = child.getFirstChild();
        String value=node.getNodeValue();
        return value;
    }
}
