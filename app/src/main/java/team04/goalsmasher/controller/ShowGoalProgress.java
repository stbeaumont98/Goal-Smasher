package team04.goalsmasher.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
        // 07-04-2020 12:20 AM: Ellis, Getting data from internal storage to get list of goals
        goalListItem = savedData.loadData();
        context = ShowGoalProgress.this;
        // 07-04-2020 12:20 AM: Ellis, Looping through the list goals and adding to listView
        for (int i = 0; i < goalListItem.size(); i++) {
            items.add(goalListItem.get(i).getGoal());
        }

        setupListWithFooter();
        progressBarFooter.setMax(goalListItem.size());
        progressBarFooter.setProgress(0);
        showProgressBar();
    }

    public void setupListWithFooter() {
        // Find the ListView
        ListView goalListView = (ListView) findViewById(R.id.goalListView);
        // Inflate the footer
        View footer = getLayoutInflater().inflate(R.layout.activity_show_goal_progress, null);
        // Find the progressbar within the footer
        progressBarFooter = (ProgressBar) footer.findViewById(R.id.pbFooterLoading);
        // Add footer to ListView
        goalListView.addFooterView(footer);
        // Set the adapter
        goalListView.setAdapter(new CustomAdapter(items, context));

    }

    public void showProgressBar() {
        progressBarFooter.setVisibility(View.VISIBLE);
    }

    public void confirm(View v){
        Button button = (Button) findViewById(R.id.confirm);

        int i = progressBarFooter.getProgress();
        progressBarFooter.setProgress(i + 1);

        if (i == progressBarFooter.getMax() - 1){
            Context context = getApplicationContext();
            CharSequence text = "You did it!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void clear(View v){

    }
}