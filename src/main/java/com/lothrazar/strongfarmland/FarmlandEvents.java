package com.lothrazar.strongfarmland;

import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent.FarmlandTrampleEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FarmlandEvents {

  @SubscribeEvent
  public void onFarmlandTrampleEvent(FarmlandTrampleEvent event) {
    // do something when the server start
    BlockState old = event.getLevel().getBlockState(event.getPos());
    if (old.hasProperty(FarmBlock.MOISTURE) &&
        old.getValue(FarmBlock.MOISTURE) > 0) {
      // normally 0 dry, 7 wet
      if (event.getEntity() instanceof Player && !ConfigFarmland.PLAYER.get()) {
        event.setCanceled(true);
      }
      if (event.getEntity() instanceof TamableAnimal tamed) {
        if (tamed.isTame() && !ConfigFarmland.TAMEABLE.get()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof AbstractHorse tamed) {
        if (tamed.isTamed() && !ConfigFarmland.HORSE.get()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof IronGolem
          && !ConfigFarmland.GOLEM.get()) {
        event.setCanceled(true);
      }
    }
  }
}
