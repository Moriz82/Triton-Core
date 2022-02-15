package Main.Util;

import org.bukkit.inventory.ItemStack;

public class LevelSet {
    public ItemStack helmet, chestPlate, leggings, boots;
    public ItemStack sword, axe;
    public int xpRequired;
    public String name;

    public LevelSet(ItemStack helmet, ItemStack chestPlate, ItemStack leggings, ItemStack boots,
                    ItemStack sword, ItemStack axe, int xpRequired, String name) {
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
        this.axe = axe;
        this.xpRequired = xpRequired;
        this.name = name;
    }
}
