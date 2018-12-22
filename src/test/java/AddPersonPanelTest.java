import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddPersonPanelTest {

    private AddPersonPanel addPersonPanel;

    @BeforeEach
    void setUp() {
        addPersonPanel = new AddPersonPanel(Log.makeConnection());
    }

    // TD_2
    @Test
    void insertPersonal() {
        String username = "";
        String password = "password";
        String name = "user";
        String surname = "name";
        String tcNo = "12345678901";
        Date birthDate = new Date(820454400);
        String sex = "Kadin";
        String phoneNumber = "5324444444";
        String position = "Personel";
        String mail = "mail@mail.com";
        int departmentId = 1;
        assertFalse(addPersonPanel.insertPersonal(username, password, name, surname, tcNo, birthDate, sex,
                phoneNumber, position, mail, departmentId));

        username = "username";
        assertTrue(addPersonPanel.insertPersonal(username, password, name, surname, tcNo, birthDate, sex,
                phoneNumber, position, mail, departmentId));
    }
}