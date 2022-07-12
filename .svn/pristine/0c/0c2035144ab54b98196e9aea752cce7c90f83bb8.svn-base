package co.com.grupoasd.nomina.common.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author jccastellanos
 * 2015-04-13
 */
public class XMLUtil {
    
    private Document document;
    
    public XMLUtil(String xml) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(xml.getBytes()));
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new IllegalArgumentException(ex);
        } 
    }
    
    public Object evalXPath(String expresion, QName returnType) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return xPath.compile(expresion).evaluate(document,  returnType);
        } catch (XPathExpressionException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    public String evalXPath(String expresion) {
        try {
            XPath xPath = XPathFactory.newInstance().newXPath();
            return xPath.compile(expresion).evaluate(document);
        } catch (XPathExpressionException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
}
