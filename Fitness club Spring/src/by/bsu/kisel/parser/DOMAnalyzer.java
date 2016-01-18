/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.bsu.kisel.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Kisel Anastasia
 */
public class DOMAnalyzer {
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
    //returns child element by it's name and parent element
    private static Element getChild(Element parent, String childName){
        NodeList nList=parent.getElementsByTagName(childName);
        Element child = (Element)nList.item(0);
        return child;
    }
    //returns text of the element
    private static String  getChildValue(Element parent,  String childName){
        Element child = getChild(parent, childName);
        Node node = child.getFirstChild();
        String value=node.getNodeValue();
        return value;
    }
}
