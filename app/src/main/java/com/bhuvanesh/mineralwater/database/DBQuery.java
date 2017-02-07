package com.bhuvanesh.mineralwater.database;

/**
 * Created by Lenovo on 06/02/2017.
 */

public interface DBQuery {

    String TABLE_NAME_PROFILE = "Customer_Profile";

    String CREATE_TABLE_PROFILE = "CREATE TABLE " + TABLE_NAME_PROFILE + "(_id long PRIMARY KEY, ProfilePicURI varchar, " +
            "FirstName varchar, LastName varchar, MobileNo varchar, PricePerCan varchar, ProfileCreatedTime varchar);";

}
