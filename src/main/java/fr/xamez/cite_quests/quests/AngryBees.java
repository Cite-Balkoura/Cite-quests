package fr.xamez.cite_quests.quests;

import fr.milekat.cite_libs.utils_tools.ItemBuilder;
import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quest_core.utils.MessagesUtil;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

import java.util.List;

public class AngryBees {

    private final Quest quest;
    public final static String ID = "Abeille enragées";

    public AngryBees(CiteQuestCore citeQuestCore){
        List<Quest> qList = citeQuestCore.getMANAGER().getQuestList();
        if (qList.stream().anyMatch(q -> q.getIdentifier().equals(ID))) {
            this.quest = qList.stream().filter(q -> q.getIdentifier().equals(ID)).findFirst().get();
            Bukkit.getLogger().warning("QUEST WITH ID \"" + ID + "\" LOADED!");
        } else {
            Bukkit.getLogger().warning("ERROR! QUEST WITH ID \"" + ID + "\" NOT FOUND! CAN'T BE LOAD");
            this.quest = null;
        }
    }

    public static void proceed(CiteQuestCore citeQuestCore, Quest quest, Player p, NPC npc) {
        int step = Manager.playerQuests.get(p.getUniqueId()).get(ID) == null ? 0 : Manager.playerQuests.get(p.getUniqueId()).get(ID);
        switch (step){
            case 0:
                if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                    Manager.playerDialogues.add(p.getUniqueId());
                    MessagesUtil.sendDialogues(citeQuestCore, quest, 0, p, npc);
                }
                break;
            case 1:
                if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                    Manager.playerDialogues.add(p.getUniqueId());
                    MessagesUtil.sendDialogues(citeQuestCore, quest, 1, p, npc);
                    PlayerManager.updatePlayerStep(p.getUniqueId(), ID, 2);
                }
                break;
            case 2:
                if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                    ItemStack nether_wart = new ItemStack(Material.NETHER_WART);
                    ItemStack mushroom = new ItemStack(Material.RED_MUSHROOM);
                    ItemStack plant = new ItemStack(Material.TALL_GRASS);
                    ItemStack rabbit_foot = new ItemStack(Material.RABBIT_FOOT);
                    System.out.println(p.getInventory().contains(nether_wart));
                    System.out.println(p.getInventory().containsAtLeast(nether_wart, 1));
                    if (p.getInventory().containsAtLeast(nether_wart, 1) && p.getInventory().containsAtLeast(mushroom, 1)
                    && p.getInventory().containsAtLeast(plant, 1) && p.getInventory().containsAtLeast(rabbit_foot, 1)) {
                        Manager.playerDialogues.add(p.getUniqueId());
                        p.getInventory().removeItem(nether_wart);
                        p.getInventory().removeItem(mushroom);
                        p.getInventory().removeItem(plant);
                        p.getInventory().removeItem(rabbit_foot);
                        p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§oLes ingrédients vous ont été retiré.");
                        ItemStack item = new ItemBuilder(Material.LINGERING_POTION).setName("§6Encens").toItemStack();
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        meta.setColor(Color.ORANGE);
                        item.setItemMeta(meta);
                        p.getInventory().addItem(item);
                        MessagesUtil.sendDialogues(citeQuestCore, quest, 2, p, npc);
                        PlayerManager.updatePlayerStep(p.getUniqueId(), ID, 3);
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Rapport moi les bons ingrédients pour l'encens\"", p, npc);
                    }
                }
                break;
            case 3:
                if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                    ItemStack item = new ItemBuilder(Material.LINGERING_POTION).setName("§6Encens").toItemStack();
                    PotionMeta meta = (PotionMeta) item.getItemMeta();
                    meta.setColor(Color.ORANGE);
                    item.setItemMeta(meta);
                    if (p.getInventory().containsAtLeast(item, 1)) {
                        if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                            Manager.playerDialogues.add(p.getUniqueId());
                            MessagesUtil.sendDialogues(citeQuestCore, quest, 3, p, npc);
                            PlayerManager.updatePlayerStep(p.getUniqueId(), ID, 4);
                        }
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Aurais-tu de quoi chasser les abeilles ?\"", p, npc);
                    }
                }
                break;
            case 4:
                if(!Manager.playerDialogues.contains(p.getUniqueId())){
                    Manager.playerDialogues.add(p.getUniqueId());
                    MessagesUtil.sendDialogues(citeQuestCore, quest, 4, p, npc);
                }
                break;
            case 5:
                if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                    Manager.playerDialogues.add(p.getUniqueId());
                    MessagesUtil.sendDialogues(citeQuestCore, quest, 5, p, npc);
                    PlayerManager.updatePlayerStep(p.getUniqueId(), ID, 6);
                    MessagesUtil.sendEndMessage(p, quest, npc);
                    ItemStack item = new ItemBuilder(Material.LINGERING_POTION).setName("§6Encens").toItemStack();
                    PotionMeta meta = (PotionMeta) item.getItemMeta();
                    meta.setColor(Color.ORANGE);
                    item.setItemMeta(meta);
                    for (ItemStack content : p.getInventory().getContents()) {
                        if (content == null) { continue; }
                        if (content.equals(item)){
                            p.getInventory().remove(content);
                        }
                    }
                }
                break;
            case 6:
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§aVous avez déjà terminé cette quête !");
                break;

            default:
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cErreur inattendu ! Veillez conntacter un membre du staff.");
        }
    }

    public Quest getQuest() {
        return quest;
    }

}
