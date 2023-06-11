/*
 * All Rights Reserved
 *
 * Copyright (c) 2023 AkashiiKun
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
    protected void mavm$setGlowVariants(T entity, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        if(entity instanceof Axolotl axolotl){
            Axolotl.Variant variant = axolotl.getVariant();
            if(variant.getName().equals("mavm:glowxolotl"))
                cir.setReturnValue(15);
            if(variant.getName().equals("mavm:white") && entity.hasCustomName() && "partyxolotl".equals(entity.getName().getString()))
                cir.setReturnValue(15);
        }
    }
}