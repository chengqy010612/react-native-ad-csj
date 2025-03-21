//package com.demo.splash;
//
//import static com.bytedance.sdk.openadsdk.TTAdLoadType.PRELOAD;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.FrameLayout;
//
//import androidx.annotation.MainThread;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.brayantad.dy.TTAdManagerHolder;
//import com.bytedance.sdk.openadsdk.AdSlot;
//import com.bytedance.sdk.openadsdk.TTAdNative;
//import com.bytedance.sdk.openadsdk.TTSplashAd;
//import com.demo.R;
//import com.demo.ad.AdManager;
//import com.demo.ad.RNADViewBaseEvent;
//import com.demo.utils.LogUtils;
//import com.facebook.react.bridge.Arguments;
//import com.facebook.react.bridge.WritableMap;
//import com.facebook.react.bridge.WritableNativeMap;
//import com.facebook.react.uimanager.UIManagerHelper;
//import com.facebook.react.uimanager.events.EventDispatcher;
//
//
//public class SplashActivity2 extends AppCompatActivity {
//    private FrameLayout mSplashContainer;
//    private String code_id;
//    private int mReactTag;
//    private static final int AD_TIME_OUT = 3500;
//    private int id = 1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//
//        Bundle extras = getIntent().getExtras();
//        code_id = extras.getString("codeid");
//
//        mSplashContainer = findViewById(R.id.splash_container);
//
//        loadSplashAd();
//    }
//
//    private void loadSplashAd() {
//        LogUtils.e("loadSplashAd");
//
//        //TODO  边界判断
//
//        AdSlot adSlot = new AdSlot.Builder()
//                .setCodeId(code_id)
//                .setSupportDeepLink(true)
////      .setImageAcceptedSize(1080, 1920)
//                .setExpressViewAcceptedSize(1080, 1920)
//                .setAdLoadType(PRELOAD)
//                .build();
//        TTAdManagerHolder.get().createAdNative(this).loadSplashAd(
//                adSlot,
//                new TTAdNative.SplashAdListener() {
//
//                    @Override
//                    @MainThread
//                    public void onError(int code, String message) {
//                        // 广告渲染失败
//                        LogUtils.e("开屏广告渲染失败:" + message);
//                        // showToast(message + " - " + code_id);
//
//                        // 回调监听方法
//                        WritableMap params = Arguments.createMap();
//                        params.putString("onAdError", "广告渲染失败:" + message);
////                        sendEvent("onAdError", params);
//
//                        // 关闭开屏广告
////                        goback.run();
//                    }
//
//                    @Override
//                    @MainThread
//                    public void onTimeout() {
//                        // 开屏广告渲染超时
//                        // showToast("加载超时");
//                        // 回调监听方法
//                        WritableMap params = Arguments.createMap();
//                        params.putString("onAdError", "加载超时");
////                        sendEvent(TAG + "-onAdError", params);
//                        LogUtils.e("加载超时:");
//                        // 关闭开屏广告
//                        goToMainActivity();
//                    }
//
//                    @Override
//                    @MainThread
//                    public void onSplashAdLoad(TTSplashAd ad) {
//                        // 开屏广告加载成功，调用显示开屏广告
////                        DyADCore.splashAd = ad;
//                        View view = ad.getSplashView();
//                        mSplashContainer.removeAllViews();
//                        // 把SplashView 添加到ViewGroup中,注意开屏广告view：width >=70%屏幕宽；height >=50%屏幕宽
//                        mSplashContainer.addView(view);
//                        LogUtils.e("onSplashAdLoad:");
////                        goToMainActivity();
//                        // 设置监听器
//                        ad.setSplashInteractionListener(
//                                new TTSplashAd.AdInteractionListener() {
//
//                                    @Override
//                                    public void onAdClicked(View view, int type) {
//                                        LogUtils.e("onAdClick");
//                                        WritableMap params = Arguments.createMap();
//                                        params.putBoolean("onAdClick", true);
////                                        sendEvent(TAG + "-onAdClick", params);
//                                        // showToast("开屏广告点击");
//                                        goToMainActivity();
//                                    }
//
//                                    @Override
//                                    public void onAdShow(View view, int type) {
//                                        LogUtils.e("onAdShow");
//                                        WritableMap params = Arguments.createMap();
//                                        params.putBoolean("onAdShow", true);
////                                        sendEvent(TAG + "-onAdShow", params);
//                                        // showToast("开屏广告展示");
//                                    }
//
//                                    @Override
//                                    public void onAdSkip() {
//                                        LogUtils.e("onAdSkip");
//                                        WritableMap params = Arguments.createMap();
//                                        params.putBoolean("onAdSkip", true);
////                                        sendEvent(TAG + "-onAdSkip", params);
//
//                                        // showToast("开屏广告跳过");
//                                        goToMainActivity();
//                                    }
//
//                                    @Override
//                                    public void onAdTimeOver() {
//                                        LogUtils.e("onAdTimeOver");
//                                        // showToast("开屏广告倒计时结束");
//                                        WritableMap params = Arguments.createMap();
//                                        params.putBoolean("onAdClose", true);
////                                        sendEvent(TAG + "-onAdClose", params);
//                                        goToMainActivity();
//                                    }
//                                }
//                        );
//                    }
//                },
//                AD_TIME_OUT
//        );
//    }
//
//    private void goToMainActivity() {
////        if (DyADCore.rewardActivity == null) {
////            // 开屏广告控制活动未绑定
////            return;
////        }
//        if (mSplashContainer != null) {
//            mSplashContainer.removeAllViews();
//        }
////        DyADCore.rewardActivity.overridePendingTransition(0, 0); // 不要过渡动画
//        this.finish();
//    }
//
//
//    public void sendEvent(String name, WritableMap args) {
//        if (args == null) {
//            args = new WritableNativeMap(); // Default value for args
//        }
//        EventDispatcher dispatcher = UIManagerHelper.getEventDispatcherForReactTag(AdManager.reactAppContext, id);
//        int surfaceId = UIManagerHelper.getSurfaceId(this);
//        if (dispatcher != null) {
//            dispatcher.dispatchEvent(
//                    new RNADViewBaseEvent(surfaceId, id, name, args)
//            );
//        }
//    }
//
//    public void sendEvent(String name) {
//        sendEvent(name, new WritableNativeMap()); // Overload for default args
//    }
//}