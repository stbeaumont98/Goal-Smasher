package team04.goalsmasher.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;


public class CalendarEventSchedule extends AppCompatActivity {
    private Context context;
    private StoreDataModel savedData = new StoreDataModel(this);
    private ArrayList<GoalSmasherModel> goalListItem = new ArrayList<GoalSmasherModel>();
    private ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_schedule);
        // 07-03-2020 5:55 PM: Ellis, Getting data from internal storage to get list of goals
        goalListItem = savedData.loadData();
        // 07-03-2020 5:55 PM: Ellis, Looping through the list goals and adding to listView
        for (int i = 0; i < goalListItem.size(); i++) {
            items.add(goalListItem.get(i).getGoal());
        }

        fileTextView();
    }

    private void fileTextView() {
        // 07-03-2020 5:57 PM: Ellis, Custom listView using an adapter. It takes the goals_view layout
        // and the arrayList
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                R.layout.goals_view, items);

        ListView entireListOfGoals = (ListView) findViewById(R.id.goalListView);
        entireListOfGoals.setAdapter(arrayAdapter);
    }

}