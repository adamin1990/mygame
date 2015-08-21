package com.adamin90.adamlee.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SoundManager a;
    private long b;

    public MainActivity() {
        this.a = SoundManager.get();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a.a(this);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        GameSceneView gameSceneView = (GameSceneView) findViewById(R.id.game_scene_view);
//        gameSceneView.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY));
//        gameSceneView.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.a.b();
        if (this.b != 0) {
            GameScenePresenter.a().a(System.currentTimeMillis() - this.b);
        }
        this.b = 0;

    }

    @Override
    protected void onPause() {
        this.b = System.currentTimeMillis();
        this.a.c();

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        this.b = 0;
        this.a.a();

        super.onDestroy();
    }
}
