package com.archadia.basicmachinery.core.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import org.lwjgl.input.Keyboard;

import basicmachinery.api.WrenchHelper;
import basicmachinery.api.block.BlockWrenchable;

import com.archadia.basicmachinery.core.common.BasicMachinery;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricBox;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricFurnace;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricPulverizer;

/**
 * @author Archadia
 *
 */
public class BlockMachine extends BlockWrenchable {

	public String machine_ident;
	
	public BlockMachine(int id, Material material, String name) {
		super(id, material, name);
		setIconMax(6);
		machine_ident = name;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		if(machine_ident == "electricFurnace") {
			return new TileEntityElectricFurnace();
		}
		else if(machine_ident == "electricPulverizer") {
			return new TileEntityElectricPulverizer();
		}
		else if(machine_ident == "electricBox") {
			return new TileEntityElectricBox();
		}
		return null;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i1, float f2, float f3, float f4) {				
		int metadata = world.getBlockMetadata(x, y, z);
		if(super.useWrench(player, world, x, y, z)) {
			return true;
		}

		if(machine_ident == "electricFurnace") {
			player.openGui(BasicMachinery.instance, 0, world, x, y, z);
			return true;
		}
		else if(machine_ident == "electricPulverizer") {
			player.openGui(BasicMachinery.instance, 1, world, x, y, z);
			return true;
		}
		else if(machine_ident == "electricBox") {
			player.openGui(BasicMachinery.instance, 2, world, x, y, z);
			return true;
		}		
		return false;
	}

	public Icon getIcon(int side, int meta)
	{
		if(side == 1 || side == 6) return icons[0];
		
		if(machine_ident == "electricFurnace") {
			if(side > 1 && side != 6) return icons[1];
		}
		
		if(machine_ident == "electricPulverizer") {
			if(side > 1 && side != 6) return icons[2];
		}
		
		if(machine_ident == "electricBox") {
			if(side > 1 && side != 6) return icons[3];
		}
		return null;
	}

	public void registerIcons(IconRegister ir) {
		icons[0] = ir.registerIcon("basicmachinery:machine_side");
		icons[1] = ir.registerIcon("basicmachinery:electric_furnace");
		icons[2] = ir.registerIcon("basicmachinery:electric_pulverizer");
		icons[3] = ir.registerIcon("basicmachinery:electric_box");
	}
}
