package theoldone822.SimpleTcon;

import java.util.List;

import com.google.common.collect.Lists;

import alexndr.api.helpers.game.RenderItemHelper;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTraits;
import theoldone822.SimpleTcon.Traits.*;

@Mod(modid = "simpletcon", name = "Simple Tcon addon", version = "2.0", dependencies = "required-after:tconstruct;required-after:simplecore;after:simpleores;after:fusion;after:netherrocks;after:netherrocksfusion")
public class SimpleTcon {

	public static Plugin plugin = new Plugin("simpletcon", "Simple Tcon addon");
	public static final AbstractTrait modifiable = new TraitModifiable(1);
	public static final AbstractTrait durable = new TraitDurable();
	public static final AbstractTrait dwarvish = new TraitDwarvish();
	public static final AbstractTrait illuminating = new TraitIlluminating();
	public static final AbstractTrait blinding = new TraitBlinding();
	public static final AbstractTrait slowing = new TraitSlowing();
	public static final AbstractTrait fieryt = new TraitFiery();
	public static final AbstractTrait magical = new TraitMagical(1);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ContentRegistry.registerPlugin(plugin);
		Settings.createOrLoadSettings(event);
		Content.doStuff();
		SimpleTinkerFluids.setupFluids(event);
		if (Loader.isModLoaded("simpleores")) {
			mythril.addItem("ingotMythril", 1, Material.VALUE_Ingot);
			mythril.setRepresentativeItem(ContentRegistry.getItem("mythril_ingot"));
			mythril.addTrait(modifiable, HEAD);
			mythril.addTrait(dwarvish, HEAD);
			mythril.addTrait(modifiable);


			adamantium.addItem("ingotAdamantium", 1, Material.VALUE_Ingot);
			adamantium.setRepresentativeItem(ContentRegistry.getItem("adamantium_ingot"));
			adamantium.addTrait(durable, HEAD);
			adamantium.addTrait(TinkerTraits.lightweight);

			if (!Settings.enableOnyxMelting.asBoolean() || !Settings.onyxCasting.asBoolean()){
				onyx.setCraftable(true);
			}
			onyx.addItem("gemOnyx", 1, Material.VALUE_Ingot);
			onyx.setRepresentativeItem(ContentRegistry.getItem("onyx_gem"));
			onyx.addTrait(TinkerTraits.hellish, HEAD);
			onyx.addTrait(TinkerTraits.aridiculous);

			MaterialIntegration mythrilI = new MaterialIntegration(mythril, SimpleTinkerFluids.mythril, "Mythril")
					.toolforge();
			MaterialIntegration adamantiumI = new MaterialIntegration(adamantium, SimpleTinkerFluids.adamantium,
					"Adamantium").toolforge();
			
			mythrilI.integrate();
			adamantiumI.integrate();
			
			mythrilI.integrateRecipes();
			adamantiumI.integrateRecipes();
			
			if (!Settings.enableOnyxMelting.asBoolean()){
			MaterialIntegration onyxI = new MaterialIntegration("gemOnyx", onyx, null, "onyx").toolforge();
			
			onyxI.integrate();

			onyxI.integrateRecipes();
			} else {
				MaterialIntegration onyxI = new MaterialIntegration("gemOnyx", onyx, SimpleTinkerFluids.onyx, "onyx").toolforge();
				
				onyxI.integrate();

				onyxI.integrateRecipes();
				TinkerRegistry.registerMelting(ContentRegistry.getItem("onyx_gem"), SimpleTinkerFluids.onyx, Material.VALUE_Ingot);
				TinkerRegistry.registerMelting(ContentRegistry.getBlock("onyx_ore"), SimpleTinkerFluids.onyx, Material.VALUE_Ingot * 2);
				TinkerRegistry.registerMelting(ContentRegistry.getBlock("onyx_block"), SimpleTinkerFluids.onyx, Material.VALUE_Ingot * 9);
				TinkerRegistry.registerTableCasting(new ItemStack(ContentRegistry.getItem("onyx_gem"), 1), TinkerSmeltery.castGem, SimpleTinkerFluids.onyx, Material.VALUE_Ingot);
				TinkerRegistry.registerBasinCasting(new ItemStack(ContentRegistry.getBlock("onyx_block"), 1), null, SimpleTinkerFluids.onyx, Material.VALUE_Ingot * 9);
				
				if(Loader.isModLoaded("fusion") && Settings.sinisiteSmelteryRecipe.asBoolean()){
					TinkerRegistry.registerAlloy(new FluidStack(SimpleTinkerFluids.sinisite, 1), new FluidStack(SimpleTinkerFluids.mythril, 1), new FluidStack(SimpleTinkerFluids.onyx, 1));
				}
			}

			if (Loader.isModLoaded("fusion")) {
				thyrium.addItem("ingotThyrium", 1, Material.VALUE_Ingot);
				thyrium.setRepresentativeItem(ContentRegistry.getItem("thyrium_ingot"));
				thyrium.addTrait(modifiable, HEAD);
				thyrium.addTrait(durable, HEAD);
				thyrium.addTrait(TinkerTraits.lightweight);

				sinisite.addItem("ingotSinisite", 1, Material.VALUE_Ingot);
				sinisite.setRepresentativeItem(ContentRegistry.getItem("sinisite_ingot"));
				sinisite.addTrait(TinkerTraits.unnatural, HEAD);
				sinisite.addTrait(TinkerTraits.aridiculous);
			    
				MaterialIntegration thyriumI = new MaterialIntegration(thyrium, SimpleTinkerFluids.thyrium, "Thyrium")
						.toolforge();
				MaterialIntegration sinisiteI = new MaterialIntegration(sinisite, SimpleTinkerFluids.sinisite,
						"Sinisite").toolforge();

				thyriumI.integrate();
				sinisiteI.integrate();

				thyriumI.integrateRecipes();
				sinisiteI.integrateRecipes();
				
				if(Settings.thyriumSmelteryRecipe.asBoolean()){
					TinkerRegistry.registerAlloy(new FluidStack(SimpleTinkerFluids.thyrium, 1), new FluidStack(SimpleTinkerFluids.mythril, 1), new FluidStack(SimpleTinkerFluids.adamantium, 1));
				}
			}
		}
		if (Loader.isModLoaded("netherrocks")){
			fyrite.addItem("ingotFyrite", 1, Material.VALUE_Ingot);
			fyrite.setRepresentativeItem(ContentRegistry.getItem("fyrite_ingot"));
			fyrite.addTrait(TinkerTraits.autosmelt, HEAD);
			fyrite.addTrait(fieryt);

			malachite.addItem("ingotMalachite", 1, Material.VALUE_Ingot);
			malachite.setRepresentativeItem(ContentRegistry.getItem("malachite_ingot"));
			malachite.addTrait(magical, HEAD);
			malachite.addTrait(magical);

			illumenite.addItem("ingotIllumenite", 1, Material.VALUE_Ingot);
			illumenite.setRepresentativeItem(ContentRegistry.getItem("illumenite_ingot"));
			illumenite.addTrait(blinding, HEAD);
			illumenite.addTrait(slowing, HEAD);
			illumenite.addTrait(illuminating);

			argonite.addItem("ingotArgonite", 1, Material.VALUE_Ingot);
			argonite.setRepresentativeItem(ContentRegistry.getItem("argonite_ingot"));
			argonite.addTrait(durable, HEAD);
			argonite.addTrait(TinkerTraits.lightweight);
			
			if (!Settings.enableOnyxMelting.asBoolean() || !Settings.onyxCasting.asBoolean()){
				ashstone.setCraftable(true);
				dragonstone.setCraftable(true);
			}
			ashstone.addItem("gemAshstone", 1, Material.VALUE_Ingot);
			ashstone.setRepresentativeItem(ContentRegistry.getItem("ashstone_gem"));
			ashstone.addTrait(TinkerTraits.hellish, HEAD);
			ashstone.addTrait(TinkerTraits.aridiculous);

			dragonstone.addItem("gemDragonstone", 1, Material.VALUE_Ingot);
			dragonstone.setRepresentativeItem(ContentRegistry.getItem("dragonstone_gem"));
			dragonstone.addTrait(TinkerTraits.hellish, HEAD);
			dragonstone.addTrait(TinkerTraits.aridiculous);

			MaterialIntegration fyriteI = new MaterialIntegration(fyrite, SimpleTinkerFluids.fyrite, "Fyrite")
					.toolforge();
			MaterialIntegration malachiteI = new MaterialIntegration(malachite, SimpleTinkerFluids.malachite,
					"Malachite").toolforge();
			MaterialIntegration illumeniteI = new MaterialIntegration(illumenite, SimpleTinkerFluids.illumenite,
					"Illumenite").toolforge();
			MaterialIntegration argoniteI = new MaterialIntegration(argonite, SimpleTinkerFluids.argonite,
					"Argonite").toolforge();
			
			fyriteI.integrate();
			malachiteI.integrate();
			illumeniteI.integrate();
			argoniteI.integrate();
			
			fyriteI.integrateRecipes();
			malachiteI.integrateRecipes();
			illumeniteI.integrateRecipes();
			argoniteI.integrateRecipes();


			if (!Settings.enableOnyxMelting.asBoolean()){
				MaterialIntegration ashstoneI = new MaterialIntegration("gemAshstone", ashstone, null, "ashstone").toolforge();
				MaterialIntegration dragonstoneI = new MaterialIntegration("gemDragonstone", dragonstone, null, "dragonstone").toolforge();
			
				ashstoneI.integrate();
				dragonstoneI.integrate();

				ashstoneI.integrateRecipes();
				dragonstoneI.integrateRecipes();
			} else {
				MaterialIntegration ashstoneI = new MaterialIntegration("gemAshstone", ashstone, SimpleTinkerFluids.ashstone, "ashstone").toolforge();
				MaterialIntegration dragonstoneI = new MaterialIntegration("gemDragonstone", dragonstone, SimpleTinkerFluids.dragonstone, "dragonstone").toolforge();
				
				ashstoneI.integrate();
				dragonstoneI.integrate();

				ashstoneI.integrateRecipes();
				dragonstoneI.integrateRecipes();
				
				TinkerRegistry.registerMelting(ContentRegistry.getItem("ashstone_gem"), SimpleTinkerFluids.ashstone, Material.VALUE_Ingot);
				TinkerRegistry.registerMelting(ContentRegistry.getBlock("ashstone_ore"), SimpleTinkerFluids.ashstone, Material.VALUE_Ingot * 2);
				TinkerRegistry.registerMelting(ContentRegistry.getBlock("ashstone_block"), SimpleTinkerFluids.ashstone, Material.VALUE_Ingot * 9);
				TinkerRegistry.registerTableCasting(new ItemStack(ContentRegistry.getItem("ashstone_gem"), 1), TinkerSmeltery.castGem, SimpleTinkerFluids.ashstone, Material.VALUE_Ingot);
				TinkerRegistry.registerBasinCasting(new ItemStack(ContentRegistry.getBlock("ashstone_block"), 1), null, SimpleTinkerFluids.ashstone, Material.VALUE_Ingot * 9);
				
				TinkerRegistry.registerMelting(ContentRegistry.getItem("dragonstone_gem"), SimpleTinkerFluids.dragonstone, Material.VALUE_Ingot);
				TinkerRegistry.registerMelting(ContentRegistry.getBlock("dragonstone_ore"), SimpleTinkerFluids.dragonstone, Material.VALUE_Ingot * 2);
				TinkerRegistry.registerMelting(ContentRegistry.getBlock("dragonstone_block"), SimpleTinkerFluids.dragonstone, Material.VALUE_Ingot * 9);
				TinkerRegistry.registerTableCasting(new ItemStack(ContentRegistry.getItem("dragonstone_gem"), 1), TinkerSmeltery.castGem, SimpleTinkerFluids.dragonstone, Material.VALUE_Ingot);
				TinkerRegistry.registerBasinCasting(new ItemStack(ContentRegistry.getBlock("dragonstone_block"), 1), null, SimpleTinkerFluids.dragonstone, Material.VALUE_Ingot * 9);
			}
			if (Loader.isModLoaded("netherrocksfusion")) {
				cinderstone.addItem("ingotCinderstone", 1, Material.VALUE_Ingot);
				cinderstone.setRepresentativeItem(ContentRegistry.getItem("cinderstoneIngot"));
				cinderstone.addTrait(TinkerTraits.autosmelt, HEAD);
				cinderstone.addTrait(blinding, HEAD);
				cinderstone.addTrait(slowing, HEAD);
				cinderstone.addTrait(fieryt);
				cinderstone.addTrait(illuminating);

				thraka.addItem("ingotThraka", 1, Material.VALUE_Ingot);
				thraka.setRepresentativeItem(ContentRegistry.getItem("thrakaIngot"));
				thraka.addTrait(TinkerTraits.unnatural, HEAD);
				thraka.addTrait(TinkerTraits.aridiculous);
			    
				pyralis.addItem("ingotPyralis", 1, Material.VALUE_Ingot);
				pyralis.setRepresentativeItem(ContentRegistry.getItem("pyralisIngot"));
				pyralis.addTrait(TinkerTraits.autosmelt, HEAD);
				pyralis.addTrait(TinkerTraits.unnatural);
			    
				MaterialIntegration cinderstoneI = new MaterialIntegration(cinderstone, SimpleTinkerFluids.cinderstone, "Cinderstone")
						.toolforge();
				MaterialIntegration thrakaI = new MaterialIntegration(thraka, SimpleTinkerFluids.thraka,
						"Thraka").toolforge();
				MaterialIntegration pyralisI = new MaterialIntegration(pyralis, SimpleTinkerFluids.pyralis,
						"Pyralis").toolforge();

				cinderstoneI.integrate();
				thrakaI.integrate();
				pyralisI.integrate();

				cinderstoneI.integrateRecipes();
				thrakaI.integrateRecipes();
				pyralisI.integrateRecipes();
				
				if(Settings.cinderstoneSmelteryRecipe.asBoolean()){
					TinkerRegistry.registerAlloy(new FluidStack(SimpleTinkerFluids.cinderstone, 1), new FluidStack(SimpleTinkerFluids.fyrite, 1), new FluidStack(SimpleTinkerFluids.illumenite, 1));
				}
				
				if(Settings.thrakaSmelteryRecipe.asBoolean() && Settings.enableOnyxMelting.asBoolean()){
					TinkerRegistry.registerAlloy(new FluidStack(SimpleTinkerFluids.thraka, 1), new FluidStack(SimpleTinkerFluids.malachite, 1), new FluidStack(SimpleTinkerFluids.ashstone, 1));
				}
				
				if(Settings.pyralisSmelteryRecipe.asBoolean() && Settings.enableOnyxMelting.asBoolean()){
					TinkerRegistry.registerAlloy(new FluidStack(SimpleTinkerFluids.pyralis, 1), new FluidStack(SimpleTinkerFluids.cinderstone, 1), new FluidStack(SimpleTinkerFluids.dragonstone, 1));
				}
				
				if(Loader.isModLoaded("simpleores")){
				dragonbezoar.addItem("ingotDragonBezoar", 1, Material.VALUE_Ingot);
				dragonbezoar.setRepresentativeItem(ContentRegistry.getItem("dragonbezoarIngot"));
				dragonbezoar.addTrait(TinkerTraits.autosmelt, HEAD);
				dragonbezoar.addTrait(TinkerTraits.unnatural, HEAD);
				dragonbezoar.addTrait(modifiable, HEAD);
				dragonbezoar.addTrait(magical, HEAD);
				dragonbezoar.addTrait(magical);
				dragonbezoar.addTrait(modifiable);
				dragonbezoar.addTrait(TinkerTraits.unnatural);
				dragonbezoar.addTrait(blinding, HEAD);
				dragonbezoar.addTrait(slowing, HEAD);
				dragonbezoar.addTrait(fieryt);
				dragonbezoar.addTrait(illuminating);
				dragonbezoar.addTrait(TinkerTraits.aridiculous);
				
				MaterialIntegration dragonbezoarI = new MaterialIntegration(dragonbezoar, SimpleTinkerFluids.dragonbezoar,
						"DragonBezoar").toolforge();
				
				dragonbezoarI.integrate();
				
				dragonbezoarI.integrateRecipes();				
				if(Settings.dragonbezoarSmelteryRecipe.asBoolean()){
					TinkerRegistry.registerAlloy(new FluidStack(SimpleTinkerFluids.dragonbezoar, 1), new FluidStack(SimpleTinkerFluids.pyralis, 1), new FluidStack(SimpleTinkerFluids.sinisite, 1));
				}}
			}
			}
		registerToolMaterials();

		if(event.getSide() == Side.CLIENT) 
		{
			RenderItemHelper renderHelper = new RenderItemHelper(plugin);

			renderHelper.renderItemsAndBlocks();
			renderHelper.renderItemStuff(event);
		}
}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		if (Settings.enableBrass.asBoolean()) {
			OreGenerator.registerOreForGen(0, Content.zinc_ore, Blocks.STONE, Settings.zincOre.getSpawnRate(),
					Settings.zincOre.getVeinSize(), Settings.zincOre.getMinHeight(), Settings.zincOre.getMaxHeight());
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if (event.getSide().isClient()) {
			registerMaterialRendering();
		}
		if(FluidRegistry.getFluid("super_lava") != null && Settings.enableSuperLava.asBoolean()){
			TinkerRegistry.registerSmelteryFuel(new FluidStack(FluidRegistry.getFluid("super_lava"), Settings.superLavaUse.asInt()), Settings.superLavaTime.asInt());
		}
		if(FluidRegistry.getFluid("steam") != null && Settings.enableSteam.asBoolean()){
			TinkerRegistry.registerSmelteryFuel(new FluidStack(FluidRegistry.getFluid("steam"), Settings.steamUse.asInt()), Settings.steamTime.asInt());
		}
	}

	public static final List<Material> materials = Lists.newArrayList();

	public static final Material mythril = mat("stconmythril", 0x2882d4);
	public static final Material adamantium = mat("stconadamantium", 0x00a10f);
	public static final Material onyx = mat("stcononyx", 0x363636);
	public static final Material thyrium = mat("stconthyrium", 0x469DCF);
	public static final Material sinisite = mat("stconsinisite", 0x175891);
	public static final Material fyrite = mat("stconfyrite", 0xFC3300);
	public static final Material malachite = mat("stconmalachite", 0x08DC73);
	public static final Material ashstone = mat("stconashstone", 0x898989);
	public static final Material illumenite = mat("stconillumenite", 0xFFFFA7);
	public static final Material dragonstone = mat("stcondragonstone", 0x661E16);
	public static final Material argonite = mat("stconargonite", 0x32004D);
	public static final Material cinderstone = mat("stconcinderstone", 0xFFB266);
	public static final Material thraka = mat("stconthraka", 0x1D8546);
	public static final Material pyralis = mat("stconpyralis", 0xC64E18);
	public static final Material dragonbezoar = mat("stcondragonbezoar", 0x693D47);

	private static Material mat(String name, int color) {
		if (TinkerRegistry.getMaterial(name) == TinkerRegistry.getMaterial("unknown")){
			Material mat = new Material(name, color);
			materials.add(mat);
			return mat;
		}
		return TinkerRegistry.getMaterial(name);
	}

	private static final String HEAD = MaterialTypes.HEAD;

	public void registerToolMaterials() {
		if (Loader.isModLoaded("simpleores")) {
			TinkerRegistry.addMaterialStats(mythril,
					new HeadMaterialStats(Settings.mythrilTinkerHeadDurability.asInt(),
							Settings.mythrilTinkerHarvestSpeed.asFloat(),
							Settings.mythrilTinkerDamageVsEntity.asFloat(), Settings.mythrilTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.mythrilTinkerhandleModifer.asFloat(),
							Settings.mythrilTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.mythrilTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.mythrilTinkerBowSpeed.asFloat(), Settings.mythrilTinkerBowRange.asFloat(), Settings.mythrilTinkerBowDamage.asFloat()));
			
			TinkerRegistry.addMaterialStats(adamantium, new HeadMaterialStats(
					Settings.adamantiumTinkerHeadDurability.asInt(), Settings.adamantiumTinkerHarvestSpeed.asFloat(),
					Settings.adamantiumTinkerDamageVsEntity.asFloat(), Settings.adamantiumTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.adamantiumTinkerhandleModifer.asFloat(),
							Settings.adamantiumTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.adamantiumTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.adamantiumTinkerBowSpeed.asFloat(), Settings.adamantiumTinkerBowRange.asFloat(), Settings.adamantiumTinkerBowDamage.asFloat()));

			TinkerRegistry.addMaterialStats(onyx,
					new HeadMaterialStats(Settings.onyxTinkerHeadDurability.asInt(),
							Settings.onyxTinkerHarvestSpeed.asFloat(), Settings.onyxTinkerDamageVsEntity.asFloat(),
							Settings.onyxTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.onyxTinkerhandleModifer.asFloat(),
							Settings.onyxTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.onyxTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.onyxTinkerBowSpeed.asFloat(), Settings.onyxTinkerBowRange.asFloat(), Settings.onyxTinkerBowDamage.asFloat()));

			if (Loader.isModLoaded("fusion")) {
				TinkerRegistry.addMaterialStats(thyrium, new HeadMaterialStats(
						Settings.thyriumTinkerHeadDurability.asInt(), Settings.thyriumTinkerHarvestSpeed.asFloat(),
						Settings.thyriumTinkerDamageVsEntity.asFloat(), Settings.thyriumTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.thyriumTinkerhandleModifer.asFloat(),
								Settings.thyriumTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.thyriumTinkerextraDurability.asInt()),
						new BowMaterialStats(Settings.thyriumTinkerBowSpeed.asFloat(), Settings.thyriumTinkerBowRange.asFloat(), Settings.thyriumTinkerBowDamage.asFloat()));

				TinkerRegistry.addMaterialStats(sinisite, new HeadMaterialStats(
						Settings.sinisiteTinkerHeadDurability.asInt(), Settings.sinisiteTinkerHarvestSpeed.asFloat(),
						Settings.sinisiteTinkerDamageVsEntity.asFloat(), Settings.sinisiteTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.sinisiteTinkerhandleModifer.asFloat(),
								Settings.sinisiteTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.sinisiteTinkerextraDurability.asInt()),
						new BowMaterialStats(Settings.sinisiteTinkerBowSpeed.asFloat(), Settings.sinisiteTinkerBowRange.asFloat(), Settings.sinisiteTinkerBowDamage.asFloat()));
			}
		}
		if (Loader.isModLoaded("netherrocks")){
			TinkerRegistry.addMaterialStats(fyrite, new HeadMaterialStats(
					Settings.fyriteTinkerHeadDurability.asInt(), Settings.fyriteTinkerHarvestSpeed.asFloat(),
					Settings.fyriteTinkerDamageVsEntity.asFloat(), Settings.fyriteTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.fyriteTinkerhandleModifer.asFloat(),
							Settings.fyriteTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.fyriteTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.fyriteTinkerBowSpeed.asFloat(), Settings.fyriteTinkerBowRange.asFloat(), Settings.fyriteTinkerBowDamage.asFloat()));

			TinkerRegistry.addMaterialStats(malachite, new HeadMaterialStats(
					Settings.malachiteTinkerHeadDurability.asInt(), Settings.malachiteTinkerHarvestSpeed.asFloat(),
					Settings.malachiteTinkerDamageVsEntity.asFloat(), Settings.malachiteTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.malachiteTinkerhandleModifer.asFloat(),
							Settings.malachiteTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.malachiteTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.malachiteTinkerBowSpeed.asFloat(), Settings.malachiteTinkerBowRange.asFloat(), Settings.malachiteTinkerBowDamage.asFloat()));

			TinkerRegistry.addMaterialStats(ashstone, new HeadMaterialStats(
					Settings.ashstoneTinkerHeadDurability.asInt(), Settings.ashstoneTinkerHarvestSpeed.asFloat(),
					Settings.ashstoneTinkerDamageVsEntity.asFloat(), Settings.ashstoneTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.ashstoneTinkerhandleModifer.asFloat(),
							Settings.ashstoneTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.ashstoneTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.ashstoneTinkerBowSpeed.asFloat(), Settings.ashstoneTinkerBowRange.asFloat(), Settings.ashstoneTinkerBowDamage.asFloat()));

			TinkerRegistry.addMaterialStats(illumenite, new HeadMaterialStats(
					Settings.illumeniteTinkerHeadDurability.asInt(), Settings.illumeniteTinkerHarvestSpeed.asFloat(),
					Settings.illumeniteTinkerDamageVsEntity.asFloat(), Settings.illumeniteTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.illumeniteTinkerhandleModifer.asFloat(),
							Settings.illumeniteTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.illumeniteTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.illumeniteTinkerBowSpeed.asFloat(), Settings.illumeniteTinkerBowRange.asFloat(), Settings.illumeniteTinkerBowDamage.asFloat()));

			TinkerRegistry.addMaterialStats(dragonstone, new HeadMaterialStats(
					Settings.dragonstoneTinkerHeadDurability.asInt(), Settings.dragonstoneTinkerHarvestSpeed.asFloat(),
					Settings.dragonstoneTinkerDamageVsEntity.asFloat(), Settings.dragonstoneTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.dragonstoneTinkerhandleModifer.asFloat(),
							Settings.dragonstoneTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.dragonstoneTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.dragonstoneTinkerBowSpeed.asFloat(), Settings.dragonstoneTinkerBowRange.asFloat(), Settings.dragonstoneTinkerBowDamage.asFloat()));

			TinkerRegistry.addMaterialStats(argonite, new HeadMaterialStats(
					Settings.argoniteTinkerHeadDurability.asInt(), Settings.argoniteTinkerHarvestSpeed.asFloat(),
					Settings.argoniteTinkerDamageVsEntity.asFloat(), Settings.argoniteTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.argoniteTinkerhandleModifer.asFloat(),
							Settings.argoniteTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.argoniteTinkerextraDurability.asInt()),
					new BowMaterialStats(Settings.argoniteTinkerBowSpeed.asFloat(), Settings.argoniteTinkerBowRange.asFloat(), Settings.argoniteTinkerBowDamage.asFloat()));

			if (Loader.isModLoaded("netherrocksfusion")) {
				TinkerRegistry.addMaterialStats(cinderstone, new HeadMaterialStats(
						Settings.cinderstoneTinkerHeadDurability.asInt(), Settings.cinderstoneTinkerHarvestSpeed.asFloat(),
						Settings.cinderstoneTinkerDamageVsEntity.asFloat(), Settings.cinderstoneTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.cinderstoneTinkerhandleModifer.asFloat(),
								Settings.cinderstoneTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.cinderstoneTinkerextraDurability.asInt()),
						new BowMaterialStats(Settings.cinderstoneTinkerBowSpeed.asFloat(), Settings.cinderstoneTinkerBowRange.asFloat(), Settings.cinderstoneTinkerBowDamage.asFloat()));
				
				TinkerRegistry.addMaterialStats(thraka, new HeadMaterialStats(
						Settings.thrakaTinkerHeadDurability.asInt(), Settings.thrakaTinkerHarvestSpeed.asFloat(),
						Settings.thrakaTinkerDamageVsEntity.asFloat(), Settings.thrakaTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.thrakaTinkerhandleModifer.asFloat(),
								Settings.thrakaTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.thrakaTinkerextraDurability.asInt()),
						new BowMaterialStats(Settings.thrakaTinkerBowSpeed.asFloat(), Settings.thrakaTinkerBowRange.asFloat(), Settings.thrakaTinkerBowDamage.asFloat()));

				TinkerRegistry.addMaterialStats(pyralis, new HeadMaterialStats(
						Settings.pyralisTinkerHeadDurability.asInt(), Settings.pyralisTinkerHarvestSpeed.asFloat(),
						Settings.pyralisTinkerDamageVsEntity.asFloat(), Settings.pyralisTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.pyralisTinkerhandleModifer.asFloat(),
								Settings.pyralisTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.pyralisTinkerextraDurability.asInt()),
						new BowMaterialStats(Settings.pyralisTinkerBowSpeed.asFloat(), Settings.pyralisTinkerBowRange.asFloat(), Settings.pyralisTinkerBowDamage.asFloat()));
			
				if (Loader.isModLoaded("simpleores")) {
					TinkerRegistry.addMaterialStats(dragonbezoar, new HeadMaterialStats(
							Settings.dragonbezoarTinkerHeadDurability.asInt(), Settings.dragonbezoarTinkerHarvestSpeed.asFloat(),
							Settings.dragonbezoarTinkerDamageVsEntity.asFloat(), Settings.dragonbezoarTinkerHarvestLevel.asInt()),
							new HandleMaterialStats(Settings.dragonbezoarTinkerhandleModifer.asFloat(),
									Settings.dragonbezoarTinkerhandleDurability.asInt()),
							new ExtraMaterialStats(Settings.dragonbezoarTinkerextraDurability.asInt()),
							new BowMaterialStats(Settings.dragonbezoarTinkerBowSpeed.asFloat(), Settings.dragonbezoarTinkerBowRange.asFloat(), Settings.dragonbezoarTinkerBowDamage.asFloat()));

				}
			}

		}
	}

	@SideOnly(Side.CLIENT)
	static void registerMaterialRendering() {
		// Metals
		if (Loader.isModLoaded("simpleores")) {
			SimpleTcon.mythril.setRenderInfo(new MaterialRenderInfo.Metal(0x167CE0, 0.5f, 0.5f, 0.2f));
			SimpleTcon.adamantium.setRenderInfo(new MaterialRenderInfo.Metal(0x169623, 0.2f, 0.3f, 0.1f));
			SimpleTcon.onyx.setRenderInfo(new MaterialRenderInfo.Metal(0x0F0F0F, 0.2f, 0.3f, 0.1f));
			if (Loader.isModLoaded("fusion")) {
				SimpleTcon.thyrium.setRenderInfo(new MaterialRenderInfo.Metal(0x4FAA92, 0.4f, 0.4f, 0.05f));
				SimpleTcon.sinisite.setRenderInfo(new MaterialRenderInfo.Metal(0x173b75, 0.0f, 0.15f, 0.2f));
			}
		}
		if (Loader.isModLoaded("netherrocks")){
			SimpleTcon.fyrite.setRenderInfo(new MaterialRenderInfo.Metal(0xFC3300, 0.2f, 0.3f, 0.1f));
			SimpleTcon.malachite.setRenderInfo(new MaterialRenderInfo.Metal(0x08DC73, 0.2f, 0.3f, 0.1f));
			SimpleTcon.ashstone.setRenderInfo(new MaterialRenderInfo.Metal(0x898989, 0.2f, 0.3f, 0.1f));
			SimpleTcon.illumenite.setRenderInfo(new MaterialRenderInfo.Metal(0xFFFFA7, 0.2f, 0.3f, 0.1f));
			SimpleTcon.dragonstone.setRenderInfo(new MaterialRenderInfo.Metal(0x661E16, 0.2f, 0.3f, 0.1f));
			SimpleTcon.argonite.setRenderInfo(new MaterialRenderInfo.Metal(0x32004D, 0.2f, 0.3f, 0.1f));
			if (Loader.isModLoaded("netherrocksfusion")) {
				SimpleTcon.cinderstone.setRenderInfo(new MaterialRenderInfo.Metal(0xFFB266, 0.2f, 0.3f, 0.1f));
				SimpleTcon.thraka.setRenderInfo(new MaterialRenderInfo.Metal(0x1D8546, 0.2f, 0.3f, 0.1f));
				SimpleTcon.pyralis.setRenderInfo(new MaterialRenderInfo.Metal(0xC64E18, 0.2f, 0.3f, 0.1f));
				if (Loader.isModLoaded("simpleores")) {
					SimpleTcon.dragonbezoar.setRenderInfo(new MaterialRenderInfo.Metal(0x693D47, 0.2f, 0.3f, 0.1f));
					}
			}
		}
	}
}
