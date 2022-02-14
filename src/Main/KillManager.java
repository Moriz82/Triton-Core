package Main;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class KillManager {

    public static double xpPerKill = 5;
    public static Map<Player, Integer> killStreaks = new HashMap<>();
    public static Map<Player, Integer> xpMultiplier = new HashMap<>();

    public static void onPlayerKilled(Player player, Player killedPlayer, Main main) {
        if (player != killedPlayer){
            player.setHealth(player.getMaxHealth());
        }
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
        double xp = main.expMap.get(player);
        int xpMulti = xpMultiplier.get(player);
        xp += xpPerKill * xpMulti;
        if (killStreaks.containsKey(killedPlayer)){
            int val = killStreaks.get(player);
            if (val >= 5){
                if (val >= 10) {
                    if (val >= 25){
                        if (val >= 60){
                            xp += 115;
                        }else{ xp += 50; }
                    }else{ xp += 20; }
                } else{ xp += 10; }
            }
            killStreaks.replace(killedPlayer, 0);
        }
        main.expMap.replace(player, xp);
        PacketPlayOutChat packet = new PacketPlayOutChat(
                new ChatComponentText(ChatColor.RED + "XP : "+ ChatColor.BLUE + xp),
                (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        killStreaks.replace(player, killStreaks.get(player)+1);
        int streak = killStreaks.get(player);
        switch (streak){
            case 5:
                xpMultiplier.replace(player, 2);
                break;
            case 15:
                xpMultiplier.replace(player, 3);
                break;
            case 30:
                xpMultiplier.replace(player, 5);
                break;
            case 50:
                xpMultiplier.replace(player, 7);
                break;
            case 75:
                xpMultiplier.replace(player, 10);
                break;
            default:
                break;
        }
    }
}
