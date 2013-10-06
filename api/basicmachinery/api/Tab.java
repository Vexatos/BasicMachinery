package basicmachinery.api;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import com.archadia.basicmachinery.core.common.BasicMachinery;

/**
 * @author Archadia
 *
 */
public class Tab extends CreativeTabs {
	
	Block blockIcon;
	
	public Tab(String tabname, Block blockToDisplay) {
		super(tabname);
		this.blockIcon = blockToDisplay;
	}
	
	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(this.blockIcon);
	}
}