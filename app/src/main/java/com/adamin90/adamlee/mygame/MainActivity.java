package com.adamin90.adamlee.mygame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.iflytek.voiceads.AdError;
import com.iflytek.voiceads.AdKeys;
import com.iflytek.voiceads.IFLYAdListener;
import com.iflytek.voiceads.IFLYAdSize;
import com.iflytek.voiceads.IFLYBannerAd;
import com.iflytek.voiceads.IFLYInterstitialAd;
import com.umeng.analytics.MobclickAgent;

public class MainActivity extends AppCompatActivity {
    private SoundManager a;
    private long b;
    private LinearLayout layout_ads;
    private IFLYBannerAd bannerView;
    private IFLYInterstitialAd interstitialAd;
    public MainActivity() {
        this.a = SoundManager.get();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        a.a(this);
        setContentView(R.layout.activity_main);
        createBannerAd();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        GameSceneView gameSceneView = (GameSceneView) findViewById(R.id.game_scene_view);
        gameSceneView.measure(View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(displayMetrics.heightPixels, View.MeasureSpec.EXACTLY));
        gameSceneView.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);

    }

    private void createBannerAd() {
        //此广告位为Demo专用，广告的展示不产生费用
        String adUnitId = "725D8D1A6D9A556BFB8910E7069B053C";
        //创建旗帜广告，传入广告位ID
        bannerView = IFLYBannerAd.createBannerAd(this, adUnitId);
        //设置请求的广告尺寸
        bannerView.setAdSize(IFLYAdSize.BANNER);
        //设置下载广告前，弹窗提示
        bannerView.setParameter(AdKeys.DOWNLOAD_ALERT, "true");

        //请求广告，添加监听器
        bannerView.loadAd(mAdListener);
        //将广告添加到布局
        layout_ads = (LinearLayout)findViewById(R.id.layout_adview);
        layout_ads.removeAllViews();
        layout_ads.addView(bannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.a.b();
        if (this.b != 0) {
            GameScenePresenter.a().a(System.currentTimeMillis() - this.b);
        }
        this.b = 0;
        MobclickAgent.onResume(this);

    }

    @Override
    protected void onPause() {
        this.b = System.currentTimeMillis();
        this.a.c();

        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        this.b = 0;
        this.a.a();

        super.onDestroy();
    }
    IFLYAdListener mAdListener = new IFLYAdListener(){

        /**
         * 广告请求成功
         */
        @Override
        public void onAdReceive() {
            //展示广告
            bannerView.showAd();

        }

        /**
         * 广告请求失败
         */
        @Override
        public void onAdFailed(AdError error) {
        }

        /**
         * 广告被点击
         */
        @Override
        public void onAdClick() {
        }

        /**
         * 广告被关闭
         */
        @Override
        public void onAdClose() {
        }
    };

    @Override
    public void onBackPressed() {
        createInterstitialAd();
    }

    public void createInterstitialAd() {
        //此广告位为Demo专用，广告的展示不产生费用
        final String adUnitId = "6338D0A5F7AA9C1C7A90C4424C6426AD";
        //创建插屏广告，传入广告位ID
        interstitialAd = IFLYInterstitialAd.createInterstitialAd(this, adUnitId);
        //设置广告尺寸
        interstitialAd.setAdSize(IFLYAdSize.INTERSTITIAL);
        //设置下载广告前，弹窗提示
        interstitialAd.setParameter(AdKeys.DOWNLOAD_ALERT, "true");

        //请求广告，添加监听器
        interstitialAd.loadAd(mAdListener2);
    }

    IFLYAdListener mAdListener2 = new IFLYAdListener(){

        /**
         * 广告请求成功
         */
        @Override
        public void onAdReceive() {
            //展示广告
            interstitialAd.showAd();

        }

        /**
         * 广告请求失败
         */
        @Override
        public void onAdFailed(AdError error) {
        }

        /**
         * 广告被点击
         */
        @Override
        public void onAdClick() {
        }

        /**
         * 广告被关闭
         */
        @Override
        public void onAdClose() {
        }
    };
}
