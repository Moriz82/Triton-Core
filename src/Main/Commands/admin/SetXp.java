package Main.Commands.admin;

import Main.EventManagers.LevelSetsManager;
import Main.EventManagers.SidebarManager;
import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetXp implements CommandExecutor {

    Main main;

    public SetXp(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        try{
            Player player = (Player) commandSender;
            try{
                Player target =  Bukkit.getPlayer(strings[0]);
                if (!player.isOp()){ player.sendMessage(ChatColor.RED + "You do not have permission to use this command"); return false;}
                main.playerData.get(target).xp = Integer.parseInt(strings[1]);
                SidebarManager.updateSidebar(target, main);
                LevelSetsManager.updatePlayer(main, target);
                player.sendMessage(ChatColor.GREEN + "XP set Successfully!");
            }
            catch (Exception e){player.sendMessage(ChatColor.RED + "some error occurred! Make sure to use '/SetXp [PLAYER_NAME] [VALUE]'");}
            return true;
        }
        catch (Exception e){System.out.println("some error occurred!");}
        return false;
    }
}