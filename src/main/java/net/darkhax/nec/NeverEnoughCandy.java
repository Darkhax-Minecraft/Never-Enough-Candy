package net.darkhax.nec;

import net.darkhax.nec.common.ProxyCommon;
import net.darkhax.nec.handler.ConfigurationHandler;
import net.darkhax.nec.handler.ForgeEventHandler;
import net.darkhax.nec.handler.LootHandler;
import net.darkhax.nec.items.ItemManager;
import net.darkhax.nec.util.Constants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, guiFactory = Constants.FACTORY, acceptedMinecraftVersions = "[1.8,1.9)", dependencies = "required-after:Forge@[11.14.4,)")
public class NeverEnoughCandy {
    
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static ProxyCommon proxy;
    
    @Mod.Instance(Constants.MOD_ID)
    public static NeverEnoughCandy instance;
    
    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent pre) {
        
        new ConfigurationHandler(pre.getSuggestedConfigurationFile());
        new ItemManager();
        new LootHandler();
        MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }

    @Mod.EventHandler
    public void init (FMLInitializationEvent init) {
        proxy.registerRenders();
    }
}