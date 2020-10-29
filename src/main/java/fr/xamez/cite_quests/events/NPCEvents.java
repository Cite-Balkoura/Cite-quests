package fr.xamez.cite_quests.events;

import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quests.CiteQuests;
import fr.xamez.cite_quests.quests.BienvenueEquipageQuest;
import fr.xamez.cite_quests.quests.ExampleQuest;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCEvents implements Listener {

    private final Manager manager;

    public NPCEvents(Manager manager){
        this.manager = manager;
    }

    @EventHandler
    public void onNpcInteract(NPCRightClickEvent e){

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

        // "bienvenueEquipageQuest" QUEST
        } else if(npc.getId() == 17){

            if (this.manager.getQuestManager().isQuestOver(p, "bienvenueEquipageQuest")){

            } else {
                final BienvenueEquipageQuest currentQuest = (BienvenueEquipageQuest) CiteQuests.getQuestRegistration().getQuestList().get("bienvenueEquipageQuest");
                final Quest quest = currentQuest.getQuest();
                if (quest != null) {
                    BienvenueEquipageQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else {
                    p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                }
            }

        }



    }
}
