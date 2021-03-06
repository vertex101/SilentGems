package net.silentchaos512.gems.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityGemArrow extends EntityTippedArrow {

  ItemStack arrowStack = ItemStack.EMPTY;

  public EntityGemArrow(World worldIn) {

    super(worldIn);
  }

  public EntityGemArrow(World worldIn, double x, double y, double z) {

    super(worldIn, x, y, z);
  }

  public EntityGemArrow(World worldIn, EntityLivingBase shooter) {

    super(worldIn, shooter);
  }

  public EntityGemArrow(World worldIn, EntityLivingBase shooter, ItemStack arrowStack) {

    super(worldIn, shooter);
    this.arrowStack = arrowStack.copy();
    this.arrowStack.setCount(1);
  }

  @Override
  protected ItemStack getArrowStack() {

    return arrowStack;
  }

}
