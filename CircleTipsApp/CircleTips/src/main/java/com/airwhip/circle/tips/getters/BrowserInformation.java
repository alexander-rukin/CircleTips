package com.airwhip.circle.tips.getters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Browser;

/**
 * Created by Whiplash on 07.03.14.
 */
public class BrowserInformation {
    private static final Uri CHROME = Uri.parse("content://com.android.chrome.browser/bookmarks");
    private static final Uri DEFAULT = Browser.BOOKMARKS_URI;

    private static final String HISTORY = Browser.BookmarkColumns.BOOKMARK + " = 0";
    private static final String BOOKMARK = Browser.BookmarkColumns.BOOKMARK + " = 1";

    private static final String[] PROJECTION = new String[]{Browser.BookmarkColumns.TITLE, Browser.BookmarkColumns.URL};

    private static final String HISTORY_TAG_BEGIN = "<history>\n";
    private static final String HISTORY_TAG_END = "</history>\n";

    private static final String BOOKMARK_TAG_BEGIN = "<bookmark>\n";
    private static final String BOOKMARK_TAG_END = "</bookmark>\n";

    private static final String ITEM_TAG_BEGIN = "\t<item>\n";
    private static final String ITEM_TAG_END = "\t</item>\n";

    private static final String TITLE_TAG_BEGIN = "\t\t<title>";
    private static final String TITLE_TAG_END = "</title>\n";

    private static final String URL_TAG_BEGIN = "\t\t<url>";
    private static final String URL_TAG_END = "</url>\n";

    public static StringBuilder getHistory(Context context) {
        StringBuilder result = new StringBuilder(HISTORY_TAG_BEGIN);

        // get history from default browser
        result.append(getInformationFromDataBase(context.getContentResolver().query(DEFAULT, PROJECTION, HISTORY, null, null)));
        // from chrome
        result.append(getInformationFromDataBase(context.getContentResolver().query(CHROME, PROJECTION, HISTORY, null, null)));

        return result.append(HISTORY_TAG_END);
    }

    public static StringBuilder getBookmarks(Context context) {
        StringBuilder result = new StringBuilder(BOOKMARK_TAG_BEGIN);

        // get bookmarks from chrome
        result.append(getInformationFromDataBase(context.getContentResolver().query(CHROME, PROJECTION, BOOKMARK, null, null)));

        return result.append(BOOKMARK_TAG_END);
    }

    private static StringBuilder getInformationFromDataBase(Cursor cursor) {
        StringBuilder result = new StringBuilder();
        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                result.append(ITEM_TAG_BEGIN);
                result.append(TITLE_TAG_BEGIN + cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.TITLE)) + TITLE_TAG_END);
                result.append(URL_TAG_BEGIN + cursor.getString(cursor.getColumnIndex(Browser.BookmarkColumns.URL)) + URL_TAG_END);
                result.append(ITEM_TAG_END);
                cursor.moveToNext();
            }
            cursor.close();
        }
        return result;
    }
}
