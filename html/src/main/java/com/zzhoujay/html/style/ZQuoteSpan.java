package com.zzhoujay.html.style;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.QuoteSpan;

/**
 * Created by zhou on 2018/3/18.
 */

public class ZQuoteSpan extends QuoteSpan {

    private static final int STRIPE_WIDTH = 15;
    private static final int GAP_WIDTH = 40;

    public ZQuoteSpan() {
        this(Color.LTGRAY);
    }

    public ZQuoteSpan(int color) {
        super(color);
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {
        Paint.Style style = p.getStyle();
        int color = p.getColor();

        p.setStyle(Paint.Style.FILL);
        p.setColor(getColor());

        c.drawRect(x, top, x + dir * STRIPE_WIDTH, bottom, p);

        p.setStyle(style);
        p.setColor(color);
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return STRIPE_WIDTH + GAP_WIDTH;
    }
}
