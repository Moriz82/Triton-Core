package Main.Command;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class SpawnShopCommand implements CommandExecutor {

	Main main;

	public SpawnShopCommand(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
		try{
			Player player = (Player) commandSender;



			player.sendMessage(ChatColor.GREEN + "Shop Spawned Successfully");
			return true;
		}
		catch (Exception e){System.out.println("Your not a player, or some other error occurred!");}

		return false;
	}
}
