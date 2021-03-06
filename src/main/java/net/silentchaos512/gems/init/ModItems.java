package net.silentchaos512.gems.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.config.GemsConfig;
import net.silentchaos512.gems.guide.GuideBookGems;
import net.silentchaos512.gems.item.*;
import net.silentchaos512.gems.item.armor.ItemArmorFrame;
import net.silentchaos512.gems.item.armor.ItemGemArmor;
import net.silentchaos512.gems.item.quiver.ItemQuiver;
import net.silentchaos512.gems.item.quiver.ItemQuiverEmpty;
import net.silentchaos512.gems.item.tool.*;
import net.silentchaos512.gems.lib.GemsCreativeTabs;
import net.silentchaos512.gems.lib.Names;
import net.silentchaos512.lib.item.IEnumItems;
import net.silentchaos512.lib.item.ItemGuideBookSL;
import net.silentchaos512.lib.registry.RecipeMaker;
import net.silentchaos512.lib.registry.SRegistry;
import net.silentchaos512.lib.util.GameUtil;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final ItemGem gem = new ItemGem();
    public static final ItemGemSuper gemSuper = new ItemGemSuper();
    public static final ItemGemShard gemShard = new ItemGemShard();
    public static final ItemSoulGem soulGem = new ItemSoulGem();
    public static final CraftingItems.ItemCrafting craftingMaterial = CraftingItems.ItemCrafting.INSTANCE;
    public static final ItemChargingAgent chargingAgent = new ItemChargingAgent();
    public static final ItemQuiver quiver = new ItemQuiver();
    public static final ItemQuiverEmpty quiverEmpty = new ItemQuiverEmpty();
    public static final ItemTipUpgrade tipUpgrade = new ItemTipUpgrade();
    public static final ItemEnchantmentToken enchantmentToken = new ItemEnchantmentToken();
    public static final ItemToolSoul toolSoul = new ItemToolSoul();
    public static final ItemSkillOrb skillOrb = new ItemSkillOrb();
    public static final ItemChaosGem chaosGem = new ItemChaosGem();
    public static final ItemChaosRune chaosRune = new ItemChaosRune();
    public static final ItemArmorFrame armorFrame = new ItemArmorFrame();
    public static final ItemFluffyPuffSeeds fluffyPuffSeeds = new ItemFluffyPuffSeeds();
    public static final ItemFluffyPuff fluffyPuff = new ItemFluffyPuff();
    public static final ItemGlowRoseFertilizer glowRoseFertilizier = new ItemGlowRoseFertilizer();
    public static final ItemDyeSG dye = new ItemDyeSG();
    public static final ItemFoodSG food = new ItemFoodSG();
    public static final ItemHoldingGem holdingGem = new ItemHoldingGem();
    public static final ItemTorchBandolier torchBandolier = new ItemTorchBandolier();
    public static final ItemDrawingCompass drawingCompass = new ItemDrawingCompass();
    public static final ItemChaosOrb chaosOrb = new ItemChaosOrb();
    public static final ItemNodeMover nodeMover = new ItemNodeMover();
    public static final ItemTeleporterLinker teleporterLinker = new ItemTeleporterLinker();
    public static final ItemReturnHome returnHomeCharm = new ItemReturnHome();
    public static final ItemPetSummoner petSummoner = new ItemPetSummoner();
    public static final ItemDebug debugItem = new ItemDebug();

    public static final ItemGemArrow arrow = new ItemGemArrow();

    // Tools
    public static final ItemGemSword sword = new ItemGemSword();
    public static final ItemGemDagger dagger = new ItemGemDagger();
    public static final ItemGemKatana katana = new ItemGemKatana();
    public static final ItemGemMachete machete = new ItemGemMachete();
    public static final ItemGemScepter scepter = new ItemGemScepter();
    public static final ItemGemTomahawk tomahawk = new ItemGemTomahawk();
    public static final ItemGemBow bow = new ItemGemBow();
    public static final ItemGemShield shield = new ItemGemShield();
    public static final ItemGemPickaxe pickaxe = new ItemGemPickaxe();
    public static final ItemGemShovel shovel = new ItemGemShovel();
    public static final ItemGemAxe axe = new ItemGemAxe();
    public static final ItemGemPaxel paxel = new ItemGemPaxel();
    public static final ItemGemHoe hoe = new ItemGemHoe();
    public static final ItemGemSickle sickle = new ItemGemSickle();

    // Armor
    public static final ItemGemArmor gemHelmet = new ItemGemArmor(0, EntityEquipmentSlot.HEAD);
    public static final ItemGemArmor gemChestplate = new ItemGemArmor(1, EntityEquipmentSlot.CHEST);
    public static final ItemGemArmor gemLeggings = new ItemGemArmor(2, EntityEquipmentSlot.LEGS);
    public static final ItemGemArmor gemBoots = new ItemGemArmor(3, EntityEquipmentSlot.FEET);

    public static final ToolRenderHelperBase toolRenderHelper = GameUtil.isClient()
            ? new ToolRenderHelper() : new ToolRenderHelperBase();

    // Guide Book
    public static final ItemGuideBookSL guideBook = new ItemGuideBookSL(new GuideBookGems());

    public static final List<Item> tools = new ArrayList<>();

    public static void registerAll(SRegistry reg) {
        IEnumItems.RegistrationHelper enumItems = new IEnumItems.RegistrationHelper(reg);

        reg.registerItem(gem, Names.GEM).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(gemSuper, Names.GEM_SUPER).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(gemShard, Names.GEM_SHARD).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(soulGem, Names.SOUL_GEM).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(craftingMaterial, Names.CRAFTING_MATERIAL).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(chargingAgent, "charging_agent").setCreativeTab(GemsCreativeTabs.MATERIALS);
        enumItems.registerItems(SoulUrnUpgrades.values());
        reg.registerItem(quiver, Names.QUIVER_NON_EMPTY).setCreativeTab(null);
        reg.registerItem(quiverEmpty, Names.QUIVER).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(tipUpgrade, Names.UPGRADE_TIP).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(enchantmentToken, Names.ENCHANTMENT_TOKEN).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(toolSoul, Names.TOOL_SOUL).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(skillOrb, Names.SKILL_ORB).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(chaosGem, Names.CHAOS_GEM).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(chaosRune, Names.CHAOS_RUNE).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(armorFrame, Names.ARMOR_FRAME).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(fluffyPuffSeeds, Names.FLUFFY_PUFF_SEEDS).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(fluffyPuff, Names.FLUFFY_PUFF).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(glowRoseFertilizier, Names.GLOW_ROSE_FERTILIZER).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(dye, Names.DYE).setCreativeTab(GemsCreativeTabs.MATERIALS);
        reg.registerItem(food, Names.FOOD).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(holdingGem, Names.HOLDING_GEM).setCreativeTab(GemsCreativeTabs.TOOLS);
        reg.registerItem(torchBandolier, Names.TORCH_BANDOLIER).setCreativeTab(GemsCreativeTabs.TOOLS);
        reg.registerItem(drawingCompass, Names.DRAWING_COMPASS).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(chaosOrb, Names.CHAOS_ORB).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(nodeMover, Names.NODE_MOVER).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(teleporterLinker, Names.TELEPORTER_LINKER).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(returnHomeCharm, Names.RETURN_HOME_CHARM).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(petSummoner, Names.PET_SUMMONER).setCreativeTab(GemsCreativeTabs.UTILITY);
        reg.registerItem(debugItem, Names.DEBUG_ITEM).setCreativeTab(GemsCreativeTabs.UTILITY);

        reg.setDefaultCreativeTab(GemsCreativeTabs.TOOLS);

        reg.registerItem(arrow, Names.ARROW).setCreativeTab(GemsCreativeTabs.TOOLS);

        // Tools
        registerTool(reg, sword, Names.SWORD);
        registerTool(reg, dagger, Names.DAGGER);
        registerTool(reg, katana, Names.KATANA);
        registerTool(reg, machete, Names.MACHETE);
        registerTool(reg, scepter, Names.SCEPTER);
        registerTool(reg, tomahawk, Names.TOMAHAWK);
        registerTool(reg, bow, Names.BOW);
        registerTool(reg, shield, Names.SHIELD);
        registerTool(reg, pickaxe, Names.PICKAXE);
        registerTool(reg, shovel, Names.SHOVEL);
        registerTool(reg, axe, Names.AXE);
        registerTool(reg, paxel, Names.PAXEL);
        registerTool(reg, hoe, Names.HOE);
        registerTool(reg, sickle, Names.SICKLE);

        // Armor
        registerArmor(reg, gemHelmet, Names.HELMET);
        registerArmor(reg, gemChestplate, Names.CHESTPLATE);
        registerArmor(reg, gemLeggings, Names.LEGGINGS);
        registerArmor(reg, gemBoots, Names.BOOTS);

        // ToolRenderHelper
        reg.registerItem(toolRenderHelper, Names.TOOL_RENDER_HELPER).setCreativeTab(null);
        toolRenderHelper.init();

        reg.registerItem(guideBook, "guide_book");

        initExtraRecipes();
    }

    private static void registerTool(SRegistry registry, Item item, String name) {
        registry.registerItem(item, name).setCreativeTab(GemsCreativeTabs.TOOLS);
        GemsConfig.NODE_REPAIR_WHITELIST.add(item);
        if (!(item instanceof ItemGemShield)) tools.add(item);
    }

    private static void registerArmor(SRegistry registry, Item item, String name) {
        registry.registerItem(item, name).setCreativeTab(GemsCreativeTabs.TOOLS);
        GemsConfig.NODE_REPAIR_WHITELIST.add(item);
    }

    private static void initExtraRecipes() {
        RecipeMaker recipes = SilentGems.registry.getRecipeMaker();
        recipes.addShapeless("flint", new ItemStack(Items.FLINT), Blocks.GRAVEL, Blocks.GRAVEL);
        recipes.addShapeless("guide_book", new ItemStack(guideBook), Items.BOOK, new ItemStack(gem, 1, OreDictionary.WILDCARD_VALUE));
    }
}
