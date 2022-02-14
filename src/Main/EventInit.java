package Main;

import org.bukkit.Bukkit;

public class EventInit {
    public static void Init(Main main){
        Bukkit.getPluginManager().registerEvents(main, main);
        Bukkit.getPluginManager().registerEvents(new SidebarListener(main), main);
    }
}
