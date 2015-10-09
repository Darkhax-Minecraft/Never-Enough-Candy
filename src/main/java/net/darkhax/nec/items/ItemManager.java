package net.darkhax.nec.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.registry.GameRegistry;
import net.darkhax.nec.handler.ConfigurationHandler;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;

public class ItemManager {
    
    public static List<CandyType> candies = new ArrayList<CandyType>();
    public static HashMap<String, ItemPlayerCookie> cookies = new HashMap<String, ItemPlayerCookie>();
    
    public ItemManager() {
        
        candies.add(new CandyType("batty", 2, 0.05f, EntityBat.class));
        candies.add(new CandyType("witch", 2, 0.05f, EntityWitch.class));
        candies.add(new CandyType("spider", 2, 0.05f, EntitySpider.class));
        candies.add(new CandyType("enderpop", 2, 0.05f, EntityEnderman.class));
        candies.add(new CandyType("skull", 2, 0.05f, EntitySkeleton.class, true));
        
        for (CandyType type : candies) {
            
            ConfigurationHandler.syncType(type);
            type.item = new ItemCandy(type);
            GameRegistry.registerItem(type.item, type.name);
        }
        
        String[] playerNames = new String[] { "darkhax", "lclc98", "stacyplays", "stampylonghead" };
        
        for (String name : playerNames) {
            
            ItemPlayerCookie cookie = new ItemPlayerCookie(name);
            GameRegistry.registerItem(cookie, "cookie_" + name);
            cookies.put(name, cookie);
        }
    }
}