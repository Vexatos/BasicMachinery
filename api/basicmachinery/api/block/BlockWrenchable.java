package basicmachinery.api.block;

import basicmachinery.api.Tab;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author Archadia
 *
 */
public class BlockWrenchable extends BlockTile {

	public BlockWrenchable(int id, Material material, String name) {
		super(id, material, name);
	}

	/**
	 * Uses the wrench.
	 * 
	 * @param player
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @return whether the wrench was used.
	 */
	protected boolean useWrench(EntityPlayer player, World world, int x, int y, int z) {
		if(wrenchHelper.canWrench(this)) {
			wrenchHelper.useWrench(player, world, x, y, z, this);
			onWrenchUse(player);
			return true;
		}
		return false;
	}
	
	protected void onWrenchUse(EntityPlayer player) {
		player.swingItem();
	}
}
