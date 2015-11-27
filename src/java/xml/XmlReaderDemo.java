package xml;

import java.io.IOException;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 This class scrapes data in xml format
 */

public class XmlReaderDemo extends DefaultHandler {

    private static List<String> list = new ArrayList();

    public List<String> scrapeDataFromNationalBanken() {
        list = new ArrayList();
        
        try {
            XMLReader xr = XMLReaderFactory.createXMLReader();
            xr.setContentHandler(new XmlReaderDemo());
            URL url = new URL("http://www.nationalbanken.dk/_vti_bin/DN/DataService.svc/CurrencyRatesXML?lang=en");
            xr.parse(new InputSource(url.openStream()));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void startDocument() throws SAXException {
        //System.out.println("Start Document (Sax-event)");
    }

    @Override
    public void endDocument() throws SAXException {
        //System.out.println("End Document (Sax-event)");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.print("Element: " + localName + ": ");
        for (int i = 0; i < attributes.getLength(); i++) {
            //System.out.print("[Atribute: NAME: " + attributes.getLocalName(i) + " VALUE: " + attributes.getValue(i) + "] ");
            list.add(attributes.getValue(i));

        }
    }
}
