package com.archadia.basicmachinery.core.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.archadia.basicmachinery.core.common.container.ContainerElectricBox;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricBox;

/**
 * @author Archadia
 *
 */
public class GuiElectricBox extends GuiContainer {
	
    private static TileEntityElectricBox tileINV = new TileEntityElectricBox();

    public GuiElectricBox(InventoryPlayer par1InventoryPlayer, TileEntityElectricBox tile) {
        super(new ContainerElectricBox(par1InventoryPlayer, tile));
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String s = this.tileINV.isInvNameLocalized() ? this.tileINV.getInvName() : I18n.func_135053_a(this.tileINV.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 2 - 4, 7, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(new ResourceLocation("basicmachinery","textures/gui/electric_box.png"));
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}
