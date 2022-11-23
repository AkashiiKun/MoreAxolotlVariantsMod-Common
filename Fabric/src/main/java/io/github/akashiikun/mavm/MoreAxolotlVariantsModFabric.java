/*
 * All Rights Reserved
 *
 * Copyright (c) 2022 AkashiiKun
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package io.github.akashiikun.mavm;

import fabric.io.github.akashiikun.mavapi.v1.api.ModdedAxolotlVariant;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;

import static io.github.akashiikun.mavm.MoreAxolotlVariantsMod.MOD_ID;

public class MoreAxolotlVariantsModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        for(AxolotlVariants variant: AxolotlVariants.values()) {
            if(variant.natural) {
                ModdedAxolotlVariant.register(new ResourceLocation(MOD_ID, variant.name().toLowerCase()))
                        .natural()
                        .build();
            } else {
                ModdedAxolotlVariant.register(new ResourceLocation(MOD_ID, variant.name().toLowerCase()))
                        .build();
            }
        }
    }
}
