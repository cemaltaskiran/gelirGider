import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PersonnelWindow {

    private JFrame frame;
    private Connection dbc;
    private JTextField textFieldCompany;
    private String username;
    private JComboBox comboBoxSpendingTypes;
    private JDateChooser dateChooser;
    private JFormattedTextField textFieldAmount;
    private JTextArea textAreaExplanation;

    /**
     * Create the application.
     */
    public PersonnelWindow(String username, Connection dbc) {
        this.dbc = dbc;
        this.username = username;
        initialize();
    }

    public JFrame getFrame() {
        return frame;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 950, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(950, 650));
        frame.getContentPane().setBackground(new Color(47, 79, 79));
        frame.getContentPane().setLayout(null);

        JLabel lblSpendingLogo = new JLabel("");
        lblSpendingLogo.setBounds(10, 11, 83, 80);
        lblSpendingLogo.setIcon(new ImageIcon(PersonnelWindow.class.getResource("/Images/SpendingLogo.png")));
        frame.getContentPane().add(lblSpendingLogo);

        JLabel lblSpendingLogo2 = new JLabel("");
        lblSpendingLogo2.setBounds(330, 123, 284, 120);
        lblSpendingLogo2.setIcon(new ImageIcon(PersonnelWindow.class.getResource("/Images/SpendingLogo2.png")));
        frame.getContentPane().add(lblSpendingLogo2);

        JPanel panelInfo = new JPanel();
        panelInfo.setBounds(624, 11, 300, 589);
        frame.getContentPane().add(panelInfo);
        panelInfo.setLayout(null);

        JButton buttonLogout = new JButton("");
        buttonLogout.setBounds(102, 554, 96, 24);
        buttonLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonLogout.setIcon(new ImageIcon(PersonnelWindow.class.getResource("/Images/Buttons/LogoutButton.png")));
        buttonLogout.setFocusPainted(false);
        buttonLogout.setBorderPainted(false);
        buttonLogout.setContentAreaFilled(false);
        panelInfo.add(buttonLogout);

        JLabel lblNewLabel = new JLabel("Personel Bilgileri");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblNewLabel.setBounds(10, 11, 280, 45);
        panelInfo.add(lblNewLabel);

        String userInfo[] = getUserInfo();
        String departmentInfo[] = getDepInfo();
        String managerInfo[] = getManInfo();
        String info = "Adı\t: " + userInfo[0] + "\nSoyadı\t: " + userInfo[1] +
                "\nEmail\t: " + userInfo[2] + "\nTelefon\t: " + userInfo[3];
        String depInfo = "Departman\t: " + departmentInfo[0] + "\nYönetici\t: " + departmentInfo[2] +
                "\nHarcama Limiti : " + departmentInfo[1] + "\nKalan Limit\t: " + DBUtils.calculateLimitLeft(username);
        String manInfo = "Adı\t: " + managerInfo[0] + "\nSoyadı\t: " + managerInfo[1] +
                "\nEmail\t: " + managerInfo[2] + "\nTelefon\t: " + managerInfo[3];

        JTextPane textPane = new JTextPane();
        textPane.setBackground(SystemColor.control);
        textPane.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        textPane.setAlignmentY(Component.TOP_ALIGNMENT);
        textPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        textPane.setEditable(false);
        textPane.setBounds(10, 67, 280, 71);
        textPane.setText(info);
        panelInfo.add(textPane);

        JLabel lblDepartmanBilgileri = new JLabel("Departman Bilgileri");
        lblDepartmanBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartmanBilgileri.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblDepartmanBilgileri.setBounds(10, 149, 280, 45);
        panelInfo.add(lblDepartmanBilgileri);

        JTextPane textPane_1 = new JTextPane();
        textPane_1.setText(depInfo);
        textPane_1.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        textPane_1.setEditable(false);
        textPane_1.setBackground(SystemColor.menu);
        textPane_1.setAlignmentY(0.0f);
        textPane_1.setAlignmentX(0.0f);
        textPane_1.setBounds(10, 210, 280, 71);
        panelInfo.add(textPane_1);

        JLabel lblYneticiBilgileri = new JLabel("Yönetici Bilgileri");
        lblYneticiBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
        lblYneticiBilgileri.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblYneticiBilgileri.setBounds(10, 292, 280, 45);
        panelInfo.add(lblYneticiBilgileri);

        JTextPane textPane_2 = new JTextPane();
        textPane_2.setText(manInfo);
        textPane_2.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        textPane_2.setEditable(false);
        textPane_2.setBackground(SystemColor.menu);
        textPane_2.setAlignmentY(0.0f);
        textPane_2.setAlignmentX(0.0f);
        textPane_2.setBounds(10, 353, 280, 71);
        panelInfo.add(textPane_2);

        JLabel lblAddSpending = new JLabel("Harcama Ekle");
        lblAddSpending.setForeground(Color.WHITE);
        lblAddSpending.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddSpending.setFont(new Font("Ubuntu", Font.PLAIN, 28));
        lblAddSpending.setBounds(10, 11, 604, 80);
        frame.getContentPane().add(lblAddSpending);

        JLabel lblSpendingType = new JLabel("Harcama Türü");
        lblSpendingType.setForeground(Color.WHITE);
        lblSpendingType.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        lblSpendingType.setBounds(10, 123, 150, 24);
        frame.getContentPane().add(lblSpendingType);

        JLabel lblSpendingDate = new JLabel("Harcama Tarihi");
        lblSpendingDate.setForeground(Color.WHITE);
        lblSpendingDate.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        lblSpendingDate.setBounds(10, 171, 150, 24);
        frame.getContentPane().add(lblSpendingDate);

        JLabel lblSpendingAmount = new JLabel("Harcama Miktarı");
        lblSpendingAmount.setForeground(Color.WHITE);
        lblSpendingAmount.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        lblSpendingAmount.setBounds(10, 219, 150, 24);
        frame.getContentPane().add(lblSpendingAmount);

        JLabel lblCompany = new JLabel("Firma");
        lblCompany.setForeground(Color.WHITE);
        lblCompany.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        lblCompany.setBounds(10, 267, 150, 24);
        frame.getContentPane().add(lblCompany);

        JLabel lblExplanation = new JLabel("A\u00E7ıklama");
        lblExplanation.setForeground(Color.WHITE);
        lblExplanation.setFont(new Font("Ubuntu", Font.PLAIN, 18));
        lblExplanation.setBounds(10, 315, 150, 24);
        frame.getContentPane().add(lblExplanation);

        comboBoxSpendingTypes = new JComboBox();
        comboBoxSpendingTypes.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        comboBoxSpendingTypes.setBounds(170, 123, 150, 24);
        frame.getContentPane().add(comboBoxSpendingTypes);

        dateChooser = new JDateChooser();
        dateChooser.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        dateChooser.setDateFormatString("dd / MMMM / yyyy");
        dateChooser.setDate(new Date());
        dateChooser.setBounds(170, 171, 150, 24);
        frame.getContentPane().add(dateChooser);

        NumberFormat format = DecimalFormat.getInstance();
        format.setGroupingUsed(false);
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(10000);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);

        textFieldAmount = new JFormattedTextField(formatter);
        textFieldAmount.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        textFieldAmount.setAlignmentX(Component.RIGHT_ALIGNMENT);
        textFieldAmount.setBounds(170, 219, 150, 24);
        textFieldAmount.setText(0 + "");
        frame.getContentPane().add(textFieldAmount);

        textFieldCompany = new JTextField();
        textFieldCompany.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        textFieldCompany.setBounds(170, 267, 444, 24);
        frame.getContentPane().add(textFieldCompany);
        textFieldCompany.setColumns(10);

        textAreaExplanation = new JTextArea();
        textAreaExplanation.setLineWrap(true);
        textAreaExplanation.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        textAreaExplanation.setBounds(169, 315, 445, 125);
        frame.getContentPane().add(textAreaExplanation);

        Component verticalStrut = Box.createVerticalStrut(20);
        verticalStrut.setBounds(10, 158, 310, 2);
        frame.getContentPane().add(verticalStrut);

        Component verticalStrut_1 = Box.createVerticalStrut(20);
        verticalStrut_1.setBounds(10, 206, 310, 2);
        frame.getContentPane().add(verticalStrut_1);

        Component verticalStrut_2 = Box.createVerticalStrut(20);
        verticalStrut_2.setBounds(10, 254, 310, 2);
        frame.getContentPane().add(verticalStrut_2);

        Component verticalStrut_3 = Box.createVerticalStrut(20);
        verticalStrut_3.setBounds(10, 302, 310, 2);
        frame.getContentPane().add(verticalStrut_3);

        Component verticalStrut_4 = Box.createVerticalStrut(20);
        verticalStrut_4.setBounds(10, 451, 604, 2);
        frame.getContentPane().add(verticalStrut_4);

        JButton btnAdd = new JButton("EKLE");
        btnAdd.setBounds(464, 464, 150, 23);
        frame.getContentPane().add(btnAdd);

        JButton btnClear = new JButton("TEM\u0130ZLE");
        btnClear.setBounds(170, 464, 150, 23);
        frame.getContentPane().add(btnClear);

        updateSpendingTypes();

        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                clearPage();
            }
        });

        btnAdd.addActionListener(arg0 -> {
            String username = this.username;
            String company = textFieldCompany.getText();
            String explanation = textAreaExplanation.getText();
            Date date = dateChooser.getDate();
            int amount = Integer.parseInt(textFieldAmount.getText());
            int spendingTypeId = comboBoxSpendingTypes.getSelectedIndex() + 1;

            addSpending(username, company, explanation, date, amount, spendingTypeId);
        });

        buttonLogout.addActionListener(e -> ViewUtils.logoutAction(frame));

        frame.setLocationRelativeTo(null);
    }

    private void clearPage() {
        comboBoxSpendingTypes.setSelectedIndex(0);
        dateChooser.setDate(new Date());
        textFieldAmount.setText(0 + "");
        textFieldCompany.setText("");
        textAreaExplanation.setText("");
    }

    private String[] getUserInfo() {
        return DBUtils.getUserInfo(username);
    }

    private String[] getDepInfo() {
        return DBUtils.getDepInfo(username);
    }

    private String[] getManInfo() {
        return DBUtils.getManInfo(username);
    }

    private void updateSpendingTypes() {
        try {
            String[] spendingTypes;
            Statement st = dbc.createStatement();
            String sql = "SELECT count(*) FROM spendingType";
            ResultSet rs = st.executeQuery(sql);
            int num = 0;
            while (rs.next()) {
                num = rs.getInt(1);
            }
            spendingTypes = new String[num];
            sql = "SELECT s_type_name FROM spendingType ORDER BY s_type_id";
            rs = st.executeQuery(sql);
            int i = 0;
            while (rs.next()) {
                spendingTypes[i++] = rs.getString("s_type_name");
            }
            comboBoxSpendingTypes.setModel(new DefaultComboBoxModel(spendingTypes));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    boolean addSpending(String username, String company, String explanation, Date date, int amount, int spendingTypeId) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        try {
            if (company.equals("")) {
                throw new Exception("Firma boş olamaz!");
            }
            if (explanation.equals("")) {
                throw new Exception("Açıklama boş olamaz!");
            }
            if (localDate.getMonth() != new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth()) {
                throw new Exception("Sadece bu ayın harcaması girilebilir!");
            }
            if (amount > DBUtils.calculateLimitLeft(username)) {
                throw new Exception("Departman limitini arttırınız, harcama eklenemedi!");
            }

            int reply = JOptionPane.showConfirmDialog(null, "Harcama Eklenecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                String sql = "INSERT INTO spending (s_amount, s_type_id, s_date, s_explanation, s_company, user_name, dep_id)" +
                        "VALUES (?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = dbc.prepareStatement(sql);
                preparedStatement.setInt(1, amount);
                preparedStatement.setInt(2, spendingTypeId);
                preparedStatement.setDate(3, new java.sql.Date(dateChooser.getDate().getTime()));
                preparedStatement.setString(4, explanation);
                preparedStatement.setString(5, company);
                preparedStatement.setString(6, username);
                preparedStatement.setInt(7, DBUtils.getUserDep(username));
                preparedStatement.executeUpdate();

                if (preparedStatement.getWarnings() != null) {
                    throw new Exception(preparedStatement.getWarnings().getMessage());
                }

                JOptionPane.showMessageDialog(null, "Harcama başarı ile eklendi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                clearPage();
                return true;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "HATA", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
