package Main;

import org.bukkit.inventory.ItemStack;

public class LevelSet {
    ItemStack helmet, chestPlate, leggings, boots;
    ItemStack sword, axe;
    double xpRequired;
    String name;

    public LevelSet(ItemStack helmet, ItemStack chestPlate, ItemStack leggings, ItemStack boots,
                    ItemStack sword, ItemStack axe, double xpRequired, String name) {
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
