import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CheckRecords {

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

    public String hasCartaDiCredito(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();

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
                return "true";
            }
        }

        return "false";
    }

    public String hasCartaDiCreditoRic(int id) throws SQLException{

        DatabaseConnector connector = new DatabaseConnector();

        ArrayList<String> modelList = new ArrayList<>();

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
                return "true";
            }
        }

        return "false";
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
}
