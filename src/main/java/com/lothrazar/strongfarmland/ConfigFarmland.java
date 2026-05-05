package com.lothrazar.strongfarmland;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigFarmland {

  public static ModConfigSpec CONFIG;
  public static ModConfigSpec.BooleanValue PLAYER;
  public static ModConfigSpec.BooleanValue TAMEABLE;
  public static ModConfigSpec.BooleanValue HORSE;
  public static ModConfigSpec.BooleanValue GOLEM;
  static {
    final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    BUILDER.comment("If a farmland has moisture, the following entity types will be blocked from trampling it into dirt when the setting is false").push(StrongFarmland.MODID);
    PLAYER = BUILDER.comment("Do players trample farmland").define("player", false);
    TAMEABLE = BUILDER.comment("Do tamed animals (cats, dogs, etc) trample farmland").define("tameable", false);
    HORSE = BUILDER.comment("Do tamed horses trample farmland").define("horse", false);
    GOLEM = BUILDER.comment("Do iron golems trample farmland").define("golem", false);
    BUILDER.pop();
    CONFIG = BUILDER.build();
  }

  public ConfigFarmland(ModContainer modContainer) {
    modContainer.registerConfig(ModConfig.Type.COMMON, CONFIG);
  }
}
