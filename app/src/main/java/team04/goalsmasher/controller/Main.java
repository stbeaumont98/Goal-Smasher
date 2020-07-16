package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import team04.goalsmasher.R;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /* This code is necessary for notifications on Android 8.0 and higher
         * Based on the example found at https://stackoverflow.com/a/47974065 */
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            String CHANNEL_ID = "Goal-Smasher";
            CharSequence name = "goal_notification_channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription("This is the notification channel for Goal Smasher");
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        // 06-16-2020 12:43 Ellis, Handles to navigation to other activities
        //
        Button calendarEventScheduleView = findViewById(R.id.viewYourCalendar);
        Button showGoalProgressView = findViewById(R.id.checkProgressGoal);
        // Nathaniel. Button for creating the goal.
        Button manageGoals = findViewById(R.id.btnManageGoals);

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

        manageGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoalManager();
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

    public void openGoalManager() {
        Intent intent = new Intent(this, ManageGoalsActivity.class);
        startActivity(intent);
    }
}