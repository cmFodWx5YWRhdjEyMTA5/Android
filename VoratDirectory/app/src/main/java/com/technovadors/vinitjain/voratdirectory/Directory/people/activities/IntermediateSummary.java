
package com.technovadors.vinitjain.voratdirectory.Directory.people.activities;

import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.technovadors.vinitjain.voratdirectory.R;

public class IntermediateSummary extends AppCompatActivity
{
    ImageView home_tick,office_tick;
    TextView next;
    RelativeLayout work_layout,home_layout,family_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intermediate_summary);
        initalize_views();
        setup_screen();
    }

    private void initalize_views()
    {
        home_layout=findViewById(R.id.home_layout);
        family_layout=findViewById(R.id.family_layout);
        work_layout=findViewById(R.id.work_layout);
        home_tick=findViewById(R.id.home_tick);
        office_tick=findViewById(R.id.office_tick);
        next=findViewById(R.id.next);
    }

    private void setup_screen()
    {
        home_layout.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                work_layout.setVisibility(View.VISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        YoYo.with(Techniques.RubberBand)
                                .duration(1000)
                                .withListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {
                                        home_tick.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        YoYo.with(Techniques.RubberBand).withListener(new Animator.AnimatorListener()
                                        {
                                            @Override
                                            public void onAnimationStart(Animator animation) {
                                                office_tick.setVisibility(View.VISIBLE);
                                            }

                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                YoYo.with(Techniques.RollIn)
                                                        .withListener(new Animator.AnimatorListener() {
                                                            @Override
                                                            public void onAnimationStart(Animator animation) {
                                                                family_layout.setVisibility(View.VISIBLE);
                                                            }

                                                            @Override
                                                            public void onAnimationEnd(Animator animation) {

                                                            }

                                                            @Override
                                                            public void onAnimationCancel(Animator animation) {

                                                            }

                                                            @Override
                                                            public void onAnimationRepeat(Animator animation) {

                                                            }
                                                        })
                                                        .duration(1000)
                                                        .playOn(family_layout);
                                            }

                                            @Override
                                            public void onAnimationCancel(Animator animation) {

                                            }

                                            @Override
                                            public void onAnimationRepeat(Animator animation) {

                                            }
                                        }).playOn(office_tick);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                }).
                                playOn(home_tick);
                    }
                }, 1000);
            }
        }, 1000);



        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(IntermediateSummary.this);
                builder.setCancelable(false);
                builder.setTitle(R.string.member_id);
                builder.setNeutralButton(R.string.next, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(IntermediateSummary.this,NewMember.class);
                        intent.putExtra("hof","1");
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
}
