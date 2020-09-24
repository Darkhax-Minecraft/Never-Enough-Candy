package net.darkhax.neverenoughcandy.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCandy extends Item {
    
    private static final EffectInstance effect = new EffectInstance(Effects.SPEED, 100, 1);
    private static final Food foodStats = new Food.Builder().fastToEat().hunger(1).saturation(1f).setAlwaysEdible().effect( () -> effect, 1f).build();
    private static final Properties props = new Item.Properties().rarity(Rarity.UNCOMMON).food(foodStats).maxStackSize(16);
    
    private final ITextComponent tooltip;
    
    public ItemCandy(String type) {
        
        super(props);
        this.tooltip = new TranslationTextComponent("tooltip.neverenoughcandy." + type + ".description").func_240701_a_(TextFormatting.DARK_PURPLE);
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation (ItemStack stack, World world, List<ITextComponent> tooltips, ITooltipFlag flag) {
        
        tooltips.add(this.tooltip);
    }
}