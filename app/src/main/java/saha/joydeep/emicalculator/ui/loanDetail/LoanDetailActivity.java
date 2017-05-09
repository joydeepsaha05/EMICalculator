package saha.joydeep.emicalculator.ui.loanDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import saha.joydeep.emicalculator.R;
import saha.joydeep.emicalculator.model.LoanDetailItem;

/**
 * Created by joydeep on 09/05/17.
 */

public class LoanDetailActivity extends AppCompatActivity {

    private static final double mRate = 0.03;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_loan_detail)
    RecyclerView mRecyclerView;

    private ArrayList<LoanDetailItem> mArrayList = new ArrayList<>();
    private LoanDetailAdapter mAdapter;
    private double mPrinciple, mTenure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LoanDetailAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mPrinciple = getIntent().getDoubleExtra("principle", 0);
        mTenure = getIntent().getDoubleExtra("tenure", 0);

        loadData();
    }

    private void loadData() {
        for (double i = mTenure - 3; i <= mTenure + 3; i++) {
            if (i <= 0) {
                continue;
            }

            double emi = (mPrinciple * mRate * Math.pow(1 + mRate, i)) /
                    (Math.pow(1 + mRate, i) - 1);

            mArrayList.add(new LoanDetailItem(i, emi, i * emi));
        }
        mAdapter.notifyDataSetChanged();
    }
}
