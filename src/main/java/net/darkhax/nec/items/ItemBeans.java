package net.darkhax.nec.items;

import java.awt.Color;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.darkhax.nec.util.Utilities;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemBeans extends ItemFood {
    
    public static IIcon[] textures = new IIcon[2];
    
    public ItemBeans() {
        
        super(3, false);
        this.setUnlocalizedName("nec.bean");
        this.setCreativeTab(CreativeTabs.tabFood);
        this.setAlwaysEdible();
        this.setHasSubtypes(true);
    }
    
    @Override
    protected void onFoodEaten (ItemStack stack, World world, EntityPlayer player) {
        
        if (!world.isRemote) {
            
            Potion potion = Utilities.getRandomPotionEffect();
            player.addPotionEffect(new PotionEffect(potion.id, (potion.id == Potion.harm.id || potion.id == Potion.heal.id) ? 5 : 640));
            
            if (potion.isBadEffect())
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + StatCollector.translateToLocal("chat.nec.bad")));
                
            else
                player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + StatCollector.translateToLocal("chat.nec.good")));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack (ItemStack stack, int pass) {
        
        if (stack.hasTagCompound()) {
            
            NBTTagCompound tag = stack.getTagCompound();
            
            if (pass == 0 && tag.hasKey("colorBase"))
                return tag.getInteger("colorBase");
                
            if (pass == 1 && tag.hasKey("colorSpeckle"))
                return tag.getInteger("colorSpeckle");
        }
        
        return (pass == 0) ? Color.orange.getRGB() : Color.black.getRGB();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamageForRenderPass (int meta, int pass) {
        
        return (pass == 0) ? this.textures[0] : this.textures[1];
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses () {
        
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons (IIconRegister ir) {
        
        this.textures[0] = ir.registerIcon("nec:bean");
        this.textures[1] = ir.registerIcon("nec:bean_overlay");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems (Item item, CreativeTabs tab, List itemList) {
        
        for (int i = 0; i < 8; i++)
            itemList.add(generateBean());
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, EntityPlayer reader, List tip, boolean isDebug) {
        
        Utilities.generateTooltip(StatCollector.translateToLocal("tooltip.nec.bean.description"), tip);
    }
    
    public static ItemStack generateBean () {
        
        ItemStack bean = new ItemStack(ItemManager.bean);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setInteger("colorBase", Utilities.getRandomColor());
        tag.setInteger("colorSpeckle", Utilities.getRandomColor());
        bean.setTagCompound(tag);
        return bean;
    }
}
