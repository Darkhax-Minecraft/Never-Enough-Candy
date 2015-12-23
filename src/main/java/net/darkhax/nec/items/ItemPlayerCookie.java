package net.darkhax.nec.items;

import net.darkhax.nec.util.Utilities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemPlayerCookie extends ItemFood {
    
    public String name;
    
    public ItemPlayerCookie(String name) {
        
        super(3, false);
        this.name = name;
        this.setUnlocalizedName("nec.cookie");
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setAlwaysEdible();
    }
    
    @Override
    protected void onFoodEaten (ItemStack stack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 1200, 1));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer reader, List tip, boolean isDebug) {
        
        Utilities.generateTooltip(StatCollector.translateToLocal("tooltip.nec.playercookie.description") + " " + this.name, tip);
    }
}