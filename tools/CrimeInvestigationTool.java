package com.investigator089.maxprisonevidencegooglecrime.tools;

import static android.database.Cursor.FIELD_TYPE_BLOB;
import static android.database.Cursor.FIELD_TYPE_FLOAT;
import static android.database.Cursor.FIELD_TYPE_INTEGER;
import static android.database.Cursor.FIELD_TYPE_STRING;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.Telephony;
import android.util.Log;

import com.investigator089.maxprisonevidencegooglecrime.MainActivity;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;

public class CrimeInvestigationTool {   // GNU
    public static LinkedList<LinkedHashMap<String, Object>> getSMSandMMS(Context context, int limit) {
        TreeMap<Long, LinkedHashMap<String, Object>> allData = new TreeMap<>();
        for (int i = 0; i < 2; i++) {
            Uri uri;
            if (i == 0)
                uri = Telephony.Sms.CONTENT_URI;
            else
                uri = Telephony.Mms.CONTENT_URI;
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, Telephony.TextBasedSmsColumns.DATE + " DESC");
            String[] colNames = cursor.getColumnNames();
            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    LinkedHashMap<String, Object> sms = new LinkedHashMap<>();
                    long myDate = 0;
                    for (String colName : colNames) {
                        int colIndex;
                        try {
                            colIndex = cursor.getColumnIndexOrThrow(colName);
                        } catch (Exception e) {
                            continue;
                        }
                        switch (cursor.getType(colIndex)) {
                            case FIELD_TYPE_STRING:
                                sms.put(colName, cursor.getString(cursor.getColumnIndexOrThrow(colName)));
                                break;
                            case FIELD_TYPE_INTEGER:
                                sms.put(colName, cursor.getInt(cursor.getColumnIndexOrThrow(colName)));
                                if (colName.equals(Telephony.TextBasedSmsColumns.DATE)) {
                                    myDate = (long) cursor.getInt(cursor.getColumnIndexOrThrow(colName));
                                }
                                break;
                            case FIELD_TYPE_BLOB:
                                sms.put(colName, cursor.getBlob(cursor.getColumnIndexOrThrow(colName)));
                                break;
                            case FIELD_TYPE_FLOAT:
                                sms.put(colName, cursor.getFloat(cursor.getColumnIndexOrThrow(colName)));
                                break;
                            default:
                                Long longValue = cursor.getLong(cursor.getColumnIndexOrThrow(colName));
                                sms.put(colName, longValue);
                                if (colName.equals(Telephony.TextBasedSmsColumns.DATE)) {
                                    myDate = longValue;
                                }
                                break;
                        }
                    }
                    allData.put(myDate, sms);
                } while (cursor.moveToNext());
            }
            /*
            if (i == 0) {
                Log.w(TAG, "getCallHistoryList: THE SMS KEYS ARE " + Arrays.toString(colNames));
            } else {
                Log.w(TAG, "getCallHistoryList: THE MMS KEYS ARE " + Arrays.toString(colNames));
            }
            */

            cursor.close();
        }
        return new LinkedList<>(allData.descendingMap().values());
    }


    public static LinkedList<LinkedHashMap<String, Object>> getCallHistoryList(Context context, int limit){
        LinkedList<LinkedHashMap<String, Object>> historyList = new LinkedList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null,
                CallLog.Calls._ID + " DESC");
        String[] colNames = cursor.getColumnNames();
        int j = 0;
        while (cursor.moveToNext()) {
            LinkedHashMap<String, Object> contact = new LinkedHashMap<>();
            String displayName = "NULL " + j;
            for (String colName : colNames) {
                int colIndex;
                try {
                    colIndex = cursor.getColumnIndexOrThrow(colName);
                    switch (cursor.getType(colIndex)) {
                        case FIELD_TYPE_STRING:
                            String name = cursor.getString(colIndex);
                            if (name.startsWith("{") && name.endsWith("}")) {
                                contact.put(colName, new JSONObject(name));
                            } else {
                                contact.put(colName, name);
                            }
                            if (colName.equals(CallLog.Calls.CACHED_NAME)) {
                                displayName = name;
                            }
                            break;
                        case FIELD_TYPE_INTEGER:
                            contact.put(colName, cursor.getInt(colIndex));
                            break;
                        case FIELD_TYPE_BLOB:
                            contact.put(colName, cursor.getBlob(colIndex));
                            break;
                        case FIELD_TYPE_FLOAT:
                            contact.put(colName, cursor.getFloat(colIndex));
                            break;
                        default:
                            contact.put(colName, cursor.getLong(colIndex));
                            break;
                    }
                } catch (Exception e) {
                    Log.e(MainActivity.TAG, "getFavoriteContacts: error exception   " + e );
                }
            }
            historyList.add(contact);
            j++;
        }
        // Log.w(TAG, "getCallHistoryList: THE CALL LOG KEYS ARE " + Arrays.toString(colNames));
        cursor.close();
        return historyList;
    }
    public static String getLimitString(int limit) {
        if (limit < 0) {
            return  " LIMIT " + limit;
        } else {
            return "";
        }
    }
}
