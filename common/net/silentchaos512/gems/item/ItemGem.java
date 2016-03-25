package net.silentchaos512.gems.item;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.api.lib.EnumMaterialTier;
import net.silentchaos512.gems.lib.EnumGem;
import net.silentchaos512.gems.lib.Names;
import net.silentchaos512.lib.item.ItemSL;
import net.silentchaos512.lib.util.RecipeHelper;

public class ItemGem extends ItemSL {

  public ItemGem() {

    super(64, SilentGems.MOD_ID, Names.GEM);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advanced) {

    EnumGem gem = EnumGem.getFromStack(stack);
    EnumMaterialTier tier = stack.getItemDamage() > 31 ? EnumMaterialTier.SUPER
        : EnumMaterialTier.REGULAR;

    if (gem != null) {
      list.add("durability: " + gem.getDurability(tier));
      list.add("mining speed: " + gem.getMiningSpeed(tier));
      list.add("enchantability: " + gem.getEnchantability(tier));
      list.add("dmg1: " + gem.getMeleeDamage(tier));
      list.add("dmg2: " + gem.getMagicDamage(tier));
      list.add("cs: " + gem.getChargeSpeed(tier));
    }
  }

  @Override
  public void addRecipes() {

    for (EnumGem gem : EnumGem.values()) {
      // Supercharged gems
      GameRegistry.addRecipe(new ShapedOreRecipe(gem.getItemSuper(), "cgc", "cdc", "cgc", 'g',
          gem.getItem(), 'd', "dustGlowstone", 'c', "gemChaos"));
      // Gems <--> shards
      RecipeHelper.addCompressionRecipe(gem.getShard(), gem.getItem(), 9);
    }
  }

  @Override
  public boolean hasEffect(ItemStack stack) {

    return stack.getItemDamage() > 31;
  }

  @Override
  public List<ModelResourceLocation> getVariants() {

    List<ModelResourceLocation> models = Lists.newArrayList();
    int i;
    for (i = 0; i < 16; ++i) {
      models.add(new ModelResourceLocation(getFullName() + i, "inventory"));
    }
    for (i = 0; i < 16; ++i) {
      models.add(new ModelResourceLocation(getFullName() + "Dark" + i, "inventory"));
    }
    for (i = 0; i < 16; ++i) {
      models.add(new ModelResourceLocation(getFullName() + "Super" + i, "inventory"));
    }
    for (i = 0; i < 16; ++i) {
      models.add(new ModelResourceLocation(getFullName() + "SuperDark" + i, "inventory"));
    }
    return models;
  }
}
