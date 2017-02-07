package com.bhuvanesh.mineralwater.database;

import android.os.Looper;
import android.os.Handler;
import android.os.Message;

import com.bhuvanesh.mineralwater.exception.MWException;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * Created by bhuvanesh on 01-02-2017.
 */

public abstract class Dao {

    protected abstract void insert(CUDModel model);
    protected abstract void update(CUDModel model);
    protected abstract void delete(CUDModel model);
    protected abstract void query(CUDModel model);

    protected enum CUDOperationType {
        INSERTION, UPDATION, DELETION, QUERY
    }

    interface OnDaoOperationListener<T> {
        void onDaoOperationSuccess(T obj);
        void onDaoOperationError(MWException exception);
    }

    private OnDaoOperationListener mOnDaoOperationListener;

    public void setOnDaoOperationListener(OnDaoOperationListener listener) {
        mOnDaoOperationListener = listener;
    }
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final int HANDLER_SUCCESS_MESSAGE = 1;
    public static final int HANDLER_ERROR_MESSAGE = 0;

//    To start DB operation in background.
    private ThreadPoolExecutor mThreadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

//    To receive db operation result from background, and send it to manager
    protected Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(final Message msg) {
            post(new Runnable() {
                @Override
                public void run() {
                    if (msg.what == HANDLER_SUCCESS_MESSAGE)
                        mOnDaoOperationListener.onDaoOperationSuccess(msg.obj);
                    else
                        mOnDaoOperationListener.onDaoOperationError(new MWException("Error In DB"));
                }
            });
        }
    };

    protected void execute(final CUDOperationType type, final CUDModel model) {
        synchronized (this) {
            mThreadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    switch (type) {
                        case INSERTION:  insert(model);  break;
                        case UPDATION:   update(model);  break;
                        case DELETION:   delete(model);  break;
                        case QUERY:      query(model);  break;
                    }
                }
            });
        }
    }
}
