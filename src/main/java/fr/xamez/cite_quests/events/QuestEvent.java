package fr.xamez.cite_quests.events;

import fr.milekat.cite_libs.utils_tools.ItemBuilder;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quests.CiteQuests;
import fr.xamez.cite_quests.gui.ExampleQuestGUI;
import fr.xamez.cite_quests.gui.OnePeaceQuestGUI;
import fr.xamez.cite_quests.quests.AngryBees;
import fr.xamez.cite_quests.quests.ExampleQuest;
import fr.xamez.cite_quests.quests.OnePeaceQuest;
import fr.xamez.cite_quests.quests.UnlovedWitch;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.LingeringPotionSplashEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class QuestEvent implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent e){

        Player p = e.getPlayer();
        Block block = e.getClickedBlock();
        if (block == null) { return; }

        if (block.getType().equals(Material.CHEST)){
            if (block.getLocation().equals(new Location(Bukkit.getWorld("world"), -1, 155, 17))) {
                e.setCancelled(true);
                if (Manager.playerQuests.get(p.getUniqueId()).get(ExampleQuest.ID) != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get(ExampleQuest.ID) == 1) {
                        p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oLe coffre est verrouillé, une clé est forcément cachée quelque part !");
                        PlayerManager.updatePlayerStep(p.getUniqueId(), ExampleQuest.ID, 2);
                    } else if (Manager.playerQuests.get(p.getUniqueId()).get(ExampleQuest.ID) == 4) {
                        p.closeInventory();
                        new ExampleQuestGUI(p, ((ExampleQuest) CiteQuests.getQuestRegistration().getQuestList().get(ExampleQuest.ID)).getQuest());
                    }
                }
            }
        }

        if (block.getType().equals(Material.BROWN_CARPET)){
            if (block.getLocation().equals(new Location(Bukkit.getWorld("world"), -3, 155, 16))) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(ExampleQuest.ID) != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get(ExampleQuest.ID) == 2) {
                        p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oVous avez reçu une clé mystérieuse !");
                        PlayerManager.updatePlayerStep(p.getUniqueId(), ExampleQuest.ID, 3);
                    }
                }
            }
        }

        if (block.getType().equals(Material.WHITE_SHULKER_BOX)){
            if (block.getLocation().equals(new Location(Bukkit.getWorld("world"), -165, 135, 71))) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(OnePeaceQuest.ID) != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get(OnePeaceQuest.ID) == 1) {
                        p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oVous remarquer que la toile du moulin est étrange à cet endroit..");
                        p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oIl semble y avoir une pochette renfermant quelque chose, essayez d’ouvrir la poche pour saisir l’objet");
                        PlayerManager.updatePlayerStep(p.getUniqueId(), OnePeaceQuest.ID, 2);
                    } else if (Manager.playerQuests.get(p.getUniqueId()).get(OnePeaceQuest.ID) == 3){
                        p.closeInventory();
                        new OnePeaceQuestGUI(p, ((OnePeaceQuest) CiteQuests.getQuestRegistration().getQuestList().get(OnePeaceQuest.ID)).getQuest());
                    }
                }
            }
        }

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            if (p.getWorld().getEnvironment().equals(World.Environment.THE_END)) {
                if (p.getInventory().contains(Material.DRAGON_BREATH)) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                        if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 2) {
                            if (p.getInventory().contains(Material.DRAGON_BREATH, 1)) {
                                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oJe pense que 32 fioles de ce souffle devraient suffir à régler la dispute entre les sorcières, dès que j’ai les fioles j’irai rendre visite au sorcières.");
                            } else if (p.getInventory().contains(Material.DRAGON_BREATH, 32)){
                                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oParfait j'ai récupéré tout ce qu'il me fallait !");
                                PlayerManager.updatePlayerStep(p.getUniqueId(), UnlovedWitch.ID, 3);
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
            if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 1) {
                    p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oCet endroit est étrange.. Il y a probablement le monstre rare dont Waldo m’a parlé !");
                    PlayerManager.updatePlayerStep(p.getUniqueId(), UnlovedWitch.ID, 2);
                }
            }
        }
    }

    @EventHandler
    public void onPotionSplash(LingeringPotionSplashEvent e) {
        Player p = (Player) e.getEntity().getShooter();
        ItemStack potion = e.getEntity().getItem();
        if (potion.getItemMeta().getDisplayName().equals("§6Encens")) {
            e.getAreaEffectCloud().setDuration(30);
            ItemStack item = new ItemBuilder(Material.LINGERING_POTION).setName("§6Encens").toItemStack();
            PotionMeta meta = (PotionMeta) item.getItemMeta();
            meta.setColor(Color.ORANGE);
            item.setItemMeta(meta);
            p.getInventory().addItem(item);
            for (ItemStack content : p.getInventory().getContents()) {
                if (content != null) {
                    if (content.equals(item)) {
                        p.getInventory().remove(content);
                    }
                }
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 4) {
                    for (Entity entity : e.getAreaEffectCloud().getNearbyEntities(3, 3, 3)) {
                        if (entity.getType().equals(EntityType.BEE)) {
                            if (entity.hasMetadata("NPC")) {;
                                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§f§oVous venez de calmer les abeilles.");
                                PlayerManager.updatePlayerStep(p.getUniqueId(), AngryBees.ID, 6);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

}
