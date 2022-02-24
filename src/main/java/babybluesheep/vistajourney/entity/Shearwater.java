package babybluesheep.vistajourney.entity;

import babybluesheep.vistajourney.registry.VistaEntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class Shearwater extends AnimalEntity implements IAnimatable {

    private final AnimationFactory factory = new AnimationFactory(this);

    private static final Ingredient BreedingIngredient;

    public Shearwater(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    protected void initDataTracker() {
        super.initDataTracker();
    }

    public Shearwater createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return VistaEntityRegistry.SHEARWATER.create(serverWorld);
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    public static DefaultAttributeContainer.Builder createShearwaterAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15D);
    }

    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.4D));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, BreedingIngredient, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public void tickMovement() {
        super.tickMovement();
        Vec3d entityVector = this.getVelocity();
        if (!this.onGround && entityVector.y < 0.0D) {
            this.setVelocity(entityVector.multiply(1.0D, 0.6D, 1.0D));
        }
    }

    public boolean isBreedingItem(ItemStack stack) {
        return BreedingIngredient.test(stack);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    {
        if(this.prevX != this.getX() || this.prevZ != this.getZ()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    private <E extends IAnimatable> PlayState flight_predicate(AnimationEvent<E> event)
    {
        if(!this.onGround){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("fly", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "flight_controller", 0, this::flight_predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    static {
        BreedingIngredient = Ingredient.ofItems(Items.COD, Items.COOKED_COD, Items.SALMON, Items.COOKED_SALMON, Items.TROPICAL_FISH);
    }
}