package com.technovadors.vinitjain.voratdirectory.Home.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.technovadors.vinitjain.voratdirectory.Directory.Gaav;
import com.technovadors.vinitjain.voratdirectory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vinitjain on 01/02/18.
 */

public class gaavListRecyclerAdapter extends RecyclerView.Adapter<gaavListRecyclerAdapter.gaav_item_holder> {
    Context context;
    JSONArray array;

    public gaavListRecyclerAdapter(Context context, JSONArray array) {
        this.context = context;
        this.array = array;
    }

    @Override
    public gaav_item_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout_home_gaav_item,null);
        gaav_item_holder holder=new gaav_item_holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(gaav_item_holder holder, int position) {
        try
        {
            final JSONObject object=array.getJSONObject(position);
            holder.name.setText(object.getString("gaav"));
            holder.symbol.setText(object.getString("abb"));
            if(object.getString("active").equals("1"))
            {
                holder.symbol.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
                holder.name.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(context, Gaav.class);
                        intent.putExtra("object",object.toString());
                        context.startActivity(intent);
                    }
                });
            }
            else {
                holder.symbol.setTextColor(Color.parseColor("#CCCCCC"));
                holder.name.setTextColor(Color.parseColor("#CCCCCC"));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,R.string.inactive_gaav,Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return array.length();
    }

    class gaav_item_holder extends RecyclerView.ViewHolder {
        TextView symbol, name;
        public gaav_item_holder(View view) {
            super(view);
            symbol = view.findViewById(R.id.symbol);
            name = view.findViewById(R.id.name);
        }
    }
}
