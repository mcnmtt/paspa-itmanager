package com.paspa;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class InterfacciaApp {

    public static final String DEV_VERSION = "2.2.1";

    private String username;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public JFrame frame = new JFrame("Inserisci credenziali SnipeIT");

    public InterfacciaApp() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Calcola le coordinate x e y per centrare la finestra
        int windowWidth = 450; // larghezza della finestra
        int windowHeight = 350; // altezza della finestra
        int x = (screenWidth - windowWidth) / 2;
        int y = (screenHeight - windowHeight) / 2;

		
		frame.setResizable(false);
		frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(x, y, windowWidth, windowHeight);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#333333"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/img/exe.png"));
        frame.setIconImage(icon.getImage()); // Imposta l'icona della finestra

		frame.setContentPane(contentPane);
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.decode("#333333"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(loginPanel, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(loginPanel, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
		);
		
		JButton buttonLogin = new JButton("Login");
		buttonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                BcryptPasswordMatcher bcrypt = new BcryptPasswordMatcher();
                CheckRecords checkRecords = new CheckRecords();

                String dbPassword = "";

                try {
                    dbPassword = checkRecords.getPassword(username);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                if(bcrypt.checkPassword(password, dbPassword)){

                    frame.remove(loginPanel);
                    frame.setVisible(false);

                    MainFrame();
                }
			}
		});
		
		JLabel usernameLabel = new JLabel("USERNAME");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Montserrat Black", Font.BOLD, 11));
		usernameLabel.setForeground(new Color(255, 255, 255));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial", Font.PLAIN, 12));
		usernameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Montserrat Black", Font.BOLD, 11));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 12));
		passwordField.setColumns(10);
		
		JLabel logoPagano = new JLabel("");
		logoPagano.setIcon(new ImageIcon("src\\main\\resources\\img\\logo-pagano.png"));
		GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
		gl_loginPanel.setHorizontalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(87)
					.addComponent(logoPagano)
					.addContainerGap(87, Short.MAX_VALUE))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(168)
					.addComponent(buttonLogin, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
					.addGap(167))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(166)
					.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(166, Short.MAX_VALUE))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(171)
					.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(171, Short.MAX_VALUE))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(107)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE))
					.addGap(107))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(26)
					.addComponent(logoPagano)
					.addGap(1)
					.addComponent(usernameLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(buttonLogin)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		loginPanel.setLayout(gl_loginPanel);
		contentPane.setLayout(gl_contentPane);
        frame.getRootPane().setDefaultButton(buttonLogin);
        frame.setVisible(true);
    }

    public void MainFrame(){
        
        JFrame frame = new JFrame("ITManager Launcher");

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.decode("#333333"));

        // Carica l'icona
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/exe.png"));
        frame.setIconImage(icon.getImage()); // Imposta l'icona della finestra

        ImageIcon iconAppExport= new ImageIcon(getClass().getResource("/img/export.png"));
        ImageIcon iconToDo = new ImageIcon(getClass().getResource("/img/to-do.png"));
        ImageIcon iconOpenWebsite = new ImageIcon(getClass().getResource("/img/snipeit-dash.png"));

        JButton buttonAppEsistente = new JButton(iconAppExport);
        buttonAppEsistente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriAppExport(frame);
            }
        });

        JButton buttonAppFutura = new JButton(iconToDo);
        buttonAppFutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    apriToDo(frame);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        JButton buttonOpenWebsite = new JButton(iconOpenWebsite);
        buttonOpenWebsite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "http://192.168.1.99/";
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Impossibile aprire il sito web.");
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Utilizza BorderLayout

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10)); // GridLayout per i bottoni

        buttonPanel.add(buttonOpenWebsite);
        buttonPanel.add(buttonAppEsistente);
        buttonPanel.add(buttonAppFutura);
        
        buttonPanel.setBackground(Color.decode("#333333"));

        panel.add(buttonPanel, BorderLayout.CENTER); // Aggiungi il pannello dei bottoni al centro con margine

        // Aggiungi spazio ai lati della finestra
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        panel.setBackground(Color.decode("#333333"));

        // Aggiungi l'etichetta per il numero di versione
        JLabel versionLabel = new JLabel("© 2024 Pagano S.p.A. | Versione "+ DEV_VERSION, SwingConstants.RIGHT);
        versionLabel.setForeground(Color.WHITE);
        versionLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        JPanel versionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        versionPanel.add(versionLabel);
        versionPanel.setBackground(Color.decode("#333333"));
        panel.add(versionPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void apriAppExport(JFrame frame) {
        MainApplication mainApp = new MainApplication();
        mainApp.execute();
        frame.setVisible(false);
    }

    private void apriToDo(JFrame frame) throws SQLException {

        ToDoFrame toDoFrame = new ToDoFrame();
        frame.setVisible(false);
        toDoFrame.show();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfacciaApp();
            }
        });
    }
}
