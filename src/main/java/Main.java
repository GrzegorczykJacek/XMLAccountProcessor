import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // List for for accounts read from xml file
        List<Account> accounts = new ArrayList<>() {};

        // The block reads and maps xml file to Account POJO
        try {
            ObjectMapper mapper = new XmlMapper();
            String filePath = "input.xml";
            InputStream inputStream = new FileInputStream(filePath);
            TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
            };
            accounts = mapper.readValue(inputStream, typeReference);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(
                accounts
        );

        // Sorts the list by Account name
        Collections.sort(accounts);

        System.out.println(
                accounts
        );

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse("2000-12-23", dateTimeFormatter);

    }

}