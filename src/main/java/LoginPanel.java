import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPanel extends JPanel {

    private JButton buttonLogin;
    private JTextField textFieldUserName;
    private JPasswordField textFieldPassword;
    private JLabel labelLogo;
    private Connection dbc;

    /**
     * Create the panel.
     */
    public LoginPanel(Frame m, Connection dbc) {
        this.dbc = dbc;
        setOpaque(false);
        setPreferredSize(new Dimension(250, 300));
        setLayout(null);


        buttonLogin = new JButton("");
        buttonLogin.setIcon(new ImageIcon(LoginPanel.class.getResource("/Images/Buttons/LoginButton.png")));
        buttonLogin.setBounds(50, 250, 150, 38);
        buttonLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonLogin.setFocusPainted(false);
        buttonLogin.setBorderPainted(false);
        buttonLogin.setContentAreaFilled(false);


        textFieldUserName = new JTextField("Kullanıcı Adı");
        textFieldUserName.setForeground(Color.LIGHT_GRAY);
        textFieldUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldUserName.setBounds(10, 130, 230, 38);
        textFieldUserName.setColumns(10);

        textFieldPassword = new JPasswordField("Password");
        textFieldPassword.setForeground(Color.LIGHT_GRAY);
        textFieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPassword.setBounds(10, 190, 230, 38);
        textFieldPassword.setColumns(10);

        labelLogo = new JLabel("");
        labelLogo.setIcon(new ImageIcon(LoginPanel.class.getResource("/Images/logo.png")));
        labelLogo.setBounds(50, 11, 150, 100);

        textFieldUserName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                if (textFieldUserName.getText().equals("Kullanıcı Adı")) {
                    textFieldUserName.setForeground(Color.BLACK);
                    textFieldUserName.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                if (textFieldUserName.getText().equals("")) {
                    textFieldUserName.setForeground(Color.LIGHT_GRAY);
                    textFieldUserName.setText("Kullanıcı Adı");
                }
            }
        });

        textFieldPassword.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent arg0) {
                if (textFieldPassword.getText().equals("Password")) {
                    textFieldPassword.setForeground(Color.BLACK);
                    textFieldPassword.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent arg0) {
                if (textFieldPassword.getText().equals("")) {
                    textFieldPassword.setForeground(Color.LIGHT_GRAY);
                    textFieldPassword.setText("Password");
                }
            }
        });

        buttonLogin.addActionListener(arg0 -> {
            String username = textFieldUserName.getText();
            String password = String.valueOf(textFieldPassword.getPassword());
            String result = checkInput(username, password, m);
            if (Log.isManager) {
                if (result != null) {
                    switch (result) {
                        case "Admin": {
                            m.dispose();
                            try {
                                AdminWindow aw = new AdminWindow(dbc);
                                aw.getFrame().setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case "Yönetici": {
                            m.dispose();
                            try {
                                ManagerWindow mw = new ManagerWindow(username, dbc);
                                mw.getFrame().setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        case "Muhasebeci": {
                            m.dispose();
                            try {
                                AccountantWindow acw = new AccountantWindow(username, dbc);
                                acw.getFrame().setVisible(true);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        }
                        default: {
                            JOptionPane.showMessageDialog(m, "Girilen kullanıcı adı ya da şifre hatalıdır", "Hata", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                    }
                }
            } else {
                if (result != null) {
                    m.dispose();
                    try {
                        PersonnelWindow pw = new PersonnelWindow(username, dbc);
                        pw.getFrame().setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });


        createPanel();
    }

    public void createPanel() {
        add(buttonLogin);
        add(textFieldUserName);
        add(textFieldPassword);
        add(labelLogo);
    }

    public void destroyPanel() {
        removeAll();
    }

    public String checkInput(String username, String password, Frame m) {
        if (username.isEmpty() || password.isEmpty() || username.toLowerCase().equals("kullanıcı adı") || password.toLowerCase().equals("password")) {
            JOptionPane.showMessageDialog(m, "Lütfen Kullanıcı adını ve Şifreyi giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            String sql = "SELECT password,position FROM personnel WHERE user_name = ? ";
            PreparedStatement ps = dbc.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String passDb = rs.getString("password");
                String pos = rs.getString("position");
                if (password.equals(passDb)) {
                    return pos;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(m, "Girilen kullanıcı adı ya da şifre hatalıdır", "Hata", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}
