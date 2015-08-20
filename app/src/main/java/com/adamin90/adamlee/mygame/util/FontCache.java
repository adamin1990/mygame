package com.adamin90.adamlee.mygame.util;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.LruCache;

/**
 * Created by adamlee on 2015/8/21.
 */
public  final  class FontCache {
    public static String BOLD;
    public static String LIGHT;
    private static LruCache<String, Typeface> a;

    static {
        LIGHT = "fonts/SofiaProLight.otf";
        BOLD = "fonts/SofiaProSemiBold.otf";
        a = new LruCache(2);
    }

    private FontCache() {
    }

    @NonNull
    public static Typeface loadFont(Context context, String str) {
        Typeface typeface = (Typeface) a.get(str);
        if (typeface == null) {
            typeface = Typeface.createFromAsset(context.getAssets(), str);
            if (typeface == null) {
                throw new IllegalArgumentException(String.format("Font %s does not exist in assets", new Object[]{str}));
            }
            a.put(str, typeface);
        }
        return typeface;
    }

}
