import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final AccountValidator accountValidator = new AccountValidator();
        final XMLBuilder xmlBuilder = new XMLBuilder();

        // List for for accounts read from xml file
        List<Account> accounts = new ArrayList<>() {};

        // The block reads and maps xml file to Account POJO
        ObjectMapper mapper = new XmlMapper();
        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        try {
            String filePath = "input.xml";
            InputStream inputStream = new FileInputStream(filePath);
            accounts = mapper.readValue(inputStream, typeReference);
        } catch (Exception e){
            e.printStackTrace();
        }

        // Validates accounts
        accountValidator.validateBalance(accounts);
        accountValidator.validateClosingDate(accounts);
        accountValidator.validateCurrency(accounts);
        accountValidator.validateIban(accounts);

        // Sorts the list by Account name
        Collections.sort(accounts);

        System.out.println("Validated accounts left: ");
        System.out.println(accounts);

        xmlBuilder.saveAccountListToXML(accounts, "output.xml");
    }

}