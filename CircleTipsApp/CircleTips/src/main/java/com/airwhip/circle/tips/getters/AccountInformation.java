package com.airwhip.circle.tips.getters;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

/**
 * Created by Whiplash on 06.03.14.
 */
public class AccountInformation {

    private static final String MAIN_TAG_BEGIN = "<account>\n";
    private static final String MAIN_TAG_END = "</account>\n";

    private static final String ITEM_TAG_BEGIN = "\t<item>\n";
    private static final String ITEM_TAG_END = "\t</item>\n";

    private static final String NAME_TAG_BEGIN = "\t\t<name>";
    private static final String NAME_TAG_END = "</name>\n";

    private static final String TYPE_TAG_BEGIN = "\t\t<type>";
    private static final String TYPE_TAG_END = "</type>\n";

    public static StringBuilder get(Context context) {
        StringBuilder result = new StringBuilder(MAIN_TAG_BEGIN);

        AccountManager am = AccountManager.get(context);
        if (am != null) {
            Account[] accounts = am.getAccounts();
            for (Account ac : accounts) {
                result.append(ITEM_TAG_BEGIN);
                result.append(NAME_TAG_BEGIN + ac.name + NAME_TAG_END);
                result.append(TYPE_TAG_BEGIN + ac.type + TYPE_TAG_END);
                result.append(ITEM_TAG_END);
            }
        }

        return result.append(MAIN_TAG_END);
    }

}
