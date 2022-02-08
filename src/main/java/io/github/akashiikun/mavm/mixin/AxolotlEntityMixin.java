package io.github.akashiikun.mavm.mixin;

import com.blackgear.cavesandcliffs.common.entity.AxolotlEntity;
import com.blackgear.cavesandcliffs.common.entity.IAngledModelEntity;
import com.blackgear.cavesandcliffs.common.entity.IBucketable;
import com.blackgear.cavesandcliffs.core.registries.CCBParticleTypes;
import com.blackgear.cavesandcliffs.core.registries.CCBSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(AxolotlEntity.class)
public abstract class AxolotlEntityMixin extends AnimalEntity implements IAngledModelEntity, IBucketable {

    private AxolotlEntityMixin(EntityType<? extends AnimalEntity> entityType, World level) {
        super(entityType, level);
    }

    @Shadow
    public abstract AxolotlEntity.Variant getVariant();

    @Inject(method = "getAmbientSound", at = @At("TAIL"), cancellable = true)
    protected void getAmbientSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (getVariant().getName().equals("glowxolotl"))
            if(Math.floor((Math.random() * 4)) == 2) {
                cir.setReturnValue(CCBSoundEvents.ENTITY_GLOW_SQUID_AMBIENT.get());
            }
    }

    @Inject(at = @At("RETURN"), method = "baseTick")
    public void baseTick(CallbackInfo ci) {
        AxolotlEntity $this = AxolotlEntity.class.cast(this);
        if (getVariant().getName().equals("glowxolotl")) {
            int i = (int) Math.floor((Math.random() * 32));
            if (i == 2 || i == 4) {
                if(this.isChild()) {
                    $this.world.addParticle(CCBParticleTypes.GLOW.get(), $this.getPosXRandom(0.3D), $this.getPosYRandom(), $this.getPosZRandom(0.3D), 0.0D, 0.0D, 0.0D);
                } else {
                    $this.world.addParticle(CCBParticleTypes.GLOW.get(), $this.getPosXRandom(0.6D), $this.getPosYRandom(), $this.getPosZRandom(0.6D), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }
}