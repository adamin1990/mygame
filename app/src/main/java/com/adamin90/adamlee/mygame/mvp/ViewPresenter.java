package com.adamin90.adamlee.mygame.mvp;

import android.support.annotation.Nullable;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by adamlee on 2015/8/21.
 */
public abstract  class ViewPresenter<V extends View> implements ViewAttacher<V>{

    private WeakReference<V> weakReference;

    @Override
    public void attachView(V v) {
        weakReference=new WeakReference<V>(v);


    }

    @Override
    public void detachView(V v) {
        weakReference.clear();

    }
@Nullable
    public V getView(){
        return  weakReference!=null?weakReference.get():null;
    }
}
