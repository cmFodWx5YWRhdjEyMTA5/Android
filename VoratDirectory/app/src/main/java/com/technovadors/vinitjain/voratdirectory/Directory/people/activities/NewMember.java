
package com.technovadors.vinitjain.voratdirectory.Directory.people.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.easing.linear.Linear;
import com.technovadors.vinitjain.voratdirectory.R;
import com.technovadors.vinitjain.voratdirectory.utils.list_selection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class NewMember extends AppCompatActivity {

    LinearLayout email_layout,mobile_layout,marital_status_layout,education_layout,dob_layout,name_layout;
    TextView email_box,mobile_box,marital_status_box,education_box,dob_box,name_box;
    ImageView male,female;
    JSONObject family,member;
    boolean is_hof=false;
    JSONArray education_array=new JSONArray(),education_masters_array=new JSONArray(),education_grad_array=new JSONArray();
    final int GET_EDUCATION_PROFILE=1,GET_EDUCATION_MASTER_PROFILE=2,GET_GRADUATE_EDUCATION_PROFILE=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_new_member);
        initialize_views();
        setup_flow();
        setup_clicks();
    }

    void initialize_views()
    {
        name_layout=findViewById(R.id.name_layout);
        dob_layout=findViewById(R.id.dob_layout);
        education_layout=findViewById(R.id.education_layout);
        marital_status_layout=findViewById(R.id.marital_status_layout);
        mobile_layout=findViewById(R.id.mobile_layout);
        email_layout=findViewById(R.id.email_layout);

        email_box=findViewById(R.id.email_box);
        mobile_box=findViewById(R.id.mobile_box);
        marital_status_box=findViewById(R.id.marital_status_box);
        education_box=findViewById(R.id.education_box);
        dob_box=findViewById(R.id.dob_box);
        name_box=findViewById(R.id.name_box);

        male=findViewById(R.id.male);
        female=findViewById(R.id.female);

        try
        {
            family=new JSONObject(getSharedPreferences("family_input", MODE_PRIVATE).getString("data",""));
            member=new JSONObject();
            if(getIntent().hasExtra("hof"))
            {
                is_hof=true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setup_flow()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
               name_layout.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dob_layout.setVisibility(View.VISIBLE);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                education_layout.setVisibility(View.VISIBLE);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    public void run() {
                                        marital_status_layout.setVisibility(View.VISIBLE);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            public void run() {
                                                mobile_layout.setVisibility(View.VISIBLE);
                                                Handler handler = new Handler();
                                                handler.postDelayed(new Runnable() {
                                                    public void run() {
                                                        email_layout.setVisibility(View.VISIBLE);
                                                    }
                                                }, 500);
                                            }
                                        }, 500);
                                    }
                                }, 500);
                            }
                        }, 500);
                    }
                }, 500);
            }
        }, 500);

    }

    void setup_clicks()
    {
        setup_gender_click();
        education_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(NewMember.this, list_selection.class);
                intent.putExtra("data", setup_education_array());
                intent.putExtra("title", getString(R.string.select_education));
                intent.putExtra("image", 0);
                intent.putExtra("type", -1);
                intent.putExtra("title_key", "value");
                startActivityForResult(intent, GET_EDUCATION_PROFILE);
            }
        });

        dob_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    void setup_gender_click()
    {
        male.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                female.setImageResource(R.drawable.female_grey);
                male.setImageResource(R.drawable.male_black);
                try
                {
                    member.put("gender","Male");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                female.setImageResource(R.drawable.female_black);
                male.setImageResource(R.drawable.male_grey);
                try
                {
                    member.put("gender","Female");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    String setup_education_array()
    {
        education_array=new JSONArray();
        if(education_array.length()==0)
        {
            String[] data=new String[]{"Gaav Ki Padhai","10th Pass","12th Pass","Graduate","Post Graduate","Phd"};
            for(int i=0;i<data.length;i++)
            {
                try
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("value",data[i]);
                    education_array.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            data=null;
        }
        return education_array.toString();
    }

    String setup_education_graduation_array(boolean post_grad_selected)
    {
        education_grad_array=new JSONArray();
        if(education_grad_array.length()==0)
        {
            String[] data=new String[]{"B.Com","B.B.A.","B.A.F.","B.Sc","Engineer [B.E. / B.Tech.]","Doctor [M.B.B.S. / B.D.S / etc..]","Law [ LL.B.]","Charted Accountant [ C.A.]","Arichitect [ B.Arch.]","Some Other Course"};
            for(int i=0;i<data.length;i++)
            {
                try
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("value",data[i]);
                    if(post_grad_selected)
                    {
                        jsonObject.put("post_grad","1");
                    }
                    else
                    {
                        jsonObject.put("post_grad","0");
                    }
                    education_grad_array.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            data=null;
        }
        return education_grad_array.toString();
    }


    String setup_education_masters_array()
    {
        education_masters_array=new JSONArray();
        if(education_masters_array.length()==0)
        {
            String[] data=new String[]{"M.Com","M.B.A.","M.Sc","Masters in Engineering [M.S. / M.Tech.]","Masters in Doctory / Surgery  [M.S. / M.D.S. / etc..]","Law [ LL.M. ]","Other"};
            for(int i=0;i<data.length;i++)
            {
                try
                {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("value",data[i]);
                    education_masters_array.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            data=null;
        }
        return education_masters_array.toString();
    }

    void get_profession()
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GET_EDUCATION_PROFILE)
        {
            try
            {
                JSONObject object = new JSONObject(data.getStringExtra("result"));
                String value=object.getString("value");
                JSONObject education_object=new JSONObject();
                education_object.put("basic",value);
                member.put("education",education_object);
                if(value.equalsIgnoreCase("Graduate"))
                {
                    Log.d("stuck","Only Graduation");
                    Intent intent = new Intent(NewMember.this, list_selection.class);
                    intent.putExtra("data", setup_education_graduation_array(false));
                    intent.putExtra("title", getString(R.string.select_education));
                    intent.putExtra("image", 0);
                    intent.putExtra("type", -1);
                    intent.putExtra("title_key", "value");
                    startActivityForResult(intent, GET_GRADUATE_EDUCATION_PROFILE);
                }
                else if(value.equalsIgnoreCase("Post Graduate"))
                {
                    Log.d("stuck","Graduate + Postgrad");
                    Intent intent = new Intent(NewMember.this, list_selection.class);
                    intent.putExtra("data", setup_education_graduation_array(true));
                    intent.putExtra("title", getString(R.string.select_education));
                    intent.putExtra("image", 0);
                    intent.putExtra("type", -1);
                    intent.putExtra("title_key", "value");
                    startActivityForResult(intent, GET_GRADUATE_EDUCATION_PROFILE);
                }
                else
                {
                    get_profession();
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        if(requestCode==GET_GRADUATE_EDUCATION_PROFILE)
        {
            try
            {
                JSONObject education=member.getJSONObject("education");
                JSONObject object = new JSONObject(data.getStringExtra("result"));
                Log.d("stuck",object.toString());
                String value=object.getString("value");
                education.put("graduation",value);
                member.put("education",education);
                if(object.getString("post_grad").equalsIgnoreCase("1"))
                {
                    Intent intent = new Intent(NewMember.this, list_selection.class);
                    intent.putExtra("data", setup_education_masters_array());
                    intent.putExtra("title", getString(R.string.select_education));
                    intent.putExtra("image", 0);
                    intent.putExtra("type", -1);
                    intent.putExtra("title_key", "value");
                    startActivityForResult(intent, GET_EDUCATION_MASTER_PROFILE);
                }
                else get_profession();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(requestCode==GET_EDUCATION_MASTER_PROFILE)
        {
            try
            {
                JSONObject object = new JSONObject(data.getStringExtra("result"));
                String value=object.getString("value");
                JSONObject education=member.getJSONObject("education");
                education.put("post graduation",value);
                member.put("education",education);
                get_profession();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
