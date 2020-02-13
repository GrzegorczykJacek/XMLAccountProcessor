import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountValidator {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    //TODO
    // validateIban(){}

    // Removes accounts whit closing date older than actual date
    public void validateClosingDate(List<Account> accounts){
        accounts.removeIf(e -> LocalDate.parse(e.getClosingDate(), dateTimeFormatter).compareTo(LocalDate.now()) < 0
        );
    }

    // Removes accounts with a currency different than: PLN
    public void validateCurrency(List<Account> accounts){
        accounts.removeIf(e -> !e.getCurrency().equals("PLN"));
    }

    // Removes accounts with balance lower than: 0
    public void validateBalance(List<Account> accounts){
        accounts.removeIf(e -> e.getBalance().compareTo(new BigDecimal("0")) < 0);
    }

}
