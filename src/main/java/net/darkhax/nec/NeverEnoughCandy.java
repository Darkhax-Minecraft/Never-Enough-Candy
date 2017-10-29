package net.darkhax.nec;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.MathsUtils;
import net.darkhax.nec.handler.ConfigurationHandler;
import net.darkhax.nec.items.ItemBeans;
import net.darkhax.nec.items.ItemCandy;
import net.darkhax.nec.items.ItemCandy.CandyType;
import net.darkhax.nec.items.ItemPlayerCookie;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = "neverenoughcandy", name = "Never Enough Candy", version = "@VERSION@", dependencies = "required-after:bookshelf", certificateFingerprint = "@FINGERPRINT@")
public class NeverEnoughCandy {

    public static RegistryHelper REGISTRY = new RegistryHelper("neverenoughcandy").setTab(CreativeTabs.FOOD).enableAutoRegistration();

    public static Item itemBeans;
    public static Item itemCandy;
    public static Item itemCookie;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        new ConfigurationHandler(event.getSuggestedConfigurationFile());

        itemBeans = REGISTRY.registerItem(new ItemBeans(), "magic_bean");
        itemCandy = REGISTRY.registerItem(new ItemCandy(), "candy");
        itemCookie = REGISTRY.registerItem(new ItemPlayerCookie(), "cookie");

        MinecraftForge.EVENT_BUS.register(this);

        ConfigurationHandler.save();
    }

    @SubscribeEvent
    public void onLivingDrops (LivingDropsEvent event) {

        for (final CandyType candy : CandyType.values()) {

            if (candy.getCheck().isValidMob(event.getEntity()) && MathsUtils.tryPercentage(candy.getChance())) {

                this.addCandy(event, candy.ordinal());
                return;
            }
        }
    }

    private void addCandy (LivingDropsEvent event, int meta) {

        event.getDrops().add(this.getItem(event.getEntity(), new ItemStack(itemCandy, 1, meta)));
    }

    private EntityItem getItem (Entity entity, ItemStack stack) {

        return new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ, stack);
    }
}