package com.adamin90.adamlee.mygame.util;

import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import java.util.List;

public final class TintUtils {
    private static final ColorFilterLruCache ca;
    private static final Mode b;

    static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int i) {
            super(i);
        }

        PorterDuffColorFilter a(int i, Mode mode) {
            return (PorterDuffColorFilter) get(Integer.valueOf(b(i, mode)));
        }

        PorterDuffColorFilter a(int i, Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) put(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }

        private int b(int i, Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }
    }

    static {
        ca = new ColorFilterLruCache(30);
        b = Mode.SRC_IN;
    }

    public static ColorFilter getColorFilter(int i) {
        Mode mode = b;
        ColorFilter a = ca.a(i, mode);
        if (a != null) {
            return a;
        }
        a = new PorterDuffColorFilter(i, mode);
        ca.a(i, mode, (PorterDuffColorFilter)a);
        return a;
    }

    public static void tintImages(List<ImageView> list, Integer num) {
        for (ImageView colorFilter : list) {
            colorFilter.setColorFilter(getColorFilter(num.intValue()));
        }
    }

    public static void tintBackground(List<View> list, int i) {
        for (View background : list) {
            background.getBackground().setColorFilter(getColorFilter(i));
        }
    }

    private TintUtils() {
    }
}
