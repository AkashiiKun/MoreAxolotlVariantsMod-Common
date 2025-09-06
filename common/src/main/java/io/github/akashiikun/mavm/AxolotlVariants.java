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

package io.github.akashiikun.mavm;

import net.minecraft.world.entity.animal.axolotl.Axolotl;

import java.util.Locale;

public enum AxolotlVariants {
    LUCIA(true),
    WILDER(true),
    GOLDEN(true),
    GREEN(true),
    SKULK(false),
    BLACK(true),
    RED(true),
    GLOWXOLOTL(false),
    WHITE(true),
    CYANIDE(true);

    private final String name;
    private final boolean natural;
    private Axolotl.Variant variant;
    AxolotlVariants(boolean natural) {
        this.natural = natural;
        this.name = name().toLowerCase(Locale.ENGLISH);
    }

    public Axolotl.Variant getVariant() {
        return variant;
    }

    public String getName() {
        return this.name;
    }

    public boolean isNatural() {
        return natural;
    }

    public void setVariant(Axolotl.Variant variant) {
        this.variant = variant;
    }

    static {
        Axolotl.Variant.values(); // Ensure class is loaded before the variant is accessed
    }
}