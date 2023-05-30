import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVHSCounseling extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel errorMessageLabel;
    private JPanel side;
    private JPanel main;

    private List<String> passwordList;

    public AVHSCounseling() {
        setTitle("AVHS Counseling");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setMinimumSize(new Dimension(500, 300));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        passwordList = new ArrayList<>(Arrays.asList(
                "skafsjaifjawj",
                "hfwujnfwqjfngqjkf",
                "fjhaugfbnujgnajgne",
                "fnejgnaejgnajk",
                "k",
                "amadorvalley",
                ""));

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                side.setPreferredSize(new Dimension(getWidth() * 20 / 100, getHeight()));
                revalidate();
                repaint();
            }
        });

        showLoginPanel();
    }

    private void showLoginPanel() {
        getContentPane().removeAll();
        side = new JPanel();
        side.setBackground(new Color(0, 51, 102));
        side.setPreferredSize(new Dimension(getWidth() * 20 / 100, getHeight()));
        side.setLayout(new GridLayout(11, 1, 0, 10));
        side.setBorder(new EmptyBorder(20, 10, 20, 10));
        JLabel emailLabel = new JLabel("       School Email:");
        emailLabel.setFont(emailLabel.getFont().deriveFont(Font.BOLD, 23f));
        emailLabel.setForeground(new Color(238, 238, 238));
        side.add(emailLabel);
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(300, 30));
        emailField.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102)));
        emailField.setOpaque(true);
        emailField.setBackground(Color.WHITE);
        emailField.setForeground(new Color(0, 51, 102));
        emailField.setFont(emailField.getFont().deriveFont(16f));
        emailField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        emailField.setCaretColor(new Color(0, 51, 102));
        emailField.setUI(new RoundCornerTextFieldUI(10));
        side.add(emailField);
        JLabel passwordLabel = new JLabel("        Password:");
        passwordLabel.setFont(passwordLabel.getFont().deriveFont(Font.BOLD, 23f));
        passwordLabel.setForeground(new Color(238, 238, 238));
        side.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(300, 30));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(0, 51, 102)));
        passwordField.setOpaque(true);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(new Color(0, 51, 102));
        passwordField.setFont(passwordField.getFont().deriveFont(16f));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setCaretColor(new Color(0, 51, 102));
        passwordField.setUI(new RoundCornerPasswordFieldUI(10));
        side.add(passwordField);
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.setFont(loginButton.getFont().deriveFont(Font.BOLD, 30f));
        Color customLogin = new Color(0, 51, 102);
        loginButton.setForeground(customLogin);
        loginButton.setBackground(new Color(238, 238, 238));
        loginButton.setUI(new RoundedCornerButtonUI(10));
        side.add(loginButton);
        errorMessageLabel = new JLabel();
        errorMessageLabel.setFont(errorMessageLabel.getFont().deriveFont(Font.BOLD, 19f));
        errorMessageLabel.setForeground(Color.RED);
        errorMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        side.add(errorMessageLabel);
        add(side, BorderLayout.WEST);
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("amador.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 40, 10, 40);
        add(main, BorderLayout.CENTER);
        ActionListener loginAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        };
        loginButton.addActionListener(loginAction);
        passwordField.addActionListener(loginAction);
        revalidate();
        repaint();
    }

    private void performLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());
        if (email.contains("@pleasantonusd.net") && passwordList.contains(password)) {
            showOptionsPanel();
            clearFields();
        } else {
            errorMessageLabel.setText("Login provided is invalid :(");
        }
    }

    private void showOptionsPanel() {
        getContentPane().removeAll();
        side = new JPanel();
        side.setForeground(Color.WHITE);
        side.setBackground(new Color(0, 51, 102));
        side.setPreferredSize(new Dimension(getWidth() * 20 / 100, getHeight()));
        side.setLayout(new GridBagLayout());
        side.setBorder(new EmptyBorder(20, 10, 20, 10));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridy++;
        JButton bulletinBoardButton = createOptionButton("Bulletin Board");
        side.add(bulletinBoardButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton appointmentButton = createOptionButton("Appointment");
        side.add(appointmentButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton scheduleButton = createOptionButton("Schedule");
        side.add(scheduleButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton mentalHealthButton = createOptionButton("Mental Health");
        side.add(mentalHealthButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton volunteeringButton = createOptionButton("Volunteering");
        side.add(volunteeringButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton outsideCoursesButton = createOptionButton("Outside Course");
        side.add(outsideCoursesButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton courseCredButton = createOptionButton("Course Cred");
        side.add(courseCredButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton agButton = createOptionButton("A-G Course");
        side.add(agButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton aboutUsButton = createOptionButton("About Us");
        side.add(aboutUsButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton extraInfoButton = createOptionButton("Extra Info");
        side.add(extraInfoButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridy++;
        JButton supportButton = createOptionButton("Support");
        side.add(supportButton, gridBagConstraints);
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.weighty = 1.0;
        side.add(Box.createVerticalGlue(), gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 10, 10, 10);
        JButton logoutButton = createOptionButton("Logout");
        logoutButton.setPreferredSize(new Dimension(150, 40));
        logoutButton.setFont(logoutButton.getFont().deriveFont(Font.BOLD, 20f));
        logoutButton.setForeground(new Color(173, 0, 0));
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        side.add(logoutButton, gridBagConstraints);
        bulletinBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBulletinBoard();
            }
        });
        extraInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayExtraInfo();
            }
        });
        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAppointment();
            }
        });
        mentalHealthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMentalHealth();
            }
        });
        volunteeringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayVolunteering();
            }
        });
        courseCredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCourseCred();
            }
        });
        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAboutUS();
            }
        });
        supportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySupport();
            }
        });
        agButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAGcourse();
            }
        });
        bulletinBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBulletinBoard();
            }
        });
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleDisplay();
            }
        });
        outsideCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOutside();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPanel();
                clearFields();
            }
        });
        add(side, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    private void showOptionsPanel2() {
        side = new JPanel();
        side.setForeground(Color.WHITE);
        side.setBackground(new Color(0, 51, 102));
        side.setPreferredSize(new Dimension(getWidth() * 20 / 100, getHeight()));
        side.setLayout(new GridBagLayout());
        side.setBorder(new EmptyBorder(20, 10, 20, 10));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridy++;
        JButton bulletinBoardButton = createOptionButton("Bulletin Board");
        side.add(bulletinBoardButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton appointmentButton = createOptionButton("Appointment");
        side.add(appointmentButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton scheduleButton = createOptionButton("Schedule");
        side.add(scheduleButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton mentalHealthButton = createOptionButton("Mental Health");
        side.add(mentalHealthButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton volunteeringButton = createOptionButton("Volunteering");
        side.add(volunteeringButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton outsideCoursesButton = createOptionButton("Outside Course");
        side.add(outsideCoursesButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton courseCredButton = createOptionButton("Course Cred");
        side.add(courseCredButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton agButton = createOptionButton("A-G Course");
        side.add(agButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton aboutUsButton = createOptionButton("About Us");
        side.add(aboutUsButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        JButton extraInfoButton = createOptionButton("Extra Info");
        side.add(extraInfoButton, gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridy++;
        JButton supportButton = createOptionButton("Support");
        side.add(supportButton, gridBagConstraints);
        gridBagConstraints.gridy = GridBagConstraints.RELATIVE;
        gridBagConstraints.weighty = 1.0;
        side.add(Box.createVerticalGlue(), gridBagConstraints);
        gridBagConstraints.insets = new Insets(0, 10, 10, 10);
        JButton logoutButton = createOptionButton("Logout");
        logoutButton.setPreferredSize(new Dimension(150, 40));
        logoutButton.setFont(logoutButton.getFont().deriveFont(Font.BOLD, 20f));
        logoutButton.setForeground(new Color(173, 0, 0));
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        side.add(logoutButton, gridBagConstraints);
        bulletinBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBulletinBoard();
            }
        });
        extraInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayExtraInfo();
            }
        });
        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAppointment();
            }
        });
        mentalHealthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayMentalHealth();
            }
        });
        volunteeringButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayVolunteering();
            }
        });
        courseCredButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCourseCred();
            }
        });
        aboutUsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAboutUS();
            }
        });
        supportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displaySupport();
            }
        });
        agButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAGcourse();
            }
        });
        bulletinBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBulletinBoard();
            }
        });
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleDisplay();
            }
        });
        outsideCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOutside();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showLoginPanel();
                clearFields();
            }
        });
        add(side, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    private void displayAppointment() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Board2.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        JEditorPane linkPane = new JEditorPane();
        linkPane.setEditable(false);
        linkPane.setOpaque(false);
        linkPane.setContentType("text/html");
        linkPane.setText(
                "<html><body><a href=\"https://calendly.com/jasmine_torres\"><font color=\"#FFFFFF\"><b><font size=\"24\">Click Here To Schedule An Appointment With Your Counselor. </font></b></a></body></html>");
        linkPane.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (IOException | URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        main.add(linkPane);
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayBulletinBoard() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Bullet.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayExtraInfo() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Extra.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayOutside() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Outside.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayAboutUS() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Infos.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayAGcourse() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("A-G.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayCourseCred() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("creds.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayMentalHealth() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("MentalHealth.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displaySupport() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("CounselorHub.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void displayVolunteering() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Service.png");
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void scheduleDisplay() {
        getContentPane().removeAll();
        main = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("Board2.png");
                ImageIcon backgroundImage2 = new ImageIcon("Schedule.png");
                Image image2 = backgroundImage2.getImage();
                Image image = backgroundImage.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                g.drawImage(image2, 0, 0, getWidth(), getHeight(), null);
                showOptionsPanel2();
            }
        };
        main.setOpaque(false);
        main.setLayout(new GridBagLayout());
        add(main, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JButton createOptionButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setFont(button.getFont().deriveFont(Font.BOLD, 18f));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setForeground(new Color(0, 51, 102));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        button.setUI(new RoundedCornerButtonUI(10));
        return button;
    }

    private void clearFields() {
        emailField.setText("");
        passwordField.setText("");
        errorMessageLabel.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AVHSCounseling().setVisible(true);
            }
        });
    }

    private class RoundedCornerButtonUI extends BasicButtonUI {
        private int radius;

        RoundedCornerButtonUI(int radius) {
            this.radius = radius;
        }

        @Override
        public void paint(Graphics g, JComponent c) {
            AbstractButton button = (AbstractButton) c;
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = button.getWidth();
            int height = button.getHeight();
            Shape shape = new RoundRectangle2D.Double(0, 0, width - 1, height - 1, radius, radius);
            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setRolloverEnabled(false);
            g2d.setColor(button.getBackground());
            g2d.fill(shape);
            g2d.setColor(button.getForeground());
            g2d.setFont(button.getFont().deriveFont(Font.BOLD, 18f));
            FontMetrics fontMetrics = g2d.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(button.getText());
            int stringHeight = fontMetrics.getAscent();
            int x = (width - stringWidth) / 2;
            int y = (height - stringHeight) / 2 + stringHeight;
            g2d.drawString(button.getText(), x, y);
        }
    }

    private class RoundCornerTextFieldUI extends javax.swing.plaf.basic.BasicTextFieldUI {
        private int radius;

        RoundCornerTextFieldUI(int radius) {
            this.radius = radius;
        }

        @Override
        protected void paintBackground(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new RoundRectangle2D.Double(0, 0, getComponent().getWidth() - 1,
                    getComponent().getHeight() - 1, radius, radius);
            g2d.setColor(getComponent().getBackground());
            g2d.fill(shape);
        }
    }

    private class RoundCornerPasswordFieldUI extends javax.swing.plaf.basic.BasicPasswordFieldUI {
        private int radius;

        RoundCornerPasswordFieldUI(int radius) {
            this.radius = radius;
        }

        @Override
        protected void paintBackground(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape shape = new RoundRectangle2D.Double(0, 0, getComponent().getWidth() - 1,
                    getComponent().getHeight() - 1, radius, radius);
            g2d.setColor(getComponent().getBackground());
            g2d.fill(shape);
        }
    }
}