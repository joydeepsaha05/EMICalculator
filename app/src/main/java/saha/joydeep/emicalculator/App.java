package saha.joydeep.emicalculator;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by joydeep on 09/05/17.
 */

public class App extends Application {

    public static String PHONE_NUMBER = "";
    public static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        mContext = this;
    }
}
