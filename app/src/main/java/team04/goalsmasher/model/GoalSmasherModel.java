package team04.goalsmasher.model;

import java.util.Calendar;

public class GoalSmasherModel {
    //06-16-2020 12:43 Ellis, our Model and business logic.
    //06-16-2020 12:46 Ellis, simply added our values.
    private String goal;
    private boolean completedGoal;
    private int progressTowardGoal;
    private Calendar calendar;

    //06-16-2020 12:54 Ellis, constructor to set the values.
    public GoalSmasherModel(String goal, boolean completedGoal, int progressTowardGoal,
                            Calendar calendar) {
        this.goal = goal;
        this.completedGoal = completedGoal;
        this.progressTowardGoal = progressTowardGoal;
        this.calendar = calendar;
    }
    //06-16-2020 12:54 Ellis, getting our values.
    public String getGoal() {
        return goal;
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
