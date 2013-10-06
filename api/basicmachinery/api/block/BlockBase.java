package basicmachinery.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import basicmachinery.api.BMLoader;
import basicmachinery.api.WrenchHelper;

/**
 * @author Archadia
 *
 */
public class BlockBase extends Block {
	
	WrenchHelper wrenchHelper = new WrenchHelper();
	BMLoader loader = new BMLoader();
	
	String blockIconLoc;
	
	public BlockBase(int id, Material material, String name) {
		super(id, material);
		setUnlocalizedName(name);
		this.blockIconLoc = name;
	}
	
	public Icon[] icons;
	
	public void registerIcons(IconRegister ir) {
		this.blockIcon = ir.registerIcon(loader.getAssetLoc() + this.blockIconLoc);
	}
	
	public void setIconMax(int max) {
		this.icons = new Icon[max];
	}
}
