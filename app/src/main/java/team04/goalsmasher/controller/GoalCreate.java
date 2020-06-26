package team04.goalsmasher.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import team04.goalsmasher.R;

public class GoalCreate extends AppCompatActivity {
        final Calendar myCalendar = Calendar.getInstance();
        private EditText edittext;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_create_goal);
                edittext = findViewById(R.id.calenderPopup);
                final TimePicker picker = findViewById(R.id.timePicker);
                picker.setIs24HourView(true);

                //The Set Goal button saves the info inputted by the user
                Button btnGet = findViewById(R.id.set);
                btnGet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                //Gets the selected time from the user
                                int hour, minute;
                                String am_pm;
                                hour = picker.getHour();
                                minute = picker.getMinute();
                                if(hour > 12) {
                                        am_pm = "PM";
                                        hour = hour - 12;
                                }
                                else
                                {
                                        am_pm="AM";
                                }

                                //Info stored as a string
                                EditText g = findViewById(R.id.goal);
                                String goal = g.getText().toString();

                                EditText des = findViewById(R.id.des);
                                String description = des.getText().toString();

                                EditText d = findViewById(R.id.calenderPopup);
                                String date = d.getText().toString();

                                String time = hour +":"+ minute+" "+am_pm;

                                //Toast
                                Context context = getApplicationContext();
                                CharSequence text = "Goal Set!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                        }
                });
                edittext.setOnClickListener(new View.OnClickListener() {
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
                edittext.setText(sdf.format(myCalendar.getTime()));
        }
}