package fr.xamez.cite_quests.events;

import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quest_core.utils.MessagesUtil;
import fr.xamez.cite_quests.CiteQuests;
import fr.xamez.cite_quests.quests.BienvenueEquipageQuest;
import fr.xamez.cite_quests.quests.ExampleQuest;
import fr.xamez.cite_quests.quests.OnePeaceQuest;
import fr.xamez.cite_quests.quests.UnlovedWitch;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCEvents implements Listener {

    private final Manager manager;

    public NPCEvents(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onNpcInteract(NPCRightClickEvent e) {

        final NPC npc = e.getNPC();
        final Player p = e.getClicker();

        // "exampleQuest" QUEST
        if (npc.getId() == 4) {
            final ExampleQuest currentQuest = (ExampleQuest) CiteQuests.getQuestRegistration().getQuestList().get("exampleQuest");
            final Quest quest = currentQuest.getQuest();
            if (quest != null) {
                ExampleQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
            } else {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
            }


        } else if (npc.getId() == 17) {

            if (this.manager.getQuestManager().isQuestOver(p, "bienvenueEquipageQuest")) {

                // "onePeaceQuest" QUEST
                final OnePeaceQuest currentQuest = (OnePeaceQuest) CiteQuests.getQuestRegistration().getQuestList().get("onePeaceQuest");
                final Quest quest = currentQuest.getQuest();
                if (quest != null) {
                    OnePeaceQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else {
                    p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                }

                // "bienvenueEquipageQuest" QUEST
            } else {
                final BienvenueEquipageQuest currentQuest = (BienvenueEquipageQuest) CiteQuests.getQuestRegistration().getQuestList().get("bienvenueEquipageQuest");
                final Quest quest = currentQuest.getQuest();
                if (quest != null) {
                    BienvenueEquipageQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else {
                    p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                }
            }

            // "unlovedWitch" QUEST
        } else if (npc.getId() == 26) {

            final UnlovedWitch currentQuest = (UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get("unlovedWitch");
            final Quest quest = currentQuest.getQuest();
            if (quest != null) {
                ExampleQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
            } else {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
            }

        } else if (npc.getId() == 28){

            if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") == 3) {
                    if (p.getInventory().contains(Material.DRAGON_BREATH, 16)) {
                        final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get("unlovedWitch")).getQuest();
                        UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                        PlayerManager.updatePlayerStep(p.getUniqueId(), "unlovedWitch", 4);
                    } else {
                        MessagesUtil.RPMessage("%npc%§7: §f\"Je n'aime pas être dérangé\"", p, npc);
                    }
                }
            }

        } else if (npc.getId() == 27){

            if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get("unlovedWitch") == 4) {
                    if (p.getInventory().contains(Material.DRAGON_BREATH, 16)) {
                        final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get("unlovedWitch")).getQuest();
                        UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                        PlayerManager.updatePlayerStep(p.getUniqueId(), "unlovedWitch", 5);
                    } else {
                        MessagesUtil.RPMessage("%npc%§7: §f\"Je n'aime pas le monde... Bah alors, qu-est-ce que tu regardes ?\"", p, npc);
                    }
                }
            }

        }

    }
}
