package Main;


import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class LevelSet {
    ItemStack helmet, chestPlate, leggings, boots;
    ItemStack sword, axe;
    double xpRequired;

    public LevelSet(ItemStack helmet, ItemStack chestPlate, ItemStack leggings, ItemStack boots,
                    ItemStack sword, ItemStack axe, double xpRequired) {
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
        this.axe = axe;
        this.xpRequired = xpRequired;
    }
}
