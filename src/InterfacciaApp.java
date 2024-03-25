import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InterfacciaApp {
    public InterfacciaApp() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("ITManager Launcher");

        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.decode("#333333"));

        // Carica l'icona
        ImageIcon icon = new ImageIcon(getClass().getResource("img/exe.png"));
        frame.setIconImage(icon.getImage()); // Imposta l'icona della finestra

        ImageIcon iconAppExport= new ImageIcon(getClass().getResource("img/export.png"));
        ImageIcon iconAppAuto = new ImageIcon(getClass().getResource("img/check-auto.png"));
        ImageIcon iconOpenWebsite = new ImageIcon(getClass().getResource("img/snipeit-dash.png"));

        JButton buttonAppEsistente = new JButton(iconAppExport);
        buttonAppEsistente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriAppExport(frame);
            }
        });

        JButton buttonAppFutura = new JButton(iconAppAuto);
        buttonAppFutura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apriAppAuto();
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
        JLabel versionLabel = new JLabel("© 2024 Pagano S.p.A. | Versione 1.9.0", SwingConstants.RIGHT);
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
        // Sostituisci con il codice per avviare l'applicazione esistente
        MainApplication mainApp = new MainApplication();
        mainApp.execute();
        frame.setVisible(false);
    }

    private void apriAppAuto() {
        // Sostituisci con il codice per avviare l'applicazione futura
        JOptionPane.showMessageDialog(null, "Funzionalità non disponibile.");
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
