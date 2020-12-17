package fr.xamez.cite_quests.quests;

import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quest_core.utils.MessagesUtil;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SilenceGrow {

    private final Quest quest;
    public final static String ID = "Silence, ça pousse";

    public SilenceGrow(CiteQuestCore citeQuestCore){
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
                    ItemStack boneMeal = new ItemStack(Material.BONE_MEAL, 16);
                    if (p.getInventory().contains(boneMeal)) {
                        if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                            Manager.playerDialogues.add(p.getUniqueId());
                            p.getInventory().remove(boneMeal);
                            p.sendMessage("§oVos engrais vous ont été retiré.");
                            MessagesUtil.sendDialogues(citeQuestCore, quest, 1, p, npc);
                            PlayerManager.updatePlayerStep(p.getUniqueId(), ID, 2);
                            MessagesUtil.sendEndMessage(p, quest, npc);
                        }
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Ramène moi 16 engrais s'il te plait\"", p, npc);
                    }
                }
            case 2:
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§aVous avez déjà terminé cette quête !");
                break;

            default:
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cErreur inattendu ! Veillez conntacter un membre du staff.");
        }
    }

    public Quest getQuest() {
        return this.quest;
    }

}
