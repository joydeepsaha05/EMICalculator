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
                R.layout.item_loan_detail, parent, false);
        return new LoanDetailAdapter.ViewHolder(itemLayoutView);
    }

    @Override
    public void onBindViewHolder(final LoanDetailAdapter.ViewHolder viewHolder, int position) {
        viewHolder.tenure.setText(String.valueOf(items.get(position).tenure));
        viewHolder.emi.setText(String.format("%.2f", items.get(position).interestPerMonth));
        viewHolder.total.setText(String.format("%.2f", items.get(position).totalPayable));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tenure, emi, total;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tenure = (TextView) itemLayoutView.findViewById(R.id.tv_tenure);
            emi = (TextView) itemLayoutView.findViewById(R.id.tv_emi);
            total = (TextView) itemLayoutView.findViewById(R.id.tv_total);
        }
    }
}
