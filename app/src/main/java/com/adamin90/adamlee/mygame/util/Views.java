package com.adamin90.adamlee.mygame.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * Created by adamlee on 2015/8/21.
 */
public final class Views {

    /* renamed from: com.balysv.loop.util.Views.1 */
    static final class AnonymousClass1 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ Runnable a;
        final /* synthetic */ View b;

        AnonymousClass1(Runnable runnable, View view) {
            a = runnable;
            b = view;
        }

        public boolean onPreDraw() {
            this.a.run();
            if (b.getViewTreeObserver().isAlive()) {
                b.getViewTreeObserver().removeOnPreDrawListener(this);
            }
            return false;
        }
    }

    private Views() {
    }

    public static void runAfterMeasure(@NonNull View view, Runnable runnable) {
        view.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass1(runnable, view));
    }

    public static int dpToPx(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}