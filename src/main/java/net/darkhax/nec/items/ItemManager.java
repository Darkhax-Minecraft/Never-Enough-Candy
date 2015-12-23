package net.darkhax.nec.items;

import net.darkhax.nec.handler.ConfigurationHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemManager {

    public static List<CandyType> candies = new ArrayList<CandyType>();
    public static HashMap<String, ItemPlayerCookie> cookies = new HashMap<String, ItemPlayerCookie>();

    public static ItemBeans bean = new ItemBeans();
    // public static ItemCandyApple apple = new ItemCandyApple();
    public static ItemPlayerCookie darkhax, lclc98, stacyplays, stampylonghead, subaraki, graser10, aaron, kiera;

    public ItemManager() {

        candies.add(new CandyType("batty", 4, 0.05f, "bat"));
        candies.add(new CandyType("witch", 4, 0.05f, "witch"));
        candies.add(new CandyType("spider", 4, 0.05f, "spider"));
        candies.add(new CandyType("enderpop", 4, 0.05f, "enderman"));
        candies.add(new CandyType("skull", 4, 0.05f, "skeleton", true));
        candies.add(new CandyType("corn", 4, 1.0f, "villager"));

        candies.add(new CandyType("midnight", 2, 0.05f, "any"));
        candies.add(new CandyType("halloween", 2, 0.05f, "any"));
        candies.add(new CandyType("witchy", 2, 0.05f, "any"));

        for (CandyType type : candies) {

            ConfigurationHandler.syncType(type);
            type.item = new ItemCandy(type);
            GameRegistry.registerItem(type.item, type.name);
        }

        String[] playerNames = new String[]{"darkhax", "lclc98", "stacyplays", "stampylonghead", "subaraki", "graser10", "aaron", "kiera"};
        for (String name : playerNames) {
            bakeCookies(name);
        }
        registerCookie(darkhax, "darkhax");
        registerCookie(lclc98, "lclc98");
        registerCookie(stacyplays, "stacyplays");
        registerCookie(stampylonghead, "stampylonghead");
        registerCookie(subaraki, "subaraki");
        registerCookie(graser10, "graser10");
        registerCookie(aaron, "aaron");
        registerCookie(kiera, "kiera");

        // GameRegistry.registerItem(apple, "apple");
        GameRegistry.registerItem(bean, "bean");
    }

    public static void bakeCookies(String name) {
        darkhax = new ItemPlayerCookie(name);
        lclc98 = new ItemPlayerCookie(name);
        stacyplays = new ItemPlayerCookie(name);
        stampylonghead = new ItemPlayerCookie(name);
        subaraki = new ItemPlayerCookie(name);
        graser10 = new ItemPlayerCookie(name);
        aaron = new ItemPlayerCookie(name);
        kiera = new ItemPlayerCookie(name);
    }

    public static void registerCookie(ItemPlayerCookie playerCookie, String name) {
        GameRegistry.registerItem(playerCookie, "cookie_" + name);
        cookies.put(name, playerCookie);
    }
}