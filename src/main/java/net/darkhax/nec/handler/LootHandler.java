package net.darkhax.nec.handler;

import java.util.Map;

import net.darkhax.nec.items.CandyType;
import net.darkhax.nec.items.ItemManager;
import net.darkhax.nec.items.ItemPlayerCookie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

public class LootHandler {
    
    public LootHandler() {
        
        String[] locations = new String[] { "mineshaftCorridor", "pyramidDesertyChest", "pyramidJungleChest", "pyramidJungleDispenser", "strongholdCorridor", "strongholdLibrary", "strongholdCrossing", "villageBlacksmith", "bonusChest", "dungeonChest" };
        
        for (String location : locations) {
            
            for (CandyType type : ItemManager.candies)
                ChestGenHooks.addItem(location, new WeightedRandomChestContent(new ItemStack(type.item), 5, 1, 3));
                
            for (Map.Entry<String, ItemPlayerCookie> entry : ItemManager.cookies.entrySet())
                ChestGenHooks.addItem(location, new WeightedRandomChestContent(new ItemStack(entry.getValue()), 2, 1, 1));
        }
    }
}
