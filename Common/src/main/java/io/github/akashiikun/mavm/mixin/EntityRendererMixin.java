package io.github.akashiikun.mavm.mixin;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {
    @Inject(method = "getBlockLightLevel", at = @At("TAIL"), cancellable = true)
    protected void getBlockLight(T entity, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(entity instanceof Axolotl axolotl){
            Axolotl.Variant variant = axolotl.getVariant();
            if(variant.getName().equals("glowxolotl"))
                cir.setReturnValue(15);
        }
    }
}