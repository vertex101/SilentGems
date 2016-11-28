package net.silentchaos512.gems.guide;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import amerifrance.guideapi.api.GuideAPI;
import amerifrance.guideapi.api.IPage;
import amerifrance.guideapi.api.impl.Book;
import amerifrance.guideapi.api.impl.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.impl.abstraction.EntryAbstract;
import amerifrance.guideapi.category.CategoryItemStack;
import amerifrance.guideapi.entry.EntryItemStack;
import amerifrance.guideapi.page.PageFurnaceRecipe;
import amerifrance.guideapi.page.PageIRecipe;
import amerifrance.guideapi.page.PageText;
import amerifrance.guideapi.page.PageTextImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.silentchaos512.gems.SilentGems;
import net.silentchaos512.gems.api.lib.EnumMaterialTier;
import net.silentchaos512.gems.block.ModBlocks;
import net.silentchaos512.gems.item.ItemChaosOrb;
import net.silentchaos512.gems.item.ModItems;
import net.silentchaos512.gems.lib.EnumGem;
import net.silentchaos512.gems.lib.Greetings;
import net.silentchaos512.gems.util.ToolHelper;
import net.silentchaos512.lib.util.LocalizationHelper;

public class GuideSilentGems {

  private static boolean CRASH_ON_ERROR = false; // Set to true to generate crash reports. Stacktrace also prints to
                                                 // log.

  public static final String GETTING_STARTED = "gettingStarted";
  public static final String BLOCKS = "blocks";
  public static final String ITEMS = "items";
  public static final String TOOLS = "tools";
  public static final String TERMS = "terms";

  public static Book book;
  private static LocalizationHelper localizationHelper;

  public static void buildGuide(LocalizationHelper loc) {

    localizationHelper = loc;
    int i;
    ItemStack stack;
    String prefix;
    EntryAbstract entry;

    List<CategoryAbstract> categories = Lists.newArrayList();
    Map<ResourceLocation, EntryAbstract> entries;
    List<IPage> pages;

    // =========================
    // Category: Getting Started
    // =========================

    entries = new CategoryMap<>();

    // Entry: Introduction
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".introduction";
    for (i = 0; i < 2; ++i)
      pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), EnumGem.RUBY.getItem());
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Flint Tools
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".flint";
    for (i = 0; i < 2; ++i)
      pages.add(new PageText(getString(prefix + i)));
    ItemStack flintPick = ModItems.pickaxe.constructTool(new ItemStack(Items.BONE),
        new ItemStack(Items.FLINT));
    pages.add(new PageIRecipe(
        new ShapedOreRecipe(flintPick, "fff", " s ", " s ", 'f', Items.FLINT, 's', Items.BONE)));
    for (; i < 4; ++i)
      pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), new ItemStack(Items.FLINT));
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Iron-Tipped
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".ironTipped";
    for (i = 0; i < 2; ++i)
      pages.add(new PageText(getString(prefix + i)));
    ItemStack ironUpgrade = new ItemStack(ModItems.tipUpgrade);
    ItemStack ironTippedFlintPick = ModItems.tipUpgrade.applyToTool(flintPick, ironUpgrade);
    ToolHelper.recalculateStats(ironTippedFlintPick);
    pages.add(new PageIRecipe(new ShapelessOreRecipe(ironTippedFlintPick, flintPick, ironUpgrade)));
    pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), new ItemStack(ModItems.tipUpgrade));
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Gem Tools
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".gemTools";
    for (i = 0; i < 3; ++i)
      pages.add(new PageText(getString(prefix + i)));
    ItemStack rubySapphirePick = ModItems.pickaxe.constructTool(
        ModItems.craftingMaterial.toolRodIron, EnumGem.RUBY.getItem(), EnumGem.SAPPHIRE.getItem(),
        EnumGem.RUBY.getItem());
    pages.add(new PageIRecipe(
        new ShapedOreRecipe(rubySapphirePick, "rsr", " i ", " i ", 'r', EnumGem.RUBY.getItem(), 's',
            EnumGem.SAPPHIRE.getItem(), 'i', ModItems.craftingMaterial.toolRodIron)));
    for (; i < 5; ++i)
      pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), rubySapphirePick);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Gem Armor
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".gemArmor";
    for (i = 0; i < 3; ++i)
      pages.add(new PageText(getString(prefix + i)));
    ItemStack exampleArmor = ModItems.gemChestplate.constructArmor(EnumGem.IOLITE.getItem(),
        EnumGem.BLUE_TOPAZ.getItem(), EnumGem.IOLITE.getItem(), EnumGem.BLACK_DIAMOND.getItem());
    pages
        .add(new PageIRecipe(new ShapedOreRecipe(exampleArmor, " t ", "ifi", " d ", 'f',
            ModItems.armorFrame.getFrameForArmorPiece(ModItems.gemChestplate,
                EnumMaterialTier.REGULAR),
            't', EnumGem.BLUE_TOPAZ.getItem(), 'i', EnumGem.IOLITE.getItem(), 'd',
            EnumGem.BLACK_DIAMOND.getItem())));
    for (; i < 4; ++i)
      pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), exampleArmor);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Chaos Ore
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".chaosOre";
    pages.add(new PageText(getString(prefix + 0)));
    ItemStack diamondUpgrade = new ItemStack(ModItems.tipUpgrade, 1, 2);
    ItemStack diamondTippedPick = ModItems.tipUpgrade.applyToTool(rubySapphirePick, diamondUpgrade);
    ToolHelper.recalculateStats(diamondTippedPick);
    pages.add(new PageIRecipe(
        new ShapelessOreRecipe(diamondTippedPick, rubySapphirePick, diamondUpgrade)));
    pages.add(new PageText(getString(prefix + 1)));
    pages.add(new PageFurnaceRecipe(new ItemStack(ModBlocks.essenceOre)));
    pages.add(new PageText(getString(prefix + 2)));
    entry = new EntryItemStack(pages, getString(prefix), new ItemStack(ModBlocks.essenceOre));
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Super Tools
    pages = Lists.newArrayList();
    prefix = GETTING_STARTED + ".superTools";
    for (i = 0; i < 2; ++i)
      pages.add(new PageText(getString(prefix + i)));
    ItemStack superPick = ModItems.pickaxe.constructTool(true, EnumGem.BLUE_TOPAZ.getItemSuper(),
        EnumGem.HELIODOR.getItemSuper(), EnumGem.BLACK_DIAMOND.getItemSuper());
    pages.add(new PageIRecipe(new ShapedOreRecipe(superPick, "thd", " r ", " r ", 'r',
        ModItems.craftingMaterial.toolRodGold, 't', EnumGem.BLUE_TOPAZ.getItemSuper(), 'h',
        EnumGem.HELIODOR.getItemSuper(), 'd', EnumGem.BLACK_DIAMOND.getItemSuper())));
    for (; i < 4; ++i)
      pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), superPick);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Add category
    String catGettingStarted = getString("category." + GETTING_STARTED);
    categories.add(new CategoryItemStack(entries, catGettingStarted, flintPick));

    // ================
    // Category: Blocks
    // ================

    entries = new CategoryMap<>();

    pages = Lists.newArrayList();
    prefix = BLOCKS + ".chaosAltar";
    pages.addAll(getPages(prefix));
    stack = new ItemStack(ModBlocks.chaosAltar);
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Chaos Flower Pot
    pages = Lists.newArrayList();
    prefix = BLOCKS + ".chaosFlowerPot";
    pages.addAll(getPages(prefix));
    stack = new ItemStack(ModBlocks.chaosFlowerPot);
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Chaos Node
    pages = Lists.newArrayList();
    prefix = BLOCKS + ".chaosNode";
    pages.add(new PageText(getString(prefix + 0)));
    ResourceLocation imageChaosNode = new ResourceLocation(SilentGems.MOD_ID.toLowerCase(),
        "textures/guide/ChaosNode.png");
    pages.add(new PageTextImage(getString(prefix + 1), imageChaosNode, false));
    pages.add(new PageText(getString(prefix + 2)));
    entry = new EntryItemStack(pages, getString(prefix), new ItemStack(ModBlocks.chaosNode));
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Fluffy Blocks
    prefix = BLOCKS + ".fluffyBlock";
    pages = getPages(prefix);
    stack = new ItemStack(ModBlocks.fluffyBlock);
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Gem Lamps
    prefix = BLOCKS + ".gemLamp";
    pages = getPages(prefix);
    stack = new ItemStack(ModBlocks.gemLamp, 1, EnumGem.SAPPHIRE.ordinal());
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Glow Roses
    prefix = BLOCKS + ".glowRose";
    pages = getPages(prefix);
    stack = new ItemStack(ModBlocks.glowRose, 1, EnumGem.AGATE.ordinal());
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Material Grader
    pages = Lists.newArrayList();
    prefix = BLOCKS + ".materialGrader";
    pages.addAll(getPages(prefix));
    stack = new ItemStack(ModBlocks.materialGrader);
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Add category
    String catBlocks = getString("category." + BLOCKS);
    categories.add(new CategoryItemStack(entries, catBlocks, new ItemStack(ModBlocks.chaosPylon)));

    // ===============
    // Category: Items
    // ===============

    entries = new CategoryMap<>();

    // Entry: Chaos Coal
    prefix = ITEMS + ".chaosCoal";
    pages = getPages(prefix);
    stack = ModItems.craftingMaterial.chaosCoal;
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Chaos Orbs
    prefix = ITEMS + ".chaosOrb";
    pages = getPages(prefix);
    stack = new ItemStack(ModItems.chaosOrb, 1, ItemChaosOrb.Type.SUPREME.ordinal());
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Enchantment Tokens
    prefix = ITEMS + ".enchantmentToken";
    pages = Lists.newArrayList();
    pages.add(new PageText(getString(prefix + 0)));
    ItemStack katanaEnchPre = ModItems.katana.constructTool(true, EnumGem.ONYX.getItemSuper());
    ItemStack tokenUnbreaking = ModItems.enchantmentToken.constructToken(Enchantments.UNBREAKING);
    ItemStack tokenLooting = ModItems.enchantmentToken.constructToken(Enchantments.LOOTING);
    ItemStack katanaEnchPost = katanaEnchPre.copy();
    katanaEnchPost.addEnchantment(Enchantments.UNBREAKING, 3);
    katanaEnchPost.addEnchantment(Enchantments.LOOTING, 3);
    pages.add(
        new PageIRecipe(new ShapelessOreRecipe(katanaEnchPost, tokenUnbreaking, tokenUnbreaking,
            tokenUnbreaking, tokenLooting, tokenLooting, tokenLooting, katanaEnchPre)));
    for (i = 1; i < 3; ++i)
      pages.add(new PageText(getString(prefix + i)));
    entry = new EntryItemStack(pages, getString(prefix), tokenUnbreaking);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Fluffy Puffs
    prefix = ITEMS + ".fluffyPuff";
    pages = getPages(prefix);
    entry = new EntryItemStack(pages, getString(prefix), new ItemStack(ModItems.fluffyPuff));
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Iron Potato
    prefix = ITEMS + ".ironPotato";
    pages = getPages(prefix);
    stack = ModItems.craftingMaterial.ironPotato;
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Torch Bandolier
    prefix = ITEMS + ".torchBandolier";
    pages = getPages(prefix);
    stack = new ItemStack(ModItems.torchBandolier);
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Add category
    String catItems = getString("category." + ITEMS);
    categories.add(new CategoryItemStack(entries, catItems, tokenLooting));

    // ===============
    // Category: Tools
    // ===============

    entries = new CategoryMap<>();

    // Entry: Tomahawk
    addToolEntry(entries, "axe", ModItems.axe, 3);
    addToolEntry(entries, "bow", ModItems.bow, 3);
    addToolEntry(entries, "hoe", ModItems.hoe, 2);
    addToolEntry(entries, "katana", ModItems.katana, 3);
    addToolEntry(entries, "pickaxe", ModItems.pickaxe, 3);
    addToolEntry(entries, "scepter", ModItems.scepter, 5);
    addToolEntry(entries, "shield", ModItems.shield, 3);
    addToolEntry(entries, "shovel", ModItems.shovel, 1);
    addToolEntry(entries, "sickle", ModItems.sickle, 3);
    addToolEntry(entries, "sword", ModItems.sword, 2);
    addToolEntry(entries, "tomahawk", ModItems.tomahawk, 4);

    // Add category
    String catTools = getString("category." + TOOLS);
    categories.add(new CategoryItemStack(entries, catTools, katanaEnchPre));

    // =====================
    // Category: Terminology
    // =====================

    entries = new CategoryMap<>();

    // Entry: Chaos
    prefix = TERMS + ".chaos";
    pages = getPages(prefix);
    entry = new EntryItemStack(pages, getString(prefix),
        ModItems.craftingMaterial.chaosEssenceEnriched);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Decorating
    prefix = TERMS + ".decorating";
    pages = getPages(prefix);
    ItemStack pickaxeDecoPre = ModItems.pickaxe.constructTool(true,
        EnumGem.HELIODOR.getItemSuper());
    ItemStack[] decoParts = { EnumGem.RUBY.getItem(), EnumGem.MALACHITE.getItem(),
        EnumGem.SAPPHIRE.getItem(), EnumGem.AMETRINE.getItem() };
    ItemStack pickaxeDecoPost = ToolHelper.decorateTool(pickaxeDecoPre, decoParts[0], decoParts[1],
        decoParts[2], decoParts[3]);
    ToolHelper.recalculateStats(pickaxeDecoPost);
    pages.add(Math.min(2, pages.size()),
        new PageIRecipe(
            new ShapedOreRecipe(pickaxeDecoPost, " n ", "wbe", " s ", 'b', pickaxeDecoPre, 'w',
                decoParts[0], 'n', decoParts[1], 'e', decoParts[2], 's', decoParts[3])));
    entry = new EntryItemStack(pages, getString(prefix), pickaxeDecoPost);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Grade
    prefix = TERMS + ".grade";
    pages = getPages(prefix);
    stack = ModItems.craftingMaterial.magnifyingGlass;
    entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Super Skills
    prefix = TERMS + ".superSkills";
    pages = getPages(prefix);
    entry = new EntryItemStack(pages, getString(prefix), superPick);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Entry: Tier
    prefix = TERMS + ".tier";
    pages = getPages(prefix);
    entry = new EntryItemStack(pages, getString(prefix), new ItemStack(Items.FLINT));
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);

    // Add category
    String catTerminology = getString("category." + TERMS);
    categories.add(new CategoryItemStack(entries, catTerminology, new ItemStack(ModItems.dye)));

    // =============
    // Register Book
    // =============

    try {
      String title = getString("title");
      String welcome = getString("welcome");
      java.awt.Color color = new java.awt.Color(255, 32, 47); // FF202F
      book = new Book();
      book.setCategoryList(categories);
      book.setTitle(title);
      book.setWelcomeMessage(welcome);
      book.setDisplayName(title);
      book.setAuthor("SilentChaos512");
      book.setCustomModel(false);
      book.setColor(color);
      book.setSpawnWithBook(true);
      book.setRegistryName(title);

      GuideAPI.BOOKS.register(book);

      if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
        GuideAPI.setModel(book);
    } catch (Exception ex) {
      warnUserOfGuideApiException(
          "Something went wrong during guide book registration! Report this to me at https://github.com/SilentChaos512/SilentGems/issues."
              + " Include a copy of your log file. If you need the guide book now, try installing an older version of Guide-API, if possible.",
          ex);

      if (CRASH_ON_ERROR)
        throw ex;
    }
  }

  public static void registerGuideBookModel() {

    // Register model (default registration fails)
    if (FMLCommonHandler.instance().getEffectiveSide() != Side.CLIENT)
      return;

    try {
      Item itemGuideBook = GuideAPI.guideBook;
      int meta = GuideAPI.BOOKS.getValues().indexOf(book);
      ModelResourceLocation model = new ModelResourceLocation("guideapi:ItemGuideBook",
          "inventory");
      ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
      ModelLoader.registerItemVariants(itemGuideBook, model);
      mesher.register(itemGuideBook, meta, model);
    } catch (Exception ex) {
      warnUserOfGuideApiException(
          "Failed to register guide book model! Report this on the issue tracker for Silent's Gems.",
          ex);

      if (CRASH_ON_ERROR)
        throw ex;
    }
  }

  private static void warnUserOfGuideApiException(String message, Exception ex) {

    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    ex.printStackTrace(printWriter);
    String stackTrace = stringWriter.toString();

    SilentGems.logHelper.warning(message);
    SilentGems.logHelper.warning(stackTrace);
    Greetings.addExtraMessage(message);
  }

  private static String getString(String key) {

    return localizationHelper.getLocalizedString("guide", key).replaceAll("\\\\n", "\n");
  }

  private static List<IPage> getPages(String prefixKey) {

    List<IPage> list = Lists.newArrayList();
    int i = 0;
    String key = prefixKey + i;
    String str = getString(key);

    while (!str.equals("guide.silentgems:" + key)) {
      list.add(new PageText(str));
      key = prefixKey + ++i;
      str = getString(key);
    }

    return list;
  }

  private static void addToolEntry(Map<ResourceLocation, EntryAbstract> entries, String toolName,
      Item toolItem, int headCount) {

    // Get the pages
    String prefix = TOOLS + "." + toolName;
    List<IPage> pages = getPages(prefix);

    // Use a random gem to generate a tool for the entry icon.
    EnumGem gem = EnumGem.values()[SilentGems.random.nextInt(EnumGem.values().length)];
    boolean superTool = toolItem == ModItems.katana || toolItem == ModItems.scepter;
    ItemStack stack = ToolHelper.constructTool(toolItem,
        superTool ? ModItems.craftingMaterial.toolRodSilver : ModItems.craftingMaterial.toolRodIron,
        superTool ? gem.getItemSuper() : gem.getItem(), headCount);
    if (toolItem == ModItems.katana)
      stack = ModItems.katana.addDefaultGrip(stack);

    // Create and add the entry.
    EntryItemStack entry = new EntryItemStack(pages, getString(prefix), stack);
    entries.put(new ResourceLocation(SilentGems.MOD_ID, prefix), entry);
  }
}
