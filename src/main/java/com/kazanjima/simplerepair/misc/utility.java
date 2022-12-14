package com.kazanjima.simplerepair.misc;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class utility {
    public static boolean compare(ItemStack itemStack, ItemStack customItem){
        String itemStackName = itemStack.displayName().toString();
        String customItemName = customItem.displayName().toString();
        List<String> itemStackLore = itemStack.getLore();
        List<String> customItemLore = customItem.getLore();

        if(itemStackName.equals(customItemName)){
            assert itemStackLore != null;
            return itemStackLore.equals(customItemLore);
        }
        return false;
    }
    public static void removeItem(Player player, ItemStack itemStack, int amount){
        if(player.getInventory().contains(itemStack)){
            for(int i = 0; i < amount; i++) {
                player.getInventory().removeItem(itemStack);
            }
        }
    }
}
