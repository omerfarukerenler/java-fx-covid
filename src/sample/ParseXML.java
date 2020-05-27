package sample;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseXML {

    public List<XmlData> parseXml(String url) throws ParserConfigurationException, IOException, SAXException {
        List<XmlData> xmlDataList = new ArrayList<>();
        //String url = "https://opendata.ecdc.europa.eu/covid19/casedistribution/xml/";
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(new URL(url).openStream());

        doc.getDocumentElement().normalize();
        System.out.println ("Root element: " +
                doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("record");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                XmlData xmlData = new XmlData();
                xmlData.setDateRep(eElement.getElementsByTagName("dateRep").item(0).getTextContent());
                xmlData.setDay(eElement.getElementsByTagName("day").item(0).getTextContent());
                xmlData.setMonth(eElement.getElementsByTagName("month").item(0).getTextContent());
                xmlData.setYear(eElement.getElementsByTagName("year").item(0).getTextContent());
                xmlData.setCases(eElement.getElementsByTagName("cases").item(0).getTextContent());
                xmlData.setDeaths(eElement.getElementsByTagName("deaths").item(0).getTextContent());
                xmlData.setCountriesAndTerritories(eElement.getElementsByTagName("countriesAndTerritories").item(0).getTextContent());
                xmlData.setGeoId(eElement.getElementsByTagName("geoId").item(0).getTextContent());
                xmlData.setCountryterritoryCode(eElement.getElementsByTagName("countryterritoryCode").item(0).getTextContent());
                xmlData.setPopData2018(eElement.getElementsByTagName("popData2018").item(0).getTextContent());
                xmlData.setContinentExp(eElement.getElementsByTagName("continentExp").item(0).getTextContent());
                xmlDataList.add(xmlData);
            }
        }
        return xmlDataList;
    }
}
