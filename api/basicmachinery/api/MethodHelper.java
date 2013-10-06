package basicmachinery.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class MethodHelper {
	
	public String getUsableName(Item item) {
		if(item != null) {
			String input = item.getUnlocalizedName();
			if(input.contains("item.")) {
				String output = input.replace("item.", "");
				return output;
			}
		}
		return null;
	}
	
	/**
	 * Checks if block exists.
	 * Used mostly with the OreDictionary
	 * @param block
	 * @return
	 */
	public boolean doesBlockExist(Block block) {
		if(block == null) return false;
		return true;
	}
	
	/**
	 * Checks if item exists.
	 * Used mostly with the OreDictionary
	 * @param item
	 * @return
	 */
	public boolean doesItemExist(Item item) {
		if(item == null) return false;
		return true;
	}
	
	/**
	 * Checks if block exists.
	 * Used mostly with the OreDictionary
	 * BlockID sensitive version
	 * @param block
	 * @return
	 */
	public boolean doesBlockExist(int blockid) {
		if(blockid == 0) return false;
		for(Block block : Block.blocksList) {
			if(blockid == block.blockID) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Checks if item exists.
	 * Used mostly with the OreDictionary
	 * ItemID sensitive version
	 * @param item
	 * @return
	 */
	public boolean doesItemExist(int itemid) {
		if(itemid == 0) return false;
		for(Item item : Item.itemsList) {
			if(item != null) {
				if(itemid == item.itemID) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
	
	public Block getBlockFromID(int id) {
		for(Block block : Block.blocksList) {
			if(id == block.blockID) {
				return block;
			}
		}
		return null;	}
	
	public Item getItemFromID(int id) {
		for(Item item : Item.itemsList) {
			if(id == item.itemID) {
				return item;
			}
		}
		return null;
	}
}
