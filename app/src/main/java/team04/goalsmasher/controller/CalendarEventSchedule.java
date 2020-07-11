package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;


public class CalendarEventSchedule extends AppCompatActivity {
    private StoreDataModel savedData = new StoreDataModel(this);
    private ArrayList<GoalSmasherModel> goalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_schedule);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        // 07-03-2020 5:55 PM: Ellis, Getting data from internal storage to get list of goals
        goalList = savedData.loadData();

        fileTextView();
    }

    private void fileTextView() {
        // 07-03-2020 5:57 PM: Ellis, Custom listView using an adapter. It takes the goals_view layout
        // and the arrayList
        GoalCalendarAdapter arrayAdapter = new GoalCalendarAdapter(goalList, this);

        ListView entireListOfGoals = (ListView) findViewById(R.id.goalListView);
        entireListOfGoals.setAdapter(arrayAdapter);
    }

}