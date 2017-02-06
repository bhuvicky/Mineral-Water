package com.bhuvanesh.mineralwater.util;

/**
 * Created by bhuvanesh on 06-02-2017.
 */

public class DataUtil {

    public static float getFloat(String num) {
        float no = 0;
        try {
            no = Float.parseFloat(num);
        } catch (NumberFormatException e) {}

        return no;
    }
}
