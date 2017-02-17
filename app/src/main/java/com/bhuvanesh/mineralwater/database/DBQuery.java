package com.bhuvanesh.mineralwater.database;

/**
 * Created by Lenovo on 06/02/2017.
 */

public interface DBQuery {

    String TABLE_NAME_PROFILE = "Customer_Profile";

    String CREATE_TABLE_PROFILE = "CREATE TABLE " + TABLE_NAME_PROFILE + "(_id varchar PRIMARY KEY, ProfilePicURI varchar, " +
            "FirstName varchar, LastName varchar, MobileNo varchar, PricePerCan float, ProfileCreatedTime long);";
    String GET_CUSTOMER_PROFILE = "SELECT * FROM " + TABLE_NAME_PROFILE;


    String ALTER_TABLE_PROFILE_VERSION_1 = "ALTER TABLE " + TABLE_NAME_PROFILE + " ADD COLUMN Email varchar";

}
