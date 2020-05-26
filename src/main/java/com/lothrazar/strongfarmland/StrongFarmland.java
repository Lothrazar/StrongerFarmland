package com.lothrazar.strongfarmland;

import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = StrongFarmland.MODID, certificateFingerprint = "@FINGERPRINT@")
public class StrongFarmland {

  public static final String MODID = "strongfarmland";

  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    MinecraftForge.EVENT_BUS.register(this);
  }

  @SubscribeEvent
  public void onFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
    // do something when the server start
    IBlockState old = event.getWorld().getBlockState(event.getPos());
    if (old.getProperties().containsKey(BlockFarmland.MOISTURE) &&
        old.getValue(BlockFarmland.MOISTURE) > 0) {
      // normally 0 dry, 7 wet
      if (event.getEntity() instanceof EntityPlayer) {
        event.setCanceled(true);
      }
      if (event.getEntity() instanceof EntityTameable) {
        EntityTameable tamed = (EntityTameable) event.getEntity();
        if (tamed.isTamed()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof AbstractHorse) {
        AbstractHorse tamed = (AbstractHorse) event.getEntity();
        if (tamed.isTame()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof EntityIronGolem) {
        event.setCanceled(true);
      }
    }
  }
}
