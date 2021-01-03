package org.ik26w30.edenmine.lastseen.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.ik26w30.edenmine.lastseen.database.DatabaseUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuitListener implements Listener {
    private final Date now = new Date();
    private final SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        DatabaseUtils databaseUtils = new DatabaseUtils();
        databaseUtils.createPlayer(player, player.getUniqueId(), System.currentTimeMillis());
    }

}
