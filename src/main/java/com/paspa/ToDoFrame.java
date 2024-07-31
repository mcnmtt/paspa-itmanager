package com.paspa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.paspa.bean.Notebook;
import com.paspa.bean.PCs;
import com.paspa.bean.Utente;

public class ToDoFrame {

    public void show() throws SQLException{
        
            JFrame frame = new JFrame("Check Assets");

            frame.setSize(500, 300);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBackground(Color.decode("#333333"));

            // Carica l'icona
            ImageIcon icon = new ImageIcon(getClass().getResource("/img/exe.png"));
            frame.setIconImage(icon.getImage()); // Imposta l'icona della finestra

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout()); // Utilizza BorderLayout

            // Aggiungi spazio ai lati della finestra
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
            panel.setBackground(Color.decode("#333333"));

            CheckRecords cr = new CheckRecords();
            ArrayList<Notebook> notebookList = new ArrayList<>();
            ArrayList<PCs> pcsList = new ArrayList<>();
            notebookList = cr.getAllNotebooksBean();
            pcsList = cr.getAllPCsBean();

            ArrayList<String> StatusNotebook = new ArrayList<>();

            int i = 0;

            int percentuale = 0;

            JFrame caricamento = new JFrame("Caricamento...");

            caricamento.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            caricamento.setSize(400, 0);
            caricamento.setLocationRelativeTo(null);
            caricamento.setVisible(true);
            caricamento.setResizable(false);
            caricamento.setBackground(Color.decode("#333333"));
            caricamento.setIconImage(icon.getImage()); // Imposta l'icona della finestra


            for (Notebook notebook : notebookList) {

                percentuale = (100*i)/(notebookList.size()+pcsList.size());
                i++;

                if(notebook.getUtente() != null){
                Utente utente = notebook.getUtente();
                RemoteFileSearcher fileSearcher = new RemoteFileSearcher();
                boolean fileFound = fileSearcher.searchFiles("*" + utente.getNome().toLowerCase() + "*" + utente.getCognome().toLowerCase() + "*");
                    if(fileFound){
                        StatusNotebook.add(notebook.getTag() + " " +  notebook.getNome() + " - " + utente.getNome() + " " + utente.getCognome() + " | " + "OK");}
                    if(!fileFound){
                        StatusNotebook.add(notebook.getTag() + " " + notebook.getNome() + " - " + utente.getNome() + " " + utente.getCognome() + " | " + "MANCA SCHEDA CONSEGNA");}
                }
                if(notebook.getUtente() == null && notebook.getLocation() != null){
                    StatusNotebook.add(notebook.getTag() + " " + notebook.getNome() + " - " + notebook.getLocation().getNome() + " | " + "OK");
                }
                if(notebook.getUtente() == null && notebook.getLocation() == null){
                    StatusNotebook.add(notebook.getTag() + " " + notebook.getNome() + " - " + "Non assegnato");
                }

                caricamento.setTitle("Asset " + notebook.getTag() + " | " + percentuale + "%");
            }

            ArrayList<String> StatusPCs = new ArrayList<>();

            for (PCs pcs : pcsList) {

                percentuale = (100*i)/(notebookList.size()+pcsList.size());
                i++;

                if(pcs.getUtente() != null){
                Utente utente = pcs.getUtente();
                RemoteFileSearcher fileSearcher = new RemoteFileSearcher();
                boolean fileFound = fileSearcher.searchFiles("*" + utente.getNome().toLowerCase() + "*" + utente.getCognome().toLowerCase() + "*");
                    if(fileFound){
                        StatusPCs.add(pcs.getTag() + " " + pcs.getNome() + " - " + utente.getNome() + " " + utente.getCognome() + " | " + "OK");}
                    if(!fileFound){
                        StatusPCs.add(pcs.getTag() + " " + pcs.getNome() + " - " + utente.getNome() + " " + utente.getCognome() + " | " + "MANCA SCHEDA CONSEGNA");}
                }
                if(pcs.getUtente() == null && pcs.getLocation() != null){
                    StatusPCs.add(pcs.getTag() + " " + pcs.getNome() + " - " + pcs.getLocation().getNome() + " | " + "OK");
                }
                if(pcs.getUtente() == null && pcs.getLocation() == null){
                    StatusPCs.add(pcs.getTag() + " " + pcs.getNome() + " - " + "Non assegnato");
                }

                caricamento.setTitle("Asset " + pcs.getTag() + " | " + percentuale + "%");
            }
            
            String[] notebookArray = StatusNotebook.toArray(new String[0]);
            String[] pcsArray = StatusPCs.toArray(new String[0]);

            int lenght = notebookArray.length + pcsArray.length;

            String[] namesArray = new String[lenght];

            System.arraycopy(notebookArray, 0, namesArray, 0, notebookArray.length);
            System.arraycopy(pcsArray, 0, namesArray, notebookArray.length, pcsArray.length);

            JList<String> nameList = new JList<>(namesArray);
            JScrollPane scrollPane = new JScrollPane(nameList);

            JButton button = new JButton("INDIETRO");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    InterfacciaApp interfacciaApp = new InterfacciaApp();
                    interfacciaApp.frame.setVisible(false);
                    interfacciaApp.MainFrame();
                    frame.setVisible(false);
                }
            });

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(button, BorderLayout.SOUTH);

            frame.add(panel);
            caricamento.setVisible(false);
            frame.setVisible(true);
    }
}
