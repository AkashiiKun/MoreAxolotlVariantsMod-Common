/*
 * All Rights Reserved
 *
 * Copyright (c) 2022-2026 AkashiiKun
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

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

public class MultiversionHelper {
	public static Identifier toIdentifier(ResourceKey<?> key) {
		//? if >=1.21.11 {
		return key.identifier();
		//?} else {
		/*return key.location();
		*///?}
	}
}
