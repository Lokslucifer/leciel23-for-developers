package com.barebrains.leciel19;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class abt extends Fragment {


    private Boolean show=false;
    private String furl,iurl,wurl,yturl;
    private DatabaseReference db,fb;
    Typeface font,fontt;
    public abt() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root= inflater.inflate(R.layout.activity_about, container, false);
       /* ((Button)root.findViewById(R.id.backabt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
        db= FirebaseDatabase.getInstance().getReference().child("misc");
        fb=FirebaseDatabase.getInstance().getReference().child("feedback");
        final FloatingActionButton main=(FloatingActionButton)root.findViewById(R.id.mainbut);
        final FloatingActionButton share=(FloatingActionButton)root.findViewById(R.id.sharebut);
        final FloatingActionButton directions=(FloatingActionButton)root.findViewById(R.id.direcbut);
        final FloatingActionButton feed=(FloatingActionButton)root.findViewById(R.id.feebut);
        /*fontt= Typeface.createFromAsset(getActivity().getAssets(),"font/gilroylight.otf");
        font=Typeface.createFromAsset(getContext().getAssets(),"font/opensansregular.ttf");*/


        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ((TextView)root.findViewById(R.id.descr)).setText(dataSnapshot.child("desc").getValue().toString());
                furl=dataSnapshot.child("fburl").getValue().toString();
                iurl=dataSnapshot.child("inurl").getValue().toString();
                wurl=dataSnapshot.child("wburl").getValue().toString();
                yturl=dataSnapshot.child("yturl").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gm= Uri.parse("http://maps.google.com/maps?&daddr=nit puducherry,thiruvettakudy,karaikal");
                Uri gmmIntentUri = Uri.parse("google.navigation:q=nit+puducherry+thiruvetakudy+karaikal");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gm);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder b=new AlertDialog.Builder(getContext());
                View v=getLayoutInflater().inflate(R.layout.feedlay,null);
                b.setView(v);
                b.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String key=fb.push().getKey();

                        RatingBar r=(RatingBar)v.findViewById(R.id.rating);
                        EditText f=(EditText)v.findViewById(R.id.feedin) ;
                        fb.child(key).child("comment").setValue(f.getText().toString());
                        fb.child(key).child("rating").setValue(r.getRating());
                        Snackbar.make(root.findViewById(R.id.ll),"Thanks for giving your feedback",Snackbar.LENGTH_LONG).show();






                    }
                });
                b.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                b.setNeutralButton("Rate us", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent w=new Intent(Intent.ACTION_VIEW,Uri.parse("https://play.google.com/store/apps/details?id=com.barebrains.leciel19"));
                        startActivity(w);
                    }
                });
                b.show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Check out the Leciel app\n https://play.google.com/store/apps/details?id=com.barebrains.leciel19";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download Leciel app");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });



        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!show){
                    ObjectAnimator s=ObjectAnimator.ofFloat(share,"translationY",0f,-220f);
                    s.setDuration(400);
                    s.setInterpolator(new DecelerateInterpolator());
                    s.start();
                    ObjectAnimator d=ObjectAnimator.ofFloat(directions,"translationY",0f,-440f);
                    d.setDuration(400);
                    d.setInterpolator(new DecelerateInterpolator());
                    d.start();
                    ObjectAnimator f=ObjectAnimator.ofFloat(feed,"translationY",0f,-660f);
                    f.setDuration(400);
                    f.setInterpolator(new DecelerateInterpolator());

                    f.start();
                    ObjectAnimator m=ObjectAnimator.ofFloat(main,"rotation",0f,-45f);
                    m.setDuration(400);
                    m.setInterpolator(new DecelerateInterpolator());
                    m.start();
                    show=!show;
                }else {

                    ObjectAnimator s=ObjectAnimator.ofFloat(share,"translationY",-220,0f);
                    s.setDuration(400);
                    s.setInterpolator(new DecelerateInterpolator());
                    s.start();
                    ObjectAnimator d=ObjectAnimator.ofFloat(directions,"translationY",-440,0f);
                    d.setDuration(400);
                    d.setInterpolator(new DecelerateInterpolator());
                    d.start();
                    ObjectAnimator f=ObjectAnimator.ofFloat(feed,"translationY",-660,0f);
                    f.setDuration(400);
                    f.setInterpolator(new DecelerateInterpolator());

                    f.start();
                    ObjectAnimator m=ObjectAnimator.ofFloat(main,"rotation",-45f,0f);
                    m.setDuration(400);
                    m.setInterpolator(new DecelerateInterpolator());
                    m.start();
                    show=!show;

                }
            }

        });

        ((Button)root.findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w=new Intent(Intent.ACTION_VIEW,Uri.parse(furl));
                startActivity(w);
            }
        });
        ((Button)root.findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w=new Intent(Intent.ACTION_VIEW,Uri.parse(iurl));
                startActivity(w);
            }
        });
        ((Button)root.findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w=new Intent(Intent.ACTION_VIEW,Uri.parse(wurl));
                startActivity(w);
            }
        });
        ((Button)root.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent w=new Intent(Intent.ACTION_VIEW,Uri.parse(yturl));
                startActivity(w);
            }
        });



        ObjectAnimator b1=ObjectAnimator.ofFloat(root.findViewById(R.id.button4),"scaleX",0.1f,1.0f);
        ObjectAnimator b11=ObjectAnimator.ofFloat(root.findViewById(R.id.button4),"scaleY",0.1f,1.0f);
        ObjectAnimator b12=ObjectAnimator.ofFloat(root.findViewById(R.id.button4),"alpha",0.0f,1.0f);
        b12.start();
        b1.setDuration(250);
        b1.setInterpolator(new DecelerateInterpolator());
        b1.start();
        b11.setDuration(250);
        b11.setInterpolator(new DecelerateInterpolator());
        b11.start();

        ObjectAnimator b2=ObjectAnimator.ofFloat(root.findViewById(R.id.button3),"scaleX",0.1f,1.0f);
        ObjectAnimator b22=ObjectAnimator.ofFloat(root.findViewById(R.id.button3),"scaleY",0.1f,1.0f);
        ObjectAnimator b21=ObjectAnimator.ofFloat(root.findViewById(R.id.button3),"alpha",0.0f,1.0f);
        b21.setStartDelay(250);
        b21.start();
        b2.setDuration(250);
        b2.setInterpolator(new DecelerateInterpolator());
        b2.setStartDelay(250);
        b2.start();
        b22.setDuration(250);
        b22.setInterpolator(new DecelerateInterpolator());
        b22.setStartDelay(250);
        b22.start();



        ObjectAnimator b3=ObjectAnimator.ofFloat(root.findViewById(R.id.button2),"scaleX",0.1f,1.0f);
        ObjectAnimator b33=ObjectAnimator.ofFloat(root.findViewById(R.id.button2),"scaleY",0.1f,1.0f);
        ObjectAnimator b31=ObjectAnimator.ofFloat(root.findViewById(R.id.button2),"alpha",0.0f,1.0f);
        b31.setStartDelay(500);
        b31.start();
        b3.setDuration(250);
        b3.setInterpolator(new DecelerateInterpolator());
        b3.setStartDelay(500);
        b3.start();
        b33.setDuration(250);
        b33.setInterpolator(new DecelerateInterpolator());
        b33.setStartDelay(500);
        b33.start();


        ObjectAnimator b4=ObjectAnimator.ofFloat(root.findViewById(R.id.button5),"scaleX",0.1f,1.0f);
        ObjectAnimator b44=ObjectAnimator.ofFloat(root.findViewById(R.id.button5),"scaleY",0.1f,1.0f);
        ObjectAnimator b41=ObjectAnimator.ofFloat(root.findViewById(R.id.button5),"alpha",0.0f,1.0f);
        b41.setStartDelay(750);
        b41.start();
        b4.setDuration(250);
        b4.setInterpolator(new DecelerateInterpolator());
        b4.setStartDelay(750);
        b4.start();
        b44.setDuration(250);
        b44.setInterpolator(new DecelerateInterpolator());
        b44.setStartDelay(750);
        b44.start();


        return root;
    }
}
