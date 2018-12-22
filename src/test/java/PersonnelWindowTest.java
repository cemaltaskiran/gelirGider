import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PersonnelWindowTest {
    private PersonnelWindow personnelWindow;
    private String username;

    @BeforeEach
    void setUp() {
        username = "cemal";
        personnelWindow = new PersonnelWindow(username, Log.makeConnection());
    }

    // TD_4
    @Test
    void addSpending() {
        String company = "";
        String explanation = "";
        Date date = new Date();
        int amount = 0;
        int spendingTypeId = 1;

        assertFalse(personnelWindow.addSpending(username, company, explanation, date, amount, spendingTypeId));

        company = "Uber";
        explanation = "Seminere ulaşım için kullanıldı.";
        amount = 67;
        assertTrue(personnelWindow.addSpending(username, company, explanation, date, amount, spendingTypeId));
    }
}