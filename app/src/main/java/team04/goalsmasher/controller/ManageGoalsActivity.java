package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;

public class ManageGoalsActivity extends AppCompatActivity implements GoalManagerAdapter.GoalManager {

    private ArrayList<GoalSmasherModel> goalList = new ArrayList<>();
    private StoreDataModel data;
    private GoalManagerAdapter goalManagerAdapter;
    private TextView textNoGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_goals);

        // This is the code for our custom toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        data = new StoreDataModel(this);
        textNoGoals = findViewById(R.id.textNoGoals);

        refreshList();

        // Get views from our activity_manage_goals layout
        ListView lvGoals = findViewById(R.id.listManageGoals);
        Button buttonCreateGoal = findViewById(R.id.buttonCreateGoal);
        Button buttonDeleteAll = findViewById(R.id.buttonDeleteAll);

        goalManagerAdapter = new GoalManagerAdapter(goalList, this, this);

        lvGoals.setAdapter(goalManagerAdapter);

        /* When the "Create Goal" button is clicked, it opens the
        * GoalCreate activity and allows the user to create a new
        * goal */
        buttonCreateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGoalCreate();
            }
        });

        /* When the "Delete All" button is clicked, it gives the user
         * a chance to change their mind with an AlertDialog and if
         * the user wishes to continue, all goals are deleted */
        buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(ManageGoalsActivity.this, R.style.Theme_AppCompat)
                        .setTitle("Are you sure?")
                        .setMessage("This will permanently delete all of your goals.")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.clearData();
                                refreshList();
                                goalManagerAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Do nothing
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
    }

    // Clears the goalList and refills it with data from internal storage
    public void refreshList() {
        goalList.clear();
        goalList.addAll(data.loadData());
        textNoGoals.setVisibility(goalList.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
        goalManagerAdapter.notifyDataSetChanged();
    }

    // Method for opening the GoalCreate activity
    public void openGoalCreate() {
        Intent intent = new Intent(this, GoalCreate.class);
        startActivity(intent);
    }

    @Override
    public void updateData() {
        refreshList();
    }
}