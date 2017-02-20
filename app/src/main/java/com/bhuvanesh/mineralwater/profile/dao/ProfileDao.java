package com.bhuvanesh.mineralwater.profile.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Message;

import com.bhuvanesh.mineralwater.MWApplicaiton;
import com.bhuvanesh.mineralwater.database.CUDModel;
import com.bhuvanesh.mineralwater.database.DBManager;
import com.bhuvanesh.mineralwater.database.DBQuery;
import com.bhuvanesh.mineralwater.database.Dao;
import com.bhuvanesh.mineralwater.model.Profile;
import com.bhuvanesh.mineralwater.util.LoggerUtil;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Lenovo on 07/02/2017.
 */

public class ProfileDao extends Dao {

    private static final String ID = "_id";
    private static final String PROFILE_PIC_URI = "ProfilePicURI";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";
    private static final String MOBILE_NO = "MobileNo";
    private static final String PRICE_PER_CAN = "PricePerCan";
    private static final String PROFILE_CREATED_TIME = "ProfileCreatedTime";

    @Override
    protected void insert(CUDModel model) {

    }

    @Override
    protected void update(CUDModel model) {
        Profile profile = (Profile) model.object;
        ContentValues values = new ContentValues();
        values.put(ID, profile.id);
        values.put(PROFILE_PIC_URI, profile.profilePicUri);
        values.put(FIRST_NAME, profile.firstName);
        values.put(LAST_NAME, profile.lastName);
        values.put(MOBILE_NO, profile.mobileNo);
        values.put(PRICE_PER_CAN, profile.pricePerCan);
        values.put(PROFILE_CREATED_TIME, profile.profileCreatedTime);
        values.put("Email", "bhuvi@hs.com");

        long rowId = new DBManager(MWApplicaiton.getInstance()).replace(DBQuery.TABLE_NAME_PROFILE, values);
        System.out.println("log after insert row id = " + rowId);

        long count = new DBManager(MWApplicaiton.getInstance()).getTableRowCount(DBQuery.TABLE_NAME_PROFILE);
        System.out.println("log table row count = " + count);

        Message msg = new Message();
        if (rowId > 0) {
            msg.what = HANDLER_SUCCESS_MESSAGE;
            msg.obj = rowId;
        } else {
            msg.what= HANDLER_ERROR_MESSAGE;
        }
        mHandler.handleMessage(msg);
    }

    @Override
    protected void delete(CUDModel model) {

    }

    @Override
    protected void query(CUDModel model) {
        List<Profile> profileList = new ArrayList<>();
        long time = (long) model.object;
        Cursor cursor;
        if (time == 0)
            cursor = new DBManager(MWApplicaiton.getInstance()).select(DBQuery.GET_CUSTOMER_PROFILE);
        else
            cursor = new DBManager(MWApplicaiton.getInstance()).select(String.format(Locale.getDefault(), DBQuery.GET_PROFILE_ON_DATE, time));

        new DBManager(MWApplicaiton.getInstance()).printCursor(cursor);

        try {
            while (cursor.moveToNext()) {
                Profile profile = new Profile();
                profile.id = cursor.getLong(cursor.getColumnIndex(ID));
                profile.profilePicUri = cursor.getString(cursor.getColumnIndex(PROFILE_PIC_URI));
                profile.firstName = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
                profile.lastName = cursor.getString(cursor.getColumnIndex(LAST_NAME));
                profile.mobileNo = cursor.getString(cursor.getColumnIndex(MOBILE_NO));
                profile.pricePerCan = cursor.getFloat(cursor.getColumnIndex(PRICE_PER_CAN));
                profile.profileCreatedTime = cursor.getLong(cursor.getColumnIndex(PROFILE_CREATED_TIME));
                profileList.add(profile);
            }
        } finally {
            cursor.close();
            cursor = null;
        }

        Message msg = new Message();
        if (profileList.size() > 0) {
            msg.what = HANDLER_SUCCESS_MESSAGE;
            msg.obj = profileList;
        } else {
            msg.what = HANDLER_ERROR_MESSAGE;
        }
        mHandler.handleMessage(msg);
    }
}
