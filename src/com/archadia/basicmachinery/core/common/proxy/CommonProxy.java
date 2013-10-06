package com.archadia.basicmachinery.core.common.proxy;

import com.archadia.basicmachinery.core.client.gui.GuiElectricBox;
import com.archadia.basicmachinery.core.client.gui.GuiElectricFurnace;
import com.archadia.basicmachinery.core.client.gui.GuiElectricPulverizer;
import com.archadia.basicmachinery.core.common.container.ContainerElectricBox;
import com.archadia.basicmachinery.core.common.container.ContainerElectricFurnace;
import com.archadia.basicmachinery.core.common.container.ContainerElectricPulverizer;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricBox;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricFurnace;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricPulverizer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author Archadia
 *
 */
public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		switch(ID) {
			case 0:
				return new ContainerElectricFurnace(player.inventory, (TileEntityElectricFurnace) tile);
			case 1:
				return new ContainerElectricPulverizer(player.inventory, (TileEntityElectricPulverizer) tile);
			case 2:
				return new ContainerElectricBox(player.inventory, (TileEntityElectricBox) tile);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		switch(ID) {
			case 0:
				return new GuiElectricFurnace(player.inventory, (TileEntityElectricFurnace) tile);
			case 1:
				return new GuiElectricPulverizer(player.inventory, (TileEntityElectricPulverizer) tile);
			case 2:
				return new GuiElectricBox(player.inventory, (TileEntityElectricBox) tile);
		}
		return null;
	}

}
