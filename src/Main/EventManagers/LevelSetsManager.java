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

public class LevelSetsManager {

    public static void updatePlayer(Main main, Player player) {
        double playerXp = main.playerData.get(player).xp;
        LevelSet levelSet = null;
        for (LevelSet set : main.levelSets) {
            if (set.xpRequired <= playerXp) {
                levelSet = set;
            }
        }
        if (levelSet != null){
            if (main.playerData.get(player).levelSet != null){
                if (levelSet != main.playerData.get(player).levelSet) {
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
                main.playerData.get(player).levelSet = levelSet;
            }else{
                main.playerData.get(player).levelSet = levelSet;
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

    public static void setLevel(Player player, LevelSet levelSet, Main main) {
        main.playerData.get(player).xp = levelSet.xpRequired;
        updatePlayer(main, player);
    }
}
