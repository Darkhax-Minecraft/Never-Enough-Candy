package net.darkhax.nec.client.gui;

import net.darkhax.nec.handler.ConfigurationHandler;
import net.darkhax.nec.util.Constants;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;

public class GuiConfigNEC extends GuiConfig {
    
    static Configuration cfg = ConfigurationHandler.config;
    
    public GuiConfigNEC(GuiScreen parent) {
        
        super(parent, generateConfigList(), Constants.MOD_ID, false, false, GuiConfigNEC.getAbridgedConfigPath(ConfigurationHandler.config.toString()));
    }
    
    /**
     * Generates a list of configuration options to be displayed in forge's configuration GUI.
     * 
     * @return List<IConfigElement>: A list of IConfigElement which are used to populate
     *         forge's configuration GUI.
     */
    public static List<IConfigElement> generateConfigList () {
        
        ArrayList<IConfigElement> elements = new ArrayList<IConfigElement>();
        
        for (String name : cfg.getCategoryNames())
            elements.add(new ConfigElement(cfg.getCategory(name)));
            
        return elements;
    }
}