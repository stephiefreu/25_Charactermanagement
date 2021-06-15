package at.htlvillach.dal;

import at.htlvillach.util.PropertyManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import at.htlvillach.bll.Character;

public class DatabaseManager {
    private String driver;
    private String url;
    private String username;
    private String password;
    private static DatabaseManager instance;
    private static HashMap<Integer, Character> characterHashMap = null;

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

    public List<Character> getAllCharacters(){
        characterHashMap = getCharacterHashMap();
        return new ArrayList<>(characterHashMap.values());
    }

    private HashMap<Integer, Character> getCharacterHashMap(){
        HashMap<Integer, Character> chars = new HashMap<>();
        Statement stmt;
        ResultSet resultSet;
        String query = "SELECT * FROM character";

        try(Connection con = this.createConnection()){
            stmt = con.createStatement();
            resultSet = stmt.executeQuery(query);

            while (resultSet.next()){
                chars.put(resultSet.getInt(1), new Character(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8)));
            }
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return chars;
    }

    public boolean updateCharacter(Character c){
        boolean result = false;
        PreparedStatement preparedStatement;
        String stmt_update = "UPDATE Character SET name = ?, age = ?, gender = ?, hairColor = ?, skinColor = ?, shirtColor = ?, trouserColor = ? WHERE id = ?";
        int numberrows = 0;

        try(Connection con = this.createConnection()){
            preparedStatement = con.prepareStatement(stmt_update, new String[]{"id"});
            preparedStatement.setString(1, c.getName());
            preparedStatement.setInt(2, c.getAge());
            preparedStatement.setString(3, c.getGender().toString());
            preparedStatement.setString(4, c.getHairColor());
            preparedStatement.setString(4, c.getSkinColor());
            preparedStatement.setString(4, c.getShirtColor());
            preparedStatement.setString(4, c.getTrouserColor());
            preparedStatement.setInt(4, c.getId());
            numberrows = preparedStatement.executeUpdate();
            if(numberrows > 0){
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public boolean insertCharacter(Character c){
        boolean result = false;
        PreparedStatement preparedStatement;
        String stmt_insert = "INSERT INTO character (name, age, gender, hairColor, skinColor, shirtColor, trouserColor) VALUES (?, ?, ?, ?, ?, ?, ?);";
        ResultSet resultSet;
        int id = -1;

        try (Connection con = this.createConnection()){
            preparedStatement = con.prepareStatement(stmt_insert, new String[] {"charId"});
            preparedStatement.setString(1, c.getName());
            preparedStatement.setInt(2, c.getAge());
            preparedStatement.setString(3, c.getGender().toString());
            preparedStatement.setString(4, c.getHairColor());
            preparedStatement.setString(5, c.getSkinColor());
            preparedStatement.setString(6, c.getShirtColor());
            preparedStatement.setString(7, c.getTrouserColor());
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getInt(1);
                c.setId(id);
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean deleteCharacter(Character c){
        boolean result = false;
        PreparedStatement preparedStatement;
        String stmt_delete = "DELETE FROM character WHERE charId = ?";
        ResultSet resultSet;

        try(Connection con = this.createConnection()){
            preparedStatement = con.prepareStatement(stmt_delete);
            preparedStatement.setInt(1, c.getId());

            if(preparedStatement.executeUpdate() == 1){
                result = true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public Character getCharacterById(int id) {
        if(characterHashMap == null)
            characterHashMap = instance.getCharacterHashMap();
        return characterHashMap.get(id);
    }
}
