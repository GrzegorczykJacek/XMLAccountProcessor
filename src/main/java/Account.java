import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Account implements Comparable<Account> {

    private String iban;
    private String name;
    private String currency;
    private BigDecimal balance;
    private String closingDate;

    public Account(){};

    @Override
    public int compareTo(Account o) {
        if(this.getName().compareTo(o.getName()) < 0) return -1;
        if(this.getName().compareTo(o.getName()) > 0) return 1;
        return 0;
    }
}
