package Main.EventManagers;

import Main.Main;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class KillManager {

    public static double xpPerKill = 5;

    public static void onPlayerKilled(Player player, Player killedPlayer, Main main) {
        main.playerData.get(player).kills++;
        main.playerData.get(killedPlayer).deaths++;
        if (player != killedPlayer){
            player.setHealth(player.getMaxHealth());
        }
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
        int xp = main.playerData.get(player).xp;
        int xpMulti = main.playerData.get(player).xpMultiplier;
        xp += xpPerKill * xpMulti;

        int val = main.playerData.get(killedPlayer).killStreak;
        if (val >= 5){
            if (val >= 10) {
                if (val >= 25){
                    if (val >= 60){
                        xp += 115;
                    }else{ xp += 50; }
                }else{ xp += 20; }
            } else{ xp += 10; }
        }
        main.playerData.get(killedPlayer).killStreak = 0;

        main.playerData.get(player).xp = xp;
        PacketPlayOutChat packet = new PacketPlayOutChat(
                new ChatComponentText(ChatColor.RED + "XP : "+ ChatColor.BLUE + xp),
                (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);

        main.playerData.get(player).killStreak++;
        int streak = main.playerData.get(player).killStreak;

        if (streak > main.playerData.get(player).longestKillStreak)
            main.playerData.get(player).longestKillStreak = streak;

        switch (streak){
            case 5:
                main.playerData.get(player).xpMultiplier = 2;
                break;
            case 15:
                main.playerData.get(player).xpMultiplier = 3;
                break;
            case 30:
                main.playerData.get(player).xpMultiplier = 5;
                break;
            case 50:
                main.playerData.get(player).xpMultiplier = 7;
                break;
            case 75:
                main.playerData.get(player).xpMultiplier = 10;
                break;
            default:
                break;
        }
    }
}
