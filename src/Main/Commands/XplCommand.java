package Main.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import Main.Main;
import org.bukkit.entity.Player;

public class XplCommand implements CommandExecutor {

	Main main;

	public XplCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		try{
			Player player = (Player) commandSender;
			player.sendMessage(ChatColor.RED + "XP : " + ChatColor.BLUE + main.playerData.get(player).xp);
			return true;
		}
		catch (Exception e){System.out.println("Your not a player, or some other error occurred!");}

		return false;
	}
}
