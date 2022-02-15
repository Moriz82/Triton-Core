package Main.Init;

import Main.MenuListeners.SetLevelMenuListener;
import Main.Main;
import org.bukkit.Bukkit;

public class EventInit {
    public static void Init(Main main){
        Bukkit.getPluginManager().registerEvents(main, main);
        Bukkit.getPluginManager().registerEvents(new SetLevelMenuListener(main), main);
    }
}
