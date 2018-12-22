import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewUtils {

    static void setTableDefault(JTable table) {
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "ID", "Harcama Türü", "Tarih", "Tutar", "Personel"
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
    }

    static void logoutAction(JFrame frame) {
        frame.dispose();
        try {
            Log m = new Log();
            m.getFrame().setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void updateSpendingTable(JTable table, ResultSet rs) {
        try {
            while (rs.next()) {
                String id = Integer.toString(rs.getInt("spending_id"));
                String type = rs.getString("s_type_name");
                String sDate = rs.getString("s_date");
                int amount = rs.getInt("s_amount");
                String user_name = rs.getString("user_name");

                Object[] row = {id, type, sDate, amount, user_name};
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
