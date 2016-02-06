package net.silentchaos512.gems.core.util;

import net.minecraft.util.StatCollector;

public class LocalizationHelper {

  public final static String BLOCKS_PREFIX = "tile.silentgems:";
  public final static String GEMS_PREFIX = "gems.silentgems:";
  public final static String MISC_PREFIX = "misc.silentgems:";
  public final static String ITEM_PREFIX = "item.silentgems:";
  public final static String TOOL_PREFIX = "tool.silentgems:";
  public static final String GUI_PREFIX = "gui.silentgems:";

  public static String getBlockDescription(String blockName, int index) {

    return getLocalizedString(getBlockDescriptionKey(blockName, index));
  }

  public static String getBlockDescriptionKey(String blockName, int index) {

    return BLOCKS_PREFIX + blockName + ".desc" + (index > 0 ? index : "");
  }

  public static String getGemName(int id) {

    return StatCollector.translateToLocal(GEMS_PREFIX + id).trim();
  }

  public static String getItemDescription(String itemName, int index) {

    return getLocalizedString(getItemDescriptionKey(itemName, index));
  }

  public static String getItemDescriptionKey(String itemName, int index) {

    return ITEM_PREFIX + itemName + ".desc" + (index > 0 ? index : "");
  }

  public static String getLocalizedString(String key) {

    return StatCollector.translateToLocal(key).trim();
  }

  public static String getMiscText(String key) {

    return getLocalizedString(MISC_PREFIX + key);
  }

  public static String getOtherBlockKey(String blockName, String key) {

    return getLocalizedString(BLOCKS_PREFIX + blockName + "." + key);
  }

  public static String getOtherItemKey(String itemName, String key) {

    return getLocalizedString(ITEM_PREFIX + itemName + "." + key);
  }

  public static String getGuiString(String key) {

    return getLocalizedString(GUI_PREFIX + key);
  }
}
