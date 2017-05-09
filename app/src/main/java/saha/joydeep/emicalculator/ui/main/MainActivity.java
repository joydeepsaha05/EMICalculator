package saha.joydeep.emicalculator.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import saha.joydeep.emicalculator.App;
import saha.joydeep.emicalculator.R;
import saha.joydeep.emicalculator.listener.RecyclerItemClickListener;
import saha.joydeep.emicalculator.realm.RealmLoan;
import saha.joydeep.emicalculator.realm.RealmSingleton;
import saha.joydeep.emicalculator.realm.RealmUser;
import saha.joydeep.emicalculator.ui.loanDetail.LoanDetailActivity;

/**
 * Created by joydeep on 09/05/17.
 */

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener.OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_default)
    TextView mDefaultTV;
    @BindView(R.id.rv_loans)
    RecyclerView mRecyclerView;

    private Realm mRealm = RealmSingleton.getInstance().getRealm();
    private RealmList<RealmLoan> mRealmLoanList;
    private MainActivityAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        RealmUser realmUser = mRealm.where(RealmUser.class)
                .equalTo("phoneNumber", App.PHONE_NUMBER)
                .findFirst();

        if (realmUser != null) {
            mRealmLoanList = realmUser.loanRealmList;
        } else {
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmUser realmUser = realm.createObject(RealmUser.class);
                    realmUser.phoneNumber = App.PHONE_NUMBER;
                    realmUser.loanRealmList = new RealmList<>();
                }
            });
            mRealmLoanList = new RealmList<>();
        }

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_add:
                addLoan();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadData() {
        RealmUser realmUser = mRealm.where(RealmUser.class)
                .equalTo("phoneNumber", App.PHONE_NUMBER)
                .findFirst();

        if (realmUser != null) {
            mRealmLoanList = realmUser.loanRealmList;
        } else {
            mRealmLoanList = new RealmList<>();
        }

        if (mRealmLoanList.size() == 0) {
            mDefaultTV.setVisibility(View.VISIBLE);
        } else {
            if (mAdapter == null) {
                mAdapter = new MainActivityAdapter(mRealmLoanList);
                mRecyclerView.setAdapter(mAdapter);
            } else {
                mAdapter.notifyDataSetChanged();
            }
            mDefaultTV.setVisibility(View.GONE);
        }
    }

    private void addLoan() {
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View sheetView = this.getLayoutInflater().inflate(R.layout.layout_add_loan_sheet, null);
        bottomSheetDialog.setContentView(sheetView);
        bottomSheetDialog.show();

        final TextInputLayout principleLayout = (TextInputLayout) sheetView.findViewById(R.id.input_layout_principle);
        final TextInputEditText principleEditText = (TextInputEditText) sheetView.findViewById(R.id.input_principle);
        final TextInputLayout tenureLayout = (TextInputLayout) sheetView.findViewById(R.id.input_layout_tenure);
        final TextInputEditText tenureEditText = (TextInputEditText) sheetView.findViewById(R.id.input_tenure);
        final Button buttonGo = (Button) sheetView.findViewById(R.id.button_go);

        principleLayout.setHint(getString(R.string.enter_principle));
        tenureLayout.setHint(getString(R.string.enter_tenure));

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String principle = principleEditText.getText().toString().trim();
                final String tenure = tenureEditText.getText().toString().trim();

                if (principle.length() == 0) {
                    principleLayout.setError(getString(R.string.edit_text_error));
                    return;
                } else if (tenure.length() == 0) {
                    tenureLayout.setError(getString(R.string.edit_text_error));
                    return;
                }

                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmUser realmUser = mRealm.where(RealmUser.class)
                                .equalTo("phoneNumber", App.PHONE_NUMBER)
                                .findFirst();

                        if (realmUser == null) {
                            realmUser = realm.createObject(RealmUser.class);
                            realmUser.phoneNumber = App.PHONE_NUMBER;
                            realmUser.loanRealmList = new RealmList<>();
                        }

                        RealmLoan realmLoan = realm.createObject(RealmLoan.class);
                        realmLoan.principle = Double.parseDouble(principle);
                        realmLoan.tenure = Double.parseDouble(tenure);
                        realmUser.loanRealmList.add(realmLoan);
                    }
                });
                bottomSheetDialog.dismiss();
                Intent i = new Intent(MainActivity.this, LoanDetailActivity.class);
                i.putExtra("principle", Double.parseDouble(principle));
                i.putExtra("tenure", Double.parseDouble(tenure));
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemClick(View childView, int position) {
        Intent i = new Intent(MainActivity.this, LoanDetailActivity.class);
        i.putExtra("principle", mRealmLoanList.get(position).principle);
        i.putExtra("tenure", mRealmLoanList.get(position).tenure);
        startActivity(i);
    }
}
