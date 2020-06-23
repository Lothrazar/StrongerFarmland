package com.lothrazar.strongfarmland.setup;

import java.nio.file.Path;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.lothrazar.strongfarmland.StrongFarmland;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class ConfigHandler {

  private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
  public static ForgeConfigSpec COMMON_CONFIG;
  public static BooleanValue PLAYER;
  public static BooleanValue TAMEABLE;
  public static BooleanValue HORSE;
  public static BooleanValue GOLEM;
  static {
    initConfig();
  }

  private static void initConfig() {
    COMMON_BUILDER.comment("If a farmland has moisture, the following entity types will be blocked from trampling it into dirt when the setting is false").push(StrongFarmland.MODID);
    PLAYER = COMMON_BUILDER.comment("Do players trample farmland").define("player", false);
    TAMEABLE = COMMON_BUILDER.comment("Do tamed animals (cats, dogs, etc) trample farmland").define("tameable", false);
    HORSE = COMMON_BUILDER.comment("Do tamed horses trample farmland").define("horse", false);
    GOLEM = COMMON_BUILDER.comment("Do iron golems trample farmland").define("golem", false);
    COMMON_BUILDER.pop();
    COMMON_CONFIG = COMMON_BUILDER.build();
  }

  public static void loadConfig(ForgeConfigSpec spec, Path path) {
    final CommentedFileConfig configData = CommentedFileConfig.builder(path)
        .sync()
        .autosave()
        .writingMode(WritingMode.REPLACE)
        .build();
    configData.load();
    spec.setConfig(configData);
  }
}
