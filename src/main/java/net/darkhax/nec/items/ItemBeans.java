package net.darkhax.nec.items;

import net.darkhax.nec.util.Utilities;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBeans extends ItemFood {

    public ItemBeans () {

        super(3, false);
        this.setAlwaysEdible();
        this.setHasSubtypes(true);
    }

    @Override
    protected void onFoodEaten (ItemStack stack, World world, EntityPlayer player) {

        if (!world.isRemote) {

            final Potion potion = Utilities.getRandomPotionEffect();
            player.addPotionEffect(new PotionEffect(potion, potion.isInstant() ? 5 : 640));
            player.sendMessage(new TextComponentTranslation("chat.neverenoughcandy.effect." + (potion.isBadEffect() ? "bad" : "good"), potion.isBadEffect() ? TextFormatting.RED : TextFormatting.GREEN));
        }
    }
}
