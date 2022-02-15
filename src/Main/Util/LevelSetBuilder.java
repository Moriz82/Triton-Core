package Main.Util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;

public class LevelSetBuilder implements Serializable {
    String helmet, chestPlate, leggings, boots;
    String sword, axe;
    String name;
    int xpRequired;
    String[] helmetEnchant, chestPlateEnchant, leggingsEnchant, bootsEnchant, swordEnchant, axeEnchant;
    Integer[] helmetEnchantLevel, chestPlateEnchantLevel, leggingsEnchantLevel,
            bootsEnchantLevel, swordEnchantLevel, axeEnchantLevel;

    public LevelSetBuilder(String helmet, String chestPlate, String leggings, String boots,
                           String sword, String axe, int xpRequired, String[] helmetEnchant,
                           String[] chestPlateEnchant, String[] leggingsEnchant, String[] bootsEnchant,
                           String[] swordEnchant, String[] axeEnchant, Integer[] helmetEnchantLevel,
                           Integer[] chestPlateEnchantLevel, Integer[] leggingsEnchantLevel,
                           Integer[] bootsEnchantLevel, Integer[] swordEnchantLevel, Integer[] axeEnchantLevel,
                           String name) {
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;
        this.sword = sword;
        this.axe = axe;
        this.xpRequired = xpRequired;
        this.helmetEnchant = helmetEnchant;
        this.chestPlateEnchant = chestPlateEnchant;
        this.leggingsEnchant = leggingsEnchant;
        this.bootsEnchant = bootsEnchant;
        this.swordEnchant = swordEnchant;
        this.axeEnchant = axeEnchant;
        this.helmetEnchantLevel = helmetEnchantLevel;
        this.chestPlateEnchantLevel = chestPlateEnchantLevel;
        this.leggingsEnchantLevel = leggingsEnchantLevel;
        this.bootsEnchantLevel = bootsEnchantLevel;
        this.swordEnchantLevel = swordEnchantLevel;
        this.axeEnchantLevel = axeEnchantLevel;
        this.name = name;
    }

    public LevelSet build() {
        ItemStack helmetStack = new ItemStack(Material.getMaterial(helmet));
        ItemStack chestPlateStack = new ItemStack(Material.getMaterial(chestPlate));
        ItemStack leggingsStack = new ItemStack(Material.getMaterial(leggings));
        ItemStack bootsStack = new ItemStack(Material.getMaterial(boots));
        ItemStack swordStack = new ItemStack(Material.getMaterial(sword));
        ItemStack axeStack = new ItemStack(Material.getMaterial(axe));

        for (int i = 0; i < helmetEnchant.length; i++)
            helmetStack.addUnsafeEnchantment(Enchantment.getByName(helmetEnchant[i]), helmetEnchantLevel[i]);
        for (int i = 0; i < chestPlateEnchant.length; i++)
            chestPlateStack.addUnsafeEnchantment(Enchantment.getByName(chestPlateEnchant[i]), chestPlateEnchantLevel[i]);
        for (int i = 0; i < leggingsEnchant.length; i++)
            leggingsStack.addUnsafeEnchantment(Enchantment.getByName(leggingsEnchant[i]), leggingsEnchantLevel[i]);
        for (int i = 0; i < bootsEnchant.length; i++)
            bootsStack.addUnsafeEnchantment(Enchantment.getByName(bootsEnchant[i]), bootsEnchantLevel[i]);
        for (int i = 0; i < swordEnchant.length; i++)
            swordStack.addUnsafeEnchantment(Enchantment.getByName(swordEnchant[i]), swordEnchantLevel[i]);
        for (int i = 0; i < axeEnchant.length; i++)
            axeStack.addUnsafeEnchantment(Enchantment.getByName(axeEnchant[i]), axeEnchantLevel[i]);

       return new LevelSet(helmetStack, chestPlateStack, leggingsStack,
               bootsStack, swordStack, axeStack, xpRequired, name);
    }
}
