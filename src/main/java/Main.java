import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AccountValidator accountValidator = new AccountValidator();

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

        // Sorts the list by Account name
        Collections.sort(accounts);

        // Validates accounts
        accountValidator.validateBalance(accounts);
        accountValidator.validateClosingDate(accounts);
        accountValidator.validateCurrency(accounts);
        accountValidator.validateIban(accounts);

        System.out.println("Rachunki które pozostały: ");
        System.out.println(accounts);


        //TODO
        // Save xml output file
        try {
            String filePath = "output.xml";
            String xml = mapper.writeValueAsString(accounts);
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(xml);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}