// Copyright (c) 2025 Jab125. All rights reserved.
// This file is part of More Axolotl Variants API.
// More Axolotl Variants API is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
// More Axolotl Variants API distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// You should have received a copy of the GNU Lesser General Public License along with More Axolotl Variants API. If not, see <https://www.gnu.org/licenses/>.
package io.github.akashiikun.mavm;


//? fabric {
//?} else if neoforge {
/*import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.neoforged.neoforge.registries.RegisterEvent;

*///?}


//? if neoforge {
/*@Mod(MAVMInit.MOD_ID)
*///?}
public class MAVMInit {
	public static final String MOD_ID = "mavm";

	//? if neoforge {
	/*public MAVMInit(IEventBus modEventBus) {
		//onInitialize();
		//modEventBus.register(this);
	}
	*///?}

	public void onInitialize() {

		//? if fabric {
		//?}
	}

	//? if neoforge {
	/*@SubscribeEvent
	public void event(DataPackRegistryEvent.NewRegistry event) {
	}

	@SubscribeEvent
	public void event(RegisterEvent event) {
		}
	*///?}
}
