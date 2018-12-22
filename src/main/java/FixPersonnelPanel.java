import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Date;

public class FixPersonnelPanel extends JPanel {
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldTcNo;
    private JTextField textFieldPhone;
    private JTextField textFieldMail;
    private JComboBox<String> comboBoxSex;
    private JComboBox<String> comboBoxDepartment;
    private JTable table;
    private JButton btnAdd;
    private JButton btnDelete;
    private Connection dbc;
    private JTextField textFieldUsername;
    private JTextField textFieldPassword;
    private JLabel lblifre;
    private JDateChooser dateChooser;
    private JComboBox<String> comboBoxPosition;
    private JButton btnClear;

    /**
     * Create the panel.
     */
    public FixPersonnelPanel(Connection dbc) {
        setOpaque(false);
        this.dbc = dbc;
        setSize(625, 550);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Personel Ekle");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblNewLabel.setBounds(10, 11, 605, 44);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Adı");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(10, 102, 110, 24);
        add(lblNewLabel_1);

        textFieldName = new JTextField();
        textFieldName.setBounds(130, 102, 175, 24);
        add(textFieldName);
        textFieldName.setColumns(10);

        JLabel lblSoyad = new JLabel("Soyadı");
        lblSoyad.setForeground(Color.WHITE);
        lblSoyad.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblSoyad.setBounds(320, 102, 110, 24);
        add(lblSoyad);

        textFieldSurname = new JTextField();
        textFieldSurname.setColumns(10);
        textFieldSurname.setBounds(440, 102, 175, 24);
        add(textFieldSurname);

        JLabel lblTelefon = new JLabel("TC Kimlik");
        lblTelefon.setForeground(Color.WHITE);
        lblTelefon.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblTelefon.setBounds(10, 137, 110, 24);
        add(lblTelefon);

        textFieldTcNo = new JTextField();
        textFieldTcNo.setColumns(10);
        textFieldTcNo.setBounds(130, 137, 175, 24);
        add(textFieldTcNo);

        JLabel lblEmail = new JLabel("Do\u011Fum Tarihi");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblEmail.setBounds(320, 137, 110, 24);
        add(lblEmail);

        JLabel label = new JLabel("Telefon");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        label.setBounds(10, 172, 110, 24);
        add(label);

        textFieldPhone = new JTextField();
        textFieldPhone.setColumns(10);
        textFieldPhone.setBounds(130, 172, 175, 24);
        add(textFieldPhone);

        JLabel label_1 = new JLabel("E-Mail");
        label_1.setForeground(Color.WHITE);
        label_1.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        label_1.setBounds(320, 172, 110, 24);
        add(label_1);

        textFieldMail = new JTextField();
        textFieldMail.setColumns(10);
        textFieldMail.setBounds(440, 172, 175, 24);
        add(textFieldMail);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 289, 485, 250);
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTableDefault();
        updateTable();
        scrollPane.setViewportView(table);

        JLabel lblCinsiyet = new JLabel("Cinsiyet");
        lblCinsiyet.setForeground(Color.WHITE);
        lblCinsiyet.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblCinsiyet.setBounds(10, 209, 110, 24);
        add(lblCinsiyet);

        comboBoxSex = new JComboBox<String>();
        comboBoxSex.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        comboBoxSex.setBounds(130, 209, 175, 24);
        comboBoxSex.setModel(new DefaultComboBoxModel<String>(new String[]{"Erkek", "Kadın"}));
        add(comboBoxSex);

        JLabel lblDepartman = new JLabel("Departman");
        lblDepartman.setForeground(Color.WHITE);
        lblDepartman.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblDepartman.setBounds(320, 209, 110, 24);
        add(lblDepartman);


        btnAdd = new JButton("G\u00DCNCELLE");
        btnAdd.setBounds(505, 244, 110, 23);
        add(btnAdd);

        btnDelete = new JButton("S\u0130L");
        btnDelete.setBounds(505, 286, 110, 23);
        add(btnDelete);

        JLabel lblKullancAd = new JLabel("Kullanıcı Adı");
        lblKullancAd.setForeground(Color.WHITE);
        lblKullancAd.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblKullancAd.setBounds(10, 66, 110, 24);
        add(lblKullancAd);

        textFieldUsername = new JTextField();
        textFieldUsername.setEditable(false);
        textFieldUsername.setEnabled(false);
        textFieldUsername.setColumns(10);
        textFieldUsername.setBounds(130, 66, 175, 24);
        add(textFieldUsername);

        lblifre = new JLabel("\u015Eifre");
        lblifre.setForeground(Color.WHITE);
        lblifre.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblifre.setBounds(320, 66, 110, 24);
        add(lblifre);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(440, 66, 175, 24);
        add(textFieldPassword);

        comboBoxDepartment = new JComboBox<String>();
        comboBoxDepartment.setBounds(440, 207, 175, 23);
        add(comboBoxDepartment);
        comboBoxDepartment.setFont(new Font("Ubuntu", Font.PLAIN, 14));

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        dateChooser.setDateFormatString("dd / MMMM / yyyy");
        dateChooser.setBounds(439, 137, 176, 24);
        dateChooser.setDate(new Date());
        add(dateChooser);

        JLabel lblPozisyon = new JLabel("Pozisyon");
        lblPozisyon.setForeground(Color.WHITE);
        lblPozisyon.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblPozisyon.setBounds(10, 244, 110, 24);
        add(lblPozisyon);

        comboBoxPosition = new JComboBox<String>();
        comboBoxPosition.setModel(new DefaultComboBoxModel(new String[]{"Personel", "Yönetici", "Muhasebeci", "Admin"}));
        comboBoxPosition.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        comboBoxPosition.setBounds(130, 244, 175, 24);
        add(comboBoxPosition);

        btnClear = new JButton("TEM\u0130ZLE");
        btnClear.setBounds(385, 244, 110, 23);
        add(btnClear);

        btnAdd.setEnabled(false);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                btnAdd.setEnabled(true);
                fillPersonnelInfo();
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int reply = JOptionPane.showConfirmDialog(null, "Personel Güncellenecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        if (textFieldUsername.getText().equals("")) {
                            throw new Exception("Kullanıcı adı boş olamaz!");
                        } else if (textFieldPassword.getText().equals("")) {
                            throw new Exception("Şifre boş olamaz!");
                        } else if (textFieldName.getText().equals("")) {
                            throw new Exception("Ad boş olamaz!");
                        } else if (textFieldSurname.getText().equals("")) {
                            throw new Exception("Soyad boş olamaz!");
                        } else if (textFieldTcNo.getText().equals("")) {
                            throw new Exception("TC no boş olamaz!");
                        } else if (dateChooser.getDate().getYear() > (new Date()).getYear() - 18) {
                            throw new Exception("Personel 18 yaşından küçük olamaz");
                        } else if (textFieldPhone.getText().equals("")) {
                            throw new Exception("Telefon boş olamaz!");
                        } else if (textFieldMail.getText().equals("")) {
                            throw new Exception("Mail boş olamaz!");
                        }

                        String sql = "UPDATE personnel SET user_name = ?, password = ?, name = ?,surname = ?,tc_no = ?,birthdate = ? "
                                + ",sex = ?, email = ?, phone = ?, position = ?, dep_id = ? WHERE user_name = '" + textFieldUsername.getText() + "'";
                        PreparedStatement preparedStatement = dbc.prepareStatement(sql);
                        preparedStatement.setString(1, textFieldUsername.getText());
                        preparedStatement.setString(2, textFieldPassword.getText());
                        preparedStatement.setString(3, textFieldName.getText());
                        preparedStatement.setString(4, textFieldSurname.getText());
                        preparedStatement.setString(5, textFieldTcNo.getText());
                        preparedStatement.setDate(6, new java.sql.Date(dateChooser.getDate().getTime()));
                        preparedStatement.setString(7, comboBoxSex.getSelectedItem().toString());
                        preparedStatement.setString(8, textFieldMail.getText());
                        preparedStatement.setString(9, textFieldPhone.getText());
                        preparedStatement.setString(10, comboBoxPosition.getSelectedItem().toString());
                        preparedStatement.setInt(11, comboBoxDepartment.getSelectedIndex() + 1);
                        preparedStatement.executeUpdate();

                        if (preparedStatement.getWarnings() != null) {
                            throw new Exception(preparedStatement.getWarnings().getMessage());
                        }

                        JOptionPane.showMessageDialog(null, "Personel başarı ile güncellendi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                        clearPage();
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
                int reply = JOptionPane.showConfirmDialog(null, "Personel ve Bütün Harcamaları Silinecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        String user = (String) table.getValueAt(table.getSelectedRow(), 0);
                        Statement st = dbc.createStatement();
                        String sql = "DELETE FROM personnel WHERE user_name = '" + user + "'";
                        st.executeUpdate(sql);
                        updateTable();
                        clearPage();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                clearPage();
                btnAdd.setEnabled(false);
            }
        });

        updateDepartmentTypes();
    }

    private void clearPage() {
        textFieldUsername.setText("");
        textFieldPassword.setText("");
        textFieldName.setText("");
        textFieldSurname.setText("");
        textFieldMail.setText("");
        textFieldPhone.setText("");
        textFieldTcNo.setText("");
        dateChooser.setDate(new Date());
        comboBoxDepartment.setSelectedIndex(0);
        comboBoxPosition.setSelectedIndex(0);
        comboBoxSex.setSelectedIndex(0);
    }

    private void fillPersonnelInfo() {
        try {
            String user = (String) table.getValueAt(table.getSelectedRow(), 0);
            Statement st = dbc.createStatement();
            String sql = "SELECT user_name, password, name, surname, tc_no, birthdate, sex, phone, email, position, dep_id  FROM personnel "
                    + "WHERE user_name = '" + user + "'";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                textFieldUsername.setText(rs.getString("user_name"));
                textFieldPassword.setText(rs.getString("password"));
                textFieldName.setText(rs.getString("name"));
                textFieldSurname.setText(rs.getString("surname"));
                textFieldTcNo.setText(rs.getString("tc_no"));
                dateChooser.setDate(new Date(rs.getDate("birthdate").getTime()));
                comboBoxSex.setSelectedItem(rs.getString("sex"));
                textFieldPhone.setText(rs.getString("phone"));
                textFieldMail.setText(rs.getString("email"));
                comboBoxPosition.setSelectedItem(rs.getString("position"));
                comboBoxDepartment.setSelectedIndex(rs.getInt("dep_id") - 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateDepartmentTypes() {
        try {
            String spendingTypes[];
            Statement st = dbc.createStatement();
            String sql = "SELECT count(*) FROM department";
            ResultSet rs = st.executeQuery(sql);
            int num = 0;
            while (rs.next()) {
                num = rs.getInt(1);
            }
            spendingTypes = new String[num];
            sql = "SELECT dep_name FROM department";
            rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                spendingTypes[i++] = rs.getString("dep_name");
            }
            comboBoxDepartment.setModel(new DefaultComboBoxModel<String>(spendingTypes));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("serial")
    void setTableDefault() {
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Kullanıcı Adı", "Adı Soyadı", "Telefon", "Email"
                }
        ) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void updateTable() {
        try {
            setTableDefault();
            Statement st = dbc.createStatement();
            String sql = "SELECT user_name, name, surname, phone, email  FROM personnel ";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String uName = rs.getString("user_name");
                String name = rs.getString("name") + " " + rs.getString("surname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                Object[] row = {uName, name, phone, email};
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
