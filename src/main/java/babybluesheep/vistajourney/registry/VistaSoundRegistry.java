package babybluesheep.vistajourney.registry;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class VistaSoundRegistry
{
    public static final Identifier SHEARWATER_IDLE_ID = new Identifier("vistajourney:shearwater.idle");
    public static SoundEvent SHEARWATER_IDLE_SOUND = new SoundEvent(SHEARWATER_IDLE_ID);
    public static final Identifier SHEARWATER_HURT_ID = new Identifier("vistajourney:shearwater.hurt");
    public static SoundEvent SHEARWATER_HURT_SOUND = new SoundEvent(SHEARWATER_HURT_ID);
    public static final Identifier SHEARWATER_DEATH_ID = new Identifier("vistajourney:shearwater.death");
    public static SoundEvent SHEARWATER_DEATH_SOUND = new SoundEvent(SHEARWATER_DEATH_ID);

    public static void registerSounds() {
        Registry.register(Registry.SOUND_EVENT, SHEARWATER_IDLE_ID, SHEARWATER_IDLE_SOUND);
        Registry.register(Registry.SOUND_EVENT, SHEARWATER_HURT_ID, SHEARWATER_HURT_SOUND);
        Registry.register(Registry.SOUND_EVENT, SHEARWATER_DEATH_ID, SHEARWATER_DEATH_SOUND);
    }
}