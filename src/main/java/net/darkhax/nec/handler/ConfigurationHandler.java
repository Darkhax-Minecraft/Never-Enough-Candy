package net.darkhax.nec.handler;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.darkhax.nec.util.Constants;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
    
    public static Configuration config;
    
    public ConfigurationHandler(File configFile) {
        
        config = new Configuration(configFile);
        FMLCommonHandler.instance().bus().register(this);
        syncConfigData();
    }
    
    @SubscribeEvent
    public void onConfigChange (ConfigChangedEvent.OnConfigChangedEvent event) {
        
        if (event.modID.equals(Constants.MOD_ID))
            syncConfigData();
    }
    
    public void syncConfigData () {
        
        if (config.hasChanged())
            config.save();
    }
}