package team04.goalsmasher.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import team04.goalsmasher.model.GoalSmasherModel;

public class StoreDataModel {
    private Gson gson = new Gson();
    private String filename = "goal_data";
    private Context context;

    public StoreDataModel(Context context) {
        this.context = context;
    }

    /* saveData: This method converts the users data to a json
     * string and saves the data to internal storage */
    public void saveData(ArrayList<GoalSmasherModel> goalList) {
        String goalJson = gson.toJson(goalList);

        try {
            FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(goalJson.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* loadData: Loads the users data from internal storage
     * this is assuming we are storing a list of goals */
    public ArrayList<GoalSmasherModel> loadData() {
        ArrayList<GoalSmasherModel> goalList = new ArrayList<>();

        try {
            FileInputStream fis = context.openFileInput(filename);
            Scanner scanner = new Scanner(fis);
            scanner.useDelimiter("\\Z");
            String content = scanner.next();
            scanner.close();
            fis.close();

            Type listType = new TypeToken<ArrayList<GoalSmasherModel>>(){}.getType();
            goalList = gson.fromJson(content, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return goalList;
    }
}
