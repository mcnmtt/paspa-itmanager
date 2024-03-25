import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class MainApplication {

    public void execute() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Avvia l'estrazione del database in un thread separato
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DatabaseExtractionDialog dialog = new DatabaseExtractionDialog();
                dialog.setVisible(true);

                new Thread(new Runnable() {
                    public void run() {
                        try {
                            DatabaseExtractor.extract(dialog);
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    dialog.updateStatus("Estrazione completata!");
                                    dialog.enableCloseButton();
                                }
                            });
                        } catch (SQLException e) {
                            SwingUtilities.invokeLater(new Runnable() {
                                public void run() {
                                    dialog.updateStatus("Errore durante l'estrazione del database: " + e.getMessage());
                                    dialog.enableCloseButton();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }
}

class DatabaseExtractionDialog extends JFrame {

    private JLabel statusLabel;
    private JProgressBar progressBar;
    private JButton closeButton;

    public DatabaseExtractionDialog() {

        setTitle("Export Assets");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 140);
        setLocationRelativeTo(null);
        setResizable(false);

        // Carica l'icona
        ImageIcon icon = new ImageIcon(getClass().getResource("img/exe.png"));
        setIconImage(icon.getImage()); // Imposta l'icona della finestra

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.decode("#333333"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        statusLabel = new JLabel("Estrazione in corso...", SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE); // Imposta il colore del testo
        panel.add(statusLabel, BorderLayout.NORTH);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true); // Mostra percentuale nella barra d'avanzamento
        Color customColor = new Color(239, 127, 19);
        progressBar.setForeground(customColor);
        progressBar.setBorderPainted(false);
        panel.add(progressBar, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#333333"));
        closeButton = new JButton("Chiudi");
        closeButton.setEnabled(false);
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().setBackground(Color.decode("#333333"));
        add(panel);
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
    }

    public void enableCloseButton() {
        closeButton.setEnabled(true);
    }

    public File showSaveDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleziona destinazione file...");
        fileChooser.setFileFilter(new FileNameExtensionFilter("File di testo (.txt)", "txt"));

        // Genera il nome del file predefinito con la data e l'ora correnti
        String defaultFileName = generateDefaultFileName();
        fileChooser.setSelectedFile(new File(defaultFileName));

        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (!selectedFile.getAbsolutePath().toLowerCase().endsWith(".txt")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".txt");
            }
            return selectedFile;
        }
        return null;
    }

    private String generateDefaultFileName() {
        // Ottieni la data corrente
        java.util.Date currentDate = new java.util.Date();
        // Formatta la data nel formato giusto
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Genera il nome del file con la data corrente
        return "assets-" + dateFormat.format(currentDate);
    }
}

class DatabaseExtractor {
    public static void extract(DatabaseExtractionDialog dialog) throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();

        try (Connection conn = connector.getConnection()) {
            String query = "SELECT * FROM users ORDER BY employee_num";
            int totalRecords = getTotalRecords(conn, query);
            int currentRecord = 0;

            CheckRecords checkRecords = new CheckRecords();

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                // Ottieni il percorso del file dall'utente
                File outputFile = dialog.showSaveDialog();
                if (outputFile == null) {
                    throw new IOException("Nessun file selezionato.");
                }

                FileWriter writer = new FileWriter(outputFile);

                while (rs.next()) {

                    if (rs.getString("deleted_at") == null && rs.getInt("id") > 1){

                    //CONTROLLA SE VUOTO CAMPO NOTEBOOK, ALTRIMENTI PONE TAG NTB_ DAVANTI AL NOME DEL DISPOSITIVO         

                    String notebooks = "";
                    if(checkRecords.getNotebook(rs.getInt("id")).isEmpty()){
                        notebooks = "false";
                    }
                    else{
                    for (String notebook : checkRecords.getNotebook(rs.getInt("id")) ) {
                        notebooks += ", " + "NTB_" + notebook;
                    }}

                    //CONTROLLA SE VUOTO CAMPO PC, ALTRIMENTI PONE TAG COM_ DAVANTI AL NOME DEL DISPOSITIVO

                    String pcs = "";
                    if(checkRecords.getPC(rs.getInt("id")).isEmpty()){
                        pcs = "false";
                    }
                    else{
                    for (String pc : checkRecords.getPC(rs.getInt("id")) ) {
                        pcs += ", " + "COM_" + pc;
                    }}

                    //CONTROLLA SE VUOTO CAMPO CELLULARE, ALTRIMENTI PONE TAG TEL_ DAVANTI AL NOME DEL DISPOSITIVO

                    String telephones = "";
                    if(checkRecords.getCellulare(rs.getInt("id")).isEmpty()){
                        telephones = "false";
                    }
                    else{
                    for (String telephone : checkRecords.getCellulare(rs.getInt("id")) ) {
                        telephones += ", " + "TEL_" + telephone;
                    }}

                    //CONTROLLA SE VUOTO CAMPO AUTOMOBILE, ALTRIMENTI PONE TAG CAR_ DAVANTI AL NOME DEL DISPOSITIVO

                    String cars = "";
                    if(checkRecords.getAutomobile(rs.getInt("id")).isEmpty()){
                        cars = "false";
                    }
                    else{
                    for (String car : checkRecords.getAutomobile(rs.getInt("id")) ) {
                        cars += ", " + "CAR_" + car;
                    }}

                    //CONTROLLA CAMPO CARTA CREDITO NORMALE

                    String ccn = checkRecords.hasCartaDiCredito(rs.getInt("id"));

                    if (!ccn.equals("false")) {
                        ccn = "CCN_" + ccn;
                    }

                    //CONTROLLA CAMPO CARTA CREDITO NORMALE

                    String ccr = checkRecords.hasCartaDiCreditoRic(rs.getInt("id"));

                    if (!ccr.equals("false")) {
                        ccr = "CCR_" + ccr;
                    }

                    //STAMPA NEL FILE LE INFORMAZIONI OTTENUTE

                    writer.write(rs.getString("employee_num") + ", " + rs.getString("first_name") + " " + rs.getString("last_name") + ",");

                    if (notebooks != "false") {
                        writer.write(" " + notebooks.substring(2) + ",");
                    }

                    if (pcs != "false") {
                        writer.write(" " + pcs.substring(2) + ",");
                    }

                    if (telephones != "false") {
                        writer.write(" " + telephones.substring(2) + ",");
                    }

                    if (cars != "false") {
                        writer.write(" " + cars.substring(2) + ",");
                    }

                    if (ccn != "false") {
                        writer.write(" " + ccn + ",");
                    }

                    if (ccr != "false") {
                        writer.write(" " + ccr + ",");
                    }

                    writer.write("," + "\n");
                }

                    currentRecord++;
                    int progress = (int) (((double) currentRecord / totalRecords) * 100);
                    dialog.updateProgress(progress);
                }
                writer.close();

                System.out.println("Dati estratti con successo e scritti in " + outputFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    private static int getTotalRecords(Connection conn, String query) throws SQLException {
        int totalRecords = 0;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM users")) {
            if (rs.next()) {
                totalRecords = rs.getInt("total");
            }
        }
        return totalRecords;
    }
}
