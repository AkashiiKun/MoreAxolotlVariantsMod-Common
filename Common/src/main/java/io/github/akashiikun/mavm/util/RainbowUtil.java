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

package io.github.akashiikun.mavm.util;

import java.util.Objects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.DyeColor;

public class RainbowUtil {
    private static final int DYES = DyeColor.values().length;

    public static float[] getColorComponents(DyeColor dyeColor, Entity entity, float tickDelta) {
        if (entity.hasCustomName() && "partyxolotl".equals(entity.getName().getString()) && entity instanceof Axolotl) {
            if(Objects.equals(((Axolotl) entity).getVariant().getName(), "mavm:white")) {
                return getColorComponents(entity.getId(), entity.tickCount, tickDelta);
            }
        }
        return dyeColor.getTextureDiffuseColors();
    }

    public static float[] getColorComponents(int seed, int age, float tickDelta) {
        int n = age / 25 + seed;
        float r = ((float) (age % 25) + tickDelta) / 25.0F;
        float[] color = Sheep.getColorArray(DyeColor.byId(n % DYES));
        float[] nextColor = Sheep.getColorArray(DyeColor.byId((n + 1) % DYES));
        float v = color[0] * (1.0F - r) + nextColor[0] * r;
        float w = color[1] * (1.0F - r) + nextColor[1] * r;
        float x = color[2] * (1.0F - r) + nextColor[2] * r;

        return new float[]{v, w, x};
    }
}