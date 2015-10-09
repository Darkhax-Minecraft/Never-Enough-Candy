package net.darkhax.nec.util;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
    
    public static final String MOD_ID = "nec";
    public static final String MOD_NAME = "Never Enough Candy";
    public static final String VERSION_NUMBER = "1.0.0";
    public static final String CLIENT_PROXY_CLASS = "net.darkhax.nec.client.ProxyClient";
    public static final String SERVER_PROXY_CLASS = "net.darkhax.nec.common.ProxyCommon";
    public static final String FACTORY = "net.darkhax.nec.client.gui.GuiFactoryNEC";
    
    public static final Random RANDOM = new Random();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);
}
