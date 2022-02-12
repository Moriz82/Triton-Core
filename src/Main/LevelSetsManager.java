package Main;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class LevelSetsManager {
    public static void onPlayerJoin(Main main, Player player) {
        if (!main.expMap.containsKey(player)) {
            if (!main.expMapStorage.containsKey(player.getDisplayName())) {
                main.expMap.put(player, 0.0);
                main.expMapStorage.put(player.getDisplayName(), 0.0);
            } else {
                main.expMap.put(player, main.expMapStorage.get(player.getDisplayName()));
            }
        }
        updatePlayer(main, player);
    }

    public static void updatePlayer(Main main, Player player){
        double playerXp = main.expMap.get(player);
        LevelSet levelSet = null;
        for (LevelSet set : main.levelSets) {
            if (set.xpRequired <= playerXp) {
                levelSet = set;
            }
        }
        if (levelSet != null){
            PlayerInventory inventory = player.getInventory();
            inventory.clear();
            inventory.setArmorContents(new ItemStack[]{
                    levelSet.helmet,
                    levelSet.chestPlate,
                    levelSet.leggings,
                    levelSet.boots,
            });
            inventory.setItem(0, levelSet.sword);
            inventory.setItem(1, levelSet.axe);
            player.updateInventory();
        }
    }
}
