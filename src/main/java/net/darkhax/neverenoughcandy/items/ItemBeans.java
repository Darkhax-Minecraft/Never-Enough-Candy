package net.darkhax.neverenoughcandy.items;

import java.awt.Color;
import java.util.Collection;

import net.darkhax.bookshelf.Bookshelf;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemBeans extends Item {
    
    private static final Food foodStats = new Food.Builder().fastToEat().setAlwaysEdible().hunger(3).saturation(1f).effect(ItemBeans::getRandomEffect, 1f).build();
    private static final Properties props = new Item.Properties().rarity(Rarity.EPIC).food(foodStats).maxStackSize(1);
    
    private static EffectInstance getRandomEffect () {
        
        final Effect effect = random(ForgeRegistries.POTIONS.getValues());
        return new EffectInstance(effect, effect.isInstant() ? 5 : 300);
    }
    
    public static <T> T random (Collection<T> coll) {
        
        int num = (int) (Math.random() * coll.size());
        for (final T t : coll) {
            if (--num < 0) {
                return t;
            }
        }
        throw new AssertionError();
    }
    
    public ItemBeans() {
        
        super(props);
    }
    
    @Override
    public void fillItemGroup (ItemGroup group, NonNullList<ItemStack> items) {
        
        if (this.isInGroup(group)) {
            
            for (int i = 0; i < 16; i++) {
                items.add(getRandomBeanColor(this));
            }
        }
    }
    
    public static ItemStack getRandomBeanColor (Item item) {
        
        final ItemStack bean = new ItemStack(item);
        final CompoundNBT tag = bean.getOrCreateTag();
        
        tag.putInt("BeanColorA", getRandomColor());
        tag.putInt("BeanColorB", getRandomColor());
        
        return bean;
    }
    
    private static int getRandomColor () {
        
        final float hue = Bookshelf.RANDOM.nextFloat();
        final float saturation = 0.9f;
        final float luminance = 1.0f;
        return Color.getHSBColor(hue, saturation, luminance).getRGB();
    }
    
    public static int getBeanColor (ItemStack item, int index) {
        
        final CompoundNBT itemTag = item.getTag();
        return itemTag.getInt(index == 1 ? "BeanColorB" : "BeanColorA");
    }
    
    public static int HSBtoRGB (float hue, float saturation, float brightness) {
        
        int r = 0, g = 0, b = 0;
        if (saturation == 0) {
            r = g = b = (int) (brightness * 255.0f + 0.5f);
        }
        else {
            final float h = (hue - (float) Math.floor(hue)) * 6.0f;
            final float f = h - (float) java.lang.Math.floor(h);
            final float p = brightness * (1.0f - saturation);
            final float q = brightness * (1.0f - saturation * f);
            final float t = brightness * (1.0f - saturation * (1.0f - f));
            switch ((int) h) {
                case 0:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (t * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 1:
                    r = (int) (q * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (p * 255.0f + 0.5f);
                    break;
                case 2:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (brightness * 255.0f + 0.5f);
                    b = (int) (t * 255.0f + 0.5f);
                    break;
                case 3:
                    r = (int) (p * 255.0f + 0.5f);
                    g = (int) (q * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 4:
                    r = (int) (t * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (brightness * 255.0f + 0.5f);
                    break;
                case 5:
                    r = (int) (brightness * 255.0f + 0.5f);
                    g = (int) (p * 255.0f + 0.5f);
                    b = (int) (q * 255.0f + 0.5f);
                    break;
            }
        }
        return 0xff000000 | r << 16 | g << 8 | b << 0;
    }
}