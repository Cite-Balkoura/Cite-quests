package fr.xamez.cite_quests.gui;

import fr.milekat.cite_libs.utils_tools.ItemBuilder;
import fr.mrmicky.fastinv.FastInv;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class OnePeaceQuestGUI extends FastInv {

    public OnePeaceQuestGUI(Player p, Quest quest) {
        super(InventoryType.DISPENSER, "§7Pochette secrète");
        setItems(getBorders(), new ItemStack(Material.WHITE_STAINED_GLASS_PANE));
        ItemStack totem = new ItemBuilder(Material.TOTEM_OF_UNDYING).setName("§eLe One Peace").toItemStack();
        setItem(13, totem, inventoryClickEvent -> {
            PlayerManager.updatePlayerStep(p.getUniqueId(), quest.getIdentifier(), 3);
            p.sendMessage("§b" + p.getName() + "§7: §f\"Il faut que je ramène cet objet à Phifi pour analyse, c’est probablement le trésor dont il m’a parlé !\"");
            p.closeInventory();
            p.getInventory().remove(totem);
            p.getInventory().addItem(totem);
        });
        open(p);
    }

    @Override
    protected void onClick(InventoryClickEvent event) { event.setCancelled(true); }

}
