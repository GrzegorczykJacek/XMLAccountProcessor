import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLBuilder {

    public void saveAccountListToXML(List<Account> accounts, String filepath){

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Add root element
            Element accountsXML = document.createElement("accounts");
            document.appendChild(accountsXML);

            // Create Accounts in xml
            for (Account a : accounts) {
                Element accountXML = document.createElement("account");
                accountsXML.appendChild(accountXML);

                accountXML.setAttribute("iban", a.getIban());

                Element currencyXML = document.createElement("currency");
                accountXML.appendChild(currencyXML);
                currencyXML.setTextContent(a.getCurrency());

                Element balanceXML = document.createElement("balance");
                accountXML.appendChild(balanceXML);
                balanceXML.setTextContent(a.getBalance().toString());

                Element nameXML = document.createElement("name");
                accountXML.appendChild(nameXML);
                nameXML.setTextContent(a.getName());

                Element closingDateXML = document.createElement("closingDate");
                accountXML.appendChild(closingDateXML);
                closingDateXML.setTextContent(a.getClosingDate());

            }

            // Saves xml to file
            document.setXmlStandalone(true);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(filepath));

            transformer.transform(domSource, streamResult);

            System.out.println("Validated accounts saved to: " + filepath);

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}
