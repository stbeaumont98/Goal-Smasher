package team04.goalsmasher;

import com.google.gson.Gson;

import java.util.ArrayList;

public class StoreData {
    private Gson gson = new Gson();

    public void saveData(ArrayList<GoalSmasherModel> goalList) {
        String goalJson = gson.toJson(goalList);
    }

    //this is assuming we are storing a list of goals
    public ArrayList<GoalSmasherModel> loadData() {
        ArrayList<GoalSmasherModel> goalList = new ArrayList<>();
        return goalList;
    }
}
