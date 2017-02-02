package com.bhuvanesh.mineralwater.profile.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.widget.CircularNetworkImageView;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {



    @Override
    public CustomerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomerListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CircularNetworkImageView imageViewProfileIcon;
        private TextView textViewName, textViewMobileNo, textViewTimeStamp;

        ViewHolder(View itemView) {
            super(itemView);

            imageViewProfileIcon = (CircularNetworkImageView) itemView.findViewById(R.id.imageview_profile_icon);
            textViewName = (TextView) itemView.findViewById(R.id.textview_name);
            textViewMobileNo = (TextView) itemView.findViewById(R.id.textview_mobile_no);
            textViewTimeStamp = (TextView) itemView.findViewById(R.id.textview_timestamp);
        }
    }
}
