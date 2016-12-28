package net.silentchaos512.gems.lib;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.silentchaos512.gems.SilentGems;

public class ChaosBuff {

  public static final ChaosBuff SPEED;
  public static final ChaosBuff SLOWNESS;
  public static final ChaosBuff HASTE;
  public static final ChaosBuff MINING_FATIGUE;
  public static final ChaosBuff STRENGTH;
  public static final ChaosBuff JUMP_BOOST;
  public static final ChaosBuff FLIGHT;
  public static final ChaosBuff NAUSEA;
  public static final ChaosBuff REGENERATION;
  public static final ChaosBuff RESISTANCE;
  public static final ChaosBuff FIRE_RESISTANCE;
  public static final ChaosBuff WATER_BREATHING;
  public static final ChaosBuff INVISIBILITY;
  public static final ChaosBuff BLINDNESS;
  public static final ChaosBuff NIGHT_VISION;
  public static final ChaosBuff HUNGER;
  public static final ChaosBuff WEAKNESS;
  public static final ChaosBuff POISON;
  public static final ChaosBuff WITHER;
  public static final ChaosBuff GLOWING;
  public static final ChaosBuff LEVITATION;
  public static final ChaosBuff CAPACITY;
  public static final ChaosBuff RECHARGE;

  static final Map<String, ChaosBuff> buffMap = new LinkedHashMap<>();

  /** A unique identifier for the buff. */
  final @Nonnull String key;
  /** The potion effect the buff applies, if any. */
  final @Nullable Potion potion;
  /** The maximum level of the buff that can be applied (assuming the gem has enough free slots. */
  final int maxLevel;
  /**
   * The base number of slots used. Additional slots are used for higher levels.
   * 
   * @see {@link ChaosBuff#getSlotsUsed(int)}
   */
  final int slotsUsed;
  /**
   * The amount of Chaos used per tick to apply the buff. Higher levels consume additional Chaos.
   * 
   * @see {@link ChaosBuff#getChaosCost(int)}
   */
  final int chaosCost;
  /**
   * The duration to apply. By default, this is done per tick, but there are exceptions. Note that smaller durations are
   * better. I use 20 ticks for most, but night vision requires more than 400 ticks to prevent the flashing effect.
   * Regeneration also has a special exception.
   * 
   * @see {@link ChaosBuff#getApplyDuration(EntityPlayer, int)}
   */
  final int applyDuration;

  public ChaosBuff(String key, int maxLevel, int slotsUsed, int chaosCost, int applyDuration,
      @Nullable Potion potion) {

    if (buffMap.containsKey(key))
      throw new IllegalArgumentException("Buff with key " + key + " has already been added!");

    this.key = key;
    this.maxLevel = maxLevel;
    this.potion = potion;
    this.slotsUsed = slotsUsed;
    this.chaosCost = chaosCost;
    this.applyDuration = applyDuration;

    buffMap.put(key, this);
  }

  static {

    final String prefix = SilentGems.RESOURCE_PREFIX;
    final int dur = 30;
    // @formatter:off
    //                                                       mLvl slots cost duration
    CAPACITY        = new ChaosBuff(prefix + "capacity",        4,   1,   0, dur, null);
    RECHARGE        = new ChaosBuff(prefix + "recharge",        4,   1,   0, dur, null);
    SPEED           = new ChaosBuff(prefix + "speed",           4,   4,  20, dur, MobEffects.SPEED);
    HASTE           = new ChaosBuff(prefix + "haste",           2,   4,  30, dur, MobEffects.HASTE);
    JUMP_BOOST      = new ChaosBuff(prefix + "jump_boost",      4,   4,  10, dur, MobEffects.JUMP_BOOST);
    FLIGHT          = new ChaosBuff(prefix + "flight",          1,  10,  70, dur, null);
    STRENGTH        = new ChaosBuff(prefix + "strength",        2,  10,  50, dur, MobEffects.STRENGTH);
    REGENERATION    = new ChaosBuff(prefix + "regeneration",    2,   8,  42,  80, MobEffects.REGENERATION);
    RESISTANCE      = new ChaosBuff(prefix + "resistance",      2,   6,  40, dur, MobEffects.RESISTANCE);
    FIRE_RESISTANCE = new ChaosBuff(prefix + "fire_resistance", 1,   8,  40, dur, MobEffects.FIRE_RESISTANCE);
    WATER_BREATHING = new ChaosBuff(prefix + "water_breathing", 1,   4,  30, dur, MobEffects.WATER_BREATHING);
    NIGHT_VISION    = new ChaosBuff(prefix + "night_vision",    1,   2,  10, 410, MobEffects.NIGHT_VISION);
    INVISIBILITY    = new ChaosBuff(prefix + "invisibility",    1,   6,  25, dur, MobEffects.INVISIBILITY);
    LEVITATION      = new ChaosBuff(prefix + "levitation",      1,   3,  20, dur, MobEffects.LEVITATION);
    GLOWING         = new ChaosBuff(prefix + "glowing",         1,   0,   5, dur, MobEffects.GLOWING);
    SLOWNESS        = new ChaosBuff(prefix + "slowness",        3,  -2,   5, dur, MobEffects.SLOWNESS);
    MINING_FATIGUE  = new ChaosBuff(prefix + "mining_fatigue",  3,  -2,   5, dur, MobEffects.MINING_FATIGUE);
    NAUSEA          = new ChaosBuff(prefix + "nausea",          3,  -4,   5, dur, MobEffects.NAUSEA);
    BLINDNESS       = new ChaosBuff(prefix + "blindness",       3,  -3,   5, dur, MobEffects.BLINDNESS);
    HUNGER          = new ChaosBuff(prefix + "hunger",          3,  -2,   5, dur, MobEffects.HUNGER);
    WEAKNESS        = new ChaosBuff(prefix + "weakness",        3,  -2,   5, dur, MobEffects.WEAKNESS);
    POISON          = new ChaosBuff(prefix + "poison",          3,  -2,   5, dur, MobEffects.POISON);
    WITHER          = new ChaosBuff(prefix + "wither",          2,  -3,   5, dur, MobEffects.WITHER);
    // @formatter:on
  }

  public static @Nullable ChaosBuff byKey(String key) {

    return buffMap.get(key);
  }

  public static Collection<ChaosBuff> getAllBuffs() {

    return buffMap.values();
  }

  public void applyToPlayer(EntityPlayer player, int level, ItemStack stack) {

    if (potion != null) {
      int duration = getApplyDuration(player, level);
      if (duration > 0)
        player.addPotionEffect(new PotionEffect(potion, duration, level - 1, false, false));
    }

    // TODO: Flight?
  }

  public void removeFromPlayer(EntityPlayer player, int level, ItemStack stack) {

    if (potion != null) {
      player.removePotionEffect(potion);
    }

    // TODO: Flight?
  }

  /**
   * @return The number of slots used. Each level beyond 1 adds a slot (or subtracts it if slotsUsed is negative)
   */
  public int getSlotsUsed(int level) {

    if (slotsUsed == 0)
      return 0;
    return slotsUsed + (level - 1) * (slotsUsed / Math.abs(slotsUsed));
  }

  /**
   * @return The amount of Chaos drained per tick by this effect. Each level beyond 1 adds 20% to the cost.
   */
  public int getChaosCost(int level, EntityPlayer player) {

    if (this == FLIGHT && !player.capabilities.isFlying)
      return 0;
    return chaosCost + chaosCost * (level - 1) / 5;
  }

  /**
   * @return The duration to apply for this effect. In most cases, this is just applyDuration, but regeneration has some
   *         special requirements.
   */
  public int getApplyDuration(EntityPlayer player, int level) {

    if (this.key.equals(REGENERATION.key)) {
      // Should apply every 2 seconds for regen I, every second for regen II.
      // Regen resets its timer when reapplied, so it won't work if applied too often.
      boolean shouldApply = false;

      PotionEffect activeRegen = player.getActivePotionEffect(MobEffects.REGENERATION);
      if (activeRegen == null) {
        shouldApply = true;
      } else {
        int remainingTime = activeRegen.getDuration();
        int healTime = level == 2 ? 20 : 40;
        if (remainingTime + healTime <= applyDuration)
          shouldApply = true;
      }

      return shouldApply ? applyDuration : 0;
    }

    return applyDuration;
  }

  public String getLocalizedName(int level) {

    if (potion != null) {
      String str = SilentGems.localizationHelper.getLocalizedString(potion.getName());
      if (level > 1)
        str += " " + (level == 2 ? "II" : level == 3 ? "III" : level == 4 ? "IV" : "" + level);
      return str;
    }
    return SilentGems.localizationHelper.getLocalizedString("buff." + key);
  }

  public String getDescription() {

    String descKey = "buff." + key + ".desc";
    String desc = SilentGems.localizationHelper.getLocalizedString(descKey);
    return !desc.equals(descKey) ? desc : "";
  }

  public int getColor() {

    if (potion != null)
      return potion.getLiquidColor();

    if (this == CAPACITY)
      return 0xA538C9;
    if (this == RECHARGE)
      return 0xFFF79E;

    return 0xFFFFFF;
  }

  public int getMaxLevel() {

    return maxLevel;
  }

  public String getKey() {

    return key;
  }

  public @Nullable Potion getPotion() {

    return potion;
  }
}
