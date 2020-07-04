package team04.goalsmasher.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team04.goalsmasher.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 06-16-2020 12:43 Ellis, Handles to navigation to other activities
        //
        Button calendarEventScheduleView = findViewById(R.id.viewYourCalendar);
        Button showGoalProgressView = findViewById(R.id.checkProgressGoal);
        // Nathaniel. Button for creating the goal.
        Button createYourGoal = findViewById(R.id.createYourGoal);

        // 06-16-2020 12:43 Ellis, when clicked will go to calendar View.
        calendarEventScheduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarEventScheduleView();
            }
        });

        // 06-16-2020 12:43 Ellis, our Model and business logic.
        showGoalProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShowGoalProgressView();
            }
        });

        createYourGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoalCreate();
            }
        });
    }
    // 06-16-2020 12:43 Ellis, our Model and business logic.
    public void openCalendarEventScheduleView() {
        Intent intent = new Intent(this, CalendarEventSchedule.class);
        startActivity(intent);
    }
    // 06-16-2020 12:43 Ellis, our Model and business logic.
    public void openShowGoalProgressView() {
        Intent intent = new Intent(this, ShowGoalProgress.class);
        startActivity(intent);
    }

    public void openGoalCreate() {
        Intent intent = new Intent(this, GoalCreate.class);
        startActivity(intent);
    }
}