package codechicken.translocator;

import static codechicken.translocator.Translocator.*;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

import codechicken.core.CCUpdateChecker;
import codechicken.core.ClientUtils;
import codechicken.lib.packet.PacketCustom;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;

public class TranslocatorClientProxy extends TranslocatorProxy {

    public void init() {
        if (config.getTag("checkUpdates").getBooleanValue(true)) {
            CCUpdateChecker.updateCheck("Translocator");
        }
        ClientUtils.enhanceSupportersList("Translocator");
        super.init();
        ClientRegistry.bindTileEntitySpecialRenderer(TileItemTranslocator.class, new TileTranslocatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLiquidTranslocator.class, new TileTranslocatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCraftingGrid.class, new TileCraftingGridRenderer());
        PacketCustom.assignHandler(TranslocatorCPH.channel, new TranslocatorCPH());
        MinecraftForgeClient
                .registerItemRenderer(Item.getItemFromBlock(blockTranslocator), new ItemTranslocatorRenderer());
        if (!disableCraftingGridKey) {
            FMLCommonHandler.instance().bus().register(CraftingGridKeyHandler.instance);
            ClientRegistry.registerKeyBinding(CraftingGridKeyHandler.instance);
        }
    }

}
