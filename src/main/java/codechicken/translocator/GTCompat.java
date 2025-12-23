package codechicken.translocator;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;

import gregtech.api.enums.ItemList;

public class GTCompat {

    public static Map<ItemStack, Integer> pumpCoverRates;

    public static void init() {
        pumpCoverRates = new HashMap<>();
        pumpCoverRates.put(ItemList.Electric_Pump_LV.get(1), 32); // LV
        pumpCoverRates.put(ItemList.Electric_Pump_MV.get(1), 128); // MV
        pumpCoverRates.put(ItemList.Electric_Pump_HV.get(1), 512); // HV
        pumpCoverRates.put(ItemList.Electric_Pump_EV.get(1), 2048); // EV
        pumpCoverRates.put(ItemList.Electric_Pump_IV.get(1), 8192); // IV
        pumpCoverRates.put(ItemList.Electric_Pump_LuV.get(1), 32768); // LuV
        pumpCoverRates.put(ItemList.Electric_Pump_ZPM.get(1), 131072); // ZPM
        pumpCoverRates.put(ItemList.Electric_Pump_UV.get(1), 524288); // UV
        pumpCoverRates.put(ItemList.Electric_Pump_UHV.get(1), 1048576); // UHV
        pumpCoverRates.put(ItemList.Electric_Pump_UEV.get(1), 2097152); // UEV
        pumpCoverRates.put(ItemList.Electric_Pump_UIV.get(1), 4194304); // UIV
        pumpCoverRates.put(ItemList.Electric_Pump_UMV.get(1), 8388608); // UMV
        pumpCoverRates.put(ItemList.Electric_Pump_UXV.get(1), 16777216); // UXV
        pumpCoverRates.put(ItemList.Electric_Pump_MAX.get(1), Integer.MAX_VALUE); // UXV
    }

    public static boolean isPumpCover(ItemStack stack) {
        if (stack == null) return false;
        for (ItemStack pumpCover : pumpCoverRates.keySet()) {
            if (stack.isItemEqual(pumpCover)) return true;
        }
        return false;
    }

    public static int getPumpCoverRate(ItemStack stack) {
        if (stack == null) return 0;
        for (Map.Entry<ItemStack, Integer> entry : pumpCoverRates.entrySet()) {
            if (stack.isItemEqual(entry.getKey())) return entry.getValue();
        }
        return 0;
    }

    public static ItemStack getItem(int fastFluidRate) {
        for (Map.Entry<ItemStack, Integer> entry : pumpCoverRates.entrySet()) {
            if (entry.getValue() == fastFluidRate) return entry.getKey();
        }
        return null;
    }
}
