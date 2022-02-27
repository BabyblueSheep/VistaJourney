package babybluesheep.vistajourney.item;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SickleMaterial implements ToolMaterial {
    public static final ClaymoreMaterial INSTANCE = new ClaymoreMaterial();

    @Override
    public int getDurability() {
        return 1279;
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
        return null;
    }

}