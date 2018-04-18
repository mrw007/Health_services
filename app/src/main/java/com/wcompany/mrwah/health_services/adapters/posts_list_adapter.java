package com.wcompany.mrwah.health_services.adapters;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wcompany.mrwah.health_services.Entities.Abonne;
import com.wcompany.mrwah.health_services.Entities.Publication;
import com.wcompany.mrwah.health_services.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by mrwah on 4/18/2018.
 */

public class posts_list_adapter extends RecyclerView.Adapter<posts_list_adapter.ViewHolder> {
    private Context mContext;
    public static posts_list_adapter adapter;
    private List<Publication> publicationList;
    RequestOptions option;

    public posts_list_adapter(Context mContext, List<Publication> publicationList) {
        this.mContext = mContext;
        this.publicationList = publicationList;
        adapter = this; //This is an important line, you need this line to keep track the adapter variable
        option = new RequestOptions().centerCrop().placeholder(R.drawable.user).error(R.drawable.user);
    }

    @Override
    public posts_list_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.post_item, parent, false);
        return new posts_list_adapter.ViewHolder(view, mContext, publicationList, parent);
    }

    @Override
    public void onBindViewHolder(posts_list_adapter.ViewHolder holder, int position) {
        try {
            holder.userName.setText(publicationList.get(position).getAbonne().getLogin());
            holder.description.setText(publicationList.get(position).getDescription());
            holder.position.setText(get_position_adress(publicationList.get(position).getPosition_long(), publicationList.get(position).getPosition_lat()));
            holder.tagGroup.setTags(publicationList.get(position).getZone().split(";", -1));
            //Glide holder
            String baseUrl = mContext.getString(R.string.server_link);
            Glide.with(mContext).load(baseUrl + "/" + publicationList.get(position).getAbonne().getImage_src()).apply(option).into(holder.profile_image);
        } catch (Exception e) {
            e.fillInStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return publicationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userName, position, description;
        TagGroup tagGroup;
        ImageView profile_image;

        public ViewHolder(View itemView, Context mContext, List<Publication> publicationList, ViewGroup parent) {
            super(itemView);
            userName = itemView.findViewById(R.id.textViewusername);
            position = itemView.findViewById(R.id.textPosition);
            description = itemView.findViewById(R.id.description);
            tagGroup = itemView.findViewById(R.id.tag_group);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }

    public String get_position_adress(float longitude, float latitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(mContext, Locale.getDefault());

        try {
            if (longitude != 0 && latitude != 0) {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
                if (knownName != null)
                    return knownName + ", " + state + ", " + country;
                else
                    return address + ", " + state + ", " + country;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Non Défini";
    }
}
