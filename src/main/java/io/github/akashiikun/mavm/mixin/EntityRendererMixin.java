package io.github.akashiikun.mavm.mixin;

import com.blackgear.cavesandcliffs.common.entity.AxolotlEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity> {
    @Inject(method = "getBlockLight", at = @At("TAIL"), cancellable = true)
    protected void getBlockLight(T entity, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(entity instanceof AxolotlEntity){
            AxolotlEntity axolotl = (AxolotlEntity)entity;
            AxolotlEntity.Variant variant = axolotl.getVariant();
            if(variant.getName().equals("glowxolotl"))
                cir.setReturnValue(15);
        }
    }
}