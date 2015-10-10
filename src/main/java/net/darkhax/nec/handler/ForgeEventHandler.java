package net.darkhax.nec.handler;

import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.darkhax.nec.items.CandyType;
import net.darkhax.nec.items.ItemBeans;
import net.darkhax.nec.items.ItemManager;
import net.darkhax.nec.items.ItemPlayerCookie;
import net.darkhax.nec.util.Utilities;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class ForgeEventHandler {
    
    @SubscribeEvent
    public void onDropsProcessed (LivingDropsEvent event) {
        
        if (Utilities.tryPercentage(0.01f))
            event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, ItemBeans.generateBean()));
        
        if (event.entityLiving instanceof EntityPlayerMP)
            for (Map.Entry<String, ItemPlayerCookie> entry : ItemManager.cookies.entrySet())
                if (((EntityPlayerMP) event.entityLiving).getCommandSenderName().equalsIgnoreCase(entry.getKey())) {
                    
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(entry.getValue())));
                    return;
                }
                    
        for (CandyType type : ItemManager.candies)
            if (type.type.equalsIgnoreCase("any") || Utilities.isSameEntity(type.type, event.entityLiving))
                if (Utilities.tryPercentage(type.odds + (0.05f * event.lootingLevel)) && type.item != null) {
                    
                    event.drops.add(new EntityItem(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(type.item)));
                    return;
                }
    }
}