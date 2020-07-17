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
    private StoreDataModel data = new StoreDataModel(this);
    private ArrayList<GoalSmasherModel> goalList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_schedule);

        // This is the code for our custom toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        /* Loads the list data from internal storage
         * and stores it in our goalList variable */
        goalList = data.loadData();

        fileTextView();
    }

    // Populates the ListView using our custom adapter
    private void fileTextView() {
        GoalCalendarAdapter arrayAdapter = new GoalCalendarAdapter(goalList, this);
        ListView entireListOfGoals = (ListView) findViewById(R.id.goalListView);
        entireListOfGoals.setAdapter(arrayAdapter);
    }

}