package com.demo.splash;

import static com.brayantad.dy.DyADCore.TTAdSdk;
import static com.bytedance.sdk.openadsdk.TTAdLoadType.PRELOAD;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import com.brayantad.dy.DyADCore;
//import com.brayantad.dy.TTAdManagerHolder;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.CSJAdError;
import com.bytedance.sdk.openadsdk.CSJSplashAd;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
//import com.bytedance.sdk.openadsdk.TTSplashAd;
import com.demo.R;
import com.demo.ad.AdManager;
import com.demo.ad.RNADViewBaseEvent;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.events.EventDispatcher;

import java.util.List;


public class SplashActivity extends AppCompatActivity {
    private FrameLayout mSplashContainer;
    private String code_id;
    private int mReactTag;
    private static final int AD_TIME_OUT = 3500;
    private int id = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Bundle extras = getIntent().getExtras();
        code_id = extras.getString("codeid");

        mSplashContainer = findViewById(R.id.ad_view);

        loadSplashAd();
    }

    private void loadSplashAd() {
        //TODO  边界判断
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(code_id)
                .setSupportDeepLink(true)
//      .setImageAcceptedSize(1080, 1920)
                .setExpressViewAcceptedSize(1080, 1920)
                .setAdLoadType(PRELOAD)
                .build();

        TTAdNative adNativeLoader = TTAdManagerHolder.get().createAdNative(this);

        /** 3、创建加载、展示监听器 */
        //@[classname]
        CSJSplashAd.SplashAdListener splashAdListener = new CSJSplashAd.SplashAdListener() {
            @Override
            public void onSplashAdShow(CSJSplashAd splashAd) {
                LogUtils.e("onSplashAdShow:" );
            }

            @Override
            public void onSplashAdClick(CSJSplashAd splashAd) {
                LogUtils.e("onSplashAdClick:" );
            }

            @Override
            public void onSplashAdClose(CSJSplashAd splashAd, int closeType) {
                LogUtils.e("onSplashAdClose:" );
                goToMainActivity();
            }
        };

        TTAdNative.CSJSplashAdListener csjSplashAdListener = new TTAdNative.CSJSplashAdListener() {
            //@[classname]
            @Override
            public void onSplashLoadSuccess(CSJSplashAd splashAd) {
                LogUtils.e("onSplashLoadSuccess:" );
                splashAd.setSplashAdListener(splashAdListener);
                mSplashContainer.removeAllViews();
                splashAd.showSplashView(mSplashContainer);
            }

            @Override
            public void onSplashLoadFail(CSJAdError error) {
                LogUtils.e("onSplashLoadFail:" + error);
                goToMainActivity();
            }

            //@[classname]
            @Override
            public void onSplashRenderSuccess(CSJSplashAd csjSplashAd) {
                LogUtils.e("onSplashRenderSuccess:" );
//                csjSplashAd.setSplashAdListener(splashAdListener);
//                if (csjSplashAd.getSplashView() != null) {
//                    mSplashContainer.addView(csjSplashAd.getSplashView());
//                }
//                mSplashContainer.removeAllViews();
//                csjSplashAd.showSplashView(mSplashContainer);
            }

            //@[classname]
            @Override
            public void onSplashRenderFail(CSJSplashAd splashAd, CSJAdError error) {
                LogUtils.e("onSplashRenderFail:" );
                goToMainActivity();
            }
        };

        /** 4、加载广告  */
        adNativeLoader.loadSplashAd(adSlot, csjSplashAdListener, 3500);
//        mH.sendEmptyMessageDelayed(1, 10 * 1000);
    }

    private void goToMainActivity() {
//        if (DyADCore.rewardActivity == null) {
//            // 开屏广告控制活动未绑定
//            return;
//        }
        if (mSplashContainer != null) {
            mSplashContainer.removeAllViews();
        }
//        DyADCore.rewardActivity.overridePendingTransition(0, 0); // 不要过渡动画
        this.finish();
    }


    public void sendEvent(String name, WritableMap args) {
        if (args == null) {
            args = new WritableNativeMap(); // Default value for args
        }
        EventDispatcher dispatcher = UIManagerHelper.getEventDispatcherForReactTag(AdManager.reactAppContext, id);
        int surfaceId = UIManagerHelper.getSurfaceId(this);
        if (dispatcher != null) {
            dispatcher.dispatchEvent(
                    new RNADViewBaseEvent(surfaceId, id, name, args)
            );
        }
    }

    public void sendEvent(String name) {
        sendEvent(name, new WritableNativeMap()); // Overload for default args
    }
}