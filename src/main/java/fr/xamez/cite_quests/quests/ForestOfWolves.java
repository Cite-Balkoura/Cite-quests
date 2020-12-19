package fr.xamez.cite_quests.quests;

import fr.milekat.cite_libs.MainLibs;
import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quest_core.utils.MessagesUtil;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ForestOfWolves {

    private final Quest quest;
    public final static String ID = "La forêt des loups";

    public ForestOfWolves(CiteQuestCore citeQuestCore){
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
                if (getKills(p.getUniqueId()) >= 10) {
                    if (!Manager.playerDialogues.contains(p.getUniqueId())) {
                        Manager.playerDialogues.add(p.getUniqueId());
                        MessagesUtil.sendDialogues(citeQuestCore, quest, 1, p, npc);
                        PlayerManager.updatePlayerStep(p.getUniqueId(), ID, 2);
                        MessagesUtil.sendEndMessage(p, quest, npc);
                    }
                } else {
                    MessagesUtil.sendRPMessage("%npc%§7: §f\"Élimine toutes la horde et reviens me voir !\"", p, npc);
                }
                break;
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

    public static int getKills(UUID uuid) {
        Connection connection = MainLibs.getSql();
        int kills = 0;
        try {
            PreparedStatement q = connection.prepareStatement("SELECT * FROM `balkoura_quest_wolves` WHERE `player_id` = " +
                    "(SELECT `player_id` FROM `balkoura_player` WHERE `uuid` = '" + uuid.toString() + "');");
            q.execute();
            if (q.getResultSet().first()) {
                kills = q.getResultSet().getInt("kills");
            }
            q.close();
        } catch (SQLException throwable) {
            Bukkit.getLogger().warning(MessagesEnum.PREFIX_CONSOLE.getText() + "Erreur lors de la récupération des kills pour la quête des loups pour: " + uuid);
            throwable.printStackTrace();
        }
        return kills;
    }

}
