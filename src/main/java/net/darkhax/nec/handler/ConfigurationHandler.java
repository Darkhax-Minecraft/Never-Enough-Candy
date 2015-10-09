package net.darkhax.nec.handler;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.darkhax.nec.items.CandyType;
import net.darkhax.nec.items.ItemManager;
import net.darkhax.nec.util.Constants;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {
    
    public static Configuration config;
    
    public ConfigurationHandler(File configFile) {
        
        config = new Configuration(configFile);
        FMLCommonHandler.instance().bus().register(this);
    }
    
    @SubscribeEvent
    public void onConfigChange (ConfigChangedEvent.OnConfigChangedEvent event) {
        
        if (event.modID.equals(Constants.MOD_ID))
            for (CandyType type : ItemManager.candies)
                syncType(type);
    }
    
    public static void syncType (CandyType type) {
        
        type.food = config.getInt("food" + type.name, "Food Points", type.food, 0, Integer.MAX_VALUE, "How many food points should the " + type.name + " candy type restore?");
        type.odds = config.getFloat("odds" + type.name, "Food Rarity", type.odds, 0.00f, 1.00f, "What percent of the time should this candy be dropped? 0.00 is 0% 1.00 is 100%, 0.1337 is 13.37%");
        
        if (config.hasChanged())
            config.save();
    }
}