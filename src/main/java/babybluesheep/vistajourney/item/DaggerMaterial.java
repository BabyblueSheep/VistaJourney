package babybluesheep.vistajourney.item;

import babybluesheep.vistajourney.registry.VistaItemRegistry;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DaggerMaterial implements ToolMaterial {
    public static final ClaymoreMaterial INSTANCE = new ClaymoreMaterial();

    @Override
    public int getDurability() {
        return 378;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0F;
    }
    @Override
    public int getMiningLevel() {
        return 0;
    }
    @Override
    public int getEnchantability() {
        return 19;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(VistaItemRegistry.GLOW_GLOB_ITEM);
    }

}