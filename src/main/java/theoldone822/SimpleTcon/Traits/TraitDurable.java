package theoldone822.SimpleTcon.Traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitDurable extends AbstractTrait {

  public TraitDurable() {
    super("durable", TextFormatting.LIGHT_PURPLE);
  }

  @Override
  public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
    float r = random.nextFloat();
    if(r < 0.1f) {
        return Math.max(0, newDamage - damage);
    }
    else if(r < 0.3f) {
      return Math.max(0, newDamage - (damage / 2));
    }
    else if(r < 0.5f) {
        return Math.max(0, newDamage - (damage / 10));
    }
    else {
      return super.onToolDamage(tool, damage, newDamage, entity);
    }
  }
}
