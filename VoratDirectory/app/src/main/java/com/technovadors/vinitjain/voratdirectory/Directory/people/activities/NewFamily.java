package com.technovadors.vinitjain.voratdirectory.Directory.people.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;
import com.technovadors.vinitjain.voratdirectory.R;
import com.technovadors.vinitjain.voratdirectory.utils.list_selection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewFamily extends AppCompatActivity {
    ListView gaav_list, samaj_list;
    ImageButton close;
    TextView hide_keyboard, title, save_family_details, save_home_details, pincode_label, process_pincode, area_label, district_state, select_district_state_label, save_firm_name, firm_name_label, process_firm_pincode, firm_area_label, firm_district_state, save_firms, add_more_firms;
    JSONArray array, firms = new JSONArray();
    EditText family_name_box, pincode, residential_details_box, area_box, firm_about_box, firm_name_box, firm_pincode, home_number_box, firm_number_box, firm_email_box, firm_details_box;
    LinearLayout family_name, auto_generated_state, manual_input, select_district_state, home_address_layout, firm_details, firm_name_layout, firm_header_display_layout, firm_address_layout, auto_generated_firm_state, firm_pincode_layout, firm_details_footer;
    JSONObject received;
    ScrollView residential_details, firm_address;
    JSONObject family_details = new JSONObject(), firm_details_object = new JSONObject(), firm_address_object = new JSONObject();
    final static int GET_HOME_STATE_CITY = 1, GET_FIRM_STATE_CITY = 2;

    ProgressDialog progress_dialog;

    boolean fm = false;
    boolean sa = false;
    boolean all_done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.list_selection);
        if (!no_preset_data()) {
            initialize_views();
            get_data();
        } else {
            all_done = true;
            show_bottom_message(getString(R.string.firm_details_saved), false);
        }
    }


    boolean no_preset_data()
    {
        try
        {
            Log.d("stuck",getSharedPreferences("family_input", MODE_PRIVATE).getString("data",""));

            family_details=new JSONObject(getSharedPreferences("family_input", MODE_PRIVATE).getString("data",""));
            if (family_details.has("firms")) {
                return true;
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // http://postalpincode.in/api/pincode/400019
    void initialize_views() {
        gaav_list = findViewById(R.id.gaav_list);
        family_name = findViewById(R.id.family_name);
        save_home_details = findViewById(R.id.save_home_details);
        save_home_details.setVisibility(View.GONE);
        family_name.setVisibility(View.GONE);
        home_address_layout = findViewById(R.id.home_address_layout);
        auto_generated_state = findViewById(R.id.auto_generated_state);
        residential_details = findViewById(R.id.residential_details);
        home_address_layout.setVisibility(View.VISIBLE);
        home_number_box = findViewById(R.id.home_number_box);
        residential_details.setVisibility(View.GONE);
        district_state = findViewById(R.id.district_state);
        manual_input = findViewById(R.id.manual_input);
        manual_input.setVisibility(View.GONE);
        firm_email_box = findViewById(R.id.firm_email_box);
        firm_number_box = findViewById(R.id.firm_number_box);
        residential_details_box = findViewById(R.id.residential_details_box);
        area_box = findViewById(R.id.area_box);
        area_label = findViewById(R.id.area_label);
        select_district_state = findViewById(R.id.select_district_state);
        select_district_state_label = findViewById(R.id.select_district_state_label);
        auto_generated_state.setVisibility(View.GONE);
        hide_keyboard = findViewById(R.id.hide_keyboard);
        pincode = findViewById(R.id.pincode);
        process_pincode = findViewById(R.id.process_pincode);
        pincode_label = findViewById(R.id.pincode_label);
        family_name_box = findViewById(R.id.family_name_box);
        firm_area_label = findViewById(R.id.firm_area_label);
        samaj_list = findViewById(R.id.samaj_list);
        save_family_details = findViewById(R.id.save_family_details);
        close = findViewById(R.id.close);
        title = findViewById(R.id.title);
        firm_district_state = findViewById(R.id.firm_district_state);
        progress_dialog = new ProgressDialog(NewFamily.this);
        progress_dialog.setCancelable(false);
        firm_details_footer = findViewById(R.id.firm_details_footer);
        firm_details_box = findViewById(R.id.firm_details_box);

        save_firms = findViewById(R.id.save_firms);
        add_more_firms = findViewById(R.id.add_more_firms);
        firm_details = findViewById(R.id.firm_details);
        firm_details.setVisibility(View.GONE);
        firm_name_layout = findViewById(R.id.firm_name_layout);
        firm_name_layout.setVisibility(View.VISIBLE);
        save_firm_name = findViewById(R.id.save_firm_name);
        firm_about_box = findViewById(R.id.firm_about_box);
        firm_name_box = findViewById(R.id.firm_name_box);
        firm_name_label = findViewById(R.id.firm_name_label);
        firm_header_display_layout = findViewById(R.id.firm_header_display_layout);
        firm_address = findViewById(R.id.firm_address);
        firm_address_layout = findViewById(R.id.firm_address_layout);
        auto_generated_firm_state = findViewById(R.id.auto_generated_firm_state);
        firm_pincode_layout = findViewById(R.id.firm_pincode_layout);
        firm_pincode = findViewById(R.id.firm_pincode);

        process_firm_pincode = findViewById(R.id.process_firm_pincode);


        progress_dialog.setMessage(getResources().getString(R.string.wait));
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        hide_keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(hide_keyboard.getWindowToken(), 0);
            }
        });
        try {
            family_details.put("status", "in-complete");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    void get_data() {
        String url = "http://our-rajasthan.com.cp-13.bigrockservers.com/apis/family_input_init.php";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String server_respose = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                received = new JSONObject(server_respose);
                                getSharedPreferences("family_input", MODE_PRIVATE).edit().putString("received_data", received.toString()).commit();
                                ArrayList gaavs = new ArrayList<>();
                                array = received.getJSONArray("gaav");
                                for (int i = 0; i < array.length(); i++) {
                                    gaavs.add(array.getJSONObject(i).getString("name"));
                                }
                                ArrayAdapter adapter = new ArrayAdapter(NewFamily.this, R.layout.selectable_list_item_big, R.id.text, gaavs);
                                gaav_list.setVisibility(View.VISIBLE);
                                gaav_list.setAdapter(adapter);
                                gaav_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        try {
                                            JSONObject gaav_object = array.getJSONObject(position);
                                            add_gaav_details(gaav_object);
                                            gaav_list.setVisibility(View.GONE);
                                            take_samaj_details();
                                            title.setText(R.string.select_samaj);
                                        } catch (JSONException e) {
                                            show_bottom_message(e.toString(), false);
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } catch (Exception e) {

                            }
                        }
                    });
                }
            }
        });
    }

    void add_gaav_details(JSONObject gaav_object) {
        try {
            family_details.put("gaav_details", gaav_object);
            getSharedPreferences("family_input", MODE_PRIVATE).edit().putString("data", family_details.toString()).commit();
            back_track_options.add(getResources().getString(R.string.edit_gaav));
            show_bottom_message(getResources().getString(R.string.gaav_details_saved_enter_samaj_details), true);
            //Toast.makeText(NewFamily.this,R.string.gaav_details_saved_enter_samaj_details,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void add_samaj_details(JSONObject gaav_object) {
        try {
            family_details.put("samaj_details", gaav_object);
            getSharedPreferences("family_input", MODE_PRIVATE).edit().putString("data", family_details.toString()).commit();
            show_bottom_message(getResources().getString(R.string.samaj_details_saved_enter_family_details), false);
            // Toast.makeText(NewFamily.this,R.string.samaj_details_saved_enter_family_details,Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void take_samaj_details() {
        try {
            gaav_list.setVisibility(View.GONE);
            residential_details.setVisibility(View.GONE);
            family_name.setVisibility(View.GONE);
            samaj_list.setVisibility(View.VISIBLE);
            close.setVisibility(View.GONE);
            close.setImageResource(R.drawable.vector_arrow_drop_down);
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    show_back_tracker();
                }
            });
            title.setText(R.string.select_samaj);
            array = received.getJSONArray("samaj");
            ArrayList samajs = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                samajs.add(array.getJSONObject(i).getString("name"));
            }
            ArrayAdapter adapter = new ArrayAdapter(NewFamily.this, R.layout.selectable_list_item_big, R.id.text, samajs);
            samaj_list.setAdapter(adapter);
            samaj_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    JSONObject samaj_object = null;
                    try {
                        samaj_object = array.getJSONObject(position);
                        samaj_list.setVisibility(View.GONE);
                        fm = true;
                        add_samaj_details(samaj_object);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    void take_family_name() {
        save_home_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!residential_details_box.getText().toString().isEmpty()) {
                    try {
                        home_address.put("address", residential_details_box.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    show_bottom_message(getString(R.string.enter_your_home_address), false);
                    return;
                }


                if (!home_number_box.getText().toString().isEmpty()) {
                    try {
                        home_address.put("home_contact", home_number_box.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    show_bottom_message(getString(R.string.home_number_placeholder), false);
                    return;
                }


                if (manual_input.getVisibility() == View.VISIBLE) {
                    if (!area_box.getText().toString().isEmpty()) {
                        try {
                            //family_details.put("area", area_box.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (all_address_details_set()) {
                    try {
                        save_home_address_details();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        gaav_list.setVisibility(View.GONE);
        samaj_list.setVisibility(View.GONE);
        residential_details.setVisibility(View.GONE);
        family_name.setVisibility(View.VISIBLE);
        title.setText(getResources().getString(R.string.family_name));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            family_name_box.setShowSoftInputOnFocus(true);
        }
        family_name_box.requestFocus();

        save_family_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!family_name_box.getText().toString().isEmpty()) {
                    add_family_name(family_name_box.getText().toString());
                } else {
                    show_bottom_message(getResources().getString(R.string.family_name_empty), true);
                }
            }
        });
    }

    void add_family_name(String name) {
        try {
            family_details.put("family_name", name);
            getSharedPreferences("family_input", MODE_PRIVATE).edit().putString("data", family_details.toString()).commit();
            sa = true;
            show_bottom_message(getResources().getString(R.string.family_name_saved_enter_home_address), false);
        } catch (JSONException e) {
            show_bottom_message(e.toString(), false);
            e.printStackTrace();
        }
    }

    JSONArray pin_array;
    JSONObject home_address = new JSONObject();

    void take_family_address() {
        gaav_list.setVisibility(View.GONE);
        samaj_list.setVisibility(View.GONE);
        family_name.setVisibility(View.GONE);
        residential_details.setVisibility(View.VISIBLE);
        home_address_layout.setVisibility(View.GONE);
        pincode.requestFocus();
        title.setText(getResources().getString(R.string.home_address));
        process_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pincode.getText().toString().length() == 6) {
                    get_pincode(pincode.getText().toString(), GET_HOME_STATE_CITY);
                } else {
                    show_bottom_message(getString(R.string.pincode_validation), true);
                }
            }
        });
    }

    boolean all_address_details_set() {
        if (!home_address.has("pincode_related")) {
            Toast.makeText(NewFamily.this, getString(R.string.enter_your_pincode), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    void save_home_address_details() {
        try {
            family_details.put("home", home_address);
            getSharedPreferences("family_input", MODE_PRIVATE).edit().putString("data", family_details.toString()).commit();
            Log.d("stuck", family_details.toString());
            hide_home_address_layout();

            AlertDialog.Builder builder = new AlertDialog.Builder(NewFamily.this);
            builder.setCancelable(false);
            builder.setTitle(getString(R.string.bottom_alert_header));
            String business_profiles[] = getResources().getStringArray(R.array.business_profiles);
            ArrayAdapter adapter = new ArrayAdapter(NewFamily.this, android.R.layout.simple_list_item_1, business_profiles);
            builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == 0) {
                        title.setText(R.string.intermediate_screen_business_details);
                        take_firm_details();
                    } else {
                        try {
                            family_details.put("firms", firms);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            builder.show();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void hide_home_address_layout() {
        residential_details.setVisibility(View.GONE);
    }

    void take_firm_details() {
        hide_home_address_layout();
        firm_details.setVisibility(View.VISIBLE);
        firm_header_display_layout.setVisibility(View.GONE);
        firm_details_footer.setVisibility(View.GONE);
        firm_name_layout.setVisibility(View.VISIBLE);
        take_firm_name();
    }

    void take_firm_name() {

        firm_about_box.setText("");
        firm_name_box.setText("");
        firm_number_box.setText("");
        firm_email_box.setText("");
        firm_name_layout.setVisibility(View.VISIBLE);
        save_firm_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firm_about_box.getText().toString().isEmpty()) {
                    Toast.makeText(NewFamily.this, R.string.enter_about_firm, Toast.LENGTH_LONG).show();
                    return;
                }

                if (firm_name_box.getText().toString().isEmpty()) {
                    Toast.makeText(NewFamily.this, R.string.enter_firm_name, Toast.LENGTH_LONG).show();
                    return;
                }

                if (firm_number_box.getText().toString().isEmpty()) {
                    Toast.makeText(NewFamily.this, R.string.home_number_placeholder, Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    if (!firm_email_box.getText().toString().isEmpty()) {
                        firm_details_object.put("contact_email", firm_email_box.getText().toString());
                    } else {
                        firm_details_object.put("contact_email", "Not Available");
                    }

                    firm_details_object.put("contact_number", firm_number_box.getText().toString());
                    firm_details_object.put("firm_name", firm_name_box.getText().toString());
                    firm_details_object.put("about_firm", firm_about_box.getText().toString());
                    firm_name_label.setText(firm_name_box.getText().toString().toUpperCase());
                    take_firm_address();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    void take_firm_address() {
        firm_name_layout.setVisibility(View.GONE);
        firm_header_display_layout.setVisibility(View.VISIBLE);
        firm_address.setVisibility(View.VISIBLE);
        firm_address_layout.setVisibility(View.GONE);
        auto_generated_firm_state.setVisibility(View.GONE);
        firm_pincode.setText("");
        firm_pincode_layout.setVisibility(View.VISIBLE);
        process_firm_pincode.setVisibility(View.VISIBLE);

        process_firm_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_pincode(firm_pincode.getText().toString(), GET_FIRM_STATE_CITY);
            }
        });
    }

    boolean firmDataisValid() {
        if (firm_details_box.getText().toString().isEmpty()) {
            show_bottom_message(getString(R.string.enter_your_firm_address), false);
            return false;
        } else {
            try {
                firm_address_object.put("address", firm_details_box.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    void show_bottom_message(String message, boolean cancellable) {
        View view = LayoutInflater.from(NewFamily.this).inflate(R.layout.bottom_promt, null);
        TextView text = view.findViewById(R.id.text);
        final TextView done = view.findViewById(R.id.okay);
        text.setText(message);
        final DialogPlus dialog = DialogPlus.newDialog(NewFamily.this).setCancelable(true).setContentHolder(new ViewHolder(view)).setCancelable(cancellable).create();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (fm == true) {
                    title.setText(getResources().getString(R.string.family_name));
                    take_family_name();
                    fm = false;
                }

                if (sa == true) {
                    take_family_address();
                    sa = false;
                }

                if (all_done) {
                    startActivity(new Intent(NewFamily.this, IntermediateSummary.class));
                }
            }
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(done.getWindowToken(), 0);
        dialog.show();
    }

    ArrayList<String> back_track_options = new ArrayList<>();

    void get_pincode(String pincode, final int request_code) {
        String url = "http://our-rajasthan.com.cp-13.bigrockservers.com/apis/get_geography_by_pincode.php";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("pincode", pincode).build();
        final Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String server_response = response.body().string();
                    Intent intent = new Intent(NewFamily.this, list_selection.class);
                    intent.putExtra("data", server_response);
                    intent.putExtra("title", getString(R.string.select_area));
                    intent.putExtra("image", 1);
                    intent.putExtra("type", 0);
                    intent.putExtra("title_key", "area");
                    intent.putExtra("desc_key", "city_state");
                    intent.putExtra("image_key", "image_url");
                    startActivityForResult(intent, request_code);
                }
            }
        });
    }

    void show_back_tracker() {
        ArrayAdapter adapter = new ArrayAdapter(NewFamily.this, R.layout.selectable_list_item_big, R.id.text, back_track_options);
        DialogPlus dialog = DialogPlus.newDialog(NewFamily.this)
                .setGravity(Gravity.BOTTOM)
                .setAdapter(adapter).setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(DialogPlus dialog, Object item, View view, int position) {
                        switch (position) {
                            case 0: {
                                get_data();
                                break;
                            }

                            case 1: {
                                take_samaj_details();
                                break;
                            }


                            case 2: {
                                take_family_name();
                                break;
                            }

                            case 3: {
                                take_family_address();
                                break;
                            }
                        }
                    }
                })
                .setHeader(R.layout.language_selection_header)
                .setCancelable(false)
                .create();

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        {
            if (requestCode == GET_HOME_STATE_CITY) {
                if (resultCode == RESULT_OK) {
                    try {
                        process_pincode.setVisibility(View.GONE);
                        JSONObject object = new JSONObject(data.getStringExtra("result"));

                        home_address.put("pincode_related", object);
                        area_label.setText(object.getString("area"));
                        district_state.setText(object.getString("city_state"));
                        auto_generated_state.setVisibility(View.VISIBLE);
                        pincode.setEnabled(false);
                        home_address_layout.setVisibility(View.VISIBLE);
                        home_number_box.requestFocus();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInputFromInputMethod(home_number_box.getWindowToken(), 0);
                        save_home_details.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromInputMethod(residential_details_box.getWindowToken(), 0);
                    show_bottom_message(getString(R.string.enter_your_pincode), false);
                }
            } else if (requestCode == GET_FIRM_STATE_CITY) {
                if (resultCode == RESULT_OK) {
                    try {

                        JSONObject object = new JSONObject(data.getStringExtra("result"));
                        firm_address_object = new JSONObject();
                        process_firm_pincode.setVisibility(View.GONE);
                        auto_generated_firm_state.setVisibility(View.VISIBLE);
                        firm_pincode.setEnabled(false);
                        firm_area_label.setText(object.getString("area"));
                        firm_district_state.setText(object.getString("city_state"));
                        firm_address_object.put("pincode_related", object);
                        firm_address_layout.setVisibility(View.VISIBLE);
                        firm_address_layout.requestFocus();
                        firm_details_footer.setVisibility(View.VISIBLE);
                        save_firms.setVisibility(View.VISIBLE);
                        add_more_firms.setVisibility(View.VISIBLE);
                        add_more_firms.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (firmDataisValid()) {
                                    try {
                                        firm_details_object.put("firm_address", firm_address_object);
                                        firms.put(firm_details_object);
                                        firm_details_box.setText("");
                                        auto_generated_firm_state.setVisibility(View.GONE);
                                        process_firm_pincode.setVisibility(View.VISIBLE);
                                        firm_address_layout.setVisibility(View.GONE);
                                        firm_pincode_layout.setVisibility(View.GONE);
                                        firm_pincode.setEnabled(true);
                                        firm_pincode.setText("");
                                        firm_header_display_layout.setVisibility(View.GONE);
                                        firm_details_footer.setVisibility(View.GONE);
                                        take_firm_name();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                        save_firms.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (firmDataisValid()) {
                                    try {
                                        firm_details_object.put("firm_address", firm_address_object);
                                        firms.put(firm_details_object);
                                        family_details.put("firms", firms);
                                        getSharedPreferences("family_input", MODE_PRIVATE).edit().putString("data", family_details.toString()).commit();
                                        Log.d("stuck", family_details.toString());
                                        all_done = true;
                                        show_bottom_message(getString(R.string.firm_details_saved), false);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    } catch (Exception e) {
                        Log.d("stuck", e.getMessage());
                    }
                }
            }
        }
    }

    public static String quote(String string) {
        if (string == null || string.length() == 0) {
            return "\"\"";
        }

        char c = 0;
        int i;
        int len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String t;

        sb.append('"');
        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
                case '\\':
                case '"':
                    sb.append('\\');
                    sb.append(c);
                    break;
                case '/':
                    //                if (b == '<') {
                    sb.append('\\');
                    //                }
                    sb.append(c);
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                default:
                    if (c < ' ') {
                        t = "000" + Integer.toHexString(c);
                        sb.append("\\u" + t.substring(t.length() - 4));
                    } else {
                        sb.append(c);
                    }
            }
        }
        sb.append('"');
        return sb.toString();
    }
}
