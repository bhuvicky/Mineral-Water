package com.bhuvanesh.mineralwater.profile.dao;

import android.content.ContentValues;
import android.os.Message;

import com.bhuvanesh.mineralwater.MWApplicaiton;
import com.bhuvanesh.mineralwater.database.CUDModel;
import com.bhuvanesh.mineralwater.database.DBManager;
import com.bhuvanesh.mineralwater.database.DBQuery;
import com.bhuvanesh.mineralwater.database.Dao;
import com.bhuvanesh.mineralwater.model.Profile;

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
        System.out.println("inside on update");
        Profile profile = (Profile) model.object;
        ContentValues values = new ContentValues();
        values.put(ID, profile.id);
        values.put(PROFILE_PIC_URI, profile.profilePicUri);
        values.put(FIRST_NAME, profile.firstName);
        values.put(LAST_NAME, profile.lastName);
        values.put(MOBILE_NO, profile.mobileNo);
        values.put(PRICE_PER_CAN, profile.pricePerCan);
        values.put(PROFILE_CREATED_TIME, profile.profileCreatedTime);

        long rowId = new DBManager(MWApplicaiton.getInstance()).replace(DBQuery.TABLE_NAME_PROFILE, values);
        System.out.println("after insert row id = " + rowId);

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

    }
}
