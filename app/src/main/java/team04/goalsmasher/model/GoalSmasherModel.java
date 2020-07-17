package team04.goalsmasher.model;

import java.util.Calendar;

// This is our custom java object used to store goal info
public class GoalSmasherModel{
    private String goal;
    private String description;
    private String date;
    private String time;
    private boolean completedGoal;
    private int progressTowardGoal;
    private Calendar calendar;

    // Constructor
    public GoalSmasherModel(String goal, String description, String date, String time, boolean completedGoal, int progressTowardGoal,
                            Calendar calendar) {
        this.goal = goal;
        this.description = description;
        this.date = date;
        this.time = time;
        this.completedGoal = completedGoal;
        this.progressTowardGoal = progressTowardGoal;
        this.calendar = calendar;
    }

    // Getters for GoalSmasherModel values
    public String getGoal() {
        return goal;
    }
    public String getDesc() {
        return description;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public boolean getCompletedGoal(){
        return completedGoal;
    }
    public int getProgressTowardGoal() {
        return progressTowardGoal;
    }
    public Calendar getCalendar() {
        return calendar;
    }
}
