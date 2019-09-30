package net.darkhax.neverenoughcandy.items;

import java.awt.Color;
import java.util.List;

import net.darkhax.bookshelf.lib.Constants;
import net.darkhax.bookshelf.util.StackUtils;
import net.darkhax.neverenoughcandy.NeverEnoughCandy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ItemBeans extends ItemFood {

    public ItemBeans () {

        super(3, false);
        this.setAlwaysEdible();
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    @Override
    protected void onFoodEaten (ItemStack stack, World world, EntityPlayer player) {

        if (!world.isRemote) {

            final Potion potion = getRandomPotionEffect();
            player.addPotionEffect(new PotionEffect(potion, potion.isInstant() ? 5 : 640));
            player.sendMessage(new TextComponentTranslation("chat.neverenoughcandy.effect." + (potion.isBadEffect() ? "bad" : "good"), potion.isBadEffect() ? TextFormatting.RED : TextFormatting.GREEN));
        }
    }
    
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
    	
        if (this.isInCreativeTab(tab)) {
        	
        	for (int i = 0; i < 16; i++) {

        		
        		items.add(this.getRandomBeanColor());
        	}
        }
    }
    
    public static ItemStack getRandomBeanColor() {
    			
		final ItemStack bean = new ItemStack(NeverEnoughCandy.itemBeans);
		
		NBTTagCompound itemTag = StackUtils.prepareStackTag(bean);
		itemTag.setInteger("BeanColorA", getRandomColor());
		itemTag.setInteger("BeanColorB", getRandomColor());
		
		return bean;
    }
    
    private static int getRandomColor() {
    	
    	final float hue = Constants.RANDOM.nextFloat();
    	final float saturation = 0.9f;
    	final float luminance = 1.0f;
    	return Color.getHSBColor(hue, saturation, luminance).getRGB();
    }
    
    public static int getBeanColor(ItemStack item, boolean overlay) {
    	
    	NBTTagCompound itemTag = StackUtils.prepareStackTag(item);
    	return itemTag.getInteger(overlay ? "BeanColorB" : "BeanColorA");
    }
    
    public static Potion getRandomPotionEffect () {

        final List<Potion> potions = ForgeRegistries.POTIONS.getValues();
        return potions.get(Constants.RANDOM.nextInt(potions.size()));
    }
}