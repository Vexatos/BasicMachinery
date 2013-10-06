package com.archadia.basicmachinery.core.common.container;

import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricPulverizer;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

/**
 * @author Archadia
 *
 */
public class ContainerElectricPulverizer extends ContainerBasicMachine {

	private TileEntityElectricPulverizer tileEnt;

    public ContainerElectricPulverizer(InventoryPlayer par1InventoryPlayer, TileEntityElectricPulverizer tile) {
        super.bindPlayerInventory(par1InventoryPlayer);
    	
    	this.tileEnt = (TileEntityElectricPulverizer) tile;
        
        addSlotToContainer(new Slot(tile, 0, 55, 37));
        addSlotToContainer(new Slot(tile, 1, 105, 37));
    }
}
