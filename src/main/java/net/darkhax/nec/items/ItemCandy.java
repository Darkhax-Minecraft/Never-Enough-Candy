package net.darkhax.nec.items;

import java.util.List;

import net.darkhax.bookshelf.registry.IVariant;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.AbstractSkeleton;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCandy extends ItemFood implements IVariant {

    private static String[] variants = null;

    public ItemCandy () {

        super(2, 1, false);
        this.setPotionEffect(new PotionEffect(MobEffects.SPEED, 100, 1), 1.0f);
        this.setAlwaysEdible();
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata (int damage) {

        return damage;
    }

    @Override
    public String getUnlocalizedName (ItemStack stack) {

        final int meta = stack.getMetadata();
        final String[] variants = this.getVariant();
        return (super.getUnlocalizedName() + "." + this.getPrefix() + (!(meta >= 0 && meta < variants.length) ? variants[0] : variants[meta])).replace("_", ".");
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

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation (ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

        tooltip.add(TextFormatting.DARK_PURPLE + "" + TextFormatting.ITALIC + I18n.format("tooltip.neverenoughcandy." + ItemCandy.variants[stack.getMetadata()] + ".description"));
    }

    @Override
    public String[] getVariant () {

        if (variants == null) {

            variants = new String[CandyType.values().length];

            int meta = 0;

            for (final CandyType type : CandyType.values()) {

                variants[meta] = type.getName().toLowerCase();
                meta++;
            }
        }

        return variants;
    }

    public enum CandyType {

        HALLOWEEN("halloween", (entity) -> {
            return true;
        }),
        WITCHY("witchy", (entity) -> {
            return true;
        }),
        MIDNIGHT("midnight", (entity) -> {
            return true;
        }),
        BATTY("batty", (entity) -> {
            return entity instanceof EntityBat;
        }),
        CORN("corn", 1f, (entity) -> {
            return entity instanceof EntityVillager;
        }),
        ENDERPOP("enderpop", (entity) -> {
            return entity instanceof EntityEnderman;
        }),
        SKULL("skull", (entity) -> {
            return entity instanceof AbstractSkeleton;
        }),
        SPIDER("spider", (entity) -> {
            return entity instanceof EntitySpider;
        }),
        WITCH("witch", (entity) -> {
            return entity instanceof EntityWitch;
        });

        public static int metaTicker = 0;

        private String name;
        private float chance;
        private ICheck check;

        CandyType (String name, ICheck check) {

            this(name, 0.05f, check);
        }

        CandyType (String name, float amount, ICheck check) {

            this.name = name;
            this.check = check;
            this.chance = amount;
        }

        public interface ICheck {

            boolean isValidMob (Entity entity);
        }

        public String getName () {

            return this.name;
        }

        public void setChance (float chance) {

            this.chance = chance;
        }

        public float getChance () {

            return this.chance;
        }

        public ICheck getCheck () {

            return this.check;
        }
    }
}
