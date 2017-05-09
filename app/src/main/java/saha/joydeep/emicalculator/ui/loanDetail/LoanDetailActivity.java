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
import saha.joydeep.emicalculator.listener.RecyclerItemClickListener;
import saha.joydeep.emicalculator.model.LoanDetailItem;
import saha.joydeep.emicalculator.ui.main.MainActivityAdapter;

/**
 * Created by joydeep on 09/05/17.
 */

public class LoanDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.rv_loan_detail)
    RecyclerView mRecyclerView;

    private ArrayList<LoanDetailItem> mArrayList = new ArrayList<>();
    private LoanDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_detail);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LoanDetailAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        loadData();
    }

    private void loadData() {

    }
}
