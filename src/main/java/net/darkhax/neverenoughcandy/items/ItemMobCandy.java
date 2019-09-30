package net.darkhax.neverenoughcandy.items;

import net.darkhax.bookshelf.util.StackUtils;
import net.darkhax.neverenoughcandy.NeverEnoughCandy;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ItemMobCandy extends ItemFood {
	
    public ItemMobCandy () {

        super(2, 1, false);
        this.setPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1), 1.0f);
        this.setAlwaysEdible();
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    	
        if (this.isInCreativeTab(tab)) {
        	
        	for (EntityEggInfo info : EntityList.ENTITY_EGGS.values()) {
        		
        		items.add(getCandyForMob(info));
        	}
        }
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String s = ("" + I18n.format(this.getTranslationKey() + ".name")).trim();
        String s1 = EntityList.getTranslationName(getMobInfo(stack));

        if (s1 != null)
        {
            s = I18n.format("entity." + s1 + ".name") + " " + s;
        }

        return s;
    }
    
    public static ItemStack getCandyForMob(EntityEggInfo info) {
    			
		final ItemStack bean = new ItemStack(NeverEnoughCandy.itemMobCandy);
		
		NBTTagCompound itemTag = StackUtils.prepareStackTag(bean);
		itemTag.setString("mob_id", info.spawnedID.toString());		
		return bean;
    }
    
    public static ResourceLocation getMobInfo(ItemStack stack) {
    	
    	NBTTagCompound itemTag = StackUtils.prepareStackTag(stack);
    	
    	if (itemTag.hasKey("mob_id")) {
    		
    		EntityEggInfo info = EntityList.ENTITY_EGGS.get(new ResourceLocation(itemTag.getString("mob_id")));
    		
    		if (info != null) {
    			
    			return info.spawnedID;
    		}
    	}
    	
    	return null;
    }
    
    public static int getItemColor(ItemStack item, boolean overlay) {
    	
    	NBTTagCompound itemTag = StackUtils.prepareStackTag(item);
    	
    	if (itemTag.hasKey("mob_id")) {
    		
    		EntityEggInfo info = EntityList.ENTITY_EGGS.get(new ResourceLocation(itemTag.getString("mob_id")));
    		
    		if (info != null) {
    			
    			return overlay ? info.primaryColor : info.secondaryColor;
    		}
    	}
    	
    	return 0xb00;
    }
}