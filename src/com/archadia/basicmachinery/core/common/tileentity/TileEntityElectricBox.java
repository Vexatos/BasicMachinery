package com.archadia.basicmachinery.core.common.tileentity;

import java.util.HashSet;
import java.util.Set;

import com.archadia.basicmachinery.core.common.BasicMachinery;
import com.google.common.io.ByteArrayDataInput;

import universalelectricity.prefab.network.PacketManager;
import basiccomponents.common.BasicComponents;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
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
public class TileEntityElectricBox extends ElectricContainer {
	private final static TileEntityElectricBox tileEntityBase = new TileEntityElectricBox();   
	
	public final Set<EntityPlayer> playersUsing = new HashSet<EntityPlayer>();

	public final static TileEntityElectricBox instance() {
		return tileEntityBase;
	}
	
	public TileEntityElectricBox() {
		setInventorySize(9);
		setMaxTicks(200);
		setInventorySize(2);
	}
	
	public void updateEntity() {
		super.updateEntity();

		if (!this.worldObj.isRemote)
		{
			this.discharge(this.inventory[0]);
			PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
		}

		System.out.println("Energy Stored: " + this.getEnergyStored());
		/**
		 * Gradually lose energy.
		 */
		this.setEnergyStored(this.getEnergyStored() - 0.00005f);
		PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
	}

	@Override
	public Packet getDescriptionPacket()
	{
		return PacketManager.getPacket(BasicMachinery.CHANNEL, this, this.getEnergyStored());
	}

	
	@Override
	public float getRequest(ForgeDirection direction) {
		return 500;
	}

	@Override
	public float getProvide(ForgeDirection direction) {
		return 1000;
	}

	@Override
	public float getMaxEnergyStored() {
		return 10000;
	}
	
	public String getInvName() {
		return "Electric Box";
	}

	/* (non-Javadoc)
	 * @see universalelectricity.prefab.network.IPacketReceiver#handlePacketData(net.minecraft.network.INetworkManager, int, net.minecraft.network.packet.Packet250CustomPayload, net.minecraft.entity.player.EntityPlayer, com.google.common.io.ByteArrayDataInput)
	 */
	@Override
	public void handlePacketData(INetworkManager network, int packetType,
			Packet250CustomPayload packet, EntityPlayer player,
			ByteArrayDataInput dataStream) {
		// TODO Auto-generated method stub
		
	}
}
