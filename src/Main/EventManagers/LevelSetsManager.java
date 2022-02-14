package Main.EventManagers;

import Main.Util.LevelSet;
import Main.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;

public class LevelSetsManager {

    public static Map<Player, LevelSet> playerLevelSets = new HashMap<>();

    public static void onPlayerJoin(Main main, Player player) {
        if (!main.expMap.containsKey(player)) {
            if (!main.expMapStorage.containsKey(player.getDisplayName())) {
                main.expMap.put(player, 0.0);
                main.expMapStorage.put(player.getDisplayName(), 0.0);
            } else {
                main.expMap.put(player, main.expMapStorage.get(player.getDisplayName()));
            }
        }else if(main.expMap.get(player) == null){main.expMap.put(player, 0.0);}
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
            if (playerLevelSets.containsKey(player)){
                if (levelSet != playerLevelSets.get(player)) {
                    player.playSound(player.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 2);

                    IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "LEVEL UP!" + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");
                    PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
                    IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + "Your Now a "+ levelSet.name + "\",color:" + ChatColor.GOLD.name().toLowerCase() + "}");
                    PacketPlayOutTitle subtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
                    PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(subtitle);
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
                }
                playerLevelSets.replace(player, levelSet);
            }else{
                playerLevelSets.put(player, levelSet);
            }
            PlayerInventory inventory = player.getInventory();
            inventory.clear();
            inventory.setHelmet(levelSet.helmet);
            inventory.setChestplate(levelSet.chestPlate);
            inventory.setLeggings(levelSet.leggings);
            inventory.setBoots(levelSet.boots);
            inventory.setItem(0, levelSet.sword);
            inventory.setItem(1, levelSet.axe);
            player.updateInventory();
            SidebarManager.updateSidebar(player, main);
        }
        System.out.println(levelSet);
        System.out.println(main.levelSets);
    }
}
