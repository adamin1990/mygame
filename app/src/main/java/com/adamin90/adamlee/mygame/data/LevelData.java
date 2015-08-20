package com.adamin90.adamlee.mygame.data;

import android.content.Context;

import com.adamin90.adamlee.mygame.BuildConfig;
import com.google.gson.Gson;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by adamlee on 2015/8/21.
 */
public final  class LevelData {
    public final Map<String, Level> levels = null;

    public static Map<String, Level> loadFromResources(Context context, int i) {
        Scanner useDelimiter = new Scanner(context.getResources().openRawResource(i)).useDelimiter("\\A");
        LevelData levelData = (LevelData) new Gson().fromJson(useDelimiter.hasNext() ? useDelimiter.next() : BuildConfig.FLAVOR, LevelData.class);
        if (levelData != null) {
            return levelData.levels;
        }
        throw new IllegalStateException("Level data is null");
    }

}
