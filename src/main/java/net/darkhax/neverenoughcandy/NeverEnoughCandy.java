package net.darkhax.neverenoughcandy;

import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.EntityUtils;
import net.darkhax.bookshelf.util.MathsUtils;
import net.darkhax.neverenoughcandy.handler.ConfigurationHandler;
import net.darkhax.neverenoughcandy.items.ItemBeans;
import net.darkhax.neverenoughcandy.items.ItemCandy;
import net.darkhax.neverenoughcandy.items.ItemCandy.CandyType;
import net.darkhax.neverenoughcandy.items.ItemMobCandy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityList.EntityEggInfo;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "neverenoughcandy", name = "Never Enough Candy", version = "@VERSION@", dependencies = "required-after:bookshelf", certificateFingerprint = "@FINGERPRINT@")
@EventBusSubscriber(modid = "neverenoughcandy")
public class NeverEnoughCandy {

	public static CreativeTabs tab = new CreativeTabs("neverenoughcandy") {

		@Override
		public ItemStack createIcon() {
			
			return new ItemStack(itemCandy, 1, 4);
		}
	};
	
    public static RegistryHelper REGISTRY = new RegistryHelper("neverenoughcandy").setTab(tab).enableAutoRegistration();

    public static Item itemBeans;
    private static Item itemCandy;
    public static Item itemMobCandy;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        new ConfigurationHandler(event.getSuggestedConfigurationFile());
        itemCandy = REGISTRY.registerItem(new ItemCandy(), "candy");
        itemBeans = REGISTRY.registerItem(new ItemBeans(), "magic_bean");
        itemMobCandy = REGISTRY.registerItem(new ItemMobCandy(), "mob_candy");
        ConfigurationHandler.save();
    }

    @SubscribeEvent
    public static void onLivingDrops (LivingDropsEvent event) {

        for (final CandyType candy : CandyType.values()) {

            if (candy.getCheck().isValidMob(event.getEntity()) && MathsUtils.tryPercentage(candy.getChance())) {

                EntityUtils.addDrop(new ItemStack(itemCandy, 1, candy.ordinal()), event);
                return;
            }
        }
        
        if (MathsUtils.tryPercentage(0.01)) {
        	
        	EntityUtils.addDrop(ItemBeans.getRandomBeanColor(), event);
        }
        
        else if (MathsUtils.tryPercentage(0.01)){
        	
        	final EntityEggInfo info = EntityList.ENTITY_EGGS.get(EntityList.getKey(event.getEntity()));
        	
        	if (info != null) {
        		
        		EntityUtils.addDrop(ItemMobCandy.getCandyForMob(info), event);
        	}
        }
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void addItemColors(ColorHandlerEvent.Item event) {
    	
    	event.getItemColors().registerItemColorHandler( (stack, index) -> ItemBeans.getBeanColor(stack, index == 1), itemBeans);
    	event.getItemColors().registerItemColorHandler( (stack, index) -> ItemMobCandy.getItemColor(stack, index == 0), itemMobCandy);
    }
}