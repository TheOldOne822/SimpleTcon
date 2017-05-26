package theoldone822.SimpleTcon.Traits;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

import java.util.List;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitIlluminating extends AbstractTrait {

  public TraitIlluminating() {
    super("illuminating", 0xff0000);
  }

  @Override
  public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit){
	    	player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 101));
  }

}
