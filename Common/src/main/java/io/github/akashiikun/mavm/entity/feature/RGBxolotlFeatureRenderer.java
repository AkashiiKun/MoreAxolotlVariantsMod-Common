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

package io.github.akashiikun.mavm.entity.feature;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.akashiikun.mavm.AxolotlVariants;
import io.github.akashiikun.mavm.util.RainbowUtil;
import net.minecraft.client.model.AxolotlModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.animal.axolotl.Axolotl;

public class RGBxolotlFeatureRenderer extends RenderLayer<Axolotl, AxolotlModel<Axolotl>> {

    protected final AxolotlModel<Axolotl> model;

    public RGBxolotlFeatureRenderer(RenderLayerParent<Axolotl, AxolotlModel<Axolotl>> context) {
        super(context);
        this.model = this.getParentModel();
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i, Axolotl entity, float f, float g, float h, float j, float k, float l) {
        if(entity.getVariant() == AxolotlVariants.WHITE.getVariant() && "partyxolotl".equals(entity.getName().getString())) {
            float[] colors = RainbowUtil.getColorComponents(entity, h);
            float s = colors[0];
            float t = colors[1];
            float u = colors[2];
            renderColoredCutoutModel(this.getParentModel(), this.getTextureLocation(entity), matrixStack, vertexConsumerProvider, i, entity,  s, t, u);
        }
    }
}