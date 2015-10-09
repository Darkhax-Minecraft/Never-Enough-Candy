package net.darkhax.nec.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.darkhax.nec.util.Utilities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemCandyApple extends ItemFood {
    
    public ItemCandyApple() {
        
        super(15, 1.2F, false);
        this.setUnlocalizedName("nec.apple");
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setTextureName("nec:golden_caramel_apple");
        this.maxStackSize = 1;
        this.setAlwaysEdible();
    }
    
    @Override
    protected void onFoodEaten (ItemStack stack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 1));
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect (ItemStack par1ItemStack, int pass) {
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer reader, List tip, boolean isDebug) {
        
        Utilities.generateTooltip(StatCollector.translateToLocal("tooltip.nec.apple.description"), tip);
    }
}