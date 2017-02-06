package com.bhuvanesh.mineralwater;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

/**
 * Created by bhuvanesh on 06-02-2017.
 */

public abstract class RunTimePermissionFragment extends BaseFragment {

    protected abstract void onPermissionsGranted(int requestCode);

    protected boolean hasPermissions(String[] permissions) {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (String perm : permissions) {
            permissionCheck += ContextCompat.checkSelfPermission(getActivity(), perm);
        }
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    protected void requestAppPermissions(String[] permissions, int requestCode) {
        requestPermissions(permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int result = PackageManager.PERMISSION_GRANTED;
        for (int res : grantResults) {
            result += res;
        }
        if (grantResults.length > 0 && result == PackageManager.PERMISSION_GRANTED)
            onPermissionsGranted(requestCode);

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
