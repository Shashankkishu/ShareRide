package com.example.root.sharide;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by root on 23/6/15.
 */
public class CustomFontCheckBox extends CheckBox{
    public CustomFontCheckBox(Context context) {
        super(context);

        applyCustomFont(context, null);
    }

    public CustomFontCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomFontCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = getTypeface().getStyle();

        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle) {
    /*
     information about the TextView textStyle:
     http://developer.android.com/reference/android/R.styleable.html#TextView_textStyle
      */
        switch (textStyle) {
            case 1: // bold
                return FontCache.getTypeface("font/CaviarDreams_Bold.ttf", context);

            case 2: // italic
                return FontCache.getTypeface("font/CaviarDreams_Italic.ttf", context);

            case 0: // regular
            default:
                return FontCache.getTypeface("font/CaviarDreams.ttf", context);
        }
    }
}
