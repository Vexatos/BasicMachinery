package com.archadia.basicmachinery.core.common.container;

import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricFurnace;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 *
 */
public class ContainerElectricFurnace extends ContainerBasicMachine {

	private TileEntityElectricFurnace tileEnt;

    public ContainerElectricFurnace(InventoryPlayer par1InventoryPlayer, TileEntityElectricFurnace tile) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tileEnt = (TileEntityElectricFurnace) tile;
        
        addSlotToContainer(new Slot(tile, 0, 55, 37));
        addSlotToContainer(new Slot(tile, 1, 105, 37));
    }
}
