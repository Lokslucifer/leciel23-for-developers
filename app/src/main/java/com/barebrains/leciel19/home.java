package com.barebrains.leciel19;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;



public class home extends Fragment {
    private long delay1=0;
    private long delay2=0;

    ImageSlider imageSlider;
    ArrayList<SlideModel> imageList = new ArrayList<>();

    public home() {
        // Required empty public constructor
    }


    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_home, container, false);

        imageSlider=root.findViewById(R.id.imslide);
        ((CardView)root.findViewById(R.id.d)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","dance");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.m)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","music");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.a)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","art");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.l)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","literary");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.i)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","informals");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.t)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","tamil");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.esport)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","esports");
                startActivity(i);
            }
        });
        ((CardView)root.findViewById(R.id.design)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","design");
                startActivity(i);
            }
        });

        ((CardView)root.findViewById(R.id.ps)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),event_categories.class);
                i.putExtra("category","Pro Shows");
                startActivity(i);
            }
        });

        Intent n=new Intent("gyanith.notify");
        getContext().sendBroadcast(n);


        CardView danc=(CardView)root.findViewById(R.id.d);
        danc.setAlpha(0);
        CardView mus=(CardView)root.findViewById(R.id.m);
        mus.setAlpha(0);




        ObjectAnimator nta=ObjectAnimator.ofFloat(danc,"translationY",100f,0f);
        nta.setInterpolator(new DecelerateInterpolator());
        nta.setStartDelay(delay1);
        nta.setDuration(300);
        nta.start();
        ObjectAnimator ntl=ObjectAnimator.ofFloat(danc,"alpha",0,1);
        ntl.setStartDelay(delay1);
        ntl.setDuration(300);
        ntl.start();
        delay1+=200;

        ObjectAnimator ua=ObjectAnimator.ofFloat(mus,"translationY",100f,0f);
        ua.setInterpolator(new DecelerateInterpolator());
        ua.setStartDelay(delay1);
        ua.setDuration(300);
        ua.start();
        ObjectAnimator ul=ObjectAnimator.ofFloat(mus,"alpha",0,1);
        ul.setStartDelay(delay1);
        ul.setDuration(300);
        ul.start();
        delay1+=200;

        /*ObjectAnimator ta=ObjectAnimator.ofFloat(te,"translationX",300f,0f);
        ta.setInterpolator(new DecelerateInterpolator());
        ta.setStartDelay(delay2);
        ta.setDuration(300);
        ta.start();
        delay2+=150;

        ObjectAnimator pa=ObjectAnimator.ofFloat(ps,"translationX",300f,0f);
        pa.setInterpolator(new DecelerateInterpolator());
        pa.setStartDelay(delay2);
        pa.setDuration(300);
        pa.start();
        ObjectAnimator pl=ObjectAnimator.ofFloat(ps,"alpha",0,1);
        pl.setStartDelay(delay2);
        pl.start();
        delay2+=150;

        ObjectAnimator ga=ObjectAnimator.ofFloat(gl,"translationX",300f,0f);
        ga.setInterpolator(new DecelerateInterpolator());
        ga.setStartDelay(delay2);
        ga.setDuration(300);
        ga.start();
        ObjectAnimator gll=ObjectAnimator.ofFloat(gl,"alpha",0,1);
        gll.setStartDelay(delay2);
        gll.start();
        delay2+=150;*/

     /*   LinearLayout ll=root.findViewById(R.id.homell);
        DisplayMetrics dm=getContext().getResources().getDisplayMetrics();
        float dh=5*dm.heightPixels/dm.density;
        ViewGroup.LayoutParams lp=ll.getLayoutParams();
        lp.height=(int)dh;
        ll.setLayoutParams(lp);*/



        //Glide.with(getContext()).load()
        ArrayList<String> urls=new ArrayList<String >();

        DatabaseReference dref= FirebaseDatabase.getInstance().getReference().child("url").child("slide");
        dref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.i("lc19", dataSnapshot.toString());
                for (DataSnapshot c:dataSnapshot.getChildren()){
                    imageList.add(new SlideModel(c.getValue().toString()));}
                imageSlider.setImageList(imageList);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("lc19",databaseError.toString());

            }
        });





        return root;
    }


}

