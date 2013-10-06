package basicmachinery.api;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import basicmachinery.api.block.BlockWrenchable;

/**
 * @author Archadia
 *
 */
public class WrenchHelper {

	BMLoader loader = new BMLoader();
	
	public boolean canWrench(Block block) {
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			if(block instanceof BlockWrenchable) {
				return true;
			}
		}
		return false;
	}
	
	public void useWrench(EntityPlayer player, World world, int x, int y, int z, Block block) {
		if(canWrench(block)) {
			if(player.getCurrentEquippedItem() != null) {
				if(player.getCurrentEquippedItem().itemID == loader.getItem("wrench").itemID) {
					world.destroyBlock(x, y, z, true);
					player.getCurrentEquippedItem().setItemDamage(player.getCurrentEquippedItem().getItemDamage() + 100);
					if(player.getCurrentEquippedItem().getItemDamage() > player.getCurrentEquippedItem().getMaxDamage()) {
						player.destroyCurrentEquippedItem();
					}
				} else {
					System.err.println("BASICMACHINERY NOT INSTALLED! INSTALL PLEASE.");
				}
					
			}
		}
	}
	
	
}
