package theoldone822.SimpleTcon;

import alexndr.api.content.blocks.SimpleBlock;
import alexndr.api.content.items.SimpleItem;
import alexndr.api.registry.ContentCategories;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Content {
	public static Item zinc_ingot;
	public static Block zinc_ore;
	public static Block zinc_block;
	public static Item brass_ingot;
	public static Block brass_block;
	
	public static void doStuff() {
		if (Settings.enableBrass.asBoolean()){
		zinc_ore = new SimpleBlock(SimpleTcon.plugin, Material.ROCK,
				ContentCategories.Block.ORE).setConfigEntry(Settings.zincOre)
				.setStepSound(SoundType.STONE)
				.setUnlocalizedName("zinc_ore");
		zinc_block = new SimpleBlock(SimpleTcon.plugin, Material.ROCK,
				ContentCategories.Block.GENERAL).setConfigEntry(Settings.zincBlock)
				.setStepSound(SoundType.METAL)
				.setUnlocalizedName("zinc_block");
		brass_block = new SimpleBlock(SimpleTcon.plugin, Material.ROCK,
				ContentCategories.Block.GENERAL).setConfigEntry(Settings.brassBlock)
				.setStepSound(SoundType.METAL)
				.setUnlocalizedName("brass_block");

		zinc_ingot = new SimpleItem(SimpleTcon.plugin,
				ContentCategories.Item.INGOT).setConfigEntry(
				Settings.zincIngot).setUnlocalizedName("zinc_ingot");
		brass_ingot = new SimpleItem(SimpleTcon.plugin,
				ContentCategories.Item.INGOT).setConfigEntry(
				Settings.brassIngot).setUnlocalizedName("brass_ingot");
		
		OreDictionary.registerOre("oreZinc", new ItemStack(zinc_ore));
		OreDictionary.registerOre("blockZinc", new ItemStack(zinc_block));
		OreDictionary.registerOre("ingotZinc", new ItemStack(zinc_ingot));
		OreDictionary.registerOre("blockBrass", new ItemStack(brass_block));
		OreDictionary.registerOre("ingotBrass", new ItemStack(brass_ingot));
		
		GameRegistry.addShapelessRecipe(new ItemStack(Content.brass_ingot, 9), new Object[] { 
				brass_block });
		GameRegistry.addShapelessRecipe(new ItemStack(Content.zinc_ingot, 9), new Object[] { 
				zinc_block });
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(zinc_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotZinc"}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(brass_block, true, new Object[]{
				"XXX", "XXX", "XXX", Character.valueOf('X'), "ingotBrass"}));
		GameRegistry.addSmelting(zinc_ore, new ItemStack(zinc_ingot, 1, 0), Settings.zincIngot.getSmeltingXP());
		}
	}

}
