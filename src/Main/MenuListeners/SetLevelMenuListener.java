package Main.MenuListeners;

import Main.EventManagers.LevelSetsManager;
import Main.Main;
import Main.Util.LevelSet;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SetLevelMenuListener implements Listener {
	Main main;

	public SetLevelMenuListener(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (ChatColor.translateAlternateColorCodes('&', e.getClickedInventory().getTitle()).equals(ChatColor.RED + "SetLevel GUI")) {
			if (e.getCurrentItem() != null) {
				e.setCancelled(true);
				String displayName = e.getCurrentItem().getItemMeta().getDisplayName().split(ChatColor.GOLD +""+ ChatColor.BOLD)[1];
				Player player = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getLore().get(0).split(ChatColor.BLUE+"")[1]);

				for (LevelSet set : main.levelSets) {
					if ((set.name).equals(displayName)) {
						LevelSetsManager.setLevel(player, set, main);
						e.getWhoClicked().closeInventory();
						e.getWhoClicked().sendMessage(ChatColor.GREEN + "Level set Successfully!");
						break;
					}
				}
			}
		}
	}
}
