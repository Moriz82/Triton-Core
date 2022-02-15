package Main.Init;

import Main.Commands.admin.SpawnShopCommand;
import Main.Commands.XplCommand;
import Main.Commands.admin.SetLevel;
import Main.Main;

public class CommandInit {

	public static void Init(Main main) {
		main.getCommand("xpl").setExecutor(new XplCommand(main));
		main.getCommand("spawnShop").setExecutor(new SpawnShopCommand(main));
		main.getCommand("setLevel").setExecutor(new SetLevel(main));
	}

}
