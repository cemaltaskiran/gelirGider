import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class AdminWindow {

    private JFrame frame;

    private Connection dbc;
    /**
     * Initialize the contents of the frame.
     */


    private Thread moveLeft;
    private Thread showPersonnel, hidePersonnel;
    private Thread showAdmin, hideAdmin;
    private Thread showDepartment, hideDepartment;
    private Thread showLimit, hideLimit;
    private int buttonsLoc = 0;
    private JButton buttonAddPersonnel;
    private JButton buttonAddAdmin;
    private JButton buttonAddDepartment;
    private JButton buttonSpendingLimit;
    private JButton buttonLogout;
    private AddPersonPanel app;
    private Component currentComp;
    private FixPersonnelPanel aap;
    private AddDepartmentPanel adp;
    private ShowAllSpendings alp;

    /**
     * Create the application.
     */
    public AdminWindow(Connection dbc) {
        this.dbc = dbc;
        initialize();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 950, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(950, 650));
        frame.getContentPane().setBackground(new Color(47, 79, 79));
        frame.getContentPane().setLayout(null);

        buttonLogout = new JButton("");
        buttonLogout.setBounds(829, 11, 95, 24);
        buttonLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonLogout.setIcon(new ImageIcon(AdminWindow.class.getResource("/Images/Buttons/LogoutButton.png")));
        buttonLogout.setFocusPainted(false);
        buttonLogout.setBorderPainted(false);
        buttonLogout.setContentAreaFilled(false);
        frame.getContentPane().add(buttonLogout);

        buttonAddPersonnel = new JButton("");
        buttonAddPersonnel.setBounds(150, 150, 300, 100);
        buttonAddPersonnel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonAddPersonnel.setIcon(new ImageIcon(AdminWindow.class.getResource("/Images/AddPersonnelButton.png")));
        buttonAddPersonnel.setFocusPainted(false);
        buttonAddPersonnel.setBorderPainted(false);
        buttonAddPersonnel.setContentAreaFilled(false);
        frame.getContentPane().add(buttonAddPersonnel);

        buttonAddAdmin = new JButton("");
        buttonAddAdmin.setFocusPainted(false);
        buttonAddAdmin.setContentAreaFilled(false);
        buttonAddAdmin.setBorderPainted(false);
        buttonAddAdmin.setBounds(484, 150, 300, 100);
        buttonAddAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonAddAdmin.setIcon(new ImageIcon(AdminWindow.class.getResource("/Images/AddAdminButton.png")));
        frame.getContentPane().add(buttonAddAdmin);

        buttonAddDepartment = new JButton("");
        buttonAddDepartment.setFocusPainted(false);
        buttonAddDepartment.setContentAreaFilled(false);
        buttonAddDepartment.setBorderPainted(false);
        buttonAddDepartment.setBounds(150, 300, 300, 100);
        buttonAddDepartment.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonAddDepartment.setIcon(new ImageIcon(AdminWindow.class.getResource("/Images/AddDepartmentButton.png")));
        frame.getContentPane().add(buttonAddDepartment);

        buttonSpendingLimit = new JButton("");
        buttonSpendingLimit.setFocusPainted(false);
        buttonSpendingLimit.setContentAreaFilled(false);
        buttonSpendingLimit.setBorderPainted(false);
        buttonSpendingLimit.setBounds(484, 300, 300, 100);
        buttonSpendingLimit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonSpendingLimit.setIcon(new ImageIcon(AdminWindow.class.getResource("/Images/SpendingLimitButton.png")));
        frame.getContentPane().add(buttonSpendingLimit);

        createMouseListeners();
        buttonLogout.addActionListener(e -> ViewUtils.logoutAction(frame));
        frame.setLocationRelativeTo(null);
    }

    public void moveButtonsToFirstPos() {
        moveLeft = new Thread() {
            public void run() {
                try {
                    while (buttonAddPersonnel.getBounds().x > -195) {
                        buttonAddPersonnel.setLocation(buttonAddPersonnel.getBounds().x - 1, buttonAddPersonnel.getBounds().y);
                        buttonAddDepartment.setLocation(buttonAddDepartment.getBounds().x - 1, buttonAddDepartment.getBounds().y);
                        buttonAddAdmin.setLocation(buttonAddAdmin.getBounds().x + 1, buttonAddAdmin.getBounds().y);
                        buttonSpendingLimit.setLocation(buttonSpendingLimit.getBounds().x + 1, buttonSpendingLimit.getBounds().y);
                        Thread.sleep(4);
                    }

                    buttonsLoc = 1;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        moveLeft.start();
    }

    public void showPersonnelButton() {
        showPersonnel = new Thread(() -> {
            try {
                while (buttonAddPersonnel.getBounds().x < -15) {
                    buttonAddPersonnel.setLocation(buttonAddPersonnel.getBounds().x + 1, buttonAddPersonnel.getBounds().y);
                    Thread.sleep(2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        showPersonnel.start();
    }

    public void hidePersonnelButton() {
        hidePersonnel = new Thread() {
            public void run() {
                try {
                    while (buttonAddPersonnel.getBounds().x > -195) {
                        buttonAddPersonnel.setLocation(buttonAddPersonnel.getBounds().x - 1, buttonAddPersonnel.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        hidePersonnel.start();
    }

    public void showAdminButton() {
        showAdmin = new Thread() {
            public void run() {
                try {
                    while (buttonAddAdmin.getBounds().x > 650) {
                        buttonAddAdmin.setLocation(buttonAddAdmin.getBounds().x - 1, buttonAddAdmin.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        showAdmin.start();
    }

    public void hideAdminButton() {
        hideAdmin = new Thread() {
            public void run() {
                try {
                    while (buttonAddAdmin.getBounds().x < 830) {
                        buttonAddAdmin.setLocation(buttonAddAdmin.getBounds().x + 1, buttonAddAdmin.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        hideAdmin.start();
    }

    //*****************

    public void showDepartmentButton() {
        showDepartment = new Thread() {
            public void run() {
                try {
                    while (buttonAddDepartment.getBounds().x < -15) {
                        buttonAddDepartment.setLocation(buttonAddDepartment.getBounds().x + 1, buttonAddDepartment.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        showDepartment.start();
    }

    public void hideDepartmentButton() {
        hideDepartment = new Thread() {
            public void run() {
                try {
                    while (buttonAddDepartment.getBounds().x > -195) {
                        buttonAddDepartment.setLocation(buttonAddDepartment.getBounds().x - 1, buttonAddDepartment.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        hideDepartment.start();
    }


    //**********
    public void showLimitButton() {
        showLimit = new Thread() {
            public void run() {
                try {
                    while (buttonSpendingLimit.getBounds().x > 650) {
                        buttonSpendingLimit.setLocation(buttonSpendingLimit.getBounds().x - 1, buttonSpendingLimit.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        showLimit.start();
    }

    public void hideLimitButton() {
        hideLimit = new Thread() {
            public void run() {
                try {
                    while (buttonSpendingLimit.getBounds().x < 830) {
                        buttonSpendingLimit.setLocation(buttonSpendingLimit.getBounds().x + 1, buttonSpendingLimit.getBounds().y);
                        Thread.sleep(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        hideLimit.start();
    }


    public void createMouseListeners() {
        //**************Personel Button********************************
        buttonAddPersonnel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (buttonsLoc == 0) {
                    app = new AddPersonPanel(dbc);
                    app.setBounds(155, 30, 625, 550);
                    moveButtonsToFirstPos();
                    frame.getContentPane().add(app);
                    app.validate();
                    frame.repaint();
                    currentComp = app;
                } else {
                    app = new AddPersonPanel(dbc);
                    app.setBounds(155, 30, 625, 550);
                    frame.remove(currentComp);
                    frame.getContentPane().add(app);
                    app.validate();
                    frame.repaint();
                    currentComp = app;
                }


            }
        });
        buttonAddPersonnel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (hidePersonnel != null) {
                        hidePersonnel.stop();
                    }
                    showPersonnelButton();
                }
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (showPersonnel != null) {
                        showPersonnel.stop();
                    }
                    hidePersonnelButton();
                }
            }
        });

        //**************Admin Button***********************************
        buttonAddAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (buttonsLoc == 0) {
                    aap = new FixPersonnelPanel(dbc);
                    aap.setBounds(155, 30, 625, 550);
                    moveButtonsToFirstPos();
                    frame.getContentPane().add(aap);
                    aap.validate();
                    frame.repaint();
                    currentComp = aap;
                } else {
                    aap = new FixPersonnelPanel(dbc);
                    aap.setBounds(155, 30, 625, 550);
                    frame.remove(currentComp);
                    frame.getContentPane().add(aap);
                    aap.validate();
                    frame.repaint();
                    currentComp = aap;
                }


            }
        });
        buttonAddAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (hideAdmin != null) {
                        hideAdmin.stop();
                    }
                    showAdminButton();
                }
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (showAdmin != null) {
                        showAdmin.stop();
                    }
                    hideAdminButton();
                }
            }
        });

        //**************Department Button******************************
        buttonAddDepartment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (buttonsLoc == 0) {
                    adp = new AddDepartmentPanel(dbc);
                    adp.setBounds(155, 30, 625, 550);
                    moveButtonsToFirstPos();
                    frame.getContentPane().add(adp);
                    adp.validate();
                    frame.repaint();
                    currentComp = adp;
                } else {
                    adp = new AddDepartmentPanel(dbc);
                    adp.setBounds(155, 30, 625, 550);
                    frame.remove(currentComp);
                    frame.getContentPane().add(adp);
                    adp.validate();
                    frame.repaint();
                    currentComp = adp;
                }
            }
        });
        buttonAddDepartment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (hideDepartment != null) {
                        hideDepartment.stop();
                    }
                    showDepartmentButton();
                }
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (showDepartment != null) {
                        showDepartment.stop();
                    }
                    hideDepartmentButton();
                }
            }
        });

        //**************Limit Button***********************************
        buttonSpendingLimit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (buttonsLoc == 0) {
                    alp = new ShowAllSpendings(dbc);
                    alp.setBounds(155, 30, 625, 550);
                    moveButtonsToFirstPos();
                    frame.getContentPane().add(alp);
                    alp.validate();
                    frame.repaint();
                    currentComp = alp;
                } else {
                    alp = new ShowAllSpendings(dbc);
                    alp.setBounds(155, 30, 625, 550);
                    frame.remove(currentComp);
                    frame.getContentPane().add(alp);
                    alp.validate();
                    frame.repaint();
                    currentComp = alp;
                }
            }
        });
        buttonSpendingLimit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (hideLimit != null) {
                        hideLimit.stop();
                    }
                    showLimitButton();
                }
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                if (buttonsLoc != 0) {
                    if (showLimit != null) {
                        showLimit.stop();
                    }
                    hideLimitButton();
                }
            }
        });

    }
}
