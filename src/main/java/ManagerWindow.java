import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Date;

public class ManagerWindow {

    JTable table;
    private JFrame frame;
    private Connection dbc;
    private JButton buttonLogout;
    private JPanel panelInfo;
    private JLabel lblDepartmanBilgileri;
    private JTextPane textPane_1;
    private JLabel lblYneticiBilgileri;
    private JTextPane textPane_2;
    private JLabel lblSpendingLogo;
    private JButton buttonApply;
    private JButton buttonDeny;
    private String username;
    private JTextPane textPaneDetail;

    /**
     * Create the application.
     */
    public ManagerWindow(String username, Connection dbc) {
        this.username = username;
        this.dbc = dbc;
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

        lblSpendingLogo = new JLabel("");
        lblSpendingLogo.setBounds(10, 11, 83, 80);
        lblSpendingLogo.setIcon(new ImageIcon(ManagerWindow.class.getResource("/Images/SpendingLogo.png")));
        frame.getContentPane().add(lblSpendingLogo);

        panelInfo = new JPanel();
        panelInfo.setBounds(624, 11, 300, 589);
        frame.getContentPane().add(panelInfo);
        panelInfo.setLayout(null);

        buttonLogout = new JButton("");
        buttonLogout.setBounds(102, 554, 96, 24);
        buttonLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonLogout.setIcon(new ImageIcon(ManagerWindow.class.getResource("/Images/Buttons/LogoutButton.png")));
        buttonLogout.setFocusPainted(false);
        buttonLogout.setBorderPainted(false);
        buttonLogout.setContentAreaFilled(false);
        panelInfo.add(buttonLogout);

        String[] userInfo = DBUtils.getUserInfo(username);
        String[] departmentInfo = DBUtils.getDepInfo(username);
        String manInfo = "Adı\t: " + userInfo[0] + "\nSoyadı\t: " + userInfo[1] +
                "\nEmail\t: " + userInfo[2] + "\nTelefon\t: " + userInfo[3];
        String depInfo = "Departman\t: " + departmentInfo[0] + "\nYönetici\t: " + departmentInfo[2] +
                "\nHarcama Limiti : " + departmentInfo[1] + "\nKalan Limit\t: " + DBUtils.calculateLimitLeft(username);

        lblDepartmanBilgileri = new JLabel("Departman Bilgileri");
        lblDepartmanBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartmanBilgileri.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblDepartmanBilgileri.setBounds(10, 149, 280, 45);
        panelInfo.add(lblDepartmanBilgileri);

        textPane_1 = new JTextPane();
        textPane_1.setText(depInfo);
        textPane_1.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        textPane_1.setEditable(false);
        textPane_1.setBackground(SystemColor.menu);
        textPane_1.setAlignmentY(0.0f);
        textPane_1.setAlignmentX(0.0f);
        textPane_1.setBounds(10, 210, 280, 71);
        panelInfo.add(textPane_1);

        lblYneticiBilgileri = new JLabel("Yönetici Bilgileri");
        lblYneticiBilgileri.setHorizontalAlignment(SwingConstants.CENTER);
        lblYneticiBilgileri.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblYneticiBilgileri.setBounds(10, 11, 280, 45);
        panelInfo.add(lblYneticiBilgileri);

        textPane_2 = new JTextPane();
        textPane_2.setText(manInfo);
        textPane_2.setFont(new Font("Ubuntu", Font.PLAIN, 12));
        textPane_2.setEditable(false);
        textPane_2.setBackground(SystemColor.menu);
        textPane_2.setAlignmentY(0.0f);
        textPane_2.setAlignmentX(0.0f);
        textPane_2.setBounds(10, 67, 280, 71);
        panelInfo.add(textPane_2);

        JLabel lblAddSpending = new JLabel("Harcama Onayla");
        lblAddSpending.setForeground(Color.WHITE);
        lblAddSpending.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddSpending.setFont(new Font("Ubuntu", Font.PLAIN, 28));
        lblAddSpending.setBounds(10, 11, 604, 80);
        frame.getContentPane().add(lblAddSpending);


        buttonApply = new JButton("");
        buttonApply.setFocusPainted(false);
        buttonApply.setContentAreaFilled(false);
        buttonApply.setBorderPainted(false);
        buttonApply.setBounds(518, 413, 88, 88);
        buttonApply.setIcon(new ImageIcon(ManagerWindow.class.getResource("/Images/applyButton.png")));
        frame.getContentPane().add(buttonApply);

        buttonDeny = new JButton("");
        buttonDeny.setFocusPainted(false);
        buttonDeny.setContentAreaFilled(false);
        buttonDeny.setBorderPainted(false);
        buttonDeny.setBounds(518, 512, 88, 88);
        buttonDeny.setIcon(new ImageIcon(ManagerWindow.class.getResource("/Images/denyButton.png")));
        frame.getContentPane().add(buttonDeny);

        textPaneDetail = new JTextPane();
        textPaneDetail.setText("Seçilen harcama bilgileri");
        textPaneDetail.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        textPaneDetail.setEditable(false);
        textPaneDetail.setBackground(SystemColor.menu);
        textPaneDetail.setAlignmentY(0.0f);
        textPaneDetail.setAlignmentX(0.0f);
        textPaneDetail.setBounds(10, 413, 498, 187);
        frame.getContentPane().add(textPaneDetail);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 102, 599, 300);
        frame.getContentPane().add(scrollPane);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(table);


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                String id = (String) table.getValueAt(table.getSelectedRow(), 0);
                try {
                    Statement st = dbc.createStatement();
                    String sql = "SELECT name, surname, s_date, s_type_name, s_company, s_explanation, s_amount  FROM spending s, spendingType t, personnel p "
                            + "WHERE s.spending_id = " + id + " AND t.s_type_id = s.s_type_id AND s.user_name = p.user_name ORDER BY spending_id";
                    ResultSet rs = st.executeQuery(sql);
                    while (rs.next()) {
                        String personnel = rs.getString("name") + " " + rs.getString("surname");
                        String sDate = rs.getString("s_date");
                        String type = rs.getString("s_type_name");
                        String company = rs.getString("s_company");
                        String explanation = rs.getString("s_explanation");
                        int amount = rs.getInt("s_amount");

                        String detail = "Personel\t: " + personnel + "\nTarih\t: " + sDate + "\nFirma\t: " + company;
                        detail += "\nHarcama Türü      : " + type + "\nHarcama Miktarı : " + amount + "\nAçıklama\t: " + explanation;
                        textPaneDetail.setText(detail);

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        updateTable();

        buttonLogout.addActionListener(e -> ViewUtils.logoutAction(frame));

        buttonApply.addActionListener(arg0 -> {
            int reply = JOptionPane.showConfirmDialog(null, "Harcama Muhasebeciye İletilecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                if (DBUtils.deleteSpending(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0))) != -1) {
                    try {
                        String sql = "INSERT INTO approvedSpending (spending_id,approvel_date) VALUES (?,?)";
                        PreparedStatement ps = dbc.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
                        ps.setDate(2, new java.sql.Date((new Date()).getTime()));
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Harcama başarı ile iletildi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                        updateTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonDeny.addActionListener(arg0 -> {
            deleteSpending();
        });
        frame.setLocationRelativeTo(null);
    }

    void updateTable() {
        ResultSet rs = null;
        try {
            ViewUtils.setTableDefault(table);
            Statement st = dbc.createStatement();
            String sql = "SELECT s.spending_id, t.s_type_name, s.s_date, s.s_amount, s.user_name  FROM spending s, spendingType t, waitingSpending w "
                    + "WHERE t.s_type_id = s.s_type_id AND s.spending_id = w.spending_id AND s.dep_id = " + DBUtils.getUserDep(username) + " ORDER BY spending_id";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs != null) {
            ViewUtils.updateSpendingTable(table, rs);
        }
    }

    boolean deleteSpending() {
        int reply = JOptionPane.showConfirmDialog(null, "Harcama Tamamen Silinecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            if (DBUtils.deleteSpending(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0))) != -1) {
                JOptionPane.showMessageDialog(null, "Harcama başarı ile silindi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                updateTable();
                return true;
            }
        }
        return false;
    }
}
