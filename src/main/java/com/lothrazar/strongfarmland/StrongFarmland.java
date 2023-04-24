package com.lothrazar.strongfarmland;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(StrongFarmland.MODID)
public class StrongFarmland {

  public static final String MODID = "strongfarmland";

  public StrongFarmland() {
    new ConfigFarmland();
    MinecraftForge.EVENT_BUS.register(new FarmlandEvents());
  }
}
