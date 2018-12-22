import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountantWindowTest {

    private AccountantWindow accountantWindow;

    @BeforeEach
    void setUp() {
        String username = "onur";
        accountantWindow = new AccountantWindow(username, Log.makeConnection());
    }

    // TD_8
    @Test
    void applySpending() {
        accountantWindow.table.setRowSelectionInterval(0, 0);
        assertTrue(accountantWindow.applySpending());
    }
}