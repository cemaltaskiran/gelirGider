import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginPanelTest {

    private LoginPanel loginPanel;

    @BeforeEach
    void setUp() {
        loginPanel = new LoginPanel(new Frame(), Log.makeConnection());
    }

    // TD_1
    @Test
    void checkInput() {
        String username = "";
        String password = "";
        assertNull(loginPanel.checkInput(username, password, new Frame()));

        username = "dogacCem";
        password = "";
        assertNull(loginPanel.checkInput(username, password, new Frame()));

        username = "";
        password = "123";
        assertNull(loginPanel.checkInput(username, password, new Frame()));

        username = "dogac";
        password = "123";
        assertNull(loginPanel.checkInput(username, password, new Frame()));

        username = "dogacCem";
        password = "123";
        assertNotNull(loginPanel.checkInput(username, password, new Frame()));
    }
}