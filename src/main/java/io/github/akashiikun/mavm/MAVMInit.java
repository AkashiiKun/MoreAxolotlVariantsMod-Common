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

//? fabric {
//?} else if neoforge {
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

//?}


//? if neoforge {
@Mod(MAVMInit.MOD_ID)
//?}
public class MAVMInit {
	public static final String MOD_ID = "mavm";

	//? if neoforge {
	public MAVMInit(IEventBus modEventBus) {
		//onInitialize();
		//modEventBus.register(this);
	}
	//?}

	public void onInitialize() {

		//? if fabric {
		//?}
	}

	//? if neoforge {
	@SubscribeEvent
	public void event(DataPackRegistryEvent.NewRegistry event) {
	}

	@SubscribeEvent
	public void event(RegisterEvent event) {
		}
	//?}
}
