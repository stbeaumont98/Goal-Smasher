package team04.goalsmasher.controller;

import com.google.gson.Gson;

import java.util.ArrayList;

import team04.goalsmasher.model.GoalSmasherModel;

public class StoreData {
    private Gson gson = new Gson();

    /* saveData: This method converts the users data to a json
     * string and saves the data to internal storage */
    public void saveData(ArrayList<GoalSmasherModel> goalList) {
        String goalJson = gson.toJson(goalList);
    }

    /* loadData: Loads the users data from internal storage
     * this is assuming we are storing a list of goals */
    public ArrayList<GoalSmasherModel> loadData() {
        ArrayList<GoalSmasherModel> goalList = new ArrayList<>();
        return goalList;
    }
}
