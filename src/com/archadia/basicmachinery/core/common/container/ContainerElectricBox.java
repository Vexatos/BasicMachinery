package com.archadia.basicmachinery.core.common.container;

import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricBox;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 *
 */
public class ContainerElectricBox extends ContainerBasicMachine {

	private TileEntityElectricBox tileEnt;

    public ContainerElectricBox(InventoryPlayer par1InventoryPlayer, TileEntityElectricBox tile) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tileEnt = (TileEntityElectricBox) tile;
        
        addSlotToContainer(new Slot(tile, 0, 43, 16));
    }

}
