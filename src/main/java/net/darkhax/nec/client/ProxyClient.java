package net.darkhax.nec.client;

import net.darkhax.nec.common.ProxyCommon;
import net.darkhax.nec.items.CandyType;
import net.darkhax.nec.items.ItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ProxyClient extends ProxyCommon {

    @Override
    @SideOnly(Side.CLIENT)
    public void registerRenders() {
        registerItemRender(ItemManager.darkhax);
        registerItemRender(ItemManager.lclc98);
        registerItemRender(ItemManager.stacyplays);
        registerItemRender(ItemManager.stampylonghead);
        registerItemRender(ItemManager.subaraki);
        registerItemRender(ItemManager.graser10);
        registerItemRender(ItemManager.aaron);
        registerItemRender(ItemManager.kiera);
        registerItemRender(ItemManager.bean);

        for (CandyType type : ItemManager.candies) {
            registerItemRender(type.item);
        }
    }

    public static void registerItemRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(GameData.getItemRegistry().getNameForObject(item), "inventory"));
    }
}