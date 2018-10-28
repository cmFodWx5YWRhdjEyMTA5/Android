package com.technovadors.vinitjain.voratdirectory.Directory.people.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.technovadors.vinitjain.voratdirectory.Directory.people.activities.FamilyPage;
import com.technovadors.vinitjain.voratdirectory.Directory.people.activities.UserProfile;
import com.technovadors.vinitjain.voratdirectory.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vinitjain on 14/02/18.
 */

public class family_list_adapter_recyclerview extends RecyclerView.Adapter<family_list_adapter_recyclerview.holder> {


    private JSONArray array = new JSONArray();
    private Context context;
    private JSONObject object = new JSONObject();

    public family_list_adapter_recyclerview(Context context, JSONArray data) {
        this.array = data;
        this.context = context;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_family_item, null);
        holder holder = new holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final holder holder, int position) {
        try {
            object = array.getJSONObject(position);
            holder.area_gaav.setText(object.getString("gaav_area"));
            holder.hof_name.setText(object.getString("hof_name"));
            holder.name.setText(object.getString("family_name"));

            holder.hof_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        open_user_profile(object.getString("hof_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.hof_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        open_user_profile(object.getString("hof_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        open_family_profile(object.getString("family_id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            holder.view_family_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    View view = holder.itemView;
                    DialogPlus dialog = DialogPlus.newDialog(context)
                            .setContentHolder(new ViewHolder(view))
                            .setGravity(Gravity.BOTTOM)
                            .setCancelable(true)
                            .setExpanded(true)
                            .create();

                    ImageView family_image=view.findViewById(R.id.family_image);
                    family_image.setVisibility(View.VISIBLE);
                    family_image.setImageResource(R.drawable.jain_home);
                    TextView view_photo=view.findViewById(R.id.view_family_photo);
                    view_photo.setVisibility(View.GONE);
                    dialog.show();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }


    class holder extends RecyclerView.ViewHolder {
        CardView container;
        TextView name, area_gaav, hof_name, view_family_photo;
        ImageView hof_image;

        public holder(View view) {
            super(view);
            container = view.findViewById(R.id.container);
            name = view.findViewById(R.id.name);
            area_gaav = view.findViewById(R.id.area_gaav);
            hof_name = view.findViewById(R.id.hof_name);
            view_family_photo = view.findViewById(R.id.view_family_photo);
            hof_image = view.findViewById(R.id.hof_image);

        }
    }

    void open_user_profile(String user_id) {
        Intent intent = new Intent(context, UserProfile.class);
        intent.putExtra("user_id", user_id);
        context.startActivity(intent);
    }

    void open_family_profile(String family_id) {
        Intent intent = new Intent(context, FamilyPage.class);
        intent.putExtra("family_id", family_id);
        context.startActivity(intent);
    }
}
