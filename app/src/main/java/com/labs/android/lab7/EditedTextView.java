package com.labs.android.lab7;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextPaint;
import android.util.AttributeSet;

public class EditedTextView extends android.support.v7.widget.AppCompatTextView {

    private int textColor;
    private TextPaint textPaint;

    public EditedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.EditedTextView);
        textColor = a.getColor(R.styleable.EditedTextView_textColor, -2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        textPaint = getPaint();
        textPaint.setTextSize(26);
        textPaint.setShadowLayer(1,1,1,textColor);
        textPaint.setColor(textColor);
        super.onDraw(canvas);
    }
}