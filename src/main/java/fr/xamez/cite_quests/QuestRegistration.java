package fr.xamez.cite_quests;

import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quests.quests.BienvenueEquipageQuest;
import fr.xamez.cite_quests.quests.ExampleQuest;
import fr.xamez.cite_quests.quests.OnePeaceQuest;
import fr.xamez.cite_quests.quests.UnlovedWitch;

import java.util.HashMap;
import java.util.Map;

public class QuestRegistration {

    private final Map<String, Object> questList = new HashMap<>();

    public QuestRegistration(){

        CiteQuestCore instance = CiteQuestCore.getInstance();
        this.questList.put("exampleQuest", new ExampleQuest(instance));
        this.questList.put("bienvenueEquipageQuest", new BienvenueEquipageQuest(instance));
        this.questList.put("onePeaceQuest", new OnePeaceQuest(instance));
        this.questList.put("unlovedWitch", new UnlovedWitch(instance));

    }

    public Map<String, Object> getQuestList() {
        return questList;
    }
}
