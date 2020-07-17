package team04.goalsmasher.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import team04.goalsmasher.R;
import team04.goalsmasher.model.GoalSmasherModel;
import team04.goalsmasher.model.StoreDataModel;

// Custom ListView adapter for our ManageGoalsActivity
public class GoalManagerAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<GoalSmasherModel> listGoals;
    private Context context;
    private StoreDataModel data;
    private GoalManager goalManager;

    public GoalManagerAdapter(ArrayList<GoalSmasherModel> listGoals, Context context, GoalManager goalManager) {
        this.listGoals = listGoals;
        this.context = context;
        this.goalManager = goalManager;
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
            view = inflater.inflate(R.layout.manager_list_item_layout, null);
        }


        // Gets an instance of our StoreData class
        data = new StoreDataModel(context);


        // Get TextView from our layout and set the text to the name of the goal
        TextView viewGoal= view.findViewById(R.id.viewGoal);
        viewGoal.setText((CharSequence) listGoals.get(position).getGoal());


        // Get the buttons from our layout so we can set the OnClickListeners
        final Button buttonEdit = view.findViewById(R.id.buttonEdit);
        final Button buttonDelete = view.findViewById(R.id.buttonDelete);


        /* When the "Edit" button is clicked, it will start an intent
         * for our GoalCreate activity and send the specific goal's
         * position in the intent */
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoalCreate.class);
                intent.putExtra("pos", position);
                context.startActivity(intent);
            }
        });


        /* When the "Delete" button is clicked, it removes the specific
         * goal from the list and saves it to internal storage; then
         * the list view is updated */
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listGoals.remove(position);
                data.saveData(listGoals);
                goalManager.updateData();
                notifyDataSetChanged();
            }
        });

        return view;
    }

    // This interface is so we can update data in out ManageGoalsActivity
    public interface GoalManager {
        void updateData();
    }
}
