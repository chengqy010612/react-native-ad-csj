package com.demo.ad;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

//import com.brayantad.dy.DyADCore;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
//import com.demo.splash.SplashActivity;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.demo.reward.RewardActivity;
import com.demo.splash.SplashActivity;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import com.nativeAd.NativeAdManagerSpec;

public class AdManager extends NativeAdManagerSpec {

    public static final String NAME = "NativeAdManager";
    public static ReactApplicationContext reactAppContext;


    public AdManager(ReactApplicationContext reactContext) {
        super(reactContext);
        reactAppContext = reactContext;
    }
//    private static final String NAME = "NativeLocalStorage";
//    public void AdManager(ReactApplicationContext reactContext) {
//        super(reactContext);
//    }
    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void init(ReadableMap options, Promise promise) {
        LogUtils.e("init ad sdk");
        //获取配置
        AdCore.appid = options.hasKey("appid") ? options.getString("appid") : AdCore.appid;
        AdCore.debug = options.hasKey("debug") ? options.getBoolean("debug") : AdCore.debug;
//        AdCore.rewardName = options.hasKey("reward") ? options.getString("reward") : DyADCore.rewardName;
//        AdCore.rewardAmount = options.hasKey("amount") ? options.getInt("amount") : DyADCore.rewardAmount;
//        AdCore.appName = options.hasKey("app") ? options.getString("app") : DyADCore.appName;

        if (AdCore.appid != null) {
            TTAdManagerHolder.init(reactAppContext, AdCore.appid, AdCore.debug);
            boolean sdkReady = TTAdSdk.isSdkReady();
            if(!sdkReady) {
                TTAdSdk.start(new TTAdSdk.Callback() {
                    @Override
                    public void success() {
                        LogUtils.e("success: " + TTAdSdk.isSdkReady());
                        promise.resolve(true);
                    }

                    @Override
                    public void fail(int code, String msg) {
                        LogUtils.e("fail:  code = " + code + " msg = " + msg);
                        promise.reject( "fail:  code = " + code + " msg = " + msg);
                    }
                });
            }
            promise.resolve(true);


        }else{
            LogUtils.e("请配置appid");
        }

    }

    @Override
    public void loadSplashAd(String codeid) {
        LogUtils.e("loadSplashAd");
        Intent intent = new Intent(reactAppContext, SplashActivity.class);
        try {
            intent.putExtra("codeid", codeid);
            final Activity context = getCurrentActivity();
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startTestActivity() {
        Intent intent = new Intent(reactAppContext, TestActivity2.class);
        final Activity context = getCurrentActivity();
        context.startActivity(intent);
    }

    @Override
    public void loadAndShowRewardAds(String codeid) {
        LogUtils.e("loadAndShowRewardAds");
        Intent intent = new Intent(reactAppContext, RewardActivity.class);
        try {
            intent.putExtra("codeid", codeid);
            final Activity context = getCurrentActivity();
            context.startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public void loadAndShowRewardAds(String codeid) {
//        AdSlot rewardAdSlot = new AdSlot.Builder()
//                .setCodeId(codeid)
//                .setOrientation(TTAdConstant.VERTICAL)
//                .build();
//        TTAdNative adRewardNativeLoader = TTAdSdk.getAdManager().createAdNative(reactAppContext);
//        TTAdNative.RewardVideoAdListener rewardVideoAdListener = new TTAdNative.RewardVideoAdListener() {
//            @Override
//            public void onError(int code, String message) {
//                LogUtils.e( "onError code = " + code + " msg = " + message);
//            }
//
//            //@[classname]
//            @Override
//            public void onRewardVideoAdLoad(TTRewardVideoAd rewardVideoAd) {
//                LogUtils.e( "onRewardVideoAdLoad");
//                if (rewardVideoAd != null) {
//                    rewardVideoAd.showRewardVideoAd(reactAppContext);
////                    if (rewardVideoAd.mediationManager.isReady()) {
//////                        rewardVideoAd.setRewardAdInteractionListener(callback);
////                        rewardVideoAd.showRewardVideoAd(reactAppContext);
////                    }
//                }
//            }
//
//            @Override
//            public void onRewardVideoCached() {
//                LogUtils.e( "onRewardVideoCached");
//            }
//
//            //@[classname]
//            @Override
//            public void onRewardVideoCached(TTRewardVideoAd rewardVideoAd) {
//                LogUtils.e( "onRewardVideoCached");
//                if (rewardVideoAd != null) {
//                    rewardVideoAd.showRewardVideoAd(reactAppContext);
////                    if (rewardVideoAd.mediationManager.isReady()) {
////                        rewardVideoAd.setRewardAdInteractionListener(callback);
////                        rewardVideoAd.showRewardVideoAd(activity);
////                    }
//                }
//            }
//        };
//        adRewardNativeLoader.loadRewardVideoAd(rewardAdSlot, rewardVideoAdListener);
//    }

}