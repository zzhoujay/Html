package com.zzhoujay.html.style;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.LeadingMarginSpan;

/**
 * Created by zhou on 2018/3/22.
 */

public class ZIndentSpan implements LeadingMarginSpan {

    private final int mIndent;

    public ZIndentSpan(int mIndent) {
        this.mIndent = mIndent;
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return first ? mIndent : 0;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean first, Layout layout) {

    }
}
