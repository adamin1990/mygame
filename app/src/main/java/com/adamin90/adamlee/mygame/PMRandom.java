package com.adamin90.adamlee.mygame;

import java.util.Random;

/**
 * Created by Administrator on 2015/8/21.
 */
public final class PMRandom {

    private  static  PMRandom pmRandom;

    private  long b;

    public PMRandom() {
       b= (long)new Random().nextInt(10000);
    }

    public  static  PMRandom getInstance(){
        if(pmRandom==null){
            pmRandom=new PMRandom();
        }
        return  pmRandom;
    }

    void a(long j){
        b=j%Integer.MAX_VALUE;
        if(b<=0){
            b+=(Integer.MAX_VALUE-1);
        }
        a();
        a();
        a();
    }


    long a(){
        b=(b*16807)%Integer.MAX_VALUE;
        return  b;
    }
    float b() {
        return (float) ((((double) a()) - 1.0d) / 2.147483646E9d);
    }

}
