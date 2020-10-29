package fr.xamez.cite_quests.events;

import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.managers.QuestManager;
import fr.xamez.cite_quests.CiteQuests;
import fr.xamez.cite_quests.gui.ExampleQuestGUI;
import fr.xamez.cite_quests.quests.ExampleQuest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class QuestEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e){

        Player p = e.getPlayer();
        Block block = e.getClickedBlock();
        if (block == null) { return; }

        if (block.getType().equals(Material.CHEST)){
            if (block.getLocation().equals(new Location(Bukkit.getWorld("world"), -1, 155, 17))) {
                e.setCancelled(true);
                if (Manager.playerQuests.get(p.getUniqueId()).get("exampleQuest") != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get("exampleQuest") == 1) {
                        p.sendMessage("§oLe coffre est verrouillé, une clé est forcément cachée quelque part !");
                        PlayerManager.updatePlayerStep(p.getUniqueId(), "exampleQuest", 2);
                    } else if (Manager.playerQuests.get(p.getUniqueId()).get("exampleQuest") == 4) {
                        p.closeInventory();
                        new ExampleQuestGUI(p, ((ExampleQuest) CiteQuests.getQuestRegistration().getQuestList().get("exampleQuest")).getQuest());
                    }
                }
            }
        }

        if (block.getType().equals(Material.BROWN_CARPET)){
            if (block.getLocation().equals(new Location(Bukkit.getWorld("world"), -3, 155, 16))) {
                if (Manager.playerQuests.get(p.getUniqueId()).get("exampleQuest") != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get("exampleQuest") == 2) {
                        p.sendMessage("§oVous avez reçu une clé mystérieuse !");
                        PlayerManager.updatePlayerStep(p.getUniqueId(), "exampleQuest", 3);
                        QuestManager.giveStepReward(p, "exampleQuest", 3);
                    }
                }
            }
        }

    }

}
