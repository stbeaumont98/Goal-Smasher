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

public class GoalManagerAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<GoalSmasherModel> listGoals;
    private Context context;
    private StoreDataModel storeDataModel;
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
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.manager_list_item_layout, null);
        }

        storeDataModel = new StoreDataModel(context);

        //Handle TextView and display string from your list
        TextView viewGoal= view.findViewById(R.id.viewGoal);
        viewGoal.setText((CharSequence) listGoals.get(position).getGoal());

        final Button buttonEdit = view.findViewById(R.id.buttonEdit);
        final Button buttonDelete = view.findViewById(R.id.buttonDelete);

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoalCreate.class);
                intent.putExtra("pos", position);
                context.startActivity(intent);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listGoals.remove(position);
                storeDataModel.saveData(listGoals);
                goalManager.updateData();
                notifyDataSetChanged();
            }
        });

        return view;
    }

    public interface GoalManager {
        void updateData();
    }
}
