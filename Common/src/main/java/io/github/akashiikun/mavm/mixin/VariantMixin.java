package io.github.akashiikun.mavm.mixin;

import io.github.akashiikun.mavm.AxolotlVariants;
import net.minecraft.world.entity.animal.axolotl.Axolotl.Variant;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(Variant.class)
public abstract class VariantMixin {
	@SuppressWarnings("InvokerTarget")
	@Invoker("<init>")
	public static Variant newVariant(String internalName, int internalId, int id, String name, boolean natural) {
		throw new AssertionError();
	}

	@SuppressWarnings("UnresolvedMixinReference")
	@Shadow @Mutable @Final private static Variant[] $VALUES;

	@SuppressWarnings("UnresolvedMixinReference")
	@Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/animal/axolotl/Axolotl$Variant;$VALUES:[Lnet/minecraft/world/entity/animal/axolotl/Axolotl$Variant;", shift = At.Shift.AFTER))
	private static void addCustomVariant(CallbackInfo ci) {
		List<Variant> variants = new ArrayList<>(Arrays.asList($VALUES));
		Variant last = variants.get(variants.size() - 1);
		int i = 1;
		for (AxolotlVariants variant : AxolotlVariants.values()) {
			variants.add(newVariant(variant.name().toString(), last.ordinal() + i, last.getId() + i, variant.name().toString().toLowerCase(), variant.natural));
			i++;
		}
		$VALUES = variants.toArray(new Variant[0]);
	}
}