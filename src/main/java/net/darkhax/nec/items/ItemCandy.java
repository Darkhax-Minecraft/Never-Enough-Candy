package net.darkhax.nec.items;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;

public class ItemCandy extends ItemFood {
    
    private CandyType type;
    
    public ItemCandy(CandyType type) {
        
        super(type.food, type.doesWolfLike);
        this.type = type;
        this.setUnlocalizedName("nec." + type.name);
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setTextureName("nec:candy_" + type.name);
        this.setPotionEffect(Potion.moveSpeed.getId(), 100, 1, 1.0f);
        this.setAlwaysEdible();
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer reader, List tip, boolean isDebug) {
        
        tip.add(StatCollector.translateToLocal("tooltip.nec." + type.name + ".description"));
    }
}
