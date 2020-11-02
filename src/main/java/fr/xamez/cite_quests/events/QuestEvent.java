package fr.xamez.cite_quests.events;

import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quests.CiteQuests;
import fr.xamez.cite_quests.gui.ExampleQuestGUI;
import fr.xamez.cite_quests.gui.OnePeaceQuestGUI;
import fr.xamez.cite_quests.quests.ExampleQuest;
import fr.xamez.cite_quests.quests.OnePeaceQuest;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChangedWorldEvent;
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
                    }
                }
            }
        }

        if (block.getType().equals(Material.WHITE_SHULKER_BOX)){
            if (block.getLocation().equals(new Location(Bukkit.getWorld("world"), -165, 135, 71))) {
                if (Manager.playerQuests.get(p.getUniqueId()).get("onePeaceQuest") != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get("onePeaceQuest") == 1) {
                        p.sendMessage("§oVous remarquer que la toile du moulin est étrange à cet endroit..");
                        p.sendMessage("§oIl semble y avoir une pochette renfermant quelque chose, essayez d’ouvrir la poche pour saisir l’objet");
                        PlayerManager.updatePlayerStep(p.getUniqueId(), "onePeaceQuest", 2);
                    } else if (Manager.playerQuests.get(p.getUniqueId()).get("onePeaceQuest") == 3){
                        p.closeInventory();
                        new OnePeaceQuestGUI(p, ((OnePeaceQuest) CiteQuests.getQuestRegistration().getQuestList().get("onePeaceQuest")).getQuest());
                    }
                }
            }
        }

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                if (p.getInventory().contains(Material.DRAGON_BREATH)) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") != null) {
                        if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") == 2) {
                            if (p.getInventory().contains(Material.DRAGON_BREATH, 1)) {
                                p.sendMessage("§oJe pense que 32 fioles de ce souffle devraient suffir à régler la dispute entre les sorcières, dès que j’ai les fioles j’irai rendre visite au sorcières.");
                            } else if (p.getInventory().contains(Material.DRAGON_BREATH, 32)){
                                p.sendMessage("§oParfait j'ai récupéré tout ce qu'il me fallait !");
                                PlayerManager.updatePlayerStep(p.getUniqueId(), "unlovedWitch", 3);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void worldChangeEvent(PlayerChangedWorldEvent e){
        Player p = e.getPlayer();
        if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)){
            if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") == 1) {
                    p.sendMessage("§oCet endroit est étrange.. Il y a probablement le monstre rare dont Waldo m’a parlé !");
                    PlayerManager.updatePlayerStep(p.getUniqueId(), "unlovedWitch", 2);
                }
            }
        }
    }

}
