package net.darkhax.neverenoughcandy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.darkhax.bookshelf.Bookshelf;
import net.darkhax.bookshelf.item.ItemGroupBase;
import net.darkhax.bookshelf.registry.RegistryHelper;
import net.darkhax.bookshelf.util.MathsUtils;
import net.darkhax.neverenoughcandy.items.ItemBeans;
import net.darkhax.neverenoughcandy.items.ItemCandy;
import net.darkhax.neverenoughcandy.items.ItemMobCandy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.WitchEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(NeverEnoughCandy.MOD_ID)
public class NeverEnoughCandy {
    
    public static final String MOD_ID = "neverenoughcandy";
    public static final Logger LOG = LogManager.getLogger("NeverEnoughCandy");
    
    private final RegistryHelper registry;
    
    private final Item candyHalloween;
    private final Item candyWitchy;
    private final Item candyMidnight;
    private final Item candyBatty;
    private final Item candyCorn;
    private final Item candyEnderpop;
    private final Item candySkull;
    private final Item candySpider;
    private final Item candyWitch;
    private final Item jellyBeans;
    private final Item candyMobs;
    private final Item[] genericCandy;
    
    public NeverEnoughCandy() {
        
        this.registry = new RegistryHelper(MOD_ID, LOG).withItemGroup(new ItemGroupBase(MOD_ID, this::getTabIcon));
        this.candyHalloween = this.makeCandy("halloween");
        this.candyWitchy = this.makeCandy("witchy");
        this.candyMidnight = this.makeCandy("midnight");
        this.candyBatty = this.makeCandy("batty");
        this.candyCorn = this.makeCandy("corn");
        this.candyEnderpop = this.makeCandy("enderpop");
        this.candySkull = this.makeCandy("skull");
        this.candySpider = this.makeCandy("spider");
        this.candyWitch = this.makeCandy("witch");
        this.jellyBeans = this.registry.items.register(new ItemBeans(), "jelly_bean");
        this.candyMobs = this.registry.items.register(new ItemMobCandy(), "mob_candy");
        this.genericCandy = new Item[] { this.candyHalloween, this.candyMidnight, this.candyWitchy };
        
        this.registry.initialize(FMLJavaModLoadingContext.get().getModEventBus());
        MinecraftForge.EVENT_BUS.addListener(this::onLivingDrops);
        MinecraftForge.EVENT_BUS.addListener(this::setupClient);
    }
    
    private void onLivingDrops (LivingDropsEvent event) {
        
        final LivingEntity entity = event.getEntityLiving();
        final float looting = event.getLootingLevel() + 1f;
        
        // Specialized Mob Candy
        if (entity instanceof BatEntity && MathsUtils.tryPercentage(0.05f * looting)) {
            
            System.out.println("hi2");
            this.addDrops(this.candyBatty, event);
        }
        
        if (entity instanceof VillagerEntity && MathsUtils.tryPercentage(0.45f * looting)) {
            
            this.addDrops(this.candyCorn, event);
        }
        
        if (entity instanceof EndermanEntity && MathsUtils.tryPercentage(0.05f * looting)) {
            
            this.addDrops(this.candyEnderpop, event);
        }
        
        if (entity instanceof AbstractSkeletonEntity && MathsUtils.tryPercentage(0.05f * looting)) {
            
            this.addDrops(this.candySkull, event);
        }
        
        if (entity instanceof SpiderEntity && MathsUtils.tryPercentage(0.05f * looting)) {
            
            this.addDrops(this.candySpider, event);
        }
        
        if (entity instanceof WitchEntity && MathsUtils.tryPercentage(0.05f * looting)) {
            
            this.addDrops(this.candyWitch, event);
        }
        
        // Generic Candy
        if (MathsUtils.tryPercentage(0.05f * looting)) {
            
            final int type = Bookshelf.RANDOM.nextInt(this.genericCandy.length);
            this.addDrops(this.genericCandy[type], event);
        }
        
        // Random Beans
        if (MathsUtils.tryPercentage(0.01 * looting)) {
            
            this.addDrops(ItemBeans.getRandomBeanColor(this.jellyBeans), event);
        }
        
        // Mob specific Candy
        if (MathsUtils.tryPercentage(0.01 * looting)) {
            
            this.addDrops(ItemMobCandy.getCandyForMob(entity.getType(), this.candyMobs), event);
        }
    }
    
    private ItemStack getTabIcon () {
        
        return new ItemStack(this.candyHalloween);
    }
    
    private Item makeCandy (String name) {
        
        return this.registry.items.register(new ItemCandy(name), "candy_" + name);
    }
    
    private void addDrops (Item item, LivingDropsEvent event) {
        
        this.addDrops(new ItemStack(item), event);
    }
    
    private void addDrops (ItemStack item, LivingDropsEvent event) {
        
        final LivingEntity living = event.getEntityLiving();
        final ItemEntity drop = new ItemEntity(living.world, living.getPosX(), living.getPosY(), living.getPosZ());
        drop.setItem(item);
        drop.setPositionAndUpdate(living.getPosX(), living.getPosY(), living.getPosZ());
        event.getDrops().add(drop);
    }
    
    private void setupClient (FMLClientSetupEvent event) {
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addItemColors);
    }
    
    private void addItemColors (ColorHandlerEvent.Item event) {
        
        event.getItemColors().register(ItemBeans::getBeanColor, this.jellyBeans);
        event.getItemColors().register(ItemMobCandy::getItemColor, this.candyMobs);
    }
}