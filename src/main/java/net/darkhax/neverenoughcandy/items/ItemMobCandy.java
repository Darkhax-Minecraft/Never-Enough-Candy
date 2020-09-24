package net.darkhax.neverenoughcandy.items;

import java.util.List;

import net.darkhax.bookshelf.util.EntityUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemMobCandy extends Item {
    
    private static final EffectInstance effect = new EffectInstance(Effects.SPEED, 100, 1);
    private static final Food foodStats = new Food.Builder().fastToEat().hunger(3).saturation(1f).setAlwaysEdible().effect( () -> effect, 1f).build();
    private static final Properties props = new Item.Properties().rarity(Rarity.RARE).food(foodStats).maxStackSize(16);
    
    public ItemMobCandy() {
        
        super(props);
    }
    
    @Override
    public void fillItemGroup (ItemGroup group, NonNullList<ItemStack> items) {
        
        if (this.isInGroup(group)) {
            
            for (final EntityType<?> info : ForgeRegistries.ENTITIES) {
                
                if (SpawnEggItem.getEgg(info) != null) {
                    
                    items.add(getCandyForMob(info, this));
                }
            }
        }
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation (ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flag) {
        
        final CompoundNBT itemTag = stack.getTag();
        
        if (itemTag != null && itemTag.contains("mob_id")) {
            
            final EntityType<?> info = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(itemTag.getString("mob_id")));
            
            if (info != null) {
                
                ITextComponent entName = info.getName();
                
                if (entName instanceof IFormattableTextComponent) {
                    
                    entName = ((IFormattableTextComponent) entName).func_240699_a_(TextFormatting.GOLD);
                }
                
                tooltips.add(new TranslationTextComponent("tooltip.neverenoughcandy.mob.desc", entName));
            }
        }
    }
    
    public static ItemStack getCandyForMob (EntityType<?> info, Item item) {
        
        final ItemStack bean = new ItemStack(item);
        final CompoundNBT itemTag = bean.getOrCreateTag();
        itemTag.putString("mob_id", info.getRegistryName().toString());
        return bean;
    }
    
    public static int getItemColor (ItemStack item, int overlay) {
        
        final CompoundNBT itemTag = item.getTag();
        
        if (itemTag != null && itemTag.contains("mob_id")) {
            
            final EntityType<?> info = ForgeRegistries.ENTITIES.getValue(new ResourceLocation(itemTag.getString("mob_id")));
            
            if (info != null) {
                
                final Tuple<Integer, Integer> colors = EntityUtils.getEggColors(info);
                return overlay == 0 ? colors.getA() : colors.getB();
            }
        }
        
        return 0xb00;
    }
}