package basicmachinery.api.item;

import basicmachinery.api.BMLoader;

import com.archadia.basicmachinery.core.common.BasicMachinery;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

/**
 * @author Archadia
 *
 */
public class ItemBase extends Item {

	String itemIconLoc;
	BMLoader loader = new BMLoader();

	public ItemBase(int id, String name) {
		super(id);
		setUnlocalizedName(name);		
		itemIconLoc = name;
		setCreativeTab(BasicMachinery.BMTab1);
	}
	
	public ItemBase(int id) {
		super(id);
		setCreativeTab(BasicMachinery.BMTab1);
	}
	
	public void registerIcons(IconRegister ir) {
		this.itemIcon = ir.registerIcon(loader.getAssetLoc() + itemIconLoc);
	}
}
