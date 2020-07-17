package team04.goalsmasher.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;

// Custom ListView adapter for our CalendarEvent activity
public class GoalCalendarAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<GoalSmasherModel> listGoals;
    private Context context;

    public GoalCalendarAdapter(ArrayList<GoalSmasherModel> listGoals, Context context) {
        this.listGoals = listGoals;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listGoals.size();
    }

    @Override
    public Object getItem(int pos) {
        return listGoals.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;

        // Inflate our custom list item layout
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.calendar_list_item_layout, null);
        }


        // Get TextView from our layout and set the text to the name of the goal
        TextView viewGoal= view.findViewById(R.id.viewGoal);
        viewGoal.setText((CharSequence) listGoals.get(position).getGoal());

        return view;
    }

}
