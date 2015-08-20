package com.adamin90.adamlee.mygame.mvp;

import android.view.View;

/**
 * Created by adamlee on 2015/8/21.
 */
public interface ViewAttacher<V extends View> {
    void attachView(V v);

    void detachView(V v);
}
