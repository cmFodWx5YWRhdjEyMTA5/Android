package com.technovadors.vinitjain.voratdirectory.utils;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technovadors.vinitjain.voratdirectory.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;

public class list_selection extends AppCompatActivity {
    ImageButton back;
    ListView list;
    TextView title;

    JSONArray array = new JSONArray();
    int type = 0;
    int image = 0;
    String title_key = "";
    String desc_key = "";
    String image_key = "";
    adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selection);
        init_views();
        init_data();
        adapter=new adapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                try
                {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",array.getJSONObject(position).toString());
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    void init_views() {
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        list = findViewById(R.id.list);
    }

    void init_data() {
        try
        {
            back.setVisibility(View.GONE);
            title.setText(getIntent().getStringExtra("title"));
            array = new JSONArray(getIntent().getStringExtra("data"));
            image = getIntent().getIntExtra("image", 0);
            type = getIntent().getIntExtra("type", 0);
            title_key = getIntent().getStringExtra("title_key");
            if(type==0)
            {
                desc_key = getIntent().getStringExtra("desc_key");
            }
            if(image!=0)
            {
                image_key=getIntent().getStringExtra("image_key");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    class adapter extends BaseAdapter {
        @Override
        public int getCount() {
            return array.length();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view= LayoutInflater.from(list_selection.this).inflate(R.layout.layout_list_selection,null);
            holder holder=new holder(view);

            try
            {
                JSONObject object=array.getJSONObject(position);
                holder.title.setText(object.getString(title_key));

                if(image!=0)
                {
                    Picasso.get().load((new JSONArray(getIntent().getStringExtra("data")).getJSONObject(position).getString(image_key)))
                            .resize(60, 60)
                            .centerCrop().into(holder.image_view);
                }

                if(type==0)
                {
                    holder.desc.setText(object.getString(desc_key));
                }
            }
            catch (Exception e)
            {

            }

            return view;
        }

        class holder
        {
            TextView title, desc;
            ImageView image_view;

            holder(View view) {
                title = view.findViewById(R.id.title);
                desc = view.findViewById(R.id.desc);
                image_view = view.findViewById(R.id.image);


                if(image==0)
                {
                    image_view.setVisibility(View.GONE);
                }
                else image_view.setVisibility(View.VISIBLE);

                if(type==0)
                {
                    desc.setVisibility(View.VISIBLE);
                }
                else desc.setVisibility(View.GONE);
            }
        }
    }


}
