package com.lothrazar.strongfarmland;

import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(StrongFarmland.MODID)
public class StrongFarmland {

  public static final String MODID = "strongfarmland";

  public StrongFarmland() {
    MinecraftForge.EVENT_BUS.register(this);
    ConfigHandler.loadConfig(ConfigHandler.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + ".toml"));
  }

  @SubscribeEvent
  public void onFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
    // do something when the server start
    BlockState old = event.getWorld().getBlockState(event.getPos());
    if (old.hasProperty(FarmlandBlock.MOISTURE) &&
        old.get(FarmlandBlock.MOISTURE) > 0) {
      // normally 0 dry, 7 wet
      if (event.getEntity() instanceof PlayerEntity && !ConfigHandler.PLAYER.get()) {
        event.setCanceled(true);
      }
      if (event.getEntity() instanceof TameableEntity) {
        TameableEntity tamed = (TameableEntity) event.getEntity();
        if (tamed.isTamed() && !ConfigHandler.TAMEABLE.get()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof AbstractHorseEntity) {
        AbstractHorseEntity tamed = (AbstractHorseEntity) event.getEntity();
        if (tamed.isTame() && !ConfigHandler.HORSE.get()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof IronGolemEntity
          && !ConfigHandler.GOLEM.get()) {
        event.setCanceled(true);
      }
    }
  }

  @SubscribeEvent
  public static void onFingerprintViolation(FMLFingerprintViolationEvent event) {
    // https://tutorials.darkhax.net/tutorials/jar_signing/
    String source = (event.getSource() == null) ? "" : event.getSource().getName() + " ";
    String msg = MODID + "Invalid fingerprint detected! The file " + source + "may have been tampered with. This version will NOT be supported by the author!";
    System.out.println(msg);
  }
}
