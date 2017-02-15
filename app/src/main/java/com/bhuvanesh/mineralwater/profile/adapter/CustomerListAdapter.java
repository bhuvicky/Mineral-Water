package com.bhuvanesh.mineralwater.profile.adapter;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bhuvanesh.mineralwater.R;
import com.bhuvanesh.mineralwater.model.Profile;
import com.bhuvanesh.mineralwater.util.DateUtil;
import com.bhuvanesh.mineralwater.widget.CircularNetworkImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bhuvanesh on 01-02-2017.
 */

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.ViewHolder> {

    public interface OnCustomerItemClickListener {
        void onProfileClick(Profile profile);
    }

    private List<Profile> mProfileList = new ArrayList<>();
    private OnCustomerItemClickListener mOnCustomerItemClickListener;

    public void setOnCustomerItemClickListener(OnCustomerItemClickListener listener) {
        mOnCustomerItemClickListener = listener;
    }

    public void setData(List<Profile> profile) {
        mProfileList = profile;
        notifyDataSetChanged();
    }


    public CustomerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomerListAdapter.ViewHolder holder, int position) {
        Profile item = mProfileList.get(position);
        holder.imageViewProfileIcon.setImageBitmap(BitmapFactory.decodeFile(item.profilePicUri));
        String name = item.firstName + " " + item.lastName;
        holder.textViewName.setText(name);
        holder.textViewMobileNo.setText(item.mobileNo);
        Linkify.addLinks(holder.textViewMobileNo, Linkify.ALL);
//        holder.textViewMobileNo.setAutoLinkMask(Linkify.ALL);
        /*holder.textViewMobileNo.setLinksClickable(true);
        holder.textViewMobileNo.setMovementMethod(LinkMovementMethod.getInstance());*/
        holder.textViewTimeStamp.setText(DateUtil.getFormattedString(item.profileCreatedTime,
                DateUtil.DATE_TIME_FORMAT_TYPE_dd_MM_yyyy));

    }

    @Override
    public int getItemCount() {
        return mProfileList != null ? mProfileList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CircularNetworkImageView imageViewProfileIcon;
        private TextView textViewName, textViewMobileNo, textViewTimeStamp;

        ViewHolder(View itemView) {
            super(itemView);

            imageViewProfileIcon = (CircularNetworkImageView) itemView.findViewById(R.id.imageview_profile_icon);
            textViewName = (TextView) itemView.findViewById(R.id.textview_name);
            textViewMobileNo = (TextView) itemView.findViewById(R.id.textview_mobile_no);
            textViewTimeStamp = (TextView) itemView.findViewById(R.id.textview_timestamp);
            imageViewProfileIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnCustomerItemClickListener.onProfileClick(mProfileList.get(getAdapterPosition()));
                }
            });
        }
    }
}
