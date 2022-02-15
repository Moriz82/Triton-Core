package Main.Commands.admin;


import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class SetLevel implements CommandExecutor {

    Main main;

    public SetLevel(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        try{
            Player player = (Player) commandSender;
            Player target =  Bukkit.getPlayer(strings[0]);

            if (!player.isOp()){ player.sendMessage(ChatColor.RED + "You do not have permission to use this command"); return false;}

            applySetLevelUI(player, target);

            return true;
        }
        catch (Exception e){System.out.println("some error occurred! Make sure to use '/setLevel [PLAYER_NAME]'");}

        return false;

    }

    public void applySetLevelUI(Player player, Player target) {
        int size = ((int) Math.ceil((double) main.levelSets.size() / 9)) * 9;
        Inventory gui = Bukkit.createInventory(null, size, ChatColor.RED + "SetLevel GUI");

        for (int i = 0; i < main.levelSets.size(); i++) {
            ItemStack itemStack;
            itemStack = new ItemStack(Material.CHEST);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.GOLD +""+ ChatColor.BOLD +  main.levelSets.get(i).name);
            itemMeta.setLore(Collections.singletonList(ChatColor.BLUE + target.getDisplayName()));
            itemStack.setItemMeta(itemMeta);
            gui.setItem(i, itemStack);
        }

        player.openInventory(gui);

    }
}

