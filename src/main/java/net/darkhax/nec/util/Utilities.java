package net.darkhax.nec.util;

import java.awt.Color;
import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;

import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumChatFormatting;

public class Utilities {
    
    public static final int POTION_COUNT = Potion.potionTypes.length;
    
    public static Potion getRandomPotionEffect () {
        
        Potion potion = null;
        
        while (potion == null)
            potion = Potion.potionTypes[Constants.RANDOM.nextInt(POTION_COUNT)];
            
        return potion;
    }
    
    public static int getRandomColor () {
        
        Color color = new Color(Constants.RANDOM.nextFloat(), Constants.RANDOM.nextFloat(), Constants.RANDOM.nextFloat());
        return color.getRGB();
    }
    
    public static boolean isSameEntity (String type, EntityLivingBase entity) {
        
        return (EntityList.getEntityString(entity).equalsIgnoreCase(type));
    }
    
    public static List<String> generateTooltip (String string, List<String> list) {
        
        String[] entries = WordUtils.wrap(string, 45).split(SystemUtils.LINE_SEPARATOR);
        
        for (String entry : entries)
            list.add(EnumChatFormatting.DARK_PURPLE + entry);
            
        return list;
    }
    
    /**
     * A method which handles the calculating of percentages. While this isn't a particularly
     * difficult piece of code, it has been added for the sake of simplicity.
     * 
     * @param percent: The percent chance that this method should return true. 1.00 = 100% 0.00
     *            = 0%
     * @return boolean: Returns are randomly true or false, based on the suplied percentage.
     */
    public static boolean tryPercentage (double percent) {
        
        return Math.random() < percent;
    }
}