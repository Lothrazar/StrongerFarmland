package com.lothrazar.strongfarmland;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lothrazar.strongfarmland.setup.ClientProxy;
import com.lothrazar.strongfarmland.setup.ConfigHandler;
import com.lothrazar.strongfarmland.setup.IProxy;
import com.lothrazar.strongfarmland.setup.ServerProxy;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.loading.FMLPaths;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("strongfarmland")
public class StrongFarmland {

  private String certificateFingerprint = "@FINGERPRINT@";
  public static final IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());
  public static final String MODID = "strongfarmland";
  private static final Logger LOGGER = LogManager.getLogger();

  public StrongFarmland() {
    MinecraftForge.EVENT_BUS.register(this);
    ConfigHandler.loadConfig(ConfigHandler.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MODID + ".toml"));
  }

  @SubscribeEvent
  public void onFarmlandTrampleEvent(BlockEvent.FarmlandTrampleEvent event) {
    // do something when the server start
    BlockState old = event.getWorld().getBlockState(event.getPos());
    if (old.has(FarmlandBlock.MOISTURE) &&
        old.get(FarmlandBlock.MOISTURE) > 0) {
      // normally 0 dry, 7 wet
      if (event.getEntity() instanceof PlayerEntity) {
        event.setCanceled(true);
      }
      if (event.getEntity() instanceof TameableEntity) {
        TameableEntity tamed = (TameableEntity) event.getEntity();
        if (tamed.isTamed()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof AbstractHorseEntity) {
        AbstractHorseEntity tamed = (AbstractHorseEntity) event.getEntity();
        if (tamed.isTame()) {
          event.setCanceled(true);
        }
      }
      if (event.getEntity() instanceof IronGolemEntity) {
        event.setCanceled(true);
      }
    }
  }

  @SubscribeEvent
  public static void onFingerprintViolation(FMLFingerprintViolationEvent event) {
    // https://tutorials.darkhax.net/tutorials/jar_signing/
    String source = (event.getSource() == null) ? "" : event.getSource().getName() + " ";
    String msg = MODID + "Invalid fingerprint detected! The file " + source + "may have been tampered with. This version will NOT be supported by the author!";
    //System.out.println(msg);
  }
}
