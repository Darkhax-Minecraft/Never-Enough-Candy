package net.darkhax.nec;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.darkhax.nec.common.ProxyCommon;
import net.darkhax.nec.handler.ConfigurationHandler;
import net.darkhax.nec.util.Constants;

@Mod(modid = Constants.MOD_ID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, guiFactory = Constants.FACTORY)
public class NeverEnoughCandy {
    
    @SidedProxy(clientSide = Constants.CLIENT_PROXY_CLASS, serverSide = Constants.SERVER_PROXY_CLASS)
    public static ProxyCommon proxy;
    
    @Mod.Instance(Constants.MOD_ID)
    public static NeverEnoughCandy instance;
    
    @EventHandler
    public void preInit (FMLPreInitializationEvent pre) {

    }
}