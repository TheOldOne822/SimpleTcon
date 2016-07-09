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
import slimeknights.tconstruct.smeltery.block.BlockMolten;

public class SimpleTinkerFluids {

	public static FluidMolten mythril;
	public static FluidMolten adamantium;
	public static FluidMolten onyx;
	public static FluidMolten thyrium;
	public static FluidMolten sinisite;

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
