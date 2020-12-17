package fr.xamez.cite_quests;

import fr.xamez.cite_quest_core.CiteQuestCore;
import fr.xamez.cite_quests.quests.*;

import java.util.HashMap;
import java.util.Map;

public class QuestRegistration {

    private final Map<String, Object> questList = new HashMap<>();

    public QuestRegistration(){
        CiteQuestCore instance = CiteQuestCore.getInstance();
        this.questList.put(ExampleQuest.ID, new ExampleQuest(instance));
        this.questList.put(BienvenueEquipageQuest.ID, new BienvenueEquipageQuest(instance));
        this.questList.put(OnePeaceQuest.ID, new OnePeaceQuest(instance));
        this.questList.put(UnlovedWitch.ID, new UnlovedWitch(instance));
        this.questList.put(AngryBees.ID, new AngryBees(instance));
        this.questList.put(ForestOfWolves.ID, new ForestOfWolves(instance));
        this.questList.put(GoodFrankness.ID, new GoodFrankness(instance));
        this.questList.put(AngelSaints.ID, new AngelSaints(instance));
    }

    public Map<String, Object> getQuestList() {
        return questList;
    }
}
