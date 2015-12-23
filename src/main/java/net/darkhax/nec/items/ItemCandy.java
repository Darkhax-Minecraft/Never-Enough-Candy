package net.darkhax.nec.items;

import net.darkhax.nec.util.Utilities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemCandy extends ItemFood {
    
    private CandyType type;
    
    public ItemCandy(CandyType type) {
        
        super(type.food, type.doesWolfLike);
        this.type = type;
        this.setUnlocalizedName("nec." + type.name);
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setPotionEffect(Potion.moveSpeed.getId(), 100, 1, 1.0f);
        this.setAlwaysEdible();
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer reader, List tip, boolean isDebug) {
        
        Utilities.generateTooltip(StatCollector.translateToLocal("tooltip.nec." + type.name + ".description"), tip);
    }
}
