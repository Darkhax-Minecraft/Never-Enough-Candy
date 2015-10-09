package net.darkhax.nec.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.darkhax.nec.items.CandyType;
import net.darkhax.nec.items.ItemManager;
import net.darkhax.nec.util.Utilities;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class DropHandler {
    
    @SubscribeEvent
    public void onDropsProcessed (LivingDropsEvent event) {
        
        for (CandyType type : ItemManager.candies) {
            
            if (event.entityLiving.getClass().isAssignableFrom(type.entityType)) {
                
                if (Utilities.tryPercentage(type.odds) && type.item != null)
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(type.item)));
            }
        }
    }
}