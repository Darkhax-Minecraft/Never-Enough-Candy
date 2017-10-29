package net.darkhax.nec.util;

import java.util.List;

import net.darkhax.bookshelf.lib.Constants;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class Utilities {

    public static Potion getRandomPotionEffect () {

        final List<Potion> potions = ForgeRegistries.POTIONS.getValues();
        return potions.get(Constants.RANDOM.nextInt(potions.size()));
    }

    public static boolean isSameEntity (String type, EntityLivingBase entity) {

        if (entity == null) {
            return false;
        }

        final String name = EntityList.getEntityString(entity);

        if (name == null) {
            return false;
        }

        return name.equalsIgnoreCase(type);
    }
}