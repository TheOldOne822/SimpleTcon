package theoldone822.SimpleTcon;

import java.util.Locale;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.mantle.item.ItemBlockMeta;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class SimpleTinkerFluids {

	public static FluidMolten mythril;
	public static FluidMolten adamantium;
	public static FluidMolten onyx;
	public static FluidMolten thyrium;
	public static FluidMolten sinisite;
	public static FluidMolten fyrite;
	public static FluidMolten malachite;
	public static FluidMolten ashstone;
	public static FluidMolten illumenite;
	public static FluidMolten dragonstone;
	public static FluidMolten argonite;
	public static FluidMolten cinderstone;
	public static FluidMolten thraka;
	public static FluidMolten pyralis;
	public static FluidMolten dragonbezoar;

	public static void setupFluids(FMLPreInitializationEvent event) {

		if (Loader.isModLoaded("simpleores")) {
			mythril = fluidMetal("mythril", 0x167CE0);
			mythril.setTemperature(750);
			registerMoltenBlock(mythril);

			adamantium = fluidMetal("adamantium", 0x169623);
			adamantium.setTemperature(900);
			registerMoltenBlock(adamantium);
			
			if (Settings.enableOnyxMelting.asBoolean()){
				onyx = fluidMetal("onyx", 0x0F0F0F);
				onyx.setTemperature(950);
				registerMoltenBlock(onyx);
			}

			if (Loader.isModLoaded("fusion")) {
				thyrium = fluidMetal("thyrium", 0x4FAA92);
				thyrium.setTemperature(850);
				registerMoltenBlock(thyrium);

				sinisite = fluidMetal("sinisite", 0x16167E);
				sinisite.setTemperature(1000);
				registerMoltenBlock(sinisite);
			}
		}
		
		if (Loader.isModLoaded("netherrocks")){
			fyrite = fluidMetal("fyrite", 0xFC3300);
			fyrite.setTemperature(1000);
			registerMoltenBlock(fyrite);
			
			malachite = fluidMetal("malachite", 0x08DC73);
			malachite.setTemperature(900);
			registerMoltenBlock(malachite);
			
			illumenite = fluidMetal("illumenite", 0xFFFFA7);
			illumenite.setTemperature(980);
			registerMoltenBlock(illumenite);
			
			argonite = fluidMetal("argonite", 0x32004D);
			argonite.setTemperature(950);
			registerMoltenBlock(argonite);
			
			if (Settings.enableOnyxMelting.asBoolean()){
				ashstone = fluidMetal("ashstone", 0x898989);
				ashstone.setTemperature(850);
				registerMoltenBlock(ashstone);
				
				dragonstone = fluidMetal("dragonstone", 0x661E16);
				dragonstone.setTemperature(1000);
				registerMoltenBlock(dragonstone);
			}
			if (Loader.isModLoaded("netherrocksfusion")) {
				cinderstone = fluidMetal("cinderstone", 0xFFB266);
				cinderstone.setTemperature(1000);
				registerMoltenBlock(cinderstone);
				
				thraka = fluidMetal("thraka", 0x1D8546);
				thraka.setTemperature(1000);
				registerMoltenBlock(thraka);
				
				pyralis = fluidMetal("pyralis", 0xC64E18);
				pyralis.setTemperature(1000);
				registerMoltenBlock(pyralis);
				
				dragonbezoar = fluidMetal("dragonbezoar", 0x693D47);
				dragonbezoar.setTemperature(1000);
				registerMoltenBlock(dragonbezoar);
			}
		}

		if (event.getSide().isClient()) {
			if (Loader.isModLoaded("simpleores")) {
				registerFluidModels(mythril);
				registerFluidModels(adamantium);
				if (Settings.enableOnyxMelting.asBoolean()){
					registerFluidModels(onyx);
				}
				if (Loader.isModLoaded("fusion")) {
					registerFluidModels(thyrium);
					registerFluidModels(sinisite);
				}
			}
			if (Loader.isModLoaded("netherrocks")){
				registerFluidModels(fyrite);
				registerFluidModels(malachite);
				registerFluidModels(illumenite);
				registerFluidModels(argonite);
				if (Settings.enableOnyxMelting.asBoolean()){
					registerFluidModels(ashstone);
					registerFluidModels(dragonstone);
				}
				if (Loader.isModLoaded("netherrocksfusion")) {
					registerFluidModels(cinderstone);
					registerFluidModels(thraka);
					registerFluidModels(pyralis);
					registerFluidModels(dragonbezoar);
				}
			}
		}

	}

	private static FluidMolten fluidMetal(String name, int color) {
		FluidMolten fluid = new FluidMolten(name, color);
		return registerFluid(fluid);
	}

	protected static <T extends Fluid> T registerFluid(T fluid) {
		fluid.setUnlocalizedName("simpletcon." + fluid.getName().toLowerCase(Locale.US));
		FluidRegistry.registerFluid(fluid);

		return fluid;
	}

	/** Registers a hot lava-based block for the fluid, prefix with molten_ */
	public static BlockMolten registerMoltenBlock(Fluid fluid) {
		BlockMolten block = new BlockMolten(fluid);
		return registerBlock(block, "molten_" + fluid.getName()); // molten_foobar
																	// prefix
	}

	protected static <T extends Block> T registerBlock(T block, String name) {
		ItemBlock itemBlock = new ItemBlockMeta(block);
		block.setUnlocalizedName("simpletcon." + name);
		itemBlock.setUnlocalizedName("simpletcon." + name);

		register(block, name);
		register(itemBlock, name);

		return block;
	}

	protected static <T extends IForgeRegistryEntry<?>> T register(T thing, String name) {
		thing.setRegistryName(new ResourceLocation("simpletcon", name));
		GameRegistry.register(thing);
		return thing;
	}

	@SideOnly(Side.CLIENT)
	public static void registerFluidModels(Fluid fluid) {
		if (fluid == null) {
			return;
		}

		Block block = fluid.getBlock();
		if (block != null) {
			Item item = Item.getItemFromBlock(block);
			FluidStateMapper mapper = new FluidStateMapper(fluid);

			// item-model
			if (item != null) {
				ModelBakery.registerItemVariants(item);
				ModelLoader.setCustomMeshDefinition(item, mapper);
			}
			// block-model
			ModelLoader.setCustomStateMapper(block, mapper);
		}
	}

	@SideOnly(Side.CLIENT)
	public static class FluidStateMapper extends StateMapperBase implements ItemMeshDefinition {

		public final Fluid fluid;
		public final ModelResourceLocation location;

		public FluidStateMapper(Fluid fluid) {
			this.fluid = fluid;

			// have each block hold its fluid per nbt? hm
			this.location = new ModelResourceLocation(new ResourceLocation("simpletcon", "fluid_block"),
					fluid.getName());
		}

		@Nonnull
		@Override
		protected ModelResourceLocation getModelResourceLocation(@Nonnull IBlockState state) {
			return location;
		}

		@Nonnull
		@Override
		public ModelResourceLocation getModelLocation(@Nonnull ItemStack stack) {
			return location;
		}
	}

}
