package team04.goalsmasher.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import team04.goalsmasher.R;

// This is the Calendar code for the app

public class CalendarEventSchedule extends AppCompatActivity {
    CalendarView calendarView;
    TextView calendarGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event_schedule);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarGoals = (TextView) findViewById(R.id.calendarGoals);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (year) + "/" + month + 1 + "/" + dayOfMonth;
                calendarGoals.setText(date);
            }
        });

    }
}