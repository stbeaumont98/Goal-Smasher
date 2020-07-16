package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;

public class GoalCreate extends AppCompatActivity {

    private Calendar myCalendar = Calendar.getInstance();
    private EditText editTextGoal, editTextDescription, editTextDate;
    private TimePicker timePicker;
    private String goal;
    private String description;
    private String calDate;
    private String time;
    private StoreDataModel data = new StoreDataModel(this);
    private ArrayList<GoalSmasherModel> list = new ArrayList<GoalSmasherModel>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_goal);

        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);

        editTextGoal = findViewById(R.id.editTextGoal);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

        list = data.loadData();

        position = list.size();

        // Checks if we are receiving any info on the intent
        // and if so, populates the fields with the data
        Intent i = getIntent();
        if (i.hasExtra("pos")) {
            position = i.getIntExtra("pos", list.size());
            populateFields(position);
        }

        // The Set Goal button saves the info inputted by the user
        Button btnSetGoal = findViewById(R.id.set);

        btnSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gets the selected time from the user
                int hour, minute;
                String am_pm;
                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                myCalendar.set(Calendar.MINUTE, minute);
                myCalendar.set(Calendar.SECOND, 0);
                if (hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                } else {
                    am_pm="AM";
                }

                // Info stored as a string
                goal = editTextGoal.getText().toString();

                description = editTextDescription.getText().toString();

                calDate = editTextDate.getText().toString();

                time = hour +":"+ minute+" "+am_pm;

                //Allows the model to access this data
                GoalSmasherModel goalData = new GoalSmasherModel(
                        goal, description, calDate, time,
                        true, 0 , myCalendar);

                // if position is equal to the size of the list, then we are creating a new goal
                if (position == list.size()) {
                    list.add(goalData);
                } else {
                    list.set(position, goalData);
                }
                data.saveData(list);

                //Toast
                Context context = getApplicationContext();
                CharSequence text = "Goal Set!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                setAlert(myCalendar);
                toast.show();
                finish();
            }
        });

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(GoalCreate.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month,
                              int day) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        }
    };

    public void cancel(View v){
        finish();
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }

    public void populateFields(int position) {
        GoalSmasherModel goal = list.get(position);
        editTextGoal.setText(goal.getGoal());
        editTextDescription.setText(goal.getDesc());
        editTextDate.setText(goal.getDate());
        int hour = goal.getCalendar().get(Calendar.HOUR_OF_DAY);
        int min = goal.getCalendar().get(Calendar.MINUTE);
        myCalendar.set(Calendar.HOUR_OF_DAY, hour);
        timePicker.setHour(hour);
        myCalendar.set(Calendar.MINUTE, min);
        timePicker.setMinute(min);
    }

    /* This sets a daily reminder on a given date provided by
     * the Calendar variable */
    public void setAlert(Calendar cal) {

        Intent alertReceiver = new Intent(getApplicationContext(), AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alertReceiver, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }
}