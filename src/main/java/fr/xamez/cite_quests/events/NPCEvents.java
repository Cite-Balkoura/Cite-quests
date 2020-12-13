package fr.xamez.cite_quests.events;

import fr.milekat.cite_libs.utils_tools.ItemBuilder;
import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quest_core.enumerations.MessagesEnum;
import fr.xamez.cite_quest_core.managers.Manager;
import fr.xamez.cite_quest_core.managers.PlayerManager;
import fr.xamez.cite_quest_core.objects.Quest;
import fr.xamez.cite_quest_core.utils.MessagesUtil;
import fr.xamez.cite_quests.CiteQuests;
import fr.xamez.cite_quests.quests.*;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

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
            final ExampleQuest currentQuest = (ExampleQuest) CiteQuests.getQuestRegistration().getQuestList().get(ExampleQuest.ID);
            final Quest quest = currentQuest.getQuest();
            if (quest != null) {
                ExampleQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
            } else {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
            }


        } else if (npc.getId() == 17) {
            if (this.manager.getQuestManager().isQuestOver(p, BienvenueEquipageQuest.ID)) {
                // "onePeaceQuest" QUEST
                final OnePeaceQuest currentQuest = (OnePeaceQuest) CiteQuests.getQuestRegistration().getQuestList().get(OnePeaceQuest.ID);
                final Quest quest = currentQuest.getQuest();
                if (quest != null) {
                    OnePeaceQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else {
                    p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                }

                // "bienvenueEquipageQuest" QUEST
            } else {
                final BienvenueEquipageQuest currentQuest = (BienvenueEquipageQuest) CiteQuests.getQuestRegistration().getQuestList().get(BienvenueEquipageQuest.ID);
                final Quest quest = currentQuest.getQuest();
                if (quest != null) {
                    BienvenueEquipageQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else {
                    p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                }
            }

            // "unlovedWitch" QUEST
        } else if (npc.getId() == 26) {

            final UnlovedWitch currentQuest = (UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get(UnlovedWitch.ID);
            final Quest quest = currentQuest.getQuest();
            if (quest != null) {
                ExampleQuest.proceed(CiteQuestCore.getInstance(), quest, p, npc);
            } else {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
            }

        } else if (npc.getId() == 28){

            if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 3) {
                    if (p.getInventory().contains(Material.DRAGON_BREATH, 16)) {
                        final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get(UnlovedWitch.ID)).getQuest();
                        UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                        PlayerManager.updatePlayerStep(p.getUniqueId(), UnlovedWitch.ID, 4);
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Je n'aime pas être dérangé\"", p, npc);
                    }
                }
            }

        } else if (npc.getId() == 27){

            if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 4) {
                    if (p.getInventory().contains(Material.DRAGON_BREATH, 16)) {
                        final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get(UnlovedWitch.ID)).getQuest();
                        UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                        PlayerManager.updatePlayerStep(p.getUniqueId(), UnlovedWitch.ID, 5);
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Je n'aime pas le monde... Bah alors, qu-est-ce que tu regardes ?\"", p, npc);
                    }
                }
            }
            // "angryBees" QUEST
        } else if (npc.getId() == 43) {
            final AngryBees currentQuest = (AngryBees) CiteQuests.getQuestRegistration().getQuestList().get(AngryBees.ID);
            final Quest quest = currentQuest.getQuest();
            if (quest != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 1){
                    MessagesUtil.sendRPMessage("%npc%§7: §f\"Va rendre visite à mon ami le chasseur, il trouvera un solution à mon problème\"", p, npc);
                } else {
                    AngryBees.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                }
            } else {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
            }
        } else if (npc.getId() == 44){
            if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 2) {
                    final Quest quest = ((AngryBees) CiteQuests.getQuestRegistration().getQuestList().get(AngryBees.ID)).getQuest();
                    AngryBees.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 3) {
                    ItemStack item = new ItemBuilder(Material.LINGERING_POTION).setName("§6Encens").toItemStack();
                    PotionMeta meta = (PotionMeta) item.getItemMeta();
                    meta.setColor(Color.ORANGE);
                    item.setItemMeta(meta);
                    if (!p.getInventory().containsAtLeast(item, 1)){
                        p.getInventory().addItem(item);
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Voilà pour toi !\"", p, npc);
                    }

                }
            }
        }
    }
}
