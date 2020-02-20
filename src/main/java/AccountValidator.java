import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccountValidator {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void validateIban(List<Account> accounts){
        accounts.removeIf(
        // remove IBAN with spaces
        e -> e.getIban().contains(" ")
        // remove IBAN that contains chars different than numbers
        || !e.getIban().substring(2).matches("[0-9]+")
        // remove IBAN that starts different than "PL"
        || !e.getIban().substring(0,2).equals("PL")
        || e.getIban().length() != 28 // remove IBAN shorter or longer that 28 chars
                );
        // remove IBAN with incorrect checksum
        accounts.removeIf(
                e -> checkSumPLIban(e.getIban()) != 1
        );
    }

    // Validates checksum for PL IBAN
    private int checkSumPLIban(String iban) {
        String tempIban = iban.substring(2,4);
        // Gets the firs 4 chars and moves them to the end of IBAN sequence, changing "P" to "25" and "L" to "21"
        String checkIban = iban.substring(4).concat("2521" + tempIban);
        BigInteger bigIntegerIban = new BigInteger(checkIban);
        return bigIntegerIban.mod(new BigInteger("97")).intValue();
    }

    // Removes accounts whith closing date older than actual date
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
