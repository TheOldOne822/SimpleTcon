package theoldone822.SimpleTcon;

import java.util.List;

import com.google.common.collect.Lists;

import alexndr.api.helpers.game.RenderItemHelper;
import alexndr.api.helpers.game.OreGenerator;
import alexndr.api.registry.ContentRegistry;
import alexndr.api.registry.Plugin;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
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
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerMaterials;
import theoldone822.SimpleTcon.Traits.TraitDurable;
import theoldone822.SimpleTcon.Traits.TraitDwarvish;
import theoldone822.SimpleTcon.Traits.TraitModifiable;

@Mod(modid = "simpletcon", name = "Simple Tcon addon", version = "0.9RC2", dependencies = "required-after:tconstruct;required-after:simplecore;after:simpleores;after:fusion")
public class SimpleTcon {

	public static Plugin plugin = new Plugin("simpletcon", "Simple Tcon addon");
	public static final AbstractTrait modifiable = new TraitModifiable(1);
	public static final AbstractTrait durable = new TraitDurable();
	public static final AbstractTrait dwarvish = new TraitDwarvish();

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
			adamantium.addTrait(TinkerMaterials.lightweight);

			if (!Settings.enableOnyxMelting.asBoolean() && !Settings.onyxCasting.asBoolean()){
			onyx.setCraftable(true);
			}
			onyx.addItem("gemOnyx", 1, Material.VALUE_Ingot);
			onyx.setRepresentativeItem(ContentRegistry.getItem("onyx_gem"));
			onyx.addTrait(TinkerMaterials.hellish, HEAD);
			onyx.addTrait(TinkerMaterials.aridiculous);

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
				thyrium.addTrait(TinkerMaterials.lightweight);

				sinisite.addItem("ingotSinisite", 1, Material.VALUE_Ingot);
				sinisite.setRepresentativeItem(ContentRegistry.getItem("sinisite_ingot"));
				sinisite.addTrait(TinkerMaterials.unnatural, HEAD);
				sinisite.addTrait(TinkerMaterials.aridiculous);
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
	}

	public static final List<Material> materials = Lists.newArrayList();

	public static final Material mythril = mat("mythril", 0x2882d4);
	public static final Material adamantium = mat("adamantium", 0x00a10f);
	public static final Material onyx = mat("onyx", 0x363636);
	public static final Material thyrium = mat("thyrium", 0x469DCF);
	public static final Material sinisite = mat("sinisite", 0x175891);

	private static Material mat(String name, int color) {
		Material mat = new Material(name, color);
		materials.add(mat);
		return mat;
	}

	private static final String HEAD = HeadMaterialStats.TYPE;

	public void registerToolMaterials() {
		if (Loader.isModLoaded("simpleores")) {
			TinkerRegistry.addMaterialStats(mythril,
					new HeadMaterialStats(Settings.mythrilTinkerHeadDurability.asInt(),
							Settings.mythrilTinkerHarvestSpeed.asFloat(),
							Settings.mythrilTinkerDamageVsEntity.asFloat(), Settings.mythrilTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.mythrilTinkerhandleModifer.asFloat(),
							Settings.mythrilTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.mythrilTinkerextraDurability.asInt()));

			TinkerRegistry.addMaterialStats(adamantium, new HeadMaterialStats(
					Settings.adamantiumTinkerHeadDurability.asInt(), Settings.adamantiumTinkerHarvestSpeed.asFloat(),
					Settings.adamantiumTinkerDamageVsEntity.asFloat(), Settings.adamantiumTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.adamantiumTinkerhandleModifer.asFloat(),
							Settings.adamantiumTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.adamantiumTinkerextraDurability.asInt()));

			TinkerRegistry.addMaterialStats(onyx,
					new HeadMaterialStats(Settings.onyxTinkerHeadDurability.asInt(),
							Settings.onyxTinkerHarvestSpeed.asFloat(), Settings.onyxTinkerDamageVsEntity.asFloat(),
							Settings.onyxTinkerHarvestLevel.asInt()),
					new HandleMaterialStats(Settings.onyxTinkerhandleModifer.asFloat(),
							Settings.onyxTinkerhandleDurability.asInt()),
					new ExtraMaterialStats(Settings.onyxTinkerextraDurability.asInt()));

			if (Loader.isModLoaded("fusion")) {
				TinkerRegistry.addMaterialStats(thyrium, new HeadMaterialStats(
						Settings.thyriumTinkerHeadDurability.asInt(), Settings.thyriumTinkerHarvestSpeed.asFloat(),
						Settings.thyriumTinkerDamageVsEntity.asFloat(), Settings.thyriumTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.thyriumTinkerhandleModifer.asFloat(),
								Settings.thyriumTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.thyriumTinkerextraDurability.asInt()));

				TinkerRegistry.addMaterialStats(sinisite, new HeadMaterialStats(
						Settings.sinisiteTinkerHeadDurability.asInt(), Settings.sinisiteTinkerHarvestSpeed.asFloat(),
						Settings.sinisiteTinkerDamageVsEntity.asFloat(), Settings.sinisiteTinkerHarvestLevel.asInt()),
						new HandleMaterialStats(Settings.sinisiteTinkerhandleModifer.asFloat(),
								Settings.sinisiteTinkerhandleDurability.asInt()),
						new ExtraMaterialStats(Settings.sinisiteTinkerextraDurability.asInt()));
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
	}
}
