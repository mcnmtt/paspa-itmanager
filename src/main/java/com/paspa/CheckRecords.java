package com.paspa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
}
