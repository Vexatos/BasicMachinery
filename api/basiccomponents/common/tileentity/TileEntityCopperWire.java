package basiccomponents.common.tileentity;

import universalelectricity.compatibility.TileEntityUniversalConductor;

public class TileEntityCopperWire extends TileEntityUniversalConductor
{
	/**
	 * Changed this if your mod wants to nerf Basic Component's copper wire.
	 */
	public static float RESISTANCE = 0.05F;
	public static float MAX_AMPS = 200;

	@Override
	public float getResistance()
	{
		return RESISTANCE;
	}

	@Override
	public float getCurrentCapacity()
	{
		return MAX_AMPS;
	}
}
