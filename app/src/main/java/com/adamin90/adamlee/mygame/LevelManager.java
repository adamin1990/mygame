package com.adamin90.adamlee.mygame;

import android.content.Context;
import android.content.SharedPreferences;

import com.adamin90.adamlee.mygame.data.Level;
import com.adamin90.adamlee.mygame.data.LevelData;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/21.
 */
public class LevelManager {
    private static LevelManager b;
    int a;
    private int c;
    private int d;
    private final SharedPreferences e;
    private final Map<String, Level> f;
    private final PMRandom g;

    static LevelManager a(Context context) {
        if (b == null) {
            b = new LevelManager(context.getApplicationContext());
        }
        return b;
    }

    private LevelManager(Context context) {
        this.e = context.getSharedPreferences("loops", 0);
        this.f = LevelData.loadFromResources(context, R.raw.levels);
        this.g = PMRandom.getInstance();
        this.c = this.e.getInt("current_level", 0);
        this.d = this.e.getInt("highest_level", this.c);
    }

    GameBoard a() {
        int c = c();
        Level level = (Level) this.f.get(String.valueOf(c));
        if (level != null) {
            return new GameBoard(level);
        }
        int floor = (int) Math.floor(Math.log((double) c) / Math.log(2.16d));
        int floor2 = (int) Math.floor(Math.log((double) c) / Math.log(1.58d));
        if (((double) this.g.b()) > 0.5d) {
            floor2++;
        }
        if (((double) this.g.b()) > 0.5d) {
            floor++;
        }
        c = Math.min(floor, 8);
        int min = Math.min(floor2, 13);
        float b = (float) ((((double) this.g.b()) * 0.24d) + 0.33d);
        if (((double) this.g.b()) > 0.9d) {
            floor2 = floor;
        } else {
            floor2 = (int) (Math.ceil(((double) ((float) (c - 3))) * Math.pow((double) this.g.b(), 0.3333333333333333d)) + 3.0d);
            floor = (int) (Math.ceil(((double) ((float) (min - 3))) * Math.pow((double) this.g.b(), 0.3333333333333333d)) + 3.0d);
        }
        return new GameBoard(floor2, floor, b);
    }

    void b() {
        this.g.a((((long) this.c) * 1000) + ((long) this.c));
        this.a = (int) (this.g.b() * 360.0f);
        this.e.edit().putInt("current_level", this.c).apply();
        if (this.c > this.d) {
            this.e.edit().putInt("highest_level", this.c).apply();
            this.d++;
        }
    }

    int c() {
        return this.c;
    }

    void d() {
        this.c++;
    }

    void e() {
        this.c--;
    }

    boolean f() {
        return this.c < this.d;
    }

    boolean g() {
        return this.c > 0;
    }

}
