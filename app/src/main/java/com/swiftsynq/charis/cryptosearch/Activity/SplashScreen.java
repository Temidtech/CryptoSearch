package com.swiftsynq.charis.cryptosearch.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.swiftsynq.charis.cryptosearch.R;

import at.favre.lib.dali.Dali;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TEMIDJOYPC on 03/09/2016.
 */
public class SplashScreen extends Activity {
    @BindView(R.id.splashbg)
    ImageView splashbg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashview);
        ButterKnife.bind(this);

        //BlurBackground
        Dali.create(getBaseContext()).load(R.drawable.splashbg).placeholder(R.drawable.splashbg).blurRadius(8)
                .downScale(2).colorFilter(Color.parseColor("#ffffff")).concurrent().reScale().into(splashbg);
       Delay();
    }
    private void Delay()
    {
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    Intent intent2=new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent2);

                }
            }
        };
        timer.start();
    }
}
