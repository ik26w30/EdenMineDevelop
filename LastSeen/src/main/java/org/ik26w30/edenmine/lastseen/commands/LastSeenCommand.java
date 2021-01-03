package org.ik26w30.edenmine.lastseen.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LastSeenCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Hi console!");
            return true;
        }

        if(!(sender.hasPermission("lastseen.admin"))){
            sender.sendMessage("§cYou don't have permissions to execute this command");
            return true;
        }

        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target != null){
                if(target != sender){

                }else {
                    sender.sendMessage("§cYou can't control yourself!");
                    return true;
                }
            }else {
                sender.sendMessage("§cPlayer to checked not found");
                return true;
            }
        }else {
            sender.sendMessage("§cError: use /lastseen [player]");
            return true;
        }
        return false;
    }
}
