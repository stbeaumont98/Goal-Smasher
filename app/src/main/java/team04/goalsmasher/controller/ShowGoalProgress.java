package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;

public class ShowGoalProgress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_goal_progress);

        // This is the code for our custom toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);


        /* Loads the list data from internal storage
         * and stores it in our list variable */
        StoreDataModel data = new StoreDataModel(this);
        ArrayList<GoalSmasherModel> goalList = data.loadData();


        // Get the views from our activity_show_goal_progress layout
        ListView goalListView = findViewById(R.id.goalListView);
        ProgressBar progressBar = findViewById(R.id.progressBar);


        // Set the adapter for the ListView
        goalListView.setAdapter(new GoalProgressAdapter(goalList, ShowGoalProgress.this, progressBar));


        // Update the progress bar settings
        progressBar.setScaleY(2f);
        progressBar.setMax(goalList.size());
        progressBar.setProgress(0);
    }
}