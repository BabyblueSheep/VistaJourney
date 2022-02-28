package babybluesheep.vistajourney.item;

import babybluesheep.vistajourney.registry.VistaEnchantmentRegistry;
import babybluesheep.vistajourney.registry.VistaPotionRegistry;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class DaggerItem extends SwordItem {

    public DaggerItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(EnchantmentHelper.getLevel(VistaEnchantmentRegistry.BLEEDING, stack) > 0){
            target.addStatusEffect(new StatusEffectInstance(VistaPotionRegistry.BLEEDING_EFFECT, 300));
        }
        return super.postHit(stack, target, attacker);
    }
}