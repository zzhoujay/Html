package com.zzhoujay.html.style;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.ReplacementSpan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhou on 2018/3/30.
 */

public class ZCodeBlockSpan extends ReplacementSpan implements LeadingMarginSpan, LineHeightSpan {

    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#F4F4F5");
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#24292E");

    private static final int RADIUS = 10;
    private static final int MARGIN = 5;
    private static final int PADDING = 16;
    private static final int PADDING_START = 20;
    private static final float TEXT_SIZE_SCALE = 0.92f;

    private int mWidth;
    private Drawable mBackground;
    private int mTextColor;
    private int mBaseline;
    private int mLineHeight;
    private List<CharSequence> mLines;

    public ZCodeBlockSpan(int backgroundColor, int textColor, CharSequence lines) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(backgroundColor);
        g.setCornerRadius(RADIUS);
        mBackground = g;
        mLines = split(lines);
        mTextColor = textColor;
    }

    public ZCodeBlockSpan(CharSequence lines) {
        this(DEFAULT_BACKGROUND_COLOR, DEFAULT_TEXT_COLOR, lines);
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        return mWidth;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float size = paint.getTextSize();
        paint.setTextSize(size * TEXT_SIZE_SCALE);
        paint.setTypeface(Typeface.MONOSPACE);

        int height = mLines.size() * mLineHeight;

        mBackground.setBounds((int) x + MARGIN, top + MARGIN, (int) x + mWidth - MARGIN, top + height + 2 * PADDING - MARGIN);
        mBackground.draw(canvas);

        canvas.save();
        canvas.clipRect(x + PADDING_START, top + PADDING, x + mWidth - PADDING_START, top + height + PADDING);


        int color = paint.getColor();
        paint.setColor(mTextColor);

        int i = mBaseline + PADDING + top;

        for (CharSequence mLine : mLines) {
            canvas.drawText(mLine, 0, mLine.length(), x + PADDING_START, i, paint);
            i += mLineHeight;
        }

        paint.setTextSize(size);
        paint.setColor(color);

        canvas.restore();
    }

    @Override
    public void chooseHeight(CharSequence text, int start, int end, int spanstartv, int v, Paint.FontMetricsInt fm) {
        int num = mLines.size();
        mLineHeight = fm.bottom - fm.top;
        mBaseline = -fm.top;
        fm.ascent = fm.top;
        fm.bottom += (num - 1) * mLineHeight + 2 * PADDING;
        fm.descent = fm.bottom;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return 0;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        mWidth = layout.getWidth();
    }

    private List<CharSequence> split(CharSequence cs) {
        int start = 0;
        int end = cs.length();
        if (start >= end) {
            return Collections.emptyList();
        }
        while (cs.charAt(start) == '\n' && start < end) {
            start++;
        }
        while (cs.charAt(end - 1) == '\n' && start < end) {
            end--;
        }
        if (start >= end) {
            return Collections.emptyList();
        }
        ArrayList<CharSequence> list = new ArrayList<>();
        int length = cs.length();
        int last = 0;
        for (int i = 0; i < length; i++) {
            char c = cs.charAt(i);
            if (c == '\n') {
                list.add(cs.subSequence(last, i));
                last = i + 1;
            }
        }
        if (last < length) {
            list.add(cs.subSequence(last, length));
        }
        return list;
    }
}
