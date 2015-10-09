package net.darkhax.nec.util;

import java.util.List;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;

import net.minecraft.util.EnumChatFormatting;

public class Utilities {
    
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