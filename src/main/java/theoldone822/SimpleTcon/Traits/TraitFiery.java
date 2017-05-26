package theoldone822.SimpleTcon.Traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

import javax.annotation.Nonnull;

import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.potion.TinkerPotion;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.shared.client.ParticleEffect;
import slimeknights.tconstruct.tools.TinkerTools;

public class TraitFiery extends AbstractTrait {


  public TraitFiery() {
    super("fieryt", 0xffffff);
  }

  @Override
  public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
	    target.setFire(5);
	    if(attackEntitySecondary(new EntityDamageSource("fire", player).setFireDamage(), 5, target, false, true)) {
	      int count = Math.round(5);
	      TinkerTools.proxy.spawnEffectParticle(ParticleEffect.Type.HEART_FIRE, target, count);
	    }
  }


}
