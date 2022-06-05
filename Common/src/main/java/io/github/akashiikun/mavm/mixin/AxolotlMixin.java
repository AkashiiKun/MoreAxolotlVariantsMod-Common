package io.github.akashiikun.mavm.mixin;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LerpingModel;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Axolotl.class)
public abstract class AxolotlMixin extends Animal implements LerpingModel, Bucketable {

    private AxolotlMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    public abstract Axolotl.Variant getVariant();

    @Inject(method = "getAmbientSound", at = @At("TAIL"), cancellable = true)
    protected void getAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (getVariant().getName().equals("glowxolotl"))
            if(Math.floor((Math.random() * 4)) == 2) {
                cir.setReturnValue(SoundEvents.GLOW_SQUID_AMBIENT);
            }
    }

    @Inject(at = @At("RETURN"), method = "baseTick")
    public void baseTick(CallbackInfo ci) {
        Axolotl $this = Axolotl.class.cast(this);
        if (getVariant().getName().equals("glowxolotl")) {
            int i = (int) Math.floor((Math.random() * 32));
            if (i == 2 || i == 4) {
                if(this.isBaby()) {
                    $this.level.addParticle(ParticleTypes.GLOW, $this.getRandomX(0.3D), $this.getRandomY(), $this.getRandomZ(0.3D), 0.0D, 0.0D, 0.0D);
                } else {
                    $this.level.addParticle(ParticleTypes.GLOW, $this.getRandomX(0.6D), $this.getRandomY(), $this.getRandomZ(0.6D), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}

