package saha.joydeep.emicalculator.realm;

/**
 * Created by joydeep on 09/05/17.
 */

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmSingleton {

    public static final int REALM_SCHEMA_VERSION = 1;
    private static RealmSingleton mInstance = null;
    private Realm realm;

    private RealmSingleton() {
        try {
            realm = Realm.getDefaultInstance();
        } catch (Exception e) {
            RealmConfiguration config = new RealmConfiguration.Builder()
                    .schemaVersion(REALM_SCHEMA_VERSION)
                    .migration(new MyRealmMigration())
                    .build();
            Realm.setDefaultConfiguration(config);
            realm = Realm.getDefaultInstance();
        }
    }

    public static RealmSingleton getInstance() {
        if (mInstance == null) {
            mInstance = new RealmSingleton();
        }
        return mInstance;
    }

    public Realm getRealm() {
        return realm;
    }

}
