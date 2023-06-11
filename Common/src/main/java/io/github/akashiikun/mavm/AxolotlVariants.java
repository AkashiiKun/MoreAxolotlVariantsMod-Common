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

    public boolean natural;
    public String name;
    private AxolotlVariants(boolean natural) {
        this.natural = natural;
        this.name = name();
    }
}