package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Main {

    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {

        XPathFactory factory = XPathFactory.newInstance();
        XPath path = factory.newXPath();
      
        XPathExpression xPathExpression = path.compile("//book[price > 10 and number(translate(publish_date,'-','')) > 20051231]");

        File xmlDocument = new File("books.xml");
        InputSource inputSource = new InputSource(new FileInputStream(xmlDocument));

        Object result = xPathExpression.evaluate(inputSource, XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;

        for (int i = 0; i < nodeList.getLength(); i++) {
            System.out.println("Book ID: " + nodeList.item(i).getAttributes().item(0).getNodeValue());
            System.out.println("Author: " + nodeList.item(i).getFirstChild().getNextSibling().getTextContent());
            System.out.println("Title: " + nodeList.item(i).getChildNodes().item(3).getTextContent());
            System.out.println("Genre: " + nodeList.item(i).getChildNodes().item(5).getTextContent());
            System.out.println("Price: " + nodeList.item(i).getChildNodes().item(7).getTextContent());
            System.out.println("Publish date: " + nodeList.item(i).getChildNodes().item(9).getTextContent());
            System.out.println("Description: " + nodeList.item(i).getChildNodes().item(11).getTextContent());
            System.out.println("\n");
        }

    }

}
