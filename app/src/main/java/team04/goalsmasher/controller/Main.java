package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
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

        // This is the code for our custom toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);


        /* This code is necessary for notifications on Android 8.0 and higher
         * Based on the example found at https://stackoverflow.com/a/47974065 */
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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


        /* Get buttons from our activity_main layout xml so we can
         * set the OnClickListeners for each of them */
        Button calendarEventScheduleView = findViewById(R.id.viewYourCalendar);
        Button showGoalProgressView = findViewById(R.id.checkProgressGoal);
        Button manageGoals = findViewById(R.id.btnManageGoals);


        /* When the "Manage Goals" button is clicked, open
         * ManageGoalsActivity */
        manageGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoalManager();
            }
        });

        /* When the "View Calendar" button is clicked, open
         * CalendarEventSchedule */
        calendarEventScheduleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalendarEventScheduleView();
            }
        });

        /* When the "Check Progress" button is clicked, open
         * ShowGoalProgress */
        showGoalProgressView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShowGoalProgressView();
            }
        });

    }

    // Method for opening the ManageGoalsActivity
    public void openGoalManager() {
        Intent intent = new Intent(this, ManageGoalsActivity.class);
        startActivity(intent);
    }

    // Method for opening the CalendarEventSchedule activity
    public void openCalendarEventScheduleView() {
        Intent intent = new Intent(this, CalendarEventSchedule.class);
        startActivity(intent);
    }

    // Method for opening the ShowGoalProgress activity
    public void openShowGoalProgressView() {
        Intent intent = new Intent(this, ShowGoalProgress.class);
        startActivity(intent);
    }

}