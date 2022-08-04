package com.lothrazar.strongfarmland;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(StrongFarmland.MODID)
public class StrongFarmland {

  public static final String MODID = "strongfarmland";

  public StrongFarmland() {
    MinecraftForge.EVENT_BUS.register(this);
    ConfigHandler.loadConfig(ConfigHandler.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + ".toml"));
  }

  @SubscribeEvent
  public void onFarmlandTrampleEvent(FarmlandTrampleEvent event) {
    // do something when the server start
    BlockState old = event.getLevel().getBlockState(event.getPos());
    if (old.hasProperty(FarmBlock.MOISTURE) &&
        old.getValue(FarmBlock.MOISTURE) > 0) {
      // normally 0 dry, 7 wet
      if (event.getEntity() instanceof Player && !ConfigHandler.PLAYER.get()) {
        event.setCanceled(true);
      }
      if (event.getEntity() instanceof TamableAnimal) {
        TamableAnimal tamed = (TamableAnimal) event.getEntity();
        if (tamed.isTame() && !ConfigHandler.TAMEABLE.get()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof AbstractHorse) {
        AbstractHorse tamed = (AbstractHorse) event.getEntity();
        if (tamed.isTamed() && !ConfigHandler.HORSE.get()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof IronGolem
          && !ConfigHandler.GOLEM.get()) {
        event.setCanceled(true);
      }
    }
  }
}
