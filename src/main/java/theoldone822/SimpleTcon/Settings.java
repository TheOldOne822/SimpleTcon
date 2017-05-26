package theoldone822.SimpleTcon;

import java.io.File;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigItem;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.logger.LogHelper;

/**
 * @author AleXndrTheGr8st
 */
public class Settings {
	private static Configuration settings = new Configuration();
	
	public static void createOrLoadSettings(FMLPreInitializationEvent event) {
		settings.setModName("Simple Tcon");
		File configDir = new File(event.getModConfigurationDirectory() + "/TheOldOne", "Simple Tcon Settings.xml");
		settings.setFile(configDir);
		
		LogHelper.verbose("Simple Tcon", "Loading Settings...");
		try {
			settings.load();
			
			//Config Help
			ConfigEntry link = new ConfigEntry("Documentation", "ConfigHelp");
				link.createNewValue("DocumentationLink").setActive().setDataType("@S").setCurrentValue("LINK TO GITHUB GOES HERE").setDefaultValue("");
			link = settings.get(link);
				
			ConfigEntry dataTypes = new ConfigEntry("Data Types", "ConfigHelp");
				dataTypes.createNewValue("ABOUT").setActive().setDataType("@S").setCurrentValue("It is important that the correct data types are used. They are designated by the @ symbol.").setDefaultValue("");
				dataTypes.createNewValue("Boolean").setActive().setDataType("@B").setCurrentValue("Accepts: true, false.").setDefaultValue("");
				dataTypes.createNewValue("Integer").setActive().setDataType("@I").setCurrentValue("Accepts: Whole numbers only, such as 2 or 4096.").setDefaultValue("");
				dataTypes.createNewValue("Float").setActive().setDataType("@F").setCurrentValue("Accepts: Decimal numbers, such as 1.5 or 98.9.").setDefaultValue("");
				dataTypes.createNewValue("Double").setActive().setDataType("@D").setCurrentValue("Accepts: Decimal numbers, such as 1.5 or 98.9.").setDefaultValue("");
				dataTypes.createNewValue("String").setActive().setDataType("@S").setCurrentValue("Accepts: Any number or character, such as abcdefg or 9dsa29213mn#.").setDefaultValue("");
			dataTypes = settings.get(dataTypes);
			
			//Toggles
			ConfigEntry toggles = new ConfigEntry("Simple Tcon Toggles", "Toggles");
				toggles.createNewValue("enableBrass").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				toggles.createNewValue("thyriumSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				toggles.createNewValue("cinderstoneSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				toggles.createNewValue("dragonbezoarSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				toggles = settings.get(toggles);
				enableBrass = toggles.getValueByName("enableBrass");
				thyriumSmelteryRecipe = toggles.getValueByName("thyriumSmelteryRecipe");
				cinderstoneSmelteryRecipe = toggles.getValueByName("cinderstoneSmelteryRecipe");
				dragonbezoarSmelteryRecipe = toggles.getValueByName("dragonbezoarSmelteryRecipe");
			
				ConfigEntry moreToggles = new ConfigEntry("Melting Onyx doesnt look quite right", "MoreToggles");
				moreToggles.createNewValue("enableOnyxMelting").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				moreToggles.createNewValue("onyxCasting").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				moreToggles.createNewValue("sinisiteSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				moreToggles.createNewValue("thrakaSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				moreToggles.createNewValue("pyralisSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				moreToggles = settings.get(moreToggles);
				enableOnyxMelting = moreToggles.getValueByName("enableOnyxMelting");
				onyxCasting = moreToggles.getValueByName("onyxCasting");
				sinisiteSmelteryRecipe = moreToggles.getValueByName("sinisiteSmelteryRecipe");
				thrakaSmelteryRecipe = moreToggles.getValueByName("thrakaSmelteryRecipe");
				pyralisSmelteryRecipe = moreToggles.getValueByName("pyralisSmelteryRecipe");
					
				ConfigEntry extraFuel = new ConfigEntry("Extra fuels from other mods (some just for fun)", "ExtraFuel");
				extraFuel.createNewValue("enableSteamAsFule").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				extraFuel.createNewValue("steamUse").setDataType("@I").setCurrentValue("250").setDefaultValue("250");
				extraFuel.createNewValue("steamTime").setDataType("@I").setCurrentValue("50").setDefaultValue("50");
				extraFuel.createNewValue("enableSuperLavaFule").setActive().setDataType("@B").setCurrentValue("true").setDefaultValue("true");
				extraFuel.createNewValue("superLavaUse").setDataType("@I").setCurrentValue("5").setDefaultValue("5");
				extraFuel.createNewValue("superLavaTime").setDataType("@I").setCurrentValue("600").setDefaultValue("600");
				extraFuel = settings.get(extraFuel);
				enableSteam = extraFuel.getValueByName("enableSteamAsFule");
				steamUse = extraFuel.getValueByName("steamUse");
				steamTime = extraFuel.getValueByName("steamTime");
				enableSuperLava = extraFuel.getValueByName("enableSuperLavaFule");
				superLavaUse = extraFuel.getValueByName("superLavaUse");
				superLavaTime = extraFuel.getValueByName("superLavaTime");
				
			//Blocks
			zincOre = settings.get(new ConfigBlock("Zinc Ore", "Ores").setHardness(1.7F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(1).setHarvestTool("pickaxe")
				.setSpawnRate(20).setVeinSize(4).setMinHeight(30).setMaxHeight(80).setCreativeTab("SimpleBlocks")).asConfigBlock();
			zincBlock = settings.get(new ConfigBlock("Block of Zinc", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			brassBlock = settings.get(new ConfigBlock("Block of Brass", "Blocks").setHardness(7.0F).setResistance(12.0F).setLightValue(0.0F).setHarvestLevel(0).setHarvestTool("pickaxe")
					.setCreativeTab("SimpleDecorations").setBeaconBase(true)).asConfigBlock();
			
			//Items
			zincIngot = settings.get(new ConfigItem("Zinc Ingot", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(0.4F)).asConfigItem();
			brassIngot = settings.get(new ConfigItem("Brass Ingot", "Items").setStackSize(64).setCreativeTab("SimpleMaterials").setSmeltingXP(0.4F)).asConfigItem();
			
			//Tinkers Material
			mythrilTinker = new ConfigEntry("Mythril Tinker Tools", "TinkersMaterial");
				mythrilTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("650").setDefaultValue("650");
				mythrilTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("2").setDefaultValue("2");
				mythrilTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("7.90").setDefaultValue("7.90");
				mythrilTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("3.0").setDefaultValue("3.0");
				mythrilTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.15").setDefaultValue("1.15");
				mythrilTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("65").setDefaultValue("65");
				mythrilTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("60").setDefaultValue("60");
				mythrilTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.9").setDefaultValue("0.9");
				mythrilTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("1.2").setDefaultValue("1.2");
				mythrilTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("2.0").setDefaultValue("2.0");
				mythrilTinker = settings.get(mythrilTinker);
				mythrilTinkerHeadDurability = mythrilTinker.getValueByName("HeadDurability");
				mythrilTinkerHarvestLevel = mythrilTinker.getValueByName("HarvestLevel");
				mythrilTinkerHarvestSpeed = mythrilTinker.getValueByName("HarvestSpeed");
				mythrilTinkerDamageVsEntity = mythrilTinker.getValueByName("DamageVsEntity");
				mythrilTinkerhandleModifer = mythrilTinker.getValueByName("HandleModifer");
				mythrilTinkerhandleDurability = mythrilTinker.getValueByName("HandleDurability");
				mythrilTinkerextraDurability = mythrilTinker.getValueByName("ExtraDurability");
				mythrilTinkerBowSpeed = mythrilTinker.getValueByName("BowDrawSpeed");
				mythrilTinkerBowRange = mythrilTinker.getValueByName("BowRangeMod");
				mythrilTinkerBowDamage = mythrilTinker.getValueByName("BowDamageBonus");
			adamantiumTinker = new ConfigEntry("Adamantium Tinker Tools", "TinkersMaterial");
				adamantiumTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("1000").setDefaultValue("1000");
				adamantiumTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("2").setDefaultValue("2");
				adamantiumTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("13.80").setDefaultValue("13.80");
				adamantiumTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("3.50").setDefaultValue("3.50");
				adamantiumTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.20").setDefaultValue("1.20");
				adamantiumTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("75").setDefaultValue("75");
				adamantiumTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("80").setDefaultValue("80");
				adamantiumTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.5").setDefaultValue("0.5");
				adamantiumTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("1.3").setDefaultValue("1.3");
				adamantiumTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("2.5").setDefaultValue("2.5");
				adamantiumTinker = settings.get(adamantiumTinker);
				adamantiumTinkerHeadDurability = adamantiumTinker.getValueByName("HeadDurability");
				adamantiumTinkerHarvestLevel = adamantiumTinker.getValueByName("HarvestLevel");
				adamantiumTinkerHarvestSpeed = adamantiumTinker.getValueByName("HarvestSpeed");
				adamantiumTinkerDamageVsEntity = adamantiumTinker.getValueByName("DamageVsEntity");
				adamantiumTinkerhandleModifer = adamantiumTinker.getValueByName("HandleModifer");
				adamantiumTinkerhandleDurability = adamantiumTinker.getValueByName("HandleDurability");
				adamantiumTinkerextraDurability = adamantiumTinker.getValueByName("ExtraDurability");
				adamantiumTinkerBowSpeed = adamantiumTinker.getValueByName("BowDrawSpeed");
				adamantiumTinkerBowRange = adamantiumTinker.getValueByName("BowRangeMod");
				adamantiumTinkerBowDamage = adamantiumTinker.getValueByName("BowDamageBonus");
			onyxTinker = new ConfigEntry("Onyx Tinker Tools", "TinkersMaterial");
				onyxTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("3000").setDefaultValue("3000");
				onyxTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("4").setDefaultValue("4");
				onyxTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("9.90").setDefaultValue("9.90");
				onyxTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("4.0").setDefaultValue("4.0");
				onyxTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("0.95").setDefaultValue("0.95");
				onyxTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("275").setDefaultValue("275");
				onyxTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("110").setDefaultValue("110");
				onyxTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.7").setDefaultValue("0.7");
				onyxTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("1.3").setDefaultValue("1.3");
				onyxTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("4.0").setDefaultValue("4.0");
				onyxTinker = settings.get(onyxTinker);
				onyxTinkerHeadDurability = onyxTinker.getValueByName("HeadDurability");
				onyxTinkerHarvestLevel = onyxTinker.getValueByName("HarvestLevel");
				onyxTinkerHarvestSpeed = onyxTinker.getValueByName("HarvestSpeed");
				onyxTinkerDamageVsEntity = onyxTinker.getValueByName("DamageVsEntity");
				onyxTinkerhandleModifer = onyxTinker.getValueByName("HandleModifer");
				onyxTinkerhandleDurability = onyxTinker.getValueByName("HandleDurability");
				onyxTinkerextraDurability = onyxTinker.getValueByName("ExtraDurability");
				onyxTinkerBowSpeed = onyxTinker.getValueByName("BowDrawSpeed");
				onyxTinkerBowRange = onyxTinker.getValueByName("BowRangeMod");
				onyxTinkerBowDamage = onyxTinker.getValueByName("BowDamageBonus");
			thyriumTinker = new ConfigEntry("Thyrium Tinker Tools", "TinkersMaterial");
				thyriumTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("1800").setDefaultValue("1800");
				thyriumTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				thyriumTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("21.25").setDefaultValue("21.25");
				thyriumTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("6.0").setDefaultValue("6.0");
				thyriumTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.50").setDefaultValue("1.50");
				thyriumTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("85").setDefaultValue("85");
				thyriumTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("120").setDefaultValue("120");
				thyriumTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.95").setDefaultValue("0.95");
				thyriumTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("1.6").setDefaultValue("1.6");
				thyriumTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("4.0").setDefaultValue("4.0");
				thyriumTinker = settings.get(thyriumTinker);
				thyriumTinkerHeadDurability = thyriumTinker.getValueByName("HeadDurability");
				thyriumTinkerHarvestLevel = thyriumTinker.getValueByName("HarvestLevel");
				thyriumTinkerHarvestSpeed = thyriumTinker.getValueByName("HarvestSpeed");
				thyriumTinkerDamageVsEntity = thyriumTinker.getValueByName("DamageVsEntity");
				thyriumTinkerhandleModifer = thyriumTinker.getValueByName("HandleModifer");
				thyriumTinkerhandleDurability = thyriumTinker.getValueByName("HandleDurability");
				thyriumTinkerextraDurability = thyriumTinker.getValueByName("ExtraDurability");
				thyriumTinkerBowSpeed = thyriumTinker.getValueByName("BowDrawSpeed");
				thyriumTinkerBowRange = thyriumTinker.getValueByName("BowRangeMod");
				thyriumTinkerBowDamage = thyriumTinker.getValueByName("BowDamageBonus");
			sinisiteTinker = new ConfigEntry("Sinisite Tinker Tools", "TinkersMaterial");
				sinisiteTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("3600").setDefaultValue("3600");
				sinisiteTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("5").setDefaultValue("5");
				sinisiteTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("17.00").setDefaultValue("17.00");
				sinisiteTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("7.9").setDefaultValue("7.9");
				sinisiteTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.65").setDefaultValue("1.65");
				sinisiteTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("95").setDefaultValue("95");
				sinisiteTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("150").setDefaultValue("150");
				sinisiteTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.7").setDefaultValue("0.7");
				sinisiteTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("1.4").setDefaultValue("1.4");
				sinisiteTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("7.0").setDefaultValue("7.0");
				sinisiteTinker = settings.get(sinisiteTinker);
				sinisiteTinkerHeadDurability = sinisiteTinker.getValueByName("HeadDurability");
				sinisiteTinkerHarvestLevel = sinisiteTinker.getValueByName("HarvestLevel");
				sinisiteTinkerHarvestSpeed = sinisiteTinker.getValueByName("HarvestSpeed");
				sinisiteTinkerDamageVsEntity = sinisiteTinker.getValueByName("DamageVsEntity");
				sinisiteTinkerhandleModifer = sinisiteTinker.getValueByName("HandleModifer");
				sinisiteTinkerhandleDurability = sinisiteTinker.getValueByName("HandleDurability");
				sinisiteTinkerextraDurability = sinisiteTinker.getValueByName("ExtraDurability");
				sinisiteTinkerBowSpeed = sinisiteTinker.getValueByName("BowDrawSpeed");
				sinisiteTinkerBowRange = sinisiteTinker.getValueByName("BowRangeMod");
				sinisiteTinkerBowDamage = sinisiteTinker.getValueByName("BowDamageBonus");
			
				fyriteTinker = new ConfigEntry("Fyrite Tinker Tools", "TinkersMaterial");
				fyriteTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("150").setDefaultValue("150");
				fyriteTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				fyriteTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("8.00").setDefaultValue("8.00");
				fyriteTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("7.0").setDefaultValue("7.0");
				fyriteTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("0.65").setDefaultValue("0.65");
				fyriteTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("35").setDefaultValue("35");
				fyriteTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("30").setDefaultValue("30");
				fyriteTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				fyriteTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				fyriteTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				fyriteTinker = settings.get(fyriteTinker);
				fyriteTinkerHeadDurability = fyriteTinker.getValueByName("HeadDurability");
				fyriteTinkerHarvestLevel = fyriteTinker.getValueByName("HarvestLevel");
				fyriteTinkerHarvestSpeed = fyriteTinker.getValueByName("HarvestSpeed");
				fyriteTinkerDamageVsEntity = fyriteTinker.getValueByName("DamageVsEntity");
				fyriteTinkerhandleModifer = fyriteTinker.getValueByName("HandleModifer");
				fyriteTinkerhandleDurability = fyriteTinker.getValueByName("HandleDurability");
				fyriteTinkerextraDurability = fyriteTinker.getValueByName("ExtraDurability");
				fyriteTinkerBowSpeed = fyriteTinker.getValueByName("BowDrawSpeed");
				fyriteTinkerBowRange = fyriteTinker.getValueByName("BowRangeMod");
				fyriteTinkerBowDamage = fyriteTinker.getValueByName("BowDamageBonus");
				
				malachiteTinker = new ConfigEntry("Malachite Tinker Tools", "TinkersMaterial");
				malachiteTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("700").setDefaultValue("700");
				malachiteTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				malachiteTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("9.00").setDefaultValue("9.00");
				malachiteTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("3.0").setDefaultValue("3.0");
				malachiteTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.2").setDefaultValue("1.2");
				malachiteTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("55").setDefaultValue("55");
				malachiteTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("75").setDefaultValue("75");
				malachiteTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				malachiteTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				malachiteTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				malachiteTinker = settings.get(malachiteTinker);
				malachiteTinkerHeadDurability = malachiteTinker.getValueByName("HeadDurability");
				malachiteTinkerHarvestLevel = malachiteTinker.getValueByName("HarvestLevel");
				malachiteTinkerHarvestSpeed = malachiteTinker.getValueByName("HarvestSpeed");
				malachiteTinkerDamageVsEntity = malachiteTinker.getValueByName("DamageVsEntity");
				malachiteTinkerhandleModifer = malachiteTinker.getValueByName("HandleModifer");
				malachiteTinkerhandleDurability = malachiteTinker.getValueByName("HandleDurability");
				malachiteTinkerextraDurability = malachiteTinker.getValueByName("ExtraDurability");
				malachiteTinkerBowSpeed = malachiteTinker.getValueByName("BowDrawSpeed");
				malachiteTinkerBowRange = malachiteTinker.getValueByName("BowRangeMod");
				malachiteTinkerBowDamage = malachiteTinker.getValueByName("BowDamageBonus");
				
				ashstoneTinker = new ConfigEntry("Ashstone Tinker Tools", "TinkersMaterial");
				ashstoneTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("900").setDefaultValue("900");
				ashstoneTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				ashstoneTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("16.00").setDefaultValue("16.00");
				ashstoneTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("1.9").setDefaultValue("1.9");
				ashstoneTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.4").setDefaultValue("1.4");
				ashstoneTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("35").setDefaultValue("35");
				ashstoneTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("66").setDefaultValue("66");
				ashstoneTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				ashstoneTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				ashstoneTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				ashstoneTinker = settings.get(ashstoneTinker);
				ashstoneTinkerHeadDurability = ashstoneTinker.getValueByName("HeadDurability");
				ashstoneTinkerHarvestLevel = ashstoneTinker.getValueByName("HarvestLevel");
				ashstoneTinkerHarvestSpeed = ashstoneTinker.getValueByName("HarvestSpeed");
				ashstoneTinkerDamageVsEntity = ashstoneTinker.getValueByName("DamageVsEntity");
				ashstoneTinkerhandleModifer = ashstoneTinker.getValueByName("HandleModifer");
				ashstoneTinkerhandleDurability = ashstoneTinker.getValueByName("HandleDurability");
				ashstoneTinkerextraDurability = ashstoneTinker.getValueByName("ExtraDurability");
				ashstoneTinkerBowSpeed = ashstoneTinker.getValueByName("BowDrawSpeed");
				ashstoneTinkerBowRange = ashstoneTinker.getValueByName("BowRangeMod");
				ashstoneTinkerBowDamage = ashstoneTinker.getValueByName("BowDamageBonus");
				
				illumeniteTinker = new ConfigEntry("Illumenite Tinker Tools", "TinkersMaterial");
				illumeniteTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("700").setDefaultValue("700");
				illumeniteTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				illumeniteTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("8.00").setDefaultValue("8.00");
				illumeniteTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("3.9").setDefaultValue("3.9");
				illumeniteTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.05").setDefaultValue("1.05");
				illumeniteTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("15").setDefaultValue("15");
				illumeniteTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("40").setDefaultValue("40");
				illumeniteTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				illumeniteTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				illumeniteTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				illumeniteTinker = settings.get(illumeniteTinker);
				illumeniteTinkerHeadDurability = illumeniteTinker.getValueByName("HeadDurability");
				illumeniteTinkerHarvestLevel = illumeniteTinker.getValueByName("HarvestLevel");
				illumeniteTinkerHarvestSpeed = illumeniteTinker.getValueByName("HarvestSpeed");
				illumeniteTinkerDamageVsEntity = illumeniteTinker.getValueByName("DamageVsEntity");
				illumeniteTinkerhandleModifer = illumeniteTinker.getValueByName("HandleModifer");
				illumeniteTinkerhandleDurability = illumeniteTinker.getValueByName("HandleDurability");
				illumeniteTinkerextraDurability = illumeniteTinker.getValueByName("ExtraDurability");
				illumeniteTinkerBowSpeed = illumeniteTinker.getValueByName("BowDrawSpeed");
				illumeniteTinkerBowRange = illumeniteTinker.getValueByName("BowRangeMod");
				illumeniteTinkerBowDamage = illumeniteTinker.getValueByName("BowDamageBonus");
				
				dragonstoneTinker = new ConfigEntry("Dragonstone Tinker Tools", "TinkersMaterial");
				dragonstoneTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("4000").setDefaultValue("4000");
				dragonstoneTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("4").setDefaultValue("4");
				dragonstoneTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("10.00").setDefaultValue("10.00");
				dragonstoneTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("7.9").setDefaultValue("7.9");
				dragonstoneTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.65").setDefaultValue("1.65");
				dragonstoneTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("95").setDefaultValue("95");
				dragonstoneTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("150").setDefaultValue("150");
				dragonstoneTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				dragonstoneTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				dragonstoneTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("6.0").setDefaultValue("6.0");
				dragonstoneTinker = settings.get(dragonstoneTinker);
				dragonstoneTinkerHeadDurability = dragonstoneTinker.getValueByName("HeadDurability");
				dragonstoneTinkerHarvestLevel = dragonstoneTinker.getValueByName("HarvestLevel");
				dragonstoneTinkerHarvestSpeed = dragonstoneTinker.getValueByName("HarvestSpeed");
				dragonstoneTinkerDamageVsEntity = dragonstoneTinker.getValueByName("DamageVsEntity");
				dragonstoneTinkerhandleModifer = dragonstoneTinker.getValueByName("HandleModifer");
				dragonstoneTinkerhandleDurability = dragonstoneTinker.getValueByName("HandleDurability");
				dragonstoneTinkerextraDurability = dragonstoneTinker.getValueByName("ExtraDurability");
				dragonstoneTinkerBowSpeed = dragonstoneTinker.getValueByName("BowDrawSpeed");
				dragonstoneTinkerBowRange = dragonstoneTinker.getValueByName("BowRangeMod");
				dragonstoneTinkerBowDamage = dragonstoneTinker.getValueByName("BowDamageBonus");
				
				argoniteTinker = new ConfigEntry("Argonite Tinker Tools", "TinkersMaterial");
				argoniteTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("1300").setDefaultValue("1300");
				argoniteTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				argoniteTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("8.00").setDefaultValue("8.00");
				argoniteTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("2.9").setDefaultValue("2.9");
				argoniteTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.15").setDefaultValue("1.15");
				argoniteTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("55").setDefaultValue("55");
				argoniteTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("120").setDefaultValue("120");
				argoniteTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				argoniteTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				argoniteTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				argoniteTinker = settings.get(argoniteTinker);
				argoniteTinkerHeadDurability = argoniteTinker.getValueByName("HeadDurability");
				argoniteTinkerHarvestLevel = argoniteTinker.getValueByName("HarvestLevel");
				argoniteTinkerHarvestSpeed = argoniteTinker.getValueByName("HarvestSpeed");
				argoniteTinkerDamageVsEntity = argoniteTinker.getValueByName("DamageVsEntity");
				argoniteTinkerhandleModifer = argoniteTinker.getValueByName("HandleModifer");
				argoniteTinkerhandleDurability = argoniteTinker.getValueByName("HandleDurability");
				argoniteTinkerextraDurability = argoniteTinker.getValueByName("ExtraDurability");
				argoniteTinkerBowSpeed = argoniteTinker.getValueByName("BowDrawSpeed");
				argoniteTinkerBowRange = argoniteTinker.getValueByName("BowRangeMod");
				argoniteTinkerBowDamage = argoniteTinker.getValueByName("BowDamageBonus");
				
				cinderstoneTinker = new ConfigEntry("Cinderstone Tinker Tools", "TinkersMaterial");
				cinderstoneTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("35").setDefaultValue("35");
				cinderstoneTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("0").setDefaultValue("0");
				cinderstoneTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("10.00").setDefaultValue("10.00");
				cinderstoneTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("7.0").setDefaultValue("7.0");
				cinderstoneTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("0.75").setDefaultValue("0.75");
				cinderstoneTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("-5").setDefaultValue("-5");
				cinderstoneTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("-8").setDefaultValue("-8");
				cinderstoneTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				cinderstoneTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				cinderstoneTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				cinderstoneTinker = settings.get(cinderstoneTinker);
				cinderstoneTinkerHeadDurability = cinderstoneTinker.getValueByName("HeadDurability");
				cinderstoneTinkerHarvestLevel = cinderstoneTinker.getValueByName("HarvestLevel");
				cinderstoneTinkerHarvestSpeed = cinderstoneTinker.getValueByName("HarvestSpeed");
				cinderstoneTinkerDamageVsEntity = cinderstoneTinker.getValueByName("DamageVsEntity");
				cinderstoneTinkerhandleModifer = cinderstoneTinker.getValueByName("HandleModifer");
				cinderstoneTinkerhandleDurability = cinderstoneTinker.getValueByName("HandleDurability");
				cinderstoneTinkerextraDurability = cinderstoneTinker.getValueByName("ExtraDurability");
				cinderstoneTinkerBowSpeed = cinderstoneTinker.getValueByName("BowDrawSpeed");
				cinderstoneTinkerBowRange = cinderstoneTinker.getValueByName("BowRangeMod");
				cinderstoneTinkerBowDamage = cinderstoneTinker.getValueByName("BowDamageBonus");
				
				thrakaTinker = new ConfigEntry("Thraka Tinker Tools", "TinkersMaterial");
				thrakaTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("825").setDefaultValue("825");
				thrakaTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				thrakaTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("14.00").setDefaultValue("14.00");
				thrakaTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("2.9").setDefaultValue("2.9");
				thrakaTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.1").setDefaultValue("1.1");
				thrakaTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("25").setDefaultValue("25");
				thrakaTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("35").setDefaultValue("35");
				thrakaTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				thrakaTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				thrakaTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				thrakaTinker = settings.get(thrakaTinker);
				thrakaTinkerHeadDurability = thrakaTinker.getValueByName("HeadDurability");
				thrakaTinkerHarvestLevel = thrakaTinker.getValueByName("HarvestLevel");
				thrakaTinkerHarvestSpeed = thrakaTinker.getValueByName("HarvestSpeed");
				thrakaTinkerDamageVsEntity = thrakaTinker.getValueByName("DamageVsEntity");
				thrakaTinkerhandleModifer = thrakaTinker.getValueByName("HandleModifer");
				thrakaTinkerhandleDurability = thrakaTinker.getValueByName("HandleDurability");
				thrakaTinkerextraDurability = thrakaTinker.getValueByName("ExtraDurability");
				thrakaTinkerBowSpeed = thrakaTinker.getValueByName("BowDrawSpeed");
				thrakaTinkerBowRange = thrakaTinker.getValueByName("BowRangeMod");
				thrakaTinkerBowDamage = thrakaTinker.getValueByName("BowDamageBonus");
				
				pyralisTinker = new ConfigEntry("Pyralis Tinker Tools", "TinkersMaterial");
				pyralisTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("3000").setDefaultValue("3000");
				pyralisTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("4").setDefaultValue("4");
				pyralisTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("12.00").setDefaultValue("12.00");
				pyralisTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("7.9").setDefaultValue("7.9");
				pyralisTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.15").setDefaultValue("1.15");
				pyralisTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("50").setDefaultValue("50");
				pyralisTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("110").setDefaultValue("110");
				pyralisTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				pyralisTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("0.4").setDefaultValue("0.4");
				pyralisTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("-1.0").setDefaultValue("-1.0");
				pyralisTinker = settings.get(pyralisTinker);
				pyralisTinkerHeadDurability = pyralisTinker.getValueByName("HeadDurability");
				pyralisTinkerHarvestLevel = pyralisTinker.getValueByName("HarvestLevel");
				pyralisTinkerHarvestSpeed = pyralisTinker.getValueByName("HarvestSpeed");
				pyralisTinkerDamageVsEntity = pyralisTinker.getValueByName("DamageVsEntity");
				pyralisTinkerhandleModifer = pyralisTinker.getValueByName("HandleModifer");
				pyralisTinkerhandleDurability = pyralisTinker.getValueByName("HandleDurability");
				pyralisTinkerextraDurability = pyralisTinker.getValueByName("ExtraDurability");
				pyralisTinkerBowSpeed = pyralisTinker.getValueByName("BowDrawSpeed");
				pyralisTinkerBowRange = pyralisTinker.getValueByName("BowRangeMod");
				pyralisTinkerBowDamage = pyralisTinker.getValueByName("BowDamageBonus");
				
				dragonbezoarTinker = new ConfigEntry("Dragon Bezoar Tinker Tools", "TinkersMaterial");
				dragonbezoarTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("4100").setDefaultValue("4100");
				dragonbezoarTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("6").setDefaultValue("6");
				dragonbezoarTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("16.00").setDefaultValue("16.00");
				dragonbezoarTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("8.8").setDefaultValue("8.8");
				dragonbezoarTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.75").setDefaultValue("1.75");
				dragonbezoarTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("98").setDefaultValue("98");
				dragonbezoarTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("165").setDefaultValue("165");
				dragonbezoarTinker.createNewValue("BowDrawSpeed").setDataType("@F").setCurrentValue("0.2").setDefaultValue("0.2");
				dragonbezoarTinker.createNewValue("BowRangeMod").setDataType("@F").setCurrentValue("1.6").setDefaultValue("1.6");
				dragonbezoarTinker.createNewValue("BowDamageBonus").setDataType("@F").setCurrentValue("7.5").setDefaultValue("7.5");
				dragonbezoarTinker = settings.get(dragonbezoarTinker);
				dragonbezoarTinkerHeadDurability = dragonbezoarTinker.getValueByName("HeadDurability");
				dragonbezoarTinkerHarvestLevel = dragonbezoarTinker.getValueByName("HarvestLevel");
				dragonbezoarTinkerHarvestSpeed = dragonbezoarTinker.getValueByName("HarvestSpeed");
				dragonbezoarTinkerDamageVsEntity = dragonbezoarTinker.getValueByName("DamageVsEntity");
				dragonbezoarTinkerhandleModifer = dragonbezoarTinker.getValueByName("HandleModifer");
				dragonbezoarTinkerhandleDurability = dragonbezoarTinker.getValueByName("HandleDurability");
				dragonbezoarTinkerextraDurability = dragonbezoarTinker.getValueByName("ExtraDurability");
				dragonbezoarTinkerBowSpeed = dragonbezoarTinker.getValueByName("BowDrawSpeed");
				dragonbezoarTinkerBowRange = dragonbezoarTinker.getValueByName("BowRangeMod");
				dragonbezoarTinkerBowDamage = dragonbezoarTinker.getValueByName("BowDamageBonus");
		}
		catch(Exception e) {
			LogHelper.severe("Simple Tcon", "Failed to load settings");
			e.printStackTrace();
		}
		finally {
			settings.save();
			LogHelper.verbose("Simple Tcon", "Settings loaded successfully");
		}
	}
	
	public static ConfigBlock zincOre, zincBlock, brassBlock;
	public static ConfigItem zincIngot, brassIngot;
	
	public static ConfigEntry mythrilTinker, adamantiumTinker, onyxTinker, thyriumTinker, sinisiteTinker, fyriteTinker, malachiteTinker, ashstoneTinker, illumeniteTinker, dragonstoneTinker, argoniteTinker, cinderstoneTinker, pyralisTinker, thrakaTinker, dragonbezoarTinker;
	
	public static ConfigValue mythrilTinkerHeadDurability, mythrilTinkerHarvestLevel, mythrilTinkerHarvestSpeed, mythrilTinkerDamageVsEntity, mythrilTinkerhandleModifer, mythrilTinkerhandleDurability, mythrilTinkerextraDurability, mythrilTinkerBowSpeed, mythrilTinkerBowRange, mythrilTinkerBowDamage;
	public static ConfigValue adamantiumTinkerHeadDurability, adamantiumTinkerHarvestLevel, adamantiumTinkerHarvestSpeed, adamantiumTinkerDamageVsEntity, adamantiumTinkerhandleModifer, adamantiumTinkerhandleDurability, adamantiumTinkerextraDurability, adamantiumTinkerBowSpeed, adamantiumTinkerBowRange, adamantiumTinkerBowDamage;
	public static ConfigValue onyxTinkerHeadDurability, onyxTinkerHarvestLevel, onyxTinkerHarvestSpeed, onyxTinkerDamageVsEntity, onyxTinkerhandleModifer, onyxTinkerhandleDurability, onyxTinkerextraDurability, onyxTinkerBowSpeed, onyxTinkerBowRange, onyxTinkerBowDamage;
	public static ConfigValue thyriumTinkerHeadDurability, thyriumTinkerHarvestLevel, thyriumTinkerHarvestSpeed, thyriumTinkerDamageVsEntity, thyriumTinkerhandleModifer, thyriumTinkerhandleDurability, thyriumTinkerextraDurability, thyriumTinkerBowSpeed, thyriumTinkerBowRange, thyriumTinkerBowDamage;
	public static ConfigValue sinisiteTinkerHeadDurability, sinisiteTinkerHarvestLevel, sinisiteTinkerHarvestSpeed, sinisiteTinkerDamageVsEntity, sinisiteTinkerhandleModifer, sinisiteTinkerhandleDurability, sinisiteTinkerextraDurability, sinisiteTinkerBowSpeed, sinisiteTinkerBowRange, sinisiteTinkerBowDamage;
	public static ConfigValue fyriteTinkerHeadDurability, fyriteTinkerHarvestLevel, fyriteTinkerHarvestSpeed, fyriteTinkerDamageVsEntity, fyriteTinkerhandleModifer, fyriteTinkerhandleDurability, fyriteTinkerextraDurability, fyriteTinkerBowSpeed, fyriteTinkerBowRange, fyriteTinkerBowDamage;
	public static ConfigValue malachiteTinkerHeadDurability, malachiteTinkerHarvestLevel, malachiteTinkerHarvestSpeed, malachiteTinkerDamageVsEntity, malachiteTinkerhandleModifer, malachiteTinkerhandleDurability, malachiteTinkerextraDurability, malachiteTinkerBowSpeed, malachiteTinkerBowRange, malachiteTinkerBowDamage;
	public static ConfigValue ashstoneTinkerHeadDurability, ashstoneTinkerHarvestLevel, ashstoneTinkerHarvestSpeed, ashstoneTinkerDamageVsEntity, ashstoneTinkerhandleModifer, ashstoneTinkerhandleDurability, ashstoneTinkerextraDurability, ashstoneTinkerBowSpeed, ashstoneTinkerBowRange, ashstoneTinkerBowDamage;
	public static ConfigValue illumeniteTinkerHeadDurability, illumeniteTinkerHarvestLevel, illumeniteTinkerHarvestSpeed, illumeniteTinkerDamageVsEntity, illumeniteTinkerhandleModifer, illumeniteTinkerhandleDurability, illumeniteTinkerextraDurability, illumeniteTinkerBowSpeed, illumeniteTinkerBowRange, illumeniteTinkerBowDamage;
	public static ConfigValue dragonstoneTinkerHeadDurability, dragonstoneTinkerHarvestLevel, dragonstoneTinkerHarvestSpeed, dragonstoneTinkerDamageVsEntity, dragonstoneTinkerhandleModifer, dragonstoneTinkerhandleDurability, dragonstoneTinkerextraDurability, dragonstoneTinkerBowSpeed, dragonstoneTinkerBowRange, dragonstoneTinkerBowDamage;
	public static ConfigValue argoniteTinkerHeadDurability, argoniteTinkerHarvestLevel, argoniteTinkerHarvestSpeed, argoniteTinkerDamageVsEntity, argoniteTinkerhandleModifer, argoniteTinkerhandleDurability, argoniteTinkerextraDurability, argoniteTinkerBowSpeed, argoniteTinkerBowRange, argoniteTinkerBowDamage;
	public static ConfigValue cinderstoneTinkerHeadDurability, cinderstoneTinkerHarvestLevel, cinderstoneTinkerHarvestSpeed, cinderstoneTinkerDamageVsEntity, cinderstoneTinkerhandleModifer, cinderstoneTinkerhandleDurability, cinderstoneTinkerextraDurability, cinderstoneTinkerBowSpeed, cinderstoneTinkerBowRange, cinderstoneTinkerBowDamage;
	public static ConfigValue thrakaTinkerHeadDurability, thrakaTinkerHarvestLevel, thrakaTinkerHarvestSpeed, thrakaTinkerDamageVsEntity, thrakaTinkerhandleModifer, thrakaTinkerhandleDurability, thrakaTinkerextraDurability, thrakaTinkerBowSpeed, thrakaTinkerBowRange, thrakaTinkerBowDamage;
	public static ConfigValue pyralisTinkerHeadDurability, pyralisTinkerHarvestLevel, pyralisTinkerHarvestSpeed, pyralisTinkerDamageVsEntity, pyralisTinkerhandleModifer, pyralisTinkerhandleDurability, pyralisTinkerextraDurability, pyralisTinkerBowSpeed, pyralisTinkerBowRange, pyralisTinkerBowDamage;
	public static ConfigValue dragonbezoarTinkerHeadDurability, dragonbezoarTinkerHarvestLevel, dragonbezoarTinkerHarvestSpeed, dragonbezoarTinkerDamageVsEntity, dragonbezoarTinkerhandleModifer, dragonbezoarTinkerhandleDurability, dragonbezoarTinkerextraDurability, dragonbezoarTinkerBowSpeed, dragonbezoarTinkerBowRange, dragonbezoarTinkerBowDamage;
	
	public static ConfigValue steamUse, steamTime, superLavaUse, superLavaTime;
	public static ConfigValue enableBrass, enableOnyxMelting, onyxCasting, thyriumSmelteryRecipe, sinisiteSmelteryRecipe, cinderstoneSmelteryRecipe, thrakaSmelteryRecipe, pyralisSmelteryRecipe, dragonbezoarSmelteryRecipe, enableSteam, enableSuperLava;
}
