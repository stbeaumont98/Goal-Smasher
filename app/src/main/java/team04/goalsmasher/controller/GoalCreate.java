package team04.goalsmasher.controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
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

        // This is the code for our custom toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayShowTitleEnabled(false);


        // Load views from our activity_goal_create xml
        editTextGoal = findViewById(R.id.editTextGoal);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextDate = findViewById(R.id.editTextDate);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        Button btnSetGoal = findViewById(R.id.set);

        /* Loads the list data from internal storage
         * and stores it in our list variable */
        list = data.loadData();

        // By default position will equal the size of the list
        position = list.size();

        /* Checks if we are receiving any info on the intent
         * (if we are editing a goal instead of creating a new goal)
         * and if so, populates the fields with the data */
        Intent i = getIntent();
        if (i.hasExtra("pos")) {
            position = i.getIntExtra("pos", list.size());
            populateFields(position);
        }

        // The Set Goal button saves the info inputted by the user
        btnSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Gets the selected time from the user
                int hour, minute;
                String am_pm;
                hour = timePicker.getHour();
                minute = timePicker.getMinute();

                // Sets the time provided by the user
                myCalendar.set(Calendar.HOUR_OF_DAY, hour);
                myCalendar.set(Calendar.MINUTE, minute);
                myCalendar.set(Calendar.SECOND, 0);
                if (hour > 12) {
                    am_pm = "PM";
                    hour = hour - 12;
                } else {
                    am_pm="AM";
                }

                // Get info from the views
                goal = editTextGoal.getText().toString();
                description = editTextDescription.getText().toString();
                calDate = editTextDate.getText().toString();
                time = hour +":"+ minute+" "+am_pm;


                // Creates a new goal object with the info provided from the user
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
                Toast.makeText(getApplicationContext(), "Goal Set!", Toast.LENGTH_SHORT).show();
                setAlert(myCalendar); //creates the repeating reminder
                finish(); // exit this activity
            }
        });

        // Opens a DatePickerDialog when the user clicks to edit the date
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(GoalCreate.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    // Exit's the activity when the "Back" button is pressed
    public void cancel(View v){
        finish();
    }

    // Updates the text of the date
    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editTextDate.setText(sdf.format(myCalendar.getTime()));
    }

    /* This method is called when you're editing a goal.
     * Fills the text fields and sets the time on the
     * timepicker */
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