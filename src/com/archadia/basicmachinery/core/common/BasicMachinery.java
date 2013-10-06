package com.archadia.basicmachinery.core.common;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import universalelectricity.prefab.network.PacketManager;
import basiccomponents.common.tileentity.TileEntityElectricFurnace;
import basicmachinery.api.BMLoader;
import basicmachinery.api.MethodHelper;
import basicmachinery.api.Tab;

import com.archadia.basicmachinery.core.common.block.BlockMachine;
import com.archadia.basicmachinery.core.common.item.ItemWrench;
import com.archadia.basicmachinery.core.common.proxy.CommonProxy;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricBox;
import com.archadia.basicmachinery.core.common.tileentity.TileEntityElectricPulverizer;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author Archadia
 *
 */
@Mod(modid = BasicMachinery.MOD_ID, name = BasicMachinery.NAME, version = BasicMachinery.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = true, channels = {BasicMachinery.CHANNEL}, packetHandler = PacketManager.class)
public class BasicMachinery {

	public static final String[] languages = new String[] {"en_US", "de_DE", "ru_RU"};
	
	public static final String CHANNEL = "basicmachinery";
	public static final String MOD_ID = "basicmachinery";
	public static final String NAME = "Basic Machinery";
	public static final String VERSION = "0.0.1";
	
	@Instance(BasicMachinery.MOD_ID)
	public static BasicMachinery instance;
	
	@SidedProxy(clientSide = "com.archadia.basicmachinery.core.common.proxy.ClientProxy", serverSide = "com.archadia.complexmachines.core.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static Block electricFurnace;
	public static Block electricPulverizer;
	public static Block electricBox;
	
	public static Item wrench;
		
	public static Tab BMTab1;
	
	public static final Configuration config = new Configuration(new File(Loader.instance().getConfigDir() + "/BasicMachinery.cfg"));
	public static MethodHelper helper = new MethodHelper();
	public static BMLoader loader = new BMLoader();
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent e) {
		electricFurnace = new BlockMachine(2490, Material.iron, "electricFurnace").setHardness(2F).setCreativeTab(BMTab1);;
		electricPulverizer = new BlockMachine(2491, Material.iron, "electricPulverizer").setHardness(2F).setCreativeTab(BMTab1);;
		electricBox = new BlockMachine(2492, Material.iron, "electricBox").setHardness(2F).setCreativeTab(BMTab1);;
		
		wrench = new ItemWrench(9012, "wrench").setCreativeTab(BMTab1);
		
		doLoaderStuff();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent e) {
		BMTab1 = new Tab("BMTab1", electricFurnace);
		
		GameRegistry.registerBlock(electricFurnace, "electricFurnace");
		GameRegistry.registerTileEntity(TileEntityElectricFurnace.class, "tileEntityElectricFurnace");
		GameRegistry.registerBlock(electricPulverizer, "electricPulverizer");
		GameRegistry.registerTileEntity(TileEntityElectricPulverizer.class, "tileEntityElectricPulverizer");
		GameRegistry.registerBlock(electricBox, "electricBox");
		GameRegistry.registerTileEntity(TileEntityElectricBox.class, "tileEntityElectricBox");
		
		for(String language : languages) {
			LanguageRegistry.instance().loadLocalization("/assets/basicmachinery/lang/" + language + ".properties", language, false);
		}		 
						
		NetworkRegistry.instance().registerGuiHandler(this, proxy);
	}
	
	public void doLoaderStuff() {
		loader.setAssetLoc("basicmachinery");
		
		loader.addItem(wrench, "wrench");
		loader.addBlock(electricFurnace, "electricFurnace");
		loader.addBlock(electricPulverizer, "electricPulverizer");
		loader.addBlock(electricBox, "electricBox");
	}
}
