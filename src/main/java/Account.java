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

    public int compareTo(Account account) {
        return 0;
    }
}
