package Main;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public class LevelSetBuilder implements Serializable {
    String helmet, chestPlate, leggings, boots;
    String sword, axe;
    double xpRequired;

    public LevelSetBuilder(String helmet, String chestPlate, String leggings,
                           String boots, String sword, String axe, double xpRequired) {
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
        this.axe = axe;
        this.xpRequired = xpRequired;
    }

    public LevelSet build() {
       return new LevelSet(
                new ItemStack(Material.getMaterial(helmet)),
                new ItemStack(Material.getMaterial(chestPlate)),
                new ItemStack(Material.getMaterial(leggings)),
                new ItemStack(Material.getMaterial(boots)),
                new ItemStack(Material.getMaterial(sword)),
                new ItemStack(Material.getMaterial(axe)),
                xpRequired
        );
    }
}
