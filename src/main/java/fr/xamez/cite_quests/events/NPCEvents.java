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
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
            final Quest q1 = ((OnePeaceQuest) CiteQuests.getQuestRegistration().getQuestList().get(OnePeaceQuest.ID)).getQuest();
            final Quest q2 = ((BienvenueEquipageQuest) CiteQuests.getQuestRegistration().getQuestList().get(BienvenueEquipageQuest.ID)).getQuest();
            if (q1 == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (q2 == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(BienvenueEquipageQuest.ID) == null) {
                BienvenueEquipageQuest.proceed(CiteQuestCore.getInstance(), q2, p, npc);
                return;
            }
            if (this.manager.getQuestManager().isQuestOver(p, BienvenueEquipageQuest.ID)) {
                // "onePeaceQuest" QUEST
                OnePeaceQuest.proceed(CiteQuestCore.getInstance(), q1, p, npc);
            } else {
                // "bienvenueEquipageQuest" QUEST
                BienvenueEquipageQuest.proceed(CiteQuestCore.getInstance(), q2, p, npc);
            }

            // "unlovedWitch" QUEST
        } else if (npc.getId() == 26) {
            final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get(UnlovedWitch.ID)).getQuest();
            if (quest != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                    if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 3 ||
                        Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 4) {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Aide moi à retrouver ma bien-aimée\"", p, npc);
                        return;
                    }
                }
                UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
            } else {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
            }

        } else if (npc.getId() == 53){
            final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get(UnlovedWitch.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 3) {
                    if (p.getInventory().contains(Material.DRAGON_BREATH, 16)) {
                        UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Je n'aime pas être dérangé\"", p, npc);
                    }
                }
            }

        } else if (npc.getId() == 51){
            // WITCH QUEST
            final Quest quest = ((UnlovedWitch) CiteQuests.getQuestRegistration().getQuestList().get(UnlovedWitch.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(UnlovedWitch.ID) == 4) {
                    if (p.getInventory().contains(Material.DRAGON_BREATH, 16)) {
                        UnlovedWitch.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                    } else {
                        MessagesUtil.sendRPMessage("%npc%§7: §f\"Je n'aime pas le monde... Bah alors, qu-est-ce que tu regardes ?\"", p, npc);
                    }
                    return;
                }
            }
            // ANGEL QUEST
            final Quest q1 = ((AngelSaints) CiteQuests.getQuestRegistration().getQuestList().get(AngelSaints.ID)).getQuest();
            if (q1 == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(AngelSaints.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngelSaints.ID) == 2) {
                    AngelSaints.proceed(CiteQuestCore.getInstance(), q1, p, npc);
                }
            }
            // "angryBees" QUEST
        } else if (npc.getId() == 43) {
            final Quest quest = ((AngryBees) CiteQuests.getQuestRegistration().getQuestList().get(AngryBees.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 1 || Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 2) {
                    MessagesUtil.sendRPMessage("%npc%§7: §f\"Va rendre visite à mon ami le chasseur, il trouvera un solution à mon problème\"", p, npc);
                    return;
                }
            }
            AngryBees.proceed(CiteQuestCore.getInstance(), quest, p, npc);

        } else if (npc.getId() == 44){
            final Quest quest = ((AngryBees) CiteQuests.getQuestRegistration().getQuestList().get(AngryBees.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) != null) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 1) {
                    AngryBees.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                } else if (Manager.playerQuests.get(p.getUniqueId()).get(AngryBees.ID) == 2) {
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
        // FOREST QUEST
        } else if (npc.getId() == 48){
            final Quest quest = ((ForestOfWolves) CiteQuests.getQuestRegistration().getQuestList().get(ForestOfWolves.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            ForestOfWolves.proceed(CiteQuestCore.getInstance(), quest, p, npc);
        // GOODFRANKNESS QUEST
        } else if (npc.getId() == 49){
            final Quest quest = ((GoodFrankness) CiteQuests.getQuestRegistration().getQuestList().get(GoodFrankness.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            GoodFrankness.proceed(CiteQuestCore.getInstance(), quest, p, npc);
        // ANGEL QUEST
        } else if (npc.getId() == 50 || npc.getId() == 52){
            final Quest quest = ((AngelSaints) CiteQuests.getQuestRegistration().getQuestList().get(AngelSaints.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            if (Manager.playerQuests.get(p.getUniqueId()).get(AngelSaints.ID) == null) {
                AngelSaints.proceed(CiteQuestCore.getInstance(), quest, p, npc);
                return;
            }
            if (npc.getId() == 50) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngelSaints.ID) == 2 ||
                        Manager.playerQuests.get(p.getUniqueId()).get(AngelSaints.ID) == 3) {
                    return;
                }
            }
            if (npc.getId() == 52) {
                if (Manager.playerQuests.get(p.getUniqueId()).get(AngelSaints.ID) != 3) { return; }
            }
            AngelSaints.proceed(CiteQuestCore.getInstance(), quest, p, npc);
        // SILENCEGROW QUEST
        } else if (npc.getId() == 18){
            final Quest quest = ((SilenceGrow) CiteQuests.getQuestRegistration().getQuestList().get(SilenceGrow.ID)).getQuest();
            if (quest == null) {
                p.sendMessage(MessagesEnum.PREFIX_CMD.getText() + "§cNous sommes désolé par la gêne occasionné mais cette quête à un problème, veuillez contacter un membre du staff (Modérateur ou Administrateur)");
                return;
            }
            SilenceGrow.proceed(CiteQuestCore.getInstance(), quest, p, npc);
        }
    }
}
