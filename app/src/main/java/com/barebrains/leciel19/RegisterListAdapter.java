package com.barebrains.leciel19;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RegisterListAdapter extends ArrayAdapter {
    int res;
    ArrayList<RegisterListItem> ei;
    String url;
    Context c;
    int pos=0;

    public RegisterListAdapter( int res, ArrayList<RegisterListItem> ei, Context c) {
        super(c, res, ei);
        this.res = res;
        this.ei = ei;
        this.c = c;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(c);
        View root = li.inflate(res, null, false);
        ((TextView) root.findViewById(R.id.reg_title_text)).setText(ei.get(position).getTitle());
        ((TextView) root.findViewById(R.id.reg_about_text)).setText(ei.get(position).getAbout());
        ((Button) root.findViewById(R.id.reg_redirect_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = ei.get(position).getUrl();
                startIntent(url);
            }
        });
        if (pos==position) {


            root.setAlpha(0);


            Long delay = Long.valueOf(position * 150);
            ObjectAnimator a = ObjectAnimator.ofFloat(root, "alpha", 0, 1);
            a.setStartDelay(delay);
            a.start();
            ObjectAnimator o = ObjectAnimator.ofFloat(root, "translationX", 500, 0f);
            o.setStartDelay(delay);
            o.setInterpolator(new DecelerateInterpolator());
            o.setDuration(500);
            o.start();
            pos++;
        }
        return root;
    }

    public void startIntent(String a) {
        Intent i = new Intent(getContext(), RegisterWebView.class);
        i.putExtra("url", a);
        c.startActivity(i);
    }
}
