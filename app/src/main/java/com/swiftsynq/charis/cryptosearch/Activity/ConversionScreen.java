package com.swiftsynq.charis.cryptosearch.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v8.renderscript.Double2;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.swiftsynq.charis.cryptosearch.Models.Conversion;
import com.swiftsynq.charis.cryptosearch.R;
import com.swiftsynq.charis.cryptosearch.Util.Constant;

import at.favre.lib.dali.Dali;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TEMIDJOY on 10/18/2017.
 */

public class ConversionScreen extends AppCompatActivity {

    @BindView(R.id.cryptothumbnail)
    ImageView cryptothumbnail;
    @BindView(R.id.cryptoeditText)
    EditText cryptoeditText;
    @BindView(R.id.currencyeditText)
    EditText currencyeditText;
    @BindView(R.id.worldcurrency)
    Button worldcurrency;
    @BindView(R.id.cryptocurrency)
    Button cryptocurrency;
    Double result=0.0;
    Boolean WCHasFocus=false,CPHasFocus=false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversionscreen);
        ButterKnife.bind(this);
        //Load Data
        final Bundle bundle = getIntent().getExtras();
        setupEdits(bundle);
        Glide.with(ConversionScreen.this).load(Constant.IMAGE_BASE_URL+bundle.getString(Constant.IMAGEURL)).into(cryptothumbnail);
    }
    private void setupEdits(final Bundle bundle)
    {
        cryptocurrency.setText(bundle.getString(Constant.CRYPTONAME));
        worldcurrency.setText(bundle.getString(Constant.CURRENCYNAME));
        cryptoeditText.setText("1");
        currencyeditText.setText(bundle.getString(Constant.EXCHANGERATE));
        currencyeditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                WCHasFocus=true;
                CPHasFocus=false;
            }
        });
        cryptoeditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                CPHasFocus=true;
                WCHasFocus=false;
            }
        });
        currencyeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try{
                    if(s.length()>0)
                    {
                        Double number=Double.parseDouble(s.toString());
                        result-=number;
                    }

                }catch (Exception e)
                {

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(WCHasFocus)
                    {
                        Double number=Double.parseDouble(s.toString());
                        Double rate=Double.parseDouble(bundle.getString(Constant.EXCHANGERATE).toString());
                        result=number/rate;
                        cryptoeditText.setText(String.valueOf(result));
                    }

                }catch (Exception e)
                {

                }

            }
        });
        cryptoeditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try{
                    if(s.length()>0)
                    {
                        Double number=Double.parseDouble(s.toString());
                        result-=number;
                    }

                }catch (Exception e)
                {

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(CPHasFocus)
                    {
                        Double number=Double.parseDouble(s.toString());
                        Double rate=Double.parseDouble(bundle.getString(Constant.EXCHANGERATE).toString());
                        result=number*rate;
                        currencyeditText.setText(String.valueOf(result));
                    }
                }catch (Exception e)
                {

                }

            }
        });

    }

}
