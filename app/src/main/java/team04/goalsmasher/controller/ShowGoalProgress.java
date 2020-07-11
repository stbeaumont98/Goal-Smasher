package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;

public class ShowGoalProgress extends AppCompatActivity {

    Context context;
    ProgressBar progressBarFooter;
    private StoreDataModel savedData = new StoreDataModel(this);
    private ArrayList<GoalSmasherModel> goalListItem = new ArrayList<GoalSmasherModel>();
    private ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_goal_progress);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        // 07-04-2020 12:20 AM: Ellis, Getting data from internal storage to get list of goals
        goalListItem = savedData.loadData();
        context = ShowGoalProgress.this;
        // 07-04-2020 12:20 AM: Ellis, Looping through the list goals and adding to listView
        for (int i = 0; i < goalListItem.size(); i++) {
            items.add(goalListItem.get(i).getGoal());
        }

        setupListWithFooter();
        progressBarFooter.setScaleY(2f);
        progressBarFooter.setMax(goalListItem.size());
        progressBarFooter.setProgress(0);
        showProgressBar();
    }

    public void setupListWithFooter() {
        // Find the ListView
        ListView goalListView = findViewById(R.id.goalListView);


        // Find the progressbar within the footer
        progressBarFooter = findViewById(R.id.progressBar);

        // Set the adapter
        goalListView.setAdapter(new GoalProgressAdapter(items, context, progressBarFooter));

    }

    public void showProgressBar() {
        progressBarFooter.setVisibility(View.VISIBLE);
    }

    public void clear(View v){

    }
}