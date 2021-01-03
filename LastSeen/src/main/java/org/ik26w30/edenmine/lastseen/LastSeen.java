package org.ik26w30.edenmine.lastseen;

import org.bukkit.plugin.java.JavaPlugin;
import org.ik26w30.edenmine.lastseen.listeners.QuitListener;
import org.ik26w30.edenmine.lastseen.commands.LastSeenCommand;
import org.ik26w30.edenmine.lastseen.database.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;

public final class LastSeen extends JavaPlugin {
    private LastSeen instance;
    private Connection connection = null;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {
        try {
            connection = DatabaseManager.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(connection == null){
            getLogger().severe("Db not found");
        }else {
            registerCommands();
            registerEvents();
            getLogger().info("Db found and connected");
        }
    }

    private void registerEvents() {
        this.getServer().getPluginManager().registerEvents(new QuitListener(), this);
    }

    @Override
    public void onDisable() {
        DatabaseManager data = new DatabaseManager();
        if(connection != null) {
            data.closeConnection();
        }else  {
            getLogger().severe("DB Not connected");
         }
    }


    private void registerCommands() {
        this.getCommand("lastseen").setExecutor(new LastSeenCommand());
    }

    public LastSeen getInstance(){
        return instance;
    }

}
