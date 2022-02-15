package Main;

import Main.EventManagers.DataManager;
import Main.EventManagers.KillManager;
import Main.EventManagers.LevelSetsManager;
import Main.EventManagers.SidebarManager;
import Main.Init.CommandInit;
import Main.Init.EventInit;
import Main.Util.LevelSet;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin implements Listener {
    public Map<String, Double> expMapStorage = new HashMap<>();
    public Map<Player, Double> expMap = new HashMap<>();
    public List<LevelSet> levelSets = new ArrayList<>();

    @Override
    public void onEnable() {
        CommandInit.Init(this);
        EventInit.Init(this);
        DataManager.LoadData(this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        KillManager.onPlayerKilled(event.getEntity().getKiller(), event.getEntity(), this);
        LevelSetsManager.updatePlayer(this, event.getEntity().getKiller());
        SidebarManager.updateSidebar(event.getEntity(), this);
        SidebarManager.updateSidebar(event.getEntity().getKiller(), this);
        DataManager.SaveData(this);
    }

    @EventHandler
    public void onPlayerSpawn(PlayerRespawnEvent ev) {
        Player player = ev.getPlayer();
        LevelSetsManager.updatePlayer(this, player);
        SidebarManager.updateSidebar(player, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        Player player = ev.getPlayer();
        if (!KillManager.xpMultiplier.containsKey(player))
            KillManager.xpMultiplier.put(player, 1);
        if (!KillManager.killStreaks.containsKey(player))
            KillManager.killStreaks.put(player, 0);
        LevelSetsManager.onPlayerJoin(this, player);
        DataManager.SaveData(this);
    }

    @EventHandler
    public void onServerStop(PluginDisableEvent event) {
        DataManager.SaveData(this);
    }
}