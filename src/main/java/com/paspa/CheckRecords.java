package com.paspa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.paspa.bean.Location;
import com.paspa.bean.Notebook;
import com.paspa.bean.PCs;
import com.paspa.bean.Utente;

public class CheckRecords {

    public ArrayList<String> getKeys(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("22")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    public ArrayList<String> getCotini(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("23")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }
    
    public ArrayList<String> getNotebook(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("2")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    public ArrayList<String> getPC(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("11")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    public ArrayList<String> getCellulare(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("13")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    public ArrayList<String> getAutomobile(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("15")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    public ArrayList<String> getCartaDiCredito(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("16")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    public ArrayList<String> getCartaDiCreditoRic(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT * FROM assets WHERE assigned_to = " + id;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                while(rs.next()){

                    modelList.add(rs.getString("model_id"));
                }
            };
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        for (String model : modelList) {
            
            if(checkModelsCategory(model).equals("17")){
                returnList.add(checkModelsName(model));
            }
        }

        return returnList;
    }

    private String checkModelsCategory(String model) throws SQLException {

        DatabaseConnector connector = new DatabaseConnector();

        try(Connection conn = connector.getConnection()){

            String query = "SELECT * FROM models WHERE id = " + model;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                String category = "";

                  while(rs.next()){
                    category = rs.getString("category_id"); 
                  }

                  return category;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    private String checkModelsName(String model) throws SQLException {

        DatabaseConnector connector = new DatabaseConnector();

        try(Connection conn = connector.getConnection()){

            String query = "SELECT * FROM models WHERE id = " + model;

            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                String name = "";

                  while(rs.next()){
                    name = rs.getString("name"); 
                  }

                  return name;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public String getPassword(String username) throws SQLException {


        DatabaseConnector connector = new DatabaseConnector();
    
        try (Connection conn = connector.getConnection()) {
    
            String query = "SELECT password FROM users WHERE username = '" + username + "'";
    
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
    
                String password = "";
    
                if (rs.next()) {
                    password = rs.getString(1); // L'indice del campo inizia da 1
                }
    
                return password;
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return "";
    }
    
    public ArrayList<Utente> getAllUsersBean() throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<Utente> userList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT id, email, first_name, last_name FROM users";

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Utente utente = new Utente();
                utente.setId(resultSet.getString("id"));
                utente.setEmail(resultSet.getString("email"));
                utente.setNome(resultSet.getString("first_name"));
                utente.setCognome(resultSet.getString("last_name"));

                userList.add(utente);
            }


        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        return userList;
    }

    public ArrayList<Notebook> getAllNotebooksBean() throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<Notebook> notebookList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT assets.name, assets.asset_tag, assets.model_id, assets.assigned_to, assets.assigned_type FROM assets JOIN models ON assets.model_id = models.id WHERE models.category_id = 2";

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                Notebook notebook = new Notebook();
                
                notebook.setNome(resultSet.getString("name"));
                notebook.setTag(resultSet.getString("asset_tag"));
                notebook.setId_modello(resultSet.getString("model_id"));

                notebook.setAssigned_type(resultSet.getString("assigned_type"));
                if(notebook.getAssigned_type().equals("App\\Models\\Location")){
                    Location location = getLocationById(resultSet.getInt("assigned_to"));
                    notebook.setLocation(location);
                }
                if(notebook.getAssigned_type().equals("App\\Models\\User")){
                    Utente utente = getUtenteById(resultSet.getInt("assigned_to"));
                    notebook.setUtente(utente);
                }

                notebookList.add(notebook);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return notebookList;

        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<PCs> getAllPCsBean() throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<PCs> pcsList = new ArrayList<>();

        try (Connection conn = connector.getConnection()) {

            String query = "SELECT assets.name, assets.asset_tag, assets.model_id, assets.assigned_to, assets.assigned_type FROM assets JOIN models ON assets.model_id = models.id WHERE models.category_id = 11";

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                PCs pcs = new PCs();
                
                pcs.setNome(resultSet.getString("name"));
                pcs.setTag(resultSet.getString("asset_tag"));
                pcs.setId_modello(resultSet.getString("model_id"));

                pcs.setAssigned_type(resultSet.getString("assigned_type"));
                if(pcs.getAssigned_type().equals("App\\Models\\Location")){
                    Location location = getLocationById(resultSet.getInt("assigned_to"));
                    pcs.setLocation(location);
                }
                if(pcs.getAssigned_type().equals("App\\Models\\User")){
                    Utente utente = getUtenteById(resultSet.getInt("assigned_to"));
                    pcs.setUtente(utente);
                }

                pcsList.add(pcs);
            }

            resultSet.close();
            statement.close();
            conn.close();

            return pcsList;

        } catch (SQLException e) {
            System.out.println("Errore di connessione al database: " + e.getMessage());
        }

        return null;
    }

    public Utente getUtenteById(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();
        Utente utente = null;

        try (Connection conn = connector.getConnection()) {

            String sql = "SELECT id, email, first_name, last_name FROM users WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                utente = new Utente();
                utente.setId(resultSet.getString("id"));
                utente.setEmail(resultSet.getString("email"));
                utente.setNome(resultSet.getString("first_name"));
                utente.setCognome(resultSet.getString("last_name"));
            }

            resultSet.close();
            statement.close();

            return utente;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Location getLocationById(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();
        Location location = null;

        try (Connection conn = connector.getConnection()) {

            String sql = "SELECT id, name FROM locations WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                location = new Location();

                location.setId(resultSet.getString("id"));
                location.setNome(resultSet.getString("name"));
            }

            resultSet.close();
            statement.close();

            return location;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
