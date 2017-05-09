package saha.joydeep.emicalculator.realm;

/**
 * Created by joydeep on 09/05/17.
 */

import io.realm.DynamicRealm;
import io.realm.RealmMigration;

public class MyRealmMigration implements RealmMigration {

    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {

        //RealmSchema schema = realm.getSchema();

    }
}