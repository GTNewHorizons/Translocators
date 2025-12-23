package codechicken.translocator;

import java.io.File;

import cpw.mods.fml.common.Loader;
import net.minecraft.item.Item;

import codechicken.core.CommonUtils;
import codechicken.core.launch.CodeChickenCorePlugin;
import codechicken.lib.config.ConfigFile;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "Translocator",
        name = "Translocator",
        version = "GRADLETOKEN_VERSION",
        dependencies = "required-after:CodeChickenCore@[" + CodeChickenCorePlugin.version
                + ",);required-after:NotEnoughItems",
        acceptedMinecraftVersions = CodeChickenCorePlugin.mcVersion)
public class Translocator {

    @SidedProxy(
            clientSide = "codechicken.translocator.TranslocatorClientProxy",
            serverSide = "codechicken.translocator.TranslocatorProxy")
    public static TranslocatorProxy proxy;

    @Instance(value = "Translocator")
    public static Translocator instance;

    public static ConfigFile config;

    public static BlockTranslocator blockTranslocator;
    public static BlockCraftingGrid blockCraftingGrid;
    public static Item itemDiamondNugget;
    public static boolean disableCraftingGridKey;
    public static Boolean isGT5uLoaded = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new ConfigFile(new File(CommonUtils.getMinecraftDir() + "/config", "Translocator.cfg")).setComment(
                "Translocator Configuration File\nDeleting any element will restore it to it's default value\nBlock ID's will be automatically generated the first time it's run");
        isGT5uLoaded = Loader.isModLoaded("gregtech");
        if(isGT5uLoaded) {
            GTCompat.init();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        disableCraftingGridKey = config.getTag("disable-crafting-grid-key")
                .setComment("Set to true to disable placement of crafting grids by keyboard shortcut.")
                .getBooleanValue(false);
        proxy.init();
    }
}
