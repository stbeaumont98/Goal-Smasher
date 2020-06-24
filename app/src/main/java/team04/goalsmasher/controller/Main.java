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

        Button calendarEventScheduleView = findViewById(R.id.viewYourCalendar);
        Button showGoalProgressView = findViewById(R.id.checkProgressGoal);

        calendarEventScheduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarEventScheduleView();
            }
        });

        showGoalProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShowGoalProgressView();
            }
        });
    }

    public void openCalendarEventScheduleView() {
        Intent intent = new Intent(this, CalendarEventSchedule.class);
        startActivity(intent);
    }

    public void openShowGoalProgressView() {
        Intent intent = new Intent(this, ShowGoalProgress.class);
        startActivity(intent);
    }
}