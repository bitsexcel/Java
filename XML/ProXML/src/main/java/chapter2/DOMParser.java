package chapter2;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class DOMParser {

	public static void main(String argv[]) {
		try {
			// Create a DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			File xmlFile = new File("catalog.xml");
			// Create a DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Parse an XML document
			Document document = builder.parse(xmlFile);
			// Retrieve Root Element
			Element rootElement = document.getDocumentElement();
			System.out.println("Root Element is: " + rootElement.getTagName());
			visitNode(null, rootElement);

		} catch (SAXException e) {
			System.out.println(e.getMessage());

		} catch (ParserConfigurationException e) {
			System.out.println(e.getMessage());

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void visitNode(Element previousNode, Element visitNode) {
		if (previousNode != null) {
			System.out.println("Element " + previousNode.getTagName()
					+ " has element:");
		}
		System.out.println("Element Name: " + visitNode.getTagName());
		if (visitNode.hasAttributes()) {
			System.out.println("Element " + visitNode.getTagName()
					+ " has attributes: ");
			NamedNodeMap attributes = visitNode.getAttributes();

			for (int j = 0; j < attributes.getLength(); j++) {
				Attr attribute = (Attr) (attributes.item(j));
				System.out.println("Attribute:" + attribute.getName()
						+ " with value " + attribute.getValue());

			}
		}
		// Obtain a NodeList of nodes in an Element node.

		NodeList nodeList = visitNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			// Retrieve Element Nodes
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				visitNode(visitNode, element);
			} else if (node.getNodeType() == Node.TEXT_NODE) {
				String str = node.getNodeValue().trim();
				if (str.length() > 0) {
					System.out.println("Element Text: " + str);

				}
			}
		}
	}
}
