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
		File configDir = new File(event.getModConfigurationDirectory() + "/AleXndr", "Simple Tcon Settings.xml");
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
				toggles = settings.get(toggles);
				enableBrass = toggles.getValueByName("enableBrass");
				thyriumSmelteryRecipe = toggles.getValueByName("thyriumSmelteryRecipe");
			
				ConfigEntry moreToggles = new ConfigEntry("Melting Onyx doesnt look quite right", "MoreToggles");
				moreToggles.createNewValue("enableOnyxMelting").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				moreToggles.createNewValue("onyxCasting").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				moreToggles.createNewValue("sinisiteSmelteryRecipe").setActive().setDataType("@B").setCurrentValue("false").setDefaultValue("false");
				moreToggles = settings.get(moreToggles);
					enableOnyxMelting = moreToggles.getValueByName("enableOnyxMelting");
					onyxCasting = moreToggles.getValueByName("onyxCasting");
					sinisiteSmelteryRecipe = moreToggles.getValueByName("sinisiteSmelteryRecipe");

			//Blocks
			zincOre = settings.get(new ConfigBlock("Zinc Ore", "Ores").setHardness(1.7F).setResistance(5.0F).setLightValue(0.0F).setHarvestLevel(1).setHarvestTool("pickaxe")
				.setSpawnRate(25).setVeinSize(6).setMinHeight(30).setMaxHeight(90).setCreativeTab("SimpleBlocks")).asConfigBlock();
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
				mythrilTinker = settings.get(mythrilTinker);
				mythrilTinkerHeadDurability = mythrilTinker.getValueByName("HeadDurability");
				mythrilTinkerHarvestLevel = mythrilTinker.getValueByName("HarvestLevel");
				mythrilTinkerHarvestSpeed = mythrilTinker.getValueByName("HarvestSpeed");
				mythrilTinkerDamageVsEntity = mythrilTinker.getValueByName("DamageVsEntity");
				mythrilTinkerhandleModifer = mythrilTinker.getValueByName("HandleModifer");
				mythrilTinkerhandleDurability = mythrilTinker.getValueByName("HandleDurability");
				mythrilTinkerextraDurability = mythrilTinker.getValueByName("ExtraDurability");
			adamantiumTinker = new ConfigEntry("Adamantium Tinker Tools", "TinkersMaterial");
				adamantiumTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("1000").setDefaultValue("1000");
				adamantiumTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("2").setDefaultValue("2");
				adamantiumTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("13.80").setDefaultValue("13.80");
				adamantiumTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("3.50").setDefaultValue("3.50");
				adamantiumTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.20").setDefaultValue("1.20");
				adamantiumTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("75").setDefaultValue("75");
				adamantiumTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("80").setDefaultValue("80");
				adamantiumTinker = settings.get(adamantiumTinker);
				adamantiumTinkerHeadDurability = adamantiumTinker.getValueByName("HeadDurability");
				adamantiumTinkerHarvestLevel = adamantiumTinker.getValueByName("HarvestLevel");
				adamantiumTinkerHarvestSpeed = adamantiumTinker.getValueByName("HarvestSpeed");
				adamantiumTinkerDamageVsEntity = adamantiumTinker.getValueByName("DamageVsEntity");
				adamantiumTinkerhandleModifer = adamantiumTinker.getValueByName("HandleModifer");
				adamantiumTinkerhandleDurability = adamantiumTinker.getValueByName("HandleDurability");
				adamantiumTinkerextraDurability = adamantiumTinker.getValueByName("ExtraDurability");
			onyxTinker = new ConfigEntry("Onyx Tinker Tools", "TinkersMaterial");
				onyxTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("3000").setDefaultValue("3000");
				onyxTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("4").setDefaultValue("4");
				onyxTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("9.90").setDefaultValue("9.90");
				onyxTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("4.0").setDefaultValue("4.0");
				onyxTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("0.95").setDefaultValue("0.95");
				onyxTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("275").setDefaultValue("275");
				onyxTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("110").setDefaultValue("110");
				onyxTinker = settings.get(onyxTinker);
				onyxTinkerHeadDurability = onyxTinker.getValueByName("HeadDurability");
				onyxTinkerHarvestLevel = onyxTinker.getValueByName("HarvestLevel");
				onyxTinkerHarvestSpeed = onyxTinker.getValueByName("HarvestSpeed");
				onyxTinkerDamageVsEntity = onyxTinker.getValueByName("DamageVsEntity");
				onyxTinkerhandleModifer = onyxTinker.getValueByName("HandleModifer");
				onyxTinkerhandleDurability = onyxTinker.getValueByName("HandleDurability");
				onyxTinkerextraDurability = onyxTinker.getValueByName("ExtraDurability");
			thyriumTinker = new ConfigEntry("Thyrium Tinker Tools", "TinkersMaterial");
				thyriumTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("1800").setDefaultValue("1800");
				thyriumTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("3").setDefaultValue("3");
				thyriumTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("21.25").setDefaultValue("21.25");
				thyriumTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("6.0").setDefaultValue("6.0");
				thyriumTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.50").setDefaultValue("1.50");
				thyriumTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("85").setDefaultValue("85");
				thyriumTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("120").setDefaultValue("120");
				thyriumTinker = settings.get(thyriumTinker);
				thyriumTinkerHeadDurability = thyriumTinker.getValueByName("HeadDurability");
				thyriumTinkerHarvestLevel = thyriumTinker.getValueByName("HarvestLevel");
				thyriumTinkerHarvestSpeed = thyriumTinker.getValueByName("HarvestSpeed");
				thyriumTinkerDamageVsEntity = thyriumTinker.getValueByName("DamageVsEntity");
				thyriumTinkerhandleModifer = thyriumTinker.getValueByName("HandleModifer");
				thyriumTinkerhandleDurability = thyriumTinker.getValueByName("HandleDurability");
				thyriumTinkerextraDurability = thyriumTinker.getValueByName("ExtraDurability");
			sinisiteTinker = new ConfigEntry("Mythril Tinker Tools", "TinkersMaterial");
				sinisiteTinker.createNewValue("HeadDurability").setDataType("@I").setCurrentValue("3600").setDefaultValue("3600");
				sinisiteTinker.createNewValue("HarvestLevel").setDataType("@I").setCurrentValue("5").setDefaultValue("5");
				sinisiteTinker.createNewValue("HarvestSpeed").setDataType("@F").setCurrentValue("17.00").setDefaultValue("17.00");
				sinisiteTinker.createNewValue("DamageVsEntity").setDataType("@F").setCurrentValue("7.9").setDefaultValue("7.9");
				sinisiteTinker.createNewValue("HandleModifer").setDataType("@F").setCurrentValue("1.65").setDefaultValue("1.65");
				sinisiteTinker.createNewValue("HandleDurability").setDataType("@I").setCurrentValue("95").setDefaultValue("95");
				sinisiteTinker.createNewValue("ExtraDurability").setDataType("@I").setCurrentValue("150").setDefaultValue("150");
				sinisiteTinker = settings.get(sinisiteTinker);
				sinisiteTinkerHeadDurability = sinisiteTinker.getValueByName("HeadDurability");
				sinisiteTinkerHarvestLevel = sinisiteTinker.getValueByName("HarvestLevel");
				sinisiteTinkerHarvestSpeed = sinisiteTinker.getValueByName("HarvestSpeed");
				sinisiteTinkerDamageVsEntity = sinisiteTinker.getValueByName("DamageVsEntity");
				sinisiteTinkerhandleModifer = sinisiteTinker.getValueByName("HandleModifer");
				sinisiteTinkerhandleDurability = sinisiteTinker.getValueByName("HandleDurability");
				sinisiteTinkerextraDurability = sinisiteTinker.getValueByName("ExtraDurability");
			
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
	
	public static ConfigEntry mythrilTinker, adamantiumTinker, onyxTinker, thyriumTinker, sinisiteTinker;
	
	public static ConfigValue mythrilTinkerHeadDurability, mythrilTinkerHarvestLevel, mythrilTinkerHarvestSpeed, mythrilTinkerDamageVsEntity, mythrilTinkerhandleModifer, mythrilTinkerhandleDurability, mythrilTinkerextraDurability;
	public static ConfigValue adamantiumTinkerHeadDurability, adamantiumTinkerHarvestLevel, adamantiumTinkerHarvestSpeed, adamantiumTinkerDamageVsEntity, adamantiumTinkerhandleModifer, adamantiumTinkerhandleDurability, adamantiumTinkerextraDurability;
	public static ConfigValue onyxTinkerHeadDurability, onyxTinkerHarvestLevel, onyxTinkerHarvestSpeed, onyxTinkerDamageVsEntity, onyxTinkerhandleModifer, onyxTinkerhandleDurability, onyxTinkerextraDurability;
	public static ConfigValue thyriumTinkerHeadDurability, thyriumTinkerHarvestLevel, thyriumTinkerHarvestSpeed, thyriumTinkerDamageVsEntity, thyriumTinkerhandleModifer, thyriumTinkerhandleDurability, thyriumTinkerextraDurability;
	public static ConfigValue sinisiteTinkerHeadDurability, sinisiteTinkerHarvestLevel, sinisiteTinkerHarvestSpeed, sinisiteTinkerDamageVsEntity, sinisiteTinkerhandleModifer, sinisiteTinkerhandleDurability, sinisiteTinkerextraDurability;

	public static ConfigValue enableBrass, enableOnyxMelting, onyxCasting, thyriumSmelteryRecipe, sinisiteSmelteryRecipe;
; 
}
