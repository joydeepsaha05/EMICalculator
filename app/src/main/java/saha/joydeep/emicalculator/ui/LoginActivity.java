package saha.joydeep.emicalculator.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.truecaller.android.sdk.ITrueCallback;
import com.truecaller.android.sdk.TrueButton;
import com.truecaller.android.sdk.TrueClient;
import com.truecaller.android.sdk.TrueError;
import com.truecaller.android.sdk.TrueProfile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import saha.joydeep.emicalculator.App;
import saha.joydeep.emicalculator.R;
import saha.joydeep.emicalculator.ui.main.MainActivity;

/**
 * Created by joydeep on 09/05/17.
 */

public class LoginActivity extends AppCompatActivity implements ITrueCallback {

    @BindView(R.id.edit_text_phone)
    EditText mPhoneEditText;
    @BindView(R.id.com_truecaller_android_sdk_truebutton)
    TrueButton mTrueButton;
    @BindView(R.id.tv_true_not_found)
    TextView mTrueErrorTV;

    private TrueClient mTrueClient;

    private String mTruecallerRequestNonce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        if (mTrueButton.isUsable()) {
            mTrueClient = new TrueClient(this, this);
            mTrueButton.setTrueClient(mTrueClient);
        } else {
            mTrueButton.setVisibility(View.GONE);
            mTrueErrorTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mTrueClient != null) {
            mTruecallerRequestNonce = mTrueClient.generateRequestNonce();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (null != mTrueClient && mTrueClient.onActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccesProfileShared(@NonNull final TrueProfile trueProfile) {
        if (mTruecallerRequestNonce.equals(trueProfile.requestNonce)) {
            finishLogin(trueProfile.phoneNumber);
        }
    }

    @Override
    public void onFailureProfileShared(@NonNull final TrueError trueError) {
        Toast.makeText(this, "Failed sharing - Reason: " + trueError.getErrorType(), Toast.LENGTH_LONG).show();
    }

    private void finishLogin(String phoneNumber) {
        App.PHONE_NUMBER = phoneNumber;
        Toast.makeText(this, "Logged in using: " + phoneNumber, Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.button_sign_in)
    public void signIn() {
        String phoneNumber = mPhoneEditText.getText().toString().trim();
        if (phoneNumber.length() == 0) {
            Toast.makeText(this, R.string.enter_valid_number, Toast.LENGTH_LONG).show();
        } else {
            finishLogin(phoneNumber);
        }
    }
}