package chapter2;

import org.xml.sax.*;
import javax.xml.parsers.*;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;

public class SAXParserApp {

	public static void main(String argv[]) {

		SAXParserApp saxParserApp = new SAXParserApp();
		saxParserApp.parseDocument();

	}

	public void parseDocument() {

		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			DefaultHandler handler = new CustomSAXHandler();
			saxParser.parse(new File("catalog.xml"), handler);
		} catch (SAXException e) {
		} catch (ParserConfigurationException e) {
		} catch (IOException e) {
		}
	}

	private class CustomSAXHandler extends DefaultHandler {
		public CustomSAXHandler() {
		}

		public void startDocument() throws SAXException {
			System.out.println("Event Type: Start Document");
		}

		public void endDocument() throws SAXException {
			System.out.println("Event Type: End Document");
		}

		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			System.out.println("Event Type: Start Element");
			System.out.println("Element Name:" + qName);
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.println("Attribute Name:" + attributes.getQName(i));
				System.out.println("Attribute Value:" + attributes.getValue(i));
			}

		}

		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			System.out.println("Event Type: End Element");
		}

		public void characters(char[] ch, int start, int length)
				throws SAXException {
			System.out.println("Event Type:  Text");
			String str = (new String(ch, start, length));
			System.out.println(str);
		}
		
		public void error(SAXParseException e)
        throws SAXException{
			System.out.println("Error: "+e.getMessage());
		}

        public void fatalError(SAXParseException e)
             throws SAXException{
        	System.out.println("Fatal Error: "+e.getMessage());
        }

        public void warning(SAXParseException e)
          throws SAXException{
        	System.out.println("Warning: "+e.getMessage());
        }
	}

}
