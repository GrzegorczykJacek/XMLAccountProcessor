import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new XmlMapper();
            String filePath = "input.xml";
            InputStream inputStream = new FileInputStream(filePath);
            TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
            };
            List<Account> accounts = mapper.readValue(inputStream, typeReference);
        } catch (Exception e){
            e.printStackTrace();
        }



    }

}
