package fr.xamez.cite_quests.gui;

import fr.milekat.cite_libs.utils_tools.ItemBuilder;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quest_core.utils.FastInv;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ExampleQuestGUI extends FastInv {

    public ExampleQuestGUI(Player p, Quest quest) {
        super(27, "§7Coffre mystérieux");

        setItems(getBorders(), new ItemStack(Material.SPRUCE_WOOD));
        ItemStack plan = new ItemBuilder(Material.FILLED_MAP).setName("§ePlan de la cité").toItemStack();
        setItem(13, plan, inventoryClickEvent -> {
            PlayerManager.updatePlayerStep(p.getUniqueId(), quest.getIdentifier(), 5);
            p.sendMessage("§b" + p.getName() + "§7: §f\"Il faut que je retourne apporter cette carte au Voyageur.\"");
            p.closeInventory();
            if (p.getInventory().contains(plan)){
                p.getInventory().remove(plan);
            }
        });
        open(p);
    }

    @Override
    protected void onClick(InventoryClickEvent event) { event.setCancelled(true); }
}
