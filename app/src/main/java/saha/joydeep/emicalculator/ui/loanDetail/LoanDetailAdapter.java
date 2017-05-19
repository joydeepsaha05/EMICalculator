package saha.joydeep.emicalculator.ui.loanDetail;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import saha.joydeep.emicalculator.App;
import saha.joydeep.emicalculator.R;
import saha.joydeep.emicalculator.model.LoanDetailItem;

/**
 * Created by joydeep on 09/05/17.
 */

public class LoanDetailAdapter extends RecyclerView.Adapter<LoanDetailAdapter.ViewHolder> {

    private ArrayList<LoanDetailItem> items;
    private DatabaseReference mDatabaseReference;

    LoanDetailAdapter(ArrayList<LoanDetailItem> items) {
        this.items = items;

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("EMI");

        mDatabaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(App.mContext, "Sent to Firebase", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(App.mContext, "Sent to Firebase", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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

        viewHolder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseReference.child(App.PHONE_NUMBER).setValue(items.get(viewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tenure, emi, total;
        CardView cv;

        ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            tenure = (TextView) itemLayoutView.findViewById(R.id.tv_tenure);
            emi = (TextView) itemLayoutView.findViewById(R.id.tv_emi);
            total = (TextView) itemLayoutView.findViewById(R.id.tv_total);
            cv = (CardView) itemLayoutView.findViewById(R.id.cv);
        }
    }
}
