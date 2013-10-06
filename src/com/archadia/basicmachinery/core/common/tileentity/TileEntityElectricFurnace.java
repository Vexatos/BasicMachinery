package com.archadia.basicmachinery.core.common.tileentity;

import com.archadia.basicmachinery.core.common.BasicMachinery;
import com.google.common.io.ByteArrayDataInput;

import cpw.mods.fml.common.network.PacketDispatcher;
import universalelectricity.prefab.network.PacketManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraftforge.common.ForgeDirection;


/**
 * @author Archadia
 *
 */
public class TileEntityElectricFurnace extends ElectricContainer {
	private final static TileEntityElectricFurnace tileEntityBase = new TileEntityElectricFurnace();   
	
	public final static TileEntityElectricFurnace instance() {
		return tileEntityBase;
	}
	
	public TileEntityElectricFurnace() {
		setInventorySize(9);
		setMaxTicks(200);
		setInventorySize(2);
	}
	
	public void updateEntity() {
		super.updateEntity();
		if(worldObj.getWorldTime()%20==0) {
			if(!worldObj.isRemote) {
		        PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
				System.out.println("SERVER, Energy Stored: " + this.getEnergyStored());
			}
			if(worldObj.isRemote) {
				System.out.println("CLIENT, Energy Stored: " + this.getEnergyStored());
			}
		}
            if (this.canProcess() && this.getEnergyStored() >= 1000)
            {
            	this.processTicks++;
            	
                if (this.processTicks == getMaxTicks())
                {
                	this.processTicks = 0;
                    processItems();
                    this.setEnergyStored(this.getEnergyStored() - 1000);
                }
            } else {
            	this.processTicks = 0;
            }
	}
	
	public boolean canProcess() {
		if(this.inventory[0] == null) {
			return false;
		} else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);
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
		return "Electric Furnace";
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
		this.energyStored = dataStream.readFloat();
	}
}
