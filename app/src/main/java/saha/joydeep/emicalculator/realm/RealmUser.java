package saha.joydeep.emicalculator.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by joydeep on 09/05/17.
 */

public class RealmUser extends RealmObject {

    public String phoneNumber;

    public RealmList<RealmLoan> loanRealmList;

}
