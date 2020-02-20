import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountValidatorTest {

    @Test
    @DisplayName("IBAN validation test")
    void shouldValidateIban() {
        // given
        List<Account> accounts = new ArrayList<>() {};
        Account account1 = new Account(
                "PL61109010140000071219812870",
                "name1",
                "PLN",
                new BigDecimal(0),
                "2029-10-11"
        );
        Account account2 = new Account(
                "PLL1109010140000071219812873",
                "name5",
                "PLN",
                new BigDecimal(999.00),
                "2050-01-01"
        );
        Account account3 = new Account(
                "PL61109010140000071219812874",
                "name6",
                "PLN",
                new BigDecimal(-100),
                "2039-05-15"
        );
        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        AccountValidator accountValidator = new AccountValidator();

        // when
        accountValidator.validateIban(accounts);

        // then
        assertEquals(1, accounts.size());

    }

    @Test
    @DisplayName("Checksum Iban checking test")
    void shouldCheckSumPLIban(){
        // given
        AccountValidator accountValidator = new AccountValidator();
        Account account1 = new Account(
                "PL61109010140000071219812870",
                "name1",
                "PLN",
                new BigDecimal(0),
                "2029-10-11"
        );
        Account account3 = new Account(
                "PL33114020040000310246414252",
                "prawidlowy",
                "PLN",
                new BigDecimal(-100),
                "2039-05-15"
        );
        int expected1 = 1;
        int expected87 = 87;
        assertEquals(expected1, accountValidator.checkSumPLIban(account3.getIban()));
        assertEquals(expected87, accountValidator.checkSumPLIban(account1.getIban()));
    }

    @Test
    @DisplayName("Date validation test")
    void shouldValidateClosingDate(){
        // given
        AccountValidator accountValidator = new AccountValidator();
        Account account1 = new Account(
                "PL61109010140000071219812870",
                "name1",
                "PLN",
                new BigDecimal(0),
                "2010-10-11"
        );
        Account account2 = new Account(
                "PL21109010140000071219812873",
                "name5",
                "PLN",
                new BigDecimal(999.00),
                "2050-01-01"
        );
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        // when
        accountValidator.validateClosingDate(accounts);
        // then
        assertEquals(1, accounts.size());
    }

    @Test
    @DisplayName("Currency validation test")
    void shouldValidateCurrency(){
        // given
        AccountValidator accountValidator = new AccountValidator();
        Account account1 = new Account(
                "PL61109010140000071219812870",
                "name1",
                "PLN",
                new BigDecimal(0),
                "2010-10-11"
        );
        Account account2 = new Account(
                "PL21109010140000071219812873",
                "name5",
                "USD",
                new BigDecimal(999.00),
                "2050-01-01"
        );
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        // when
        accountValidator.validateCurrency(accounts);
        // then
        assertEquals(1, accounts.size());
    }

    @Test
    @DisplayName("Balance validation test")
    void shouldValidateBalance(){
        // given
        AccountValidator accountValidator = new AccountValidator();
        Account account1 = new Account(
                "PL61109010140000071219812870",
                "name1",
                "PLN",
                new BigDecimal(-12),
                "2010-10-11"
        );
        Account account2 = new Account(
                "PL21109010140000071219812873",
                "name5",
                "USD",
                new BigDecimal(999.00),
                "2050-01-01"
        );
        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);
        // when
        accountValidator.validateBalance(accounts);
        // then
        assertEquals(1, accounts.size());
    }




}