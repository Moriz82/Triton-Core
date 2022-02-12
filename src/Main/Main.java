package Main;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin implements Listener {
    public Map<String, Double> expMapStorage = new HashMap<>();
    public Map<Player, Double> expMap = new HashMap<>();
    public List<LevelSet> levelSets = new ArrayList<>();
    public double xpPerKill = 1;

    @Override
    public void onEnable() {
        EventInit.Init(this);
        DataManager.LoadData(this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getKiller();
        double xp = expMap.get(player);
        xp += xpPerKill;
        expMap.replace(player, xp);
        PacketPlayOutChat packet = new PacketPlayOutChat(
                new ChatComponentText(ChatColor.RED + "XP : "+ ChatColor.BLUE + xp),
                (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();
        LevelSetsManager.onPlayerJoin(this, player);
        DataManager.SaveData(this);
    }

    @EventHandler
    public void onServerStop(PluginDisableEvent event) {
        DataManager.SaveData(this);
    }
}