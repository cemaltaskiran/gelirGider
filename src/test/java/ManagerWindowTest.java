import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ManagerWindowTest {

    private ManagerWindow managerWindow;
    private Connection dbc;

    @BeforeEach
    void setUp() {
        dbc = Log.makeConnection();
        managerWindow = new ManagerWindow("cemal", dbc);
    }

    // TD_3
    @Test
    void approveSpendingByManager() {
        JTable table = managerWindow.table;
        int spendingId = Integer.parseInt((String) table.getValueAt(0, 0));
        int reply = JOptionPane.showConfirmDialog(null, "Harcama Muhasebeciye İletilecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
        assertSame(reply, JOptionPane.YES_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            if (DBUtils.deleteSpending(spendingId) != -1) {
                try {
                    String sql = "INSERT INTO approvedSpending (spending_id,approvel_date) VALUES (?,?)";
                    PreparedStatement ps = dbc.prepareStatement(sql);
                    ps.setInt(1, spendingId);
                    ps.setDate(2, new java.sql.Date((new Date()).getTime()));
                    assertNotSame(ps.executeUpdate(), 0);
                    JOptionPane.showMessageDialog(null, "Harcama başarı ile iletildi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                    managerWindow.updateTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // TD_5
    @Test
    void deleteSpending() {
        assertTrue(managerWindow.deleteSpending());
    }
}