package saha.joydeep.emicalculator.ui.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.RealmList;
import saha.joydeep.emicalculator.R;
import saha.joydeep.emicalculator.realm.RealmLoan;

/**
 * Created by joydeep on 09/05/17.
 */

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private RealmList<RealmLoan> itemsData;

    MainActivityAdapter(RealmList<RealmLoan> itemsData) {
        this.itemsData = itemsData;
    }

    @Override
    public MainActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_main_activity, parent, false);
        return new ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.principle.setText(String.valueOf(itemsData.get(position).principle));
        viewHolder.tenure.setText(String.valueOf(itemsData.get(position).tenure));
    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView principle, tenure;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            principle = (TextView) itemLayoutView.findViewById(R.id.tv_principle);
            tenure = (TextView) itemLayoutView.findViewById(R.id.tv_tenure);
        }
    }
}