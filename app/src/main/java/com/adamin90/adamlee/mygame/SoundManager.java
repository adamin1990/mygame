package com.adamin90.adamlee.mygame;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by adamlee on 2015/8/20.
 */
public class SoundManager {
    private static  SoundManager soundManager;
    private  final AtomicBoolean atomicBoolean;
    private SharedPreferences sharedPreferences;
    private SoundPool soundPool;
    private  int e;
    private  int f;
    private SoundPool soundPool2;
    private  int h;
    private int i;
    private  int j;
    private  int k;
    private  int l;
    private  int [] m;
    private WeakReference<Context> weakReference;

    public SoundManager() {
        this.atomicBoolean=new AtomicBoolean(false);
    }

    public static SoundManager get(){
        if(soundManager==null){
            soundManager=new SoundManager();
        }
        return  soundManager;
    }

    void a(Context context){
        synchronized (this.atomicBoolean){
            weakReference=new WeakReference(context);
            if(atomicBoolean.compareAndSet(false,true)){
                sharedPreferences=context.getSharedPreferences("loops",Context.MODE_PRIVATE);
                soundPool=new SoundPool(1, AudioManager.STREAM_MUSIC,0);
                l();
                soundPool2=new SoundPool(5,AudioManager.STREAM_MUSIC,0);
                h=soundPool2.load(context,R.raw.newlvl,1);
                i=soundPool2.load(context,R.raw.shift,1);
                j=soundPool2.load(context,R.raw.menu_button,1);
                k=soundPool2.load(context,R.raw.menu_close,1);
                l=soundPool2.load(context,R.raw.menu_open,1);

                m=new int[5];
                m[0]=soundPool2.load(context,R.raw.turn,1);
                m[1]=soundPool2.load(context,R.raw.turn1,1);
                m[2]=soundPool2.load(context,R.raw.turn2,1);
                m[3]=soundPool2.load(context,R.raw.turn3,1);
                m[4]=soundPool2.load(context,R.raw.turn4,1);
            }
        }
    }

    private void l() {
        if(weakReference.get()!=null&&k()&&f==0){
            soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                @Override
                public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    soundManager.a(soundPool,sampleId,status);
                }
            });
            e= soundPool.load(weakReference.get(),R.raw.background,1);
        }

    }

    private  void a(SoundPool soundPool,int sampleId,int status){
        f=soundPool.play(e,0.7f,0.7f,1,-1,1.0f);
        if(!k()){
            soundPool.pause(f);
        }
    }

    void a(){
        synchronized (atomicBoolean){
            weakReference.clear();
            if(atomicBoolean.compareAndSet(true,false)){
                soundPool.release();
                soundPool=null;
                f=0;
                soundPool2.release();;
                soundPool2=null;
                weakReference.clear();
            }
        }
    }

    void b(){
        if(!k()){
            return;
        }
        if(soundPool==null||f==0){
            l();
        }else {
            soundPool.resume(f);
        }
    }
    void c(){
        if(soundPool!=null&&f!=0){
            soundPool.pause(f);
        }
    }

    void d(){
        a(l,0.85f);
    }
    void e() {
        a(k, 0.7f);
    }

    void f() {
        a(j, 0.7f);
    }

    void g() {
        a(h, 1.0f);
    }

    void h() {
        a(this.i, 1.0f);
    }
    void i() {
        a(m[new Random().nextInt(5)], 0.85f);
    }
    void  a(final int i,float f){
        if(soundPool2!=null&&k()&&soundPool2.play(i,f,f,0,0,1.0f)==0){
            soundPool2.setOnLoadCompleteListener(
                    new SoundPool.OnLoadCompleteListener() {
                                                     @Override
                                                     public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                    b(i,soundPool,sampleId,status);
                                                     }
                                                 }
            );
        }
    }

    private  static  void b(int i,SoundPool soundPool,int i2,int i3){
        if(i2==i){
            soundPool.play(i2, 1.0f, 1.0f, 0, 0, 1.0f);
        }
    }
    void j(){
        boolean z=!k();
        sharedPreferences.edit().putBoolean("sound_enabled", z).apply();
        if(z){
            b();
        }else {
            c();
        }
    }

    /*
    * 判断是否允许音乐*/
    boolean k(){
        return  sharedPreferences.getBoolean("sound_enabled",true);
    }
}
