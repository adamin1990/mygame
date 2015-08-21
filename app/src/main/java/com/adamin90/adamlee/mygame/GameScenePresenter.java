package com.adamin90.adamlee.mygame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.adamin90.adamlee.mygame.mvp.ViewPresenter;
import com.adamin90.adamlee.mygame.util.FontCache;
import com.adamin90.adamlee.mygame.util.Views;
//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.HitBuilders;
//import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Set;

/**
 * Created by Administrator on 2015/8/21.
 */
class GameScenePresenter extends ViewPresenter<GameSceneView>  implements GameSceneView.Presenter {
    private static GameScenePresenter a;
    private GameBoard b;
    private LevelManager c;
//    private Tracker d;
    private long e;
    private long f;

    @Override
    public void attachView(GameSceneView gameSceneView) {
        super.attachView(gameSceneView);
        a(gameSceneView);
    }



    static GameScenePresenter a(Context context) {
        if (a == null) {
            a = new GameScenePresenter(context);
        }
        return a;
    }

    static GameScenePresenter a() {
        if (a != null) {
            return a;
        }
        throw new IllegalStateException("Presenter not initialized");
    }

    private GameScenePresenter(Context context) {
        this.c = LevelManager.a(context);
//        GoogleAnalytics instance = GoogleAnalytics.getInstance(context.getApplicationContext());
//        instance.setLocalDispatchPeriod(20);
//        this.d = instance.newTracker("UA-38705998-8");
//        this.d.enableExceptionReporting(true);
//        this.d.enableAutoActivityTracking(true);
//        this.d.enableAdvertisingIdCollection(false);
    }

    public int getColumnCount() {
        return this.b.a;
    }

    public int getRowCount() {
        return this.b.b;
    }

    public GameBoard.Edge getCellRotation(int i, int i2) {
        return this.b.c[i][i2].c;
    }

    public Set<GameBoard.Edge> getCellOpenSides(int i, int i2) {
        return this.b.c[i][i2].d;
    }

    public void rotateCell(int i, int i2) {
        this.b.c[i][i2].a();
        if (this.b.a()) {
            String format = String.format("%05d", new Object[]{Integer.valueOf(this.c.c() + 1)});
            long currentTimeMillis = (System.currentTimeMillis() - this.f) - this.e;
//            a("level_completed_with_duration", format, currentTimeMillis);
//            a("level_duration", currentTimeMillis, format);
            this.e = 0;
            Log.e("Debug", "finished level in " + currentTimeMillis);
            this.c.d();
            if (getView() != null) {
                ((GameSceneView) getView()).a();
            }
            GameOptionsPresenter.a().a(true);
        }
    }

    public void startNewGame(boolean z) {
        this.c.b();
        this.b = this.c.a();
        this.f = System.currentTimeMillis();
//        a("level_started", String.format("%05d", new Object[]{Integer.valueOf(this.c.c() + 1)}), 0);
        GameOptionsPresenter.a().b();
        if (getView() != null) {
            ((GameSceneView) getView()).a(this.c.c() + 1, this.c.a, z);
        }
    }

    public boolean isGamePaused() {
        return GameOptionsPresenter.a().isMenuExpanded();
    }

    public void resumeGame() {
        if (isGamePaused()) {
            GameOptionsPresenter.a().toggleMenu();
        }
    }

    public void a(GameSceneView gameSceneView) {
        super.attachView(gameSceneView);
//        a("started", null, 0);
        startNewGame(true);
        if (isGamePaused()) {
            ((GameSceneView) getView()).a(true, false);
        }
    }

    void a(boolean z) {
        if (getView() != null) {
            ((GameSceneView) getView()).a(z, true);
        }
    }

    void a(long j) {
        this.e += j;
    }

//    void a(String str, String str2, long j) {
//        this.d.send(new HitBuilders.EventBuilder().setCategory("game_action").setAction(str).setLabel(str2).setValue(j).build());
//    }
//
//    void a(String str, long j, String str2) {
//        this.d.send(new HitBuilders.TimingBuilder().setCategory("game_action").setVariable(str).setValue(j).setLabel(str2).build());
//    }
}