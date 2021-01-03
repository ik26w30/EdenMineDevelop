package org.ik26w30.edenmine.lastseen.database;

import org.bukkit.entity.Player;
import org.bukkit.material.SimpleAttachableMaterialData;
import org.ik26w30.edenmine.lastseen.LastSeen;

import javax.xml.crypto.Data;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

public class DatabaseUtils { 

    public boolean playerAlreadyExist(UUID player){
        try {
            PreparedStatement statement = DatabaseManager.getInstance().getConnection()
                    .prepareStatement("SELECT * FROM player_data WHERE UUID=?");
            statement.setString(1, player.toString());

            ResultSet res = statement.executeQuery();
            if(res.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer(Player player , UUID uuid, long now){
        try {
            now = System.currentTimeMillis();

            PreparedStatement statement = DatabaseManager.getInstance().getConnection()
            .prepareStatement("SELECT * FROM player_data WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet res = statement.executeQuery();
            if(!this.playerAlreadyExist(uuid)){
                PreparedStatement insert =
                        DatabaseManager.getInstance().getConnection()
                        .prepareStatement("INSERT INTO player_data (UUID, NAME, DATE) VALUE (?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, player.getName());
                insert.setLong(3, now);
                insert.executeUpdate();

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
