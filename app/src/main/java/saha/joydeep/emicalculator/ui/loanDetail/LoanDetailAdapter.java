package saha.joydeep.emicalculator.ui.loanDetail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import saha.joydeep.emicalculator.R;
import saha.joydeep.emicalculator.model.LoanDetailItem;

/**
 * Created by joydeep on 09/05/17.
 */

public class LoanDetailAdapter extends RecyclerView.Adapter<LoanDetailAdapter.ViewHolder> {

    private ArrayList<LoanDetailItem> items;

    LoanDetailAdapter(ArrayList<LoanDetailItem> items) {
        this.items = items;
    }

    @Override
    public LoanDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_main_activity, parent, false);
        return new LoanDetailAdapter.ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final LoanDetailAdapter.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            title = (TextView) itemLayoutView.findViewById(R.id.title);

        }
    }
}
