package com.lothrazar.strongfarmland;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(StrongFarmland.MODID)
public class StrongFarmland {

  public static final String MODID = "strongfarmland";

  public StrongFarmland(IEventBus modEventBus, ModContainer modContainer) {
    new ConfigFarmland(modContainer);
    NeoForge.EVENT_BUS.register(new FarmlandEvents());
  }
}
