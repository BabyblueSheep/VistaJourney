package babybluesheep.vistajourney.item;

import java.util.List;
import java.util.UUID;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.SilkTouchEnchantment;
import net.minecraft.enchantment.SoulSpeedEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Soullace extends TrinketItem {

    public Soullace(Settings settings) {
        super(settings);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);

        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "vistajourney:movement_speed", 0.20, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

        return modifiers;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("item.vistajourney.soullace.tooltip").formatted(Formatting.AQUA) );
    }

    public BlockPos getVelocityAffectingPos(LivingEntity entity) {
        return new BlockPos(entity.getBlockX(), entity.getBoundingBox().minY - 0.5000001D, entity.getBlockZ());
    }


    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if(entity.world.getBlockState(this.getVelocityAffectingPos(entity)).isIn(BlockTags.SOUL_SPEED_BLOCKS))
        {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 4));
        }
    }
}