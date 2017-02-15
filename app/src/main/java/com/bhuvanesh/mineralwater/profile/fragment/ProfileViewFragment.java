package com.bhuvanesh.mineralwater.profile.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvanesh.mineralwater.BaseFragment;
import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.model.Profile;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class ProfileViewFragment extends BaseFragment {

    private Profile mProfile;

    public static ProfileViewFragment newInstance(Profile profile) {
        ProfileViewFragment fragment = new ProfileViewFragment();
        fragment.mProfile = profile;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        setHasOptionsMenu(true);
        setTitle(mProfile.firstName + " " + mProfile.lastName);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_general, menu);
        menu.findItem(R.id.menu_edit).setVisible(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_edit) {
            replace(R.id.rlayout_container, EditProfileFragment.newInstance(mProfile));
            return true;
        }
        return false;
    }
}
