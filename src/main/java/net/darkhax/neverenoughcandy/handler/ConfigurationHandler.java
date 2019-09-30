package net.darkhax.neverenoughcandy.handler;

import java.io.File;

import net.darkhax.neverenoughcandy.items.ItemCandy.CandyType;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler {

    public static Configuration config;

    public ConfigurationHandler (File configFile) {

        config = new Configuration(configFile);
    }

    public static void save () {

        for (final CandyType type : CandyType.values()) {

            type.setChance(config.getFloat("chance_" + type.getName(), "general", type.getChance(), 0f, 1f, "The chance that this candy will be dropped by it's mob."));
        }

        if (config.hasChanged()) {

            config.save();
        }
    }
}