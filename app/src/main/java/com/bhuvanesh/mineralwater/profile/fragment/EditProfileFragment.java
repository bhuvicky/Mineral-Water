package com.bhuvanesh.mineralwater.profile.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.RunTimePermissionFragment;
import com.bhuvanesh.mineralwater.model.Profile;
import com.bhuvanesh.mineralwater.util.DataUtil;
import com.bhuvanesh.mineralwater.widget.CircularNetworkImageView;
;
import java.util.concurrent.atomic.AtomicLong;

public class EditProfileFragment extends RunTimePermissionFragment implements TextWatcher  {

    public static final int READ_EXTERNAL_STOREAGE_PERMISSION_CODE = 1;
    public static final int PICK_GALLERY_IMAGE_REQUEST_CODE = 100;

    private EditText mEditTextFirstName, mEditTextLastName, mEditTextMobileNo, mEditTextPrice;
    private TextInputLayout mTextInputFirstName, mTextInputLastName, mTextInputMobileNo, mTextInputPrice;

    private Profile mProfile;
    private CircularNetworkImageView mImageViewProfile;
    private String mImagePath;

    public static EditProfileFragment newInstance(Profile profile) {
        EditProfileFragment fragment = new EditProfileFragment();
        fragment.mProfile = profile != null ? profile : new Profile();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        setHasOptionsMenu(true);

        mEditTextFirstName = (EditText) view.findViewById(R.id.edittext_first_name);
        mEditTextFirstName.addTextChangedListener(this);
        mTextInputFirstName = (TextInputLayout) view.findViewById(R.id.textinput_first_name);

        mEditTextLastName = (EditText) view.findViewById(R.id.edittext_last_name);
        mEditTextLastName.addTextChangedListener(this);
        mTextInputLastName = (TextInputLayout) view.findViewById(R.id.textinput_last_name);

        mEditTextMobileNo = (EditText) view.findViewById(R.id.edittext_mobile_noe);
        mEditTextMobileNo.addTextChangedListener(this);
        mTextInputMobileNo = (TextInputLayout) view.findViewById(R.id.textinput_mobile_no);

        mEditTextPrice = (EditText) view.findViewById(R.id.edittext_price_per_can);
        mEditTextPrice.addTextChangedListener(this);
        mTextInputPrice = (TextInputLayout) view.findViewById(R.id.textinput_price_per_can);

        mImageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(16)
            public void onClick(View view) {
                if (hasPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE})) {
                    loadImageFromGallery();
                } else {
                    requestAppPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STOREAGE_PERMISSION_CODE);
                }
            }
        });

        setProfileDetails();
        return view;
    }

    private void setProfileDetails() {
        mEditTextFirstName.setText(mProfile.firstName != null ? mProfile.firstName : "");
        mEditTextLastName.setText(mProfile.lastName != null ? mProfile.lastName : "");
        mEditTextMobileNo.setText(mProfile.mobileNo != null ? mProfile.mobileNo : "");
        mEditTextPrice.setText(mProfile.pricePerCan != 0f ? String.valueOf(mProfile.pricePerCan) : "0");
    }

    private void saveIntoDB() {
        if (mProfile.id == 0) {
            // only unique within a process
            AtomicLong atomicLong = new AtomicLong();
            mProfile.id = atomicLong.incrementAndGet();
        }
        mProfile.firstName = mEditTextFirstName.getText().toString();
        mProfile.lastName = mEditTextLastName.getText().toString();
        mProfile.mobileNo = mEditTextMobileNo.getText().toString();
        mProfile.pricePerCan = DataUtil.getFloat(mEditTextPrice.getText().toString());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_save, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_save) {
            if (isValid())
                saveIntoDB();
            return true;
        }
        return false;
    }

    private void loadImageFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_GALLERY_IMAGE_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_GALLERY_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Bitmap bitmap = null;

            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImageUri, filePathColumn, null, null, null);
            cursor.moveToFirst();
            mImagePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();
            mImageViewProfile.setImageBitmap(BitmapFactory.decodeFile(mImagePath));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isValid() {
        boolean isValid = true;
        if (TextUtils.isEmpty(mEditTextFirstName.getText().toString())) {
//            mTextInputFirstName.setError(getString(R.string.msg_error_required));
            isValid = false;
        } else if (TextUtils.isEmpty(mEditTextLastName.getText().toString())) {
//            mTextInputLastName.setError(getString(R.string.msg_error_required));
            isValid = false;
        } else if (TextUtils.isEmpty(mEditTextMobileNo.getText().toString())) {
//            mTextInputMobileNo.setError(getString(R.string.m));
            isValid = false;
        } else if (TextUtils.isEmpty(mEditTextPrice.getText().toString())) {
//            mTextInputPrice.setError(getString(R.string.msg_error_required));
            isValid = false;
        }
        return isValid;
    }

    @Override
    protected void onPermissionsGranted(int requestCode) {
        if (requestCode == PICK_GALLERY_IMAGE_REQUEST_CODE)
            loadImageFromGallery();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mTextInputFirstName.setErrorEnabled(false);
        mTextInputLastName.setErrorEnabled(false);
        mTextInputMobileNo.setErrorEnabled(false);
        mTextInputPrice.setErrorEnabled(false);
    }
}
