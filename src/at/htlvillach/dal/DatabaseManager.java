package at.htlvillach.dal;

import at.htlvillach.util.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private String driver;
    private String url;
    private String username;
    private String password;
    private static DatabaseManager instance;
    //private static HashMap<Integer, Person> personHashMap = null;

    private DatabaseManager() {
        PropertyManager.getInstance().setFilename("db.properties");
        driver = PropertyManager.getInstance().readProperty("driver", "oracle.jdbc.OracleDriver");
        url = PropertyManager.getInstance().readProperty("url", "jdbc:oracle:thin:@tcif.htl-villach.at:1521/orcl");
        username = PropertyManager.getInstance().readProperty("username", "d3a24");
        password = PropertyManager.getInstance().readProperty("password", "d3a24");
    }

    private Connection createConnection(){
        Connection con = null;
        //1. Laden des Treibers
        try{
            Class.forName(this.driver);
            //2. Erzeugen der Datenbankverbindung
            con = DriverManager.getConnection(url, username, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

        return con;
    }

    public static DatabaseManager getInstance(){
        if(instance == null){
            instance = new DatabaseManager();
        }
        return instance;
    }
}
