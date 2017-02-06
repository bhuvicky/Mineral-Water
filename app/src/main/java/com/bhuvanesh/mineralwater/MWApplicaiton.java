package com.bhuvanesh.mineralwater;

import android.app.Application;

import com.bhuvanesh.mineralwater.database.DBManager;

/**
 * Created by Lenovo on 06/02/2017.
 */

public class MWApplicaiton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        /*connect to DB
        It creates DB with given name & version; & creates all table
        only one time when app installed.*/
        new DBManager(this).connect();
    }
}
