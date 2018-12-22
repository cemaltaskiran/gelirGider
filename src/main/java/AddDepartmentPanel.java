import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddDepartmentPanel extends JPanel {

    private JTextField textFieldName;
    private JSpinner spinnerLimit;
    private JTable table;
    private JButton btnAdd;
    private JButton btnDelete;
    private Connection dbc;
    private JTextField textField;
    private JTextField textFieldFix;
    private JSpinner spinnerFix;
    private JButton btnGncelle;

    /**
     * Create the panel.
     */
    public AddDepartmentPanel(Connection dbc) {
        setOpaque(false);
        this.dbc = dbc;
        setSize(625, 550);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Departman Ekle");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblNewLabel.setBounds(10, 11, 605, 44);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Departman Adı");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 66, 110, 24);
        add(lblNewLabel_1);

        textFieldName = new JTextField();
        textFieldName.setBounds(130, 66, 175, 24);
        add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblHarcama = new JLabel("Harcama Limiti");
        lblHarcama.setForeground(Color.WHITE);
        lblHarcama.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblHarcama.setBounds(320, 66, 110, 24);
        add(lblHarcama);

        spinnerLimit = new JSpinner();
        spinnerLimit.setModel(new SpinnerNumberModel(0, 0, 25000, 100));
        spinnerLimit.setBounds(440, 66, 175, 24);
        add(spinnerLimit);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 134, 485, 250);
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTableDefault();
        updateTable();
        scrollPane.setViewportView(table);

        JButton btnClear = new JButton("TEM\u0130ZLE");
        btnClear.setBounds(385, 101, 110, 23);
        add(btnClear);

        btnAdd = new JButton("EKLE");
        btnAdd.setBounds(505, 101, 110, 23);
        add(btnAdd);

        btnDelete = new JButton("S\u0130L");
        btnDelete.setBounds(505, 134, 110, 23);
        add(btnDelete);

        JLabel lblDepartmanId = new JLabel("Departman ID");
        lblDepartmanId.setForeground(Color.WHITE);
        lblDepartmanId.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblDepartmanId.setBounds(10, 395, 110, 24);
        add(lblDepartmanId);

        textField = new JTextField();
        textField.setEditable(false);
        textField.setEnabled(false);
        textField.setColumns(10);
        textField.setBounds(130, 395, 175, 24);
        add(textField);

        JLabel label_1 = new JLabel("Harcama Limiti");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        label_1.setBounds(10, 430, 110, 24);
        add(label_1);

        spinnerFix = new JSpinner();
        spinnerFix.setBounds(130, 430, 175, 24);
        spinnerFix.setModel(new SpinnerNumberModel(0, 0, 25000, 100));
        add(spinnerFix);

        JLabel label_2 = new JLabel("Departman Adı");
        label_2.setForeground(Color.WHITE);
        label_2.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        label_2.setBounds(320, 395, 110, 24);
        add(label_2);

        textFieldFix = new JTextField();
        textFieldFix.setColumns(10);
        textFieldFix.setBounds(440, 395, 175, 24);
        add(textFieldFix);

        btnGncelle = new JButton("G\u00DCNCELLE");
        btnGncelle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Departman Güncellenecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        if (textFieldFix.getText().equals("")) {
                            throw new Exception("Departman adı boş olamaz!");
                        }

                        String sql = "UPDATE department SET dep_name = ?, dep_limit = ? WHERE dep_id = ?";
                        PreparedStatement preparedStatement = dbc.prepareStatement(sql);
                        preparedStatement.setString(1, textFieldFix.getText());
                        preparedStatement.setInt(2, (int) spinnerFix.getValue());
                        preparedStatement.setInt(3, Integer.parseInt(textField.getText()));
                        preparedStatement.executeUpdate();

                        if (preparedStatement.getWarnings() != null) {
                            throw new Exception(preparedStatement.getWarnings().getMessage());
                        }

                        JOptionPane.showMessageDialog(null, "Departman başarı ile güncellendi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                        updateTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "HATA", JOptionPane.ERROR_MESSAGE);
                    }
                } else {

                }
            }
        });
        btnGncelle.setBounds(505, 433, 110, 23);
        add(btnGncelle);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Departman Eklenecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        if (textFieldName.getText().equals("")) {
                            throw new Exception("Departman adı boş olamaz!");
                        }

                        String sql = "INSERT INTO department (dep_name,dep_limit)" +
                                "VALUES (?,?)";
                        PreparedStatement preparedStatement = dbc.prepareStatement(sql);
                        preparedStatement.setString(1, textFieldName.getText());
                        preparedStatement.setInt(2, (int) spinnerLimit.getValue());
                        preparedStatement.executeUpdate();

                        if (preparedStatement.getWarnings() != null) {
                            throw new Exception(preparedStatement.getWarnings().getMessage());
                        }

                        JOptionPane.showMessageDialog(null, "Departman başarı ile eklendi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                        updateTable();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "HATA", JOptionPane.ERROR_MESSAGE);
                    }
                } else {

                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int reply = JOptionPane.showConfirmDialog(null, "Departman Silinecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                        Statement st = dbc.createStatement();
                        String sql = "DELETE FROM department WHERE dep_id = " + id + "";
                        st.executeUpdate(sql);
                        updateTable();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                int id = (int) table.getValueAt(table.getSelectedRow(), 0);
                try {

                    String sql = "SELECT dep_id, dep_name, dep_limit FROM department"
                            + " WHERE dep_id = ? ";
                    PreparedStatement ps = dbc.prepareStatement(sql);
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        textField.setText(Integer.toString(rs.getInt("dep_id")));
                        textFieldFix.setText(rs.getString("dep_name"));
                        spinnerFix.setValue(rs.getInt("dep_limit"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void clearPage() {
        textFieldName.setText("");
        spinnerLimit.setValue(0);
    }

    @SuppressWarnings("serial")
    void setTableDefault() {
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Departman ID", "Departman Adı", "Harcama Limiti"
                }
        ));
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setMaxWidth(80);
    }

    private void updateTable() {
        try {
            setTableDefault();
            Statement st = dbc.createStatement();
            String sql = "SELECT dep_id, dep_name, dep_limit  FROM department ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int depID = rs.getInt("dep_id");
                String depName = rs.getString("dep_name");
                int depLimit = rs.getInt("dep_limit");

                Object[] row = {depID, depName, depLimit};
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
