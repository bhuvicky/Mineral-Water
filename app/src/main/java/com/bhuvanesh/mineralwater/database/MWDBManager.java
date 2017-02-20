package com.bhuvanesh.mineralwater.database;

import com.bhuvanesh.mineralwater.exception.MWException;
import com.bhuvanesh.mineralwater.model.Profile;
import com.bhuvanesh.mineralwater.profile.dao.ProfileDao;

import java.util.List;

/**
 * Created by Lenovo on 06/02/2017.
 */

public class MWDBManager {

    public interface OnMWDBManagerListener<T> {
        void onDBManagerSuccess(T obj);
        void onDBManagerError(MWException exception);
    }

    private OnMWDBManagerListener mOnMWDBManagerListener;

    public void setOnMWDBManagerListener(OnMWDBManagerListener listener) {
        mOnMWDBManagerListener = listener;
    }

    public void updateProfile(Profile profile) {
        Dao dao = new ProfileDao();
        dao.setOnDaoOperationListener(new Dao.OnDaoOperationListener() {
            @Override
            public void onDaoOperationSuccess(Object obj) {
                System.out.println("db operation success");
            }

            @Override
            public void onDaoOperationError(MWException exception) {
                System.out.println("db operation fails");
            }
        });
        CUDModel model = new CUDModel();
        model.object = profile;
        dao.execute(Dao.CUDOperationType.UPDATION, model);
    }

    public void getCustomerProfileList(long profileCreatedTime) {
        Dao dao = new ProfileDao();
        dao.setOnDaoOperationListener(new Dao.OnDaoOperationListener<List<Profile>>() {
            @Override
            public void onDaoOperationSuccess(List<Profile> obj) {
                mOnMWDBManagerListener.onDBManagerSuccess(obj);
            }

            @Override
            public void onDaoOperationError(MWException exception) {
                mOnMWDBManagerListener.onDBManagerError(exception);
            }
        });
        CUDModel model = new CUDModel();
        model.object = profileCreatedTime;
        dao.execute(Dao.CUDOperationType.QUERY, model);
    }
}
