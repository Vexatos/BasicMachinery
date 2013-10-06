package com.archadia.basicmachinery.core.common.recipe;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

/**
 * @author Archadia
 *
 */
public class BasicMachineryRecipe {
	 
	private static BasicMachineryRecipe instanceBase = new BasicMachineryRecipe();
	
	public static BasicMachineryRecipe instance() {
		return instanceBase;
	}
	
	public enum Recipe {
		PULVERIZER(new HashMap<Integer, ItemStack>());
		
		public HashMap map;
		
		Recipe(HashMap map) {
			this.map = map;
		}
		
		public ItemStack getResult(int input) {
			ItemStack item = (ItemStack) this.map.get(input);
			if (item == null) {
				return null;
			}
			return (ItemStack)this.map.get(input);
		}
		
		public void put(Object input, Object output) {
			map.put(input, output);
		}
	}
	
	 public void addPulverizerRecipes(int input, ItemStack output) {
		 Recipe.PULVERIZER.put(input, output);
	 }
	
}
