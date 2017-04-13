package com.quantum.accessibilityObjs;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;

/**
 * Created by uzie on 2/2/17.
 */
public class AccPageData {

    LinkedList<AccSingleLine> lines = new LinkedList() ;
    String imageLocation;

    public AccPageData(String xml,String image) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        imageLocation=image;

        Document doc = null;
        try {
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            xml=xml.replaceAll("&","");
            InputSource is = new InputSource(new StringReader(xml));

            doc = dBuilder.parse(is);
            buildLine(doc);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public AccPageData(File xml,String image) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xml);
            buildLine(doc);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void buildLine(Document doc)
    {
        NodeList nList = doc.getElementsByTagName("object");
        for (int i = 0; i < nList.getLength(); i++) {

            Node nNode = nList.item(i);


            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                int x=0 ;
                int y=0 ;
                int width=0;
                int height=0;

                NodeList rect = eElement.getElementsByTagName("rect");
                for (int i2 = 0; i2 < rect.getLength(); i2++) {
                    Node rNode = rect.item(i2);
                    if (rNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element rElement = (Element) rNode;

                        y = Integer.valueOf(rElement.getElementsByTagName("top").item(0).getTextContent());
                        x = Integer.valueOf(rElement.getElementsByTagName("left").item(0).getTextContent());
                        width = Integer.valueOf(rElement.getElementsByTagName("width").item(0).getTextContent());
                        height = Integer.valueOf(rElement.getElementsByTagName("height").item(0).getTextContent());

                    }
                }
               String type =   eElement.getElementsByTagName("type").item(0).getTextContent();
               String message = eElement.getElementsByTagName("message").item(0).getTextContent();

                AccSingleLine l = new AccSingleLine(type,message,x,y,width,height);
                lines.add(l);

            }
        }
    }


    public LinkedList<AccSingleLine> getLines()
    {
        return lines;
    }
    public String getImage()
    {
        return imageLocation;
    }

}
