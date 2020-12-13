package fr.xamez.cite_quests;

import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quests.events.NPCEvents;
import fr.xamez.cite_quests.events.QuestEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CiteQuests extends JavaPlugin {

    private static QuestRegistration questRegistration;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new NPCEvents(CiteQuestCore.getInstance().getMANAGER()), this);
        getServer().getPluginManager().registerEvents(new QuestEvent(), this);
        questRegistration = new QuestRegistration();

    }

    public static QuestRegistration getQuestRegistration() {
        return questRegistration;
    }
}