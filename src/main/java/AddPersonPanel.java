import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddPersonPanel extends JPanel {
    private JTextField textFieldName;
    private JTextField textFieldSurname;
    private JTextField textFieldTcNo;
    private JTextField textFieldPhone;
    private JTextField textFieldMail;
    private JComboBox<String> comboBoxSex;
    private JComboBox<String> comboBoxDepartment;
    private JButton btnAdd;
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
    public AddPersonPanel(Connection dbc) {
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

        JLabel lblCinsiyet = new JLabel("Cinsiyet");
        lblCinsiyet.setForeground(Color.WHITE);
        lblCinsiyet.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblCinsiyet.setBounds(10, 209, 110, 24);
        add(lblCinsiyet);

        comboBoxSex = new JComboBox<>();
        comboBoxSex.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        comboBoxSex.setBounds(130, 209, 175, 24);
        comboBoxSex.setModel(new DefaultComboBoxModel<>(new String[]{"Erkek", "Kadın"}));
        add(comboBoxSex);

        JLabel lblDepartman = new JLabel("Departman");
        lblDepartman.setForeground(Color.WHITE);
        lblDepartman.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblDepartman.setBounds(320, 209, 110, 24);
        add(lblDepartman);


        btnAdd = new JButton("EKLE");
        btnAdd.setBounds(505, 244, 110, 23);
        add(btnAdd);

        JLabel lblKullancAd = new JLabel("Kullanıcı Adı");
        lblKullancAd.setForeground(Color.WHITE);
        lblKullancAd.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblKullancAd.setBounds(10, 66, 110, 24);
        add(lblKullancAd);

        textFieldUsername = new JTextField();
        textFieldUsername.setColumns(10);
        textFieldUsername.setBounds(130, 66, 175, 24);
        add(textFieldUsername);

        lblifre = new JLabel("Şifre");
        lblifre.setForeground(Color.WHITE);
        lblifre.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblifre.setBounds(320, 66, 110, 24);
        add(lblifre);

        textFieldPassword = new JTextField();
        textFieldPassword.setColumns(10);
        textFieldPassword.setBounds(440, 66, 175, 24);
        add(textFieldPassword);

        comboBoxDepartment = new JComboBox<>();
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

        comboBoxPosition = new JComboBox<>();
        comboBoxPosition.setModel(new DefaultComboBoxModel(new String[]{"Personel", "Yönetici", "Muhasebeci", "Admin"}));
        comboBoxPosition.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        comboBoxPosition.setBounds(130, 244, 175, 24);
        add(comboBoxPosition);

        btnClear = new JButton("TEMİZLE");
        btnClear.setBounds(385, 244, 110, 23);
        add(btnClear);

        JLabel lblNewLabel_2 = new JLabel();
        lblNewLabel_2.setBounds(10, 279, 605, 260);
        lblNewLabel_2.setIcon(new ImageIcon(AddPersonPanel.class.getResource("/Images/addPersonBck.png")));
        add(lblNewLabel_2);

        btnAdd.addActionListener(arg0 -> {
            int reply = JOptionPane.showConfirmDialog(null, "Personel Eklenecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                String username = textFieldUsername.getText();
                String password = textFieldPassword.getText();
                String name = textFieldName.getText();
                String surname = textFieldSurname.getText();
                String tcNo = textFieldTcNo.getText();
                Date birthDate = dateChooser.getDate();
                String sex = comboBoxSex.getSelectedItem().toString();
                String phoneNumber = textFieldPhone.getText();
                String position = comboBoxPosition.getSelectedItem().toString();
                String mail = textFieldMail.getText();
                int departmentId = comboBoxDepartment.getSelectedIndex() + 1;
                insertPersonal(username, password, name, surname, tcNo, birthDate, sex, phoneNumber, position, mail,
                        departmentId);
            }
        });

        btnClear.addActionListener(arg0 -> clearPage());

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

    public boolean insertPersonal(String username, String password, String name, String surname, String tcNo,
                                  Date birthDate, String sex, String phoneNumber, String position, String mail,
                                  int departmentId) {
        try {
            LocalDate birthLocalDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (username.equals("")) {
                throw new Exception("Kullanıcı adı boş olamaz!");
            } else if (password.equals("")) {
                throw new Exception("Şifre boş olamaz!");
            } else if (name.equals("")) {
                throw new Exception("Ad boş olamaz!");
            } else if (surname.equals("")) {
                throw new Exception("Soyad boş olamaz!");
            } else if (tcNo.equals("")) {
                throw new Exception("TC no boş olamaz!");
            } else if (birthLocalDate.getYear() > (new Date().toInstant().atZone(ZoneId.systemDefault()).getYear() - 18)) {
                throw new Exception("Personel 18 yaşından küçük olamaz");
            } else if (phoneNumber.equals("")) {
                throw new Exception("Telefon boş olamaz!");
            } else if (mail.equals("")) {
                throw new Exception("Mail boş olamaz!");
            }

            String sql = "INSERT INTO personnel (user_name,password,name,surname,tc_no,birthdate,sex,email,phone,position,dep_id)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = dbc.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, tcNo);
            preparedStatement.setDate(6, new java.sql.Date(birthDate.getTime()));
            preparedStatement.setString(7, sex);
            preparedStatement.setString(8, mail);
            preparedStatement.setString(9, phoneNumber);
            preparedStatement.setString(10, position);
            preparedStatement.setInt(11, departmentId);
            preparedStatement.executeUpdate();

            if (preparedStatement.getWarnings() != null) {
                throw new Exception(preparedStatement.getWarnings().getMessage());
            }

            JOptionPane.showMessageDialog(null, "Personel başarı ile eklendi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
            clearPage();
            return true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "HATA", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
