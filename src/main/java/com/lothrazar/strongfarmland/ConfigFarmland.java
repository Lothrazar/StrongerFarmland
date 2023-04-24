package com.lothrazar.strongfarmland;

import com.lothrazar.library.config.ConfigTemplate;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ConfigFarmland extends ConfigTemplate {

  public static ForgeConfigSpec CONFIG;
  public static BooleanValue PLAYER;
  public static BooleanValue TAMEABLE;
  public static BooleanValue HORSE;
  public static BooleanValue GOLEM;
  static {
    final ForgeConfigSpec.Builder BUILDER = builder();
    BUILDER.comment("If a farmland has moisture, the following entity types will be blocked from trampling it into dirt when the setting is false").push(StrongFarmland.MODID);
    PLAYER = BUILDER.comment("Do players trample farmland").define("player", false);
    TAMEABLE = BUILDER.comment("Do tamed animals (cats, dogs, etc) trample farmland").define("tameable", false);
    HORSE = BUILDER.comment("Do tamed horses trample farmland").define("horse", false);
    GOLEM = BUILDER.comment("Do iron golems trample farmland").define("golem", false);
    BUILDER.pop();
    CONFIG = BUILDER.build();
  }

  public ConfigFarmland() {
    CONFIG.setConfig(setup(StrongFarmland.MODID));
  }
}
