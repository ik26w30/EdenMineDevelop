package org.ik26w30.edenmine.lastseen.database;

import org.ik26w30.edenmine.lastseen.LastSeen;
import java.sql.*;

public class DatabaseManager {
    private Connection connection;
    private static DatabaseManager instance;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
        } catch (ClassNotFoundException e) {
            LastSeen seen = new LastSeen();
            seen.getInstance().getLogger().severe("Connection to database failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeDataBase(PreparedStatement statement, ResultSet res) throws SQLException{
            if(res != null){
                res.close();
            }

            if(statement != null){
                statement.close();
            }
    }

    public void closeConnection(){
        try {
            if(instance != null) {
                getConnection().close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance() throws SQLException{
        if(instance == null){
            instance = new DatabaseManager();
        }else if(instance.getConnection().isClosed()){
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }

}
