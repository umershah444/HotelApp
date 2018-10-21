package com.example.umer.hotelapp;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class CurrencyAdaptor extends ArrayAdapter<Currency> {

    public CurrencyAdaptor(@NonNull Context context, int resource, @NonNull List<Currency> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=convertView;
        if(view==null)
        {
            view=View.inflate(this.getContext(),R.layout.list_item,null);
        }
        Currency currency=getItem(position);
        TextView name=(TextView) view.findViewById(R.id.currency);
        TextView rate=(TextView) view.findViewById(R.id.rate);
        ImageView flag=(ImageView) view.findViewById(R.id.flag);
        FrameLayout l1=(FrameLayout) view.findViewById(R.id.imageFrame);
        FrameLayout l2=(FrameLayout) view.findViewById(R.id.curFrame);
        FrameLayout l3=(FrameLayout) view.findViewById(R.id.rateFrame);

        if(position%2==0) {
            l1.setBackgroundColor(Color.parseColor("#efefef"));
            l2.setBackgroundColor(Color.parseColor("#efefef"));
            l3.setBackgroundColor(Color.parseColor("#efefef"));
        }
        else {
            l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        name.setText(currency.getName());
        rate.setText(currency.getRate());

        Picasso.get().load(currency.getFlag()).resize(200,125).into(flag);

        return view;
    }
}
