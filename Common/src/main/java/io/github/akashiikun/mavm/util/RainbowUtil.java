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

package io.github.akashiikun.mavm.util;

import java.util.Objects;

import net.minecraft.util.FastColor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.item.DyeColor;

public class RainbowUtil {
    private static final int DYES = DyeColor.values().length;

    public static int getColorComponents(Entity entity, float tickDelta) {
                return getColorComponents(entity.getId(), entity.tickCount, tickDelta);
    }

    public static int getColorComponents(int seed, int age, float tickDelta) {
        int n = age / 25 + seed;
        float r = ((float) (age % 25) + tickDelta) / 25.0F;

        int o = DyeColor.values().length;
        int p = n % o;
        int q = (n + 1) % o;
        int s = Sheep.getColor(DyeColor.byId(p));
        int t = Sheep.getColor(DyeColor.byId(q));

        return FastColor.ARGB32.lerp(r, s, t);
    }
}