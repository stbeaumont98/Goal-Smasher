package team04.goalsmasher.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import team04.goalsmasher.R;

public class GoalProgressAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<String> list;
    private Context context;
    private ProgressBar progressBarFooter;

    public GoalProgressAdapter(ArrayList<String> list, Context context, ProgressBar bar) {
        this.list = list;
        this.context = context;
        this.progressBarFooter = bar;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.progress_list_item_layout, null);
        }

        //Handle TextView and display string from your list
        TextView viewGoal= view.findViewById(R.id.viewGoal);
        viewGoal.setText((CharSequence) list.get(position));

        final Button buttonConfirm = view.findViewById(R.id.buttonConfirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonConfirm.setEnabled(false);

                int i = progressBarFooter.getProgress();
                progressBarFooter.setProgress(i + 1);

                if (i == progressBarFooter.getMax() - 1){
                    CharSequence text = "You did it!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });

        return view;
    }
}
