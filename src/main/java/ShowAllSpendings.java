import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class ShowAllSpendings extends JPanel {

    private JTable table;
    private Connection dbc;
    private JTextPane textPaneDetail;
    private JButton btnAllSpending;
    private JButton btnWaitingSpending;
    private JButton btnApproved;
    private JButton btnPaid;
    private JButton button;
    private JButton button_1;
    private JButton buttonApply;
    private JButton buttonDeny;
    private JLabel label;

    /**
     * Create the panel.
     */
    public ShowAllSpendings(Connection dbc) {
        setOpaque(false);
        this.dbc = dbc;
        setSize(625, 550);
        setLayout(null);

        JLabel lblNewLabel = new JLabel("Harcamaları Görüntüle");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Ubuntu", Font.PLAIN, 22));
        lblNewLabel.setBounds(10, 11, 605, 44);
        add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(10, 100, 605, 233);
        add(scrollPane);

        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTableDefault();
        updateTable();
        scrollPane.setViewportView(table);

        textPaneDetail = new JTextPane();
        textPaneDetail.setOpaque(false);
        textPaneDetail.setForeground(Color.WHITE);
        textPaneDetail.setText("Seçilen harcama bilgileri");
        textPaneDetail.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        textPaneDetail.setEditable(false);
        textPaneDetail.setBackground(SystemColor.menu);
        textPaneDetail.setAlignmentY(0.0f);
        textPaneDetail.setAlignmentX(0.0f);
        textPaneDetail.setBounds(10, 344, 507, 195);
        add(textPaneDetail);

        btnAllSpending = new JButton("Bütün Harcamalar");
        btnAllSpending.setBounds(10, 66, 143, 23);
        add(btnAllSpending);

        btnWaitingSpending = new JButton("Bekleyen Harcamalar");
        btnWaitingSpending.setBounds(163, 66, 143, 23);
        add(btnWaitingSpending);

        btnApproved = new JButton("Onaylanmı\u015F Harcamalar");
        btnApproved.setBounds(316, 66, 150, 23);
        add(btnApproved);

        btnPaid = new JButton("\u00D6denmi\u015F Harcamalar");
        btnPaid.setBounds(472, 66, 143, 23);
        add(btnPaid);

        buttonDeny = new JButton("");
        buttonDeny.setToolTipText("Harcamayı Sil");
        buttonDeny.setFocusPainted(false);
        buttonDeny.setContentAreaFilled(false);
        buttonDeny.setBorderPainted(false);
        buttonDeny.setBounds(527, 451, 88, 88);
        buttonDeny.setIcon(new ImageIcon(ShowAllSpendings.class.getResource("/Images/denyButton.png")));
        add(buttonDeny);

        JLabel lblToplam = new JLabel("TOPLAM");
        lblToplam.setForeground(Color.WHITE);
        lblToplam.setHorizontalAlignment(SwingConstants.CENTER);
        lblToplam.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        lblToplam.setBounds(527, 399, 88, 23);
        add(lblToplam);

        label = new JLabel("TOPLAM");
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Ubuntu", Font.PLAIN, 14));
        label.setBounds(527, 375, 88, 23);
        add(label);

        buttonDeny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int reply = JOptionPane.showConfirmDialog(null, "Harcama Tamamen Silinecektir Onaylıyor Musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        String sql = "DELETE FROM spending WHERE spending_id = ? ";
                        PreparedStatement ps = dbc.prepareStatement(sql);
                        ps.setInt(1, Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Harcama başarı ile silindi.", "Onay", JOptionPane.INFORMATION_MESSAGE);
                        updateTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnAllSpending.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTable();
                try {
                    Date d = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    int month = cal.get(Calendar.MONTH) + 1;
                    Statement st = dbc.createStatement();
                    String sql = "SELECT sum(s_amount) FROM spending s "
                            + "WHERE EXTRACT(month FROM \"s_date\") = " + month;
                    ResultSet rs = st.executeQuery(sql);
                    int num = 0;
                    if (rs.next()) {
                        num = rs.getInt(1);
                    }
                    lblToplam.setText(num + " TL");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnWaitingSpending.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTableWaiting();
                try {
                    Date d = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    int month = cal.get(Calendar.MONTH) + 1;
                    Statement st = dbc.createStatement();
                    String sql = "SELECT sum(s_amount) FROM spending s, waitingSpending w "
                            + "WHERE w.spending_id = s.spending_id AND EXTRACT(month FROM \"s_date\") = " + month;
                    ResultSet rs = st.executeQuery(sql);
                    int num = 0;
                    if (rs.next()) {
                        num = rs.getInt(1);
                    }
                    lblToplam.setText(num + " TL");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnApproved.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTableApproved();
                try {
                    Date d = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    int month = cal.get(Calendar.MONTH) + 1;
                    Statement st = dbc.createStatement();
                    String sql = "SELECT sum(s_amount) FROM spending s, approvedSpending w "
                            + "WHERE w.spending_id = s.spending_id AND EXTRACT(month FROM \"s_date\") = " + month;
                    ResultSet rs = st.executeQuery(sql);
                    int num = 0;
                    if (rs.next()) {
                        num = rs.getInt(1);
                    }
                    lblToplam.setText(num + " TL");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPaid.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTablePaid();
                try {
                    Date d = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(d);
                    int month = cal.get(Calendar.MONTH) + 1;
                    Statement st = dbc.createStatement();
                    String sql = "SELECT sum(s_amount) FROM spending s, paidSpending w "
                            + "WHERE w.spending_id = s.spending_id AND EXTRACT(month FROM \"s_date\") = " + month;
                    ResultSet rs = st.executeQuery(sql);
                    int num = 0;
                    if (rs.next()) {
                        num = rs.getInt(1);
                    }
                    lblToplam.setText(num + " TL");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


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

    }

    private void updateTable() {
        ResultSet rs = null;
        try {
            setTableDefault();
            Statement st = dbc.createStatement();
            String sql = "SELECT s.spending_id, t.s_type_name, s.s_date, s.s_amount, s.user_name  FROM spending s, spendingType t "
                    + "WHERE t.s_type_id = s.s_type_id ORDER BY spending_id";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            ViewUtils.updateSpendingTable(table, rs);
        }
    }

    private void updateTableApproved() {
        ResultSet rs = null;
        try {
            setTableDefault();
            Statement st = dbc.createStatement();
            String sql = "SELECT s.spending_id, t.s_type_name, s.s_date, s.s_amount, s.user_name  FROM spending s, spendingType t, approvedSpending p "
                    + "WHERE t.s_type_id = s.s_type_id AND p.spending_id = s.spending_id ORDER BY spending_id";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            ViewUtils.updateSpendingTable(table, rs);
        }
    }

    private void updateTableWaiting() {
        ResultSet rs = null;
        try {
            setTableDefault();
            Statement st = dbc.createStatement();
            String sql = "SELECT s.spending_id, t.s_type_name, s.s_date, s.s_amount, s.user_name  FROM spending s, spendingType t, waitingSpending p "
                    + "WHERE t.s_type_id = s.s_type_id AND p.spending_id = s.spending_id ORDER BY spending_id";
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            ViewUtils.updateSpendingTable(table, rs);
        }
    }

    private void updateTablePaid() {
        ResultSet rs = null;
        try {
            setTableDefault();
            Statement st = dbc.createStatement();
            String sql = "SELECT s.spending_id, t.s_type_name, s.s_date, s.s_amount, s.user_name  FROM spending s, spendingType t, paidSpending p "
                    + "WHERE t.s_type_id = s.s_type_id AND p.spending_id = s.spending_id ORDER BY spending_id";
            rs = st.executeQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) {
            ViewUtils.updateSpendingTable(table, rs);
        }
    }

    @SuppressWarnings("serial")
    private void setTableDefault() {
        ViewUtils.setTableDefault(table);
    }
}
