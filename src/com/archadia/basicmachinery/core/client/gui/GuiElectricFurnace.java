package com.archadia.basicmachinery.core.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.archadia.basicmachinery.core.common.container.ContainerElectricFurnace;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricFurnace;

import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * @author Archadia
 *
 */
public class GuiElectricFurnace extends GuiContainer {
	
    private static TileEntityElectricFurnace tileINV = new TileEntityElectricFurnace();

    public GuiElectricFurnace(InventoryPlayer par1InventoryPlayer, TileEntityElectricFurnace tile) {
        super(new ContainerElectricFurnace(par1InventoryPlayer, tile));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tileINV.isInvNameLocalized() ? this.tileINV.getInvName() : I18n.func_135053_a(this.tileINV.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - 4, 7, 4210752);
        this.fontRenderer.drawString("J: " + tileINV.energyStored, this.xSize / 2 - 82, 7, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(new ResourceLocation("basicmachinery","textures/gui/electric_furnace.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;
        i1 = tileINV.getProcessProgressScaled(24);
        this.drawTexturedModalRect(k + 76, l + 40, 176, 0, i1 + 1, 1);
    }

}
