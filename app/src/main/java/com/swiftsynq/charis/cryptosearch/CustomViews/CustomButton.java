package com.swiftsynq.charis.cryptosearch.CustomViews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by TEMIDJOY on 7/28/2017.
 */

public class CustomButton extends Button {

    public CustomButton(Context context) {
        super(context);
        setFont();

    }

    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        Typeface normal = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Bold.ttf");
        setTypeface(normal, Typeface.NORMAL);

        Typeface bold = Typeface.createFromAsset(getContext().getAssets(), "Montserrat-Bold.ttf");
        setTypeface(normal, Typeface.BOLD);
    }
}

