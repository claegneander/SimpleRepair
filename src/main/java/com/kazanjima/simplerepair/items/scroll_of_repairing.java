package com.kazanjima.simplerepair.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class scroll_of_repairing {

    public static ItemStack get(){
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null) {
            itemMeta.setDisplayName(ChatColor.AQUA + "Scroll of Repairing");

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.BLUE + "The spell bound within this");
            lore.add(ChatColor.BLUE + "page can repair any item.");
            lore.add(ChatColor.BLUE + "Action: [RIGHT CLICK]");
            itemMeta.setLore(lore);

            itemMeta.addEnchant(Enchantment.DURABILITY, 1, true);

            itemStack.setItemMeta(itemMeta);
        }
        return itemStack;
    }
}
