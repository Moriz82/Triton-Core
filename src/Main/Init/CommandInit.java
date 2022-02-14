package Main.Init;

import Main.Command.SpawnShopCommand;
import Main.Command.XplCommand;
import Main.Main;
import org.bukkit.Bukkit;

public class CommandInit {

	public static void Init(Main main){
		main.getCommand("xpl").setExecutor(new XplCommand(main));
		main.getCommand("spawnShop").setExecutor(new SpawnShopCommand(main));
	}

}
