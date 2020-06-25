package team04.goalsmasher.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import team04.goalsmasher.R;

public class GoalCreate extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_create_goal);
                final TimePicker picker = findViewById(R.id.timePicker);
                picker.setIs24HourView(true);
                Button btnGet = findViewById(R.id.set);
                btnGet.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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
                                EditText g = findViewById(R.id.goal);
                                String goal = g.getText().toString();
                                EditText des = findViewById(R.id.des);
                                String description = des.getText().toString();
                                String time = hour +":"+ minute+" "+am_pm;
                                Context context = getApplicationContext();
                                CharSequence text = "Goal Set!";
                                int duration = Toast.LENGTH_SHORT;
                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                        }
                });
        }
        public void cancel(View v){
                finish();
        }
}