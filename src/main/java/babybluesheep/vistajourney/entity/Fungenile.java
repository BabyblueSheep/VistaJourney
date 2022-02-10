package babybluesheep.vistajourney.entity;

import babybluesheep.vistajourney.registry.VistaItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class Fungenile extends AnimalEntity implements IAnimatable {

    private static final TrackedData<Boolean> SHEARED;

    private final AnimationFactory factory = new AnimationFactory(this);

    public Fungenile(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHEARED, false);
    }

    public boolean isSheared() {
        return this.dataTracker.get(SHEARED);
    }

    public void setSheared(boolean state) {
        this.dataTracker.set(SHEARED, state);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sheared", this.isSheared());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSheared(nbt.getBoolean("Sheared"));
    }

    public Fungenile createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder createFungenileAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 5.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1);
    }

    protected void initGoals() {
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 2.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
    }

    protected void mobTick() {
        Random rand = new Random();
        if(this.isSheared()) {
            int regrowRNG = rand.nextInt(1200);
            if (regrowRNG == 0) {
                this.setSheared(false);
            }
        }
        super.mobTick();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHEARS)){
            if (!this.isSheared()) {
                this.dropStack(new ItemStack(VistaItemRegistry.ROOTED_MYCELIUM), 0.5F);
                this.setSheared(true);
            }
        }
        return super.interactMob(player, hand);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event)
    { if(this.prevX != this.getX() || this.prevZ != this.getZ()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("walk", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimationData data)
    {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory()
    {
        return this.factory;
    }

    static {
        SHEARED = DataTracker.registerData(Fungenile.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}