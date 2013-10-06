package com.archadia.basicmachinery.core.common.tileentity;

import universalelectricity.prefab.network.PacketManager;

import com.archadia.basicmachinery.core.common.BasicMachinery;
import com.archadia.basicmachinery.core.common.recipe.BasicMachineryRecipe;
import com.google.common.io.ByteArrayDataInput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author Archadia
 *
 */
public class TileEntityElectricPulverizer extends ElectricContainer {
	
	private final static TileEntityElectricPulverizer tileEntityBase = new TileEntityElectricPulverizer();   
	
	public final static TileEntityElectricPulverizer instance() {
		return tileEntityBase;
	}
	
	public TileEntityElectricPulverizer() {
		setInventorySize(9);
		setMaxTicks(200);
		setInventorySize(2);
	}
	
	public void updateEntity() {
		super.updateEntity();
		boolean flag1 = false;

		if(this.worldObj.getWorldTime()%20==0) {
            PacketManager.sendPacketToClients(getDescriptionPacket(), worldObj);
		}
		
    	System.out.println("Energy Stored: " + this.getEnergyStored());
		
		if (!this.worldObj.isRemote)
        {
            if (this.canProcess() && this.getEnergyStored() >= 1000)
            {
            	this.processTicks++;
            	
                if (this.processTicks == getMaxTicks())
                {
                	this.processTicks = 0;
                    processItems();
                    this.setEnergyStored(this.getEnergyStored() - 1000);
                    flag1 = true;
                }
            } else {
            	this.processTicks = 0;
            }
        }
        if (flag1)
        {
            this.onInventoryChanged();
        }
	}
	
	public boolean canProcess() {
		if(this.inventory[0] == null) {
			return false;
		} else {
            ItemStack itemstack = BasicMachineryRecipe.Recipe.PULVERIZER.getResult(inventory[0].itemID);
            if (itemstack == null) {
            	return false;
            }
            if (inventory[1] == null) {
            	return true;
            }
            if (!inventory[1].isItemEqual(itemstack)) { 
            	return false;
            }
            int result = inventory[1].stackSize + itemstack.stackSize;
            return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
        }
	}
	
	public void processItems() {
		if(canProcess()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);
	
	        if (inventory[1] == null)
	        {
	        	inventory[1] = itemstack.copy();
	        }
	        else if (inventory[1].isItemEqual(itemstack))
	        {
	        	inventory[1].stackSize += itemstack.stackSize;
	        } 
	
	        --inventory[0].stackSize;
	
	        if (inventory[0].stackSize <= 0)
	        {
	            inventory[0] = null;
	        }
		}
	}

	@Override
	public float getRequest(ForgeDirection direction) {
		return 1000;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 0;
	}

	@Override
	public float getMaxEnergyStored() {
		return 10000;
	}
	
	public String getInvName() {
		return "Electric Pulverizer";
	}
	
    public int getProcessProgressScaled(int par1) {   
    	return this.processTicks * par1 / 200;
    }

	@Override
	public Packet getDescriptionPacket() {
		return PacketManager.getPacket(BasicMachinery.CHANNEL, this, this.getEnergyStored());
	}

	@Override
	public void handlePacketData(INetworkManager network, int packetType,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		this.energyStored = dataStream.readInt();
	}
}
