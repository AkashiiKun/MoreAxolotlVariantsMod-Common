package io.github.akashiikun.mavm.mixin;

import com.blackgear.cavesandcliffs.common.entity.AxolotlEntity.Variant;
import io.github.akashiikun.mavm.AxolotlVariants;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Pseudo
@Mixin(Variant.class)
public class VariantMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    public static Variant newVariant(String internalName, int internalId, int id, String name, boolean natural) {
        throw new AssertionError();
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @Shadow @Mutable @Final private static Variant[] VARIANTS;

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lcom/blackgear/cavesandcliffs/common/entity/AxolotlEntity$Variant;VARIANTS:[Lcom/blackgear/cavesandcliffs/common/entity/AxolotlEntity$Variant;", shift = At.Shift.AFTER))
    private static void addCustomVariant(CallbackInfo ci) {
        List<Variant> variants = new ArrayList<>(Arrays.asList(VARIANTS));
        Variant last = variants.get(variants.size() - 1);
        int i = 1;
        for (AxolotlVariants variant : AxolotlVariants.values()) {
            variants.add(newVariant(variant.name().toString(), last.ordinal() + i, last.getId() + i, variant.name().toString().toLowerCase(), variant.natural));
            i++;
        }
        VARIANTS = variants.toArray(new Variant[0]);
    }

}
