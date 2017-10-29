package net.darkhax.nec.items;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.darkhax.bookshelf.registry.IVariant;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPlayerCookie extends ItemFood implements IVariant {

    private static String[] variants = null;

    public ItemPlayerCookie () {

        super(3, false);
        this.setAlwaysEdible();
        this.setHasSubtypes(true);
    }

    @Override
    protected void onFoodEaten (ItemStack stack, World world, EntityPlayer player) {

        if (!world.isRemote) {

            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 600, 4));
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 1200, 1));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        final CookieType type = CookieType.getType(stack.getMetadata());

        tooltip.add(TextFormatting.DARK_PURPLE + "" + TextFormatting.ITALIC + I18n.format("tooltip.neverenoughcandy.playername.description", type.getName()));
    }

    @Override
    public int getMetadata (int damage) {

        return damage;
    }

    @Override
    public String[] getVariant () {

        if (variants == null) {

            variants = new String[CookieType.values().length];

            int meta = 0;

            for (final CookieType type : CookieType.values()) {

                variants[meta] = type.getName().toLowerCase();
                meta++;
            }
        }

        return variants;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems (CreativeTabs tab, NonNullList<ItemStack> subItems) {

        if (this.isInCreativeTab(tab)) {

            for (int meta = 0; meta < this.getVariant().length; meta++) {

                subItems.add(new ItemStack(this, 1, meta));
            }
        }
    }

    public enum CookieType {

        DARKHAX("Darkhax", "d183e5a2-a087-462a-963e-c3d7295f9ec5"),
        LCLC98("lclc98", "f0f76db6-0461-4151-8ba7-392d65d62ea3"),
        JAREDlll08("Jared", "3bf32666-f9ba-4060-af02-53bdb0df38fc"),
        AARON("Aaron", null),
        KIERA("Kiera", null),
        STACYPLAYS("Stacy", "1f471396-84d9-41a0-ad9b-52a722c12a6a"),
        GRASER10("Graser10", "5122efdb-aee9-4c49-bcdd-dceb168fb4c1");

        public static final List<String> KNOWN = new ArrayList<>();

        private final String name;
        private final UUID owner;

        CookieType (String name, String owner) {

            this.name = name;
            this.owner = owner != null && !owner.isEmpty() ? UUID.fromString(owner) : null;
        }

        public String getName () {

            return this.name;
        }

        public UUID getOwner () {

            return this.owner;
        }

        public static CookieType getType (int meta) {

            return CookieType.values()[meta];
        }
    }
}