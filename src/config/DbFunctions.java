package config;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbFunctions {

    private static DbFunctions instance;
    private Connection con;


    private DbFunctions() {}

    public static DbFunctions getInstance() {
        if (instance == null) {
            instance = new DbFunctions();
        }
        return instance;
    }

    public Connection connectToDb() {
        if (con != null) {
            return con;
        }
        try {
            String url = "jdbc:postgresql://localhost:5432/BatiCuisine";
            String user = "postgres";
            String pass = "@aahmhmm28";

            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, pass);

            if (con != null) {
                System.out.println("Connected to PostgreSQL database");
            }
            else {
                System.out.println("Failed to connect to PostgreSQL database");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return con;
    }

    public void closeConnection() {
        if (con != null) {
            try {
                con.close();
                con = null;
                System.out.println("Connection closed");
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to close the database connection", e);
            }
        }
    }
}
