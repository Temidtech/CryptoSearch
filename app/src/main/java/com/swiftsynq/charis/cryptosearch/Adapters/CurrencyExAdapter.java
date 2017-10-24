package com.swiftsynq.charis.cryptosearch.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.swiftsynq.charis.cryptosearch.Activity.ConversionScreen;
import com.swiftsynq.charis.cryptosearch.Models.Conversion;
import com.swiftsynq.charis.cryptosearch.R;
import com.swiftsynq.charis.cryptosearch.Util.Constant;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Ravi Tamada on 18/05/16.
 */
public class CurrencyExAdapter extends RecyclerView.Adapter<CurrencyExAdapter.MyViewHolder> {

    private Context mContext;
    private List<Conversion> CurrencyList;
    private LinearLayout addlyt;
    private RecyclerView recyclerView;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cryptocurrency, worldcurrency;
        public ImageView currencyimg;
        public CardView cv;
        public Button removebtn;
        public MyViewHolder(View view) {
            super(view);
            currencyimg=(ImageView)view.findViewById(R.id.Bthumbnail);
            cryptocurrency=(TextView)view.findViewById(R.id.cryptocurrency) ;
            worldcurrency=(TextView)view.findViewById(R.id.worldcurrency) ;
            removebtn=(Button)view.findViewById(R.id.removebtn);
            cv=(CardView)view.findViewById(R.id.cv);
        }
    }


    public CurrencyExAdapter(Context mContext, List<Conversion> CurrencyList, LinearLayout addlyt,RecyclerView recyclerView) {
        this.mContext = mContext;
        this.CurrencyList = CurrencyList;
        this.addlyt=addlyt;
        this.recyclerView=recyclerView;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversioncard, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
         final Conversion conversion = CurrencyList.get(position);
        addlyt.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        DecimalFormat formatter=new DecimalFormat("#,###,###");

        holder.cryptocurrency.setText(conversion.getCrypoName());
        holder.worldcurrency.setText(formatter.format(Double.valueOf(conversion.getRate()))+" "+conversion.getCurrencyName());
        holder.removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrencyList.remove(position);
                notifyDataSetChanged();
                if(CurrencyList.size()==0)
                {
                    recyclerView.setVisibility(View.GONE);
                    addlyt.setVisibility(View.VISIBLE);
                }
            }
        });
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Bundle args = new Bundle();
                args.putString(Constant.IMAGEURL,conversion.getImageUrl());
                args.putString(Constant.CRYPTONAME,conversion.getCrypoName());
                args.putString(Constant.CURRENCYNAME,conversion.getCurrencyName());
                args.putString(Constant.EXCHANGERATE,conversion.getRate());
               Intent intent=new Intent(mContext, ConversionScreen.class);
                intent.putExtras(args);
                mContext.startActivity(intent);
            }
        });
       Glide.with(mContext).load(Constant.IMAGE_BASE_URL+conversion.getImageUrl()).into(holder.currencyimg);

    }



    @Override
    public int getItemCount() {
        return CurrencyList.size();
    }
}
