package com.adamin90.adamlee.mygame.util;

import android.animation.TypeEvaluator;

/**
 * Created by adamlee on 2015/8/21.
 */
public class ElasticEvaluator implements TypeEvaluator<Float> {

    private  float a;
    public ElasticEvaluator(float f) {
        this.a = f;
    }

    @Override
    public Float evaluate(float fraction, Float startValue, Float endValue) {
        return calculate(a*fraction,startValue.floatValue(),endValue.floatValue()-startValue.floatValue(),a);
    }

    public Float calculate(float f, float f2, float f3, float f4) {
        if (f == 0.0f) {
            return Float.valueOf(f2);
        }
        float f5 = f / f4;
        if (f5 == 1.0f) {
            return Float.valueOf(f2 + f3);
        }
        float f6 = 0.9f * f4;
        return Float.valueOf(((((float) Math.sin((double) ((((f5 * f4) - (f6 / 4.0f)) * 6.2831855f) / f6))) * (((float) Math.pow(2.0d, (double) (-8.0f * f5))) * f3)) + f3) + f2);
    }

}
