package basicmachinery.api;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class BMLoader {

	public String assetLocation;
	
	public static HashMap<String, Block> blockList = new HashMap<String, Block>();
	public static HashMap<String, Item> itemList = new HashMap<String, Item>();
	
	public void addBlock(Block block, String name) {
		blockList.put(name, block);
	}
	
	public Block getBlock(String name) {
		return blockList.get(name);
	}
	
	public void addItem(Item item, String name) {
		itemList.put(name, item);
	}
	
	public Item getItem(String name) {
		return itemList.get(name);
	}
	
	public void setAssetLoc(String locpath) {
		assetLocation = locpath + ":";
	}
	
	public String getAssetLoc() {
		return assetLocation;
	}
}
