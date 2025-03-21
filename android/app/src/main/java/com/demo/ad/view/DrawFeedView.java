package com.demo.ad.view;

import static com.bytedance.sdk.openadsdk.TTAdLoadType.LOAD;
import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.brayantad.dy.DyADCore;
//import com.brayantad.dy.TTAdManagerHolder;
import com.brayantad.utils.Utils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.demo.R;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;

import java.util.List;

public class DrawFeedView extends RelativeLayout {
    protected ReactContext reactContext;
    protected final FrameLayout mContainer;

    public DrawFeedView(ReactApplicationContext context) {
        super(context);
        reactContext = context;
        inflate(reactContext, R.layout.draw_feed, this);
        mContainer = findViewById(com.brayantad.R.id.tt_video_layout_hxb);
        // 这个函数很关键，不然不能触发再次渲染，让 view 在 RN 里渲染成功!!
        Utils.setupLayoutHack(this);
    }


    public void setCodeId(String codeId) {
        runOnUiThread(
                () -> {
                    tryShowAd(codeId);
                }
        );
    }

    void tryShowAd(String codeId) {
        float expressViewWidth = 1080;
        float expressViewHeight = 1920;
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(codeId)
                .setSupportDeepLink(true)
                .setAdCount(1) // 请求广告数量为1到3条
                .setExpressViewAcceptedSize(expressViewWidth, expressViewHeight) // 期望模板广告view的size,单位dp
                .setAdLoadType(LOAD)
                .build();

        TTAdManagerHolder.get().createAdNative(reactContext).loadExpressDrawFeedAd(
                adSlot,
                new TTAdNative.NativeExpressAdListener() {

                    @Override
                    public void onError(int code, String message) {
                        LogUtils.e( message);
                        // showToast(message);
                    }

                    @Override
                    public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                        if (ads == null || ads.isEmpty()) {
                            // TToast.show(mContext, " ad is null!");
                            return;
                        }
                        //成功加载到广告，开始渲染,我们每次只拉取1条
                        TTNativeExpressAd ad = ads.get(0);
                        //尝试展示广告
                        showAd(ad);
                        //缓存给下次直接秒展示
//                        DyADCore.drawfeedAd = ad;
                    }
                }
        );
    }


    private void showAd(TTNativeExpressAd ad) {
        // 点击监听器必须在getAdView之前调
        ad.setVideoAdListener(
                new TTNativeExpressAd.ExpressVideoAdListener() {

                    @Override
                    public void onVideoLoad() {
                        LogUtils.e( "express onVideoLoad");
//                        onExpressAdLoad();
                    }

                    @Override
                    public void onVideoError(int errorCode, int extraCode) {
                        LogUtils.e( "express onVideoError");
                    }

                    @Override
                    public void onVideoAdStartPlay() {
                        LogUtils.e( "express onVideoAdStartPlay");
                    }

                    @Override
                    public void onVideoAdPaused() {
                        LogUtils.e( "express onVideoAdPaused");
                    }

                    @Override
                    public void onVideoAdContinuePlay() {
                        LogUtils.e("express onVideoAdContinuePlay");
                    }

                    @Override
                    public void onProgressUpdate(long current, long duration) {
                        //                            Log.d(TAG, "express onProgressUpdate");
                    }

                    @Override
                    public void onVideoAdComplete() {
                        LogUtils.e("express onVideoAdComplete");
                    }

                    @Override
                    public void onClickRetry() {
                        // TToast.show(mContext, " onClickRetry !");
                        LogUtils.e( "express onClickRetry!");
                    }
                }
        );
        ad.setCanInterruptVideoPlay(true);
        ad.setExpressInteractionListener(
                new TTNativeExpressAd.ExpressAdInteractionListener() {

                    @Override
                    public void onAdClicked(View view, int type) {
                        LogUtils.e("express onAdClicked");
//                        onAdClick();
                    }

                    @Override
                    public void onAdShow(View view, int type) {
                        LogUtils.e( "express onAdShow");
//                        onExpressAdLoad();
                    }

                    @Override
                    public void onRenderFail(View view, String msg, int code) {
                        LogUtils.e("express onRenderFail");
                    }

                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        LogUtils.e("express onRenderSuccess");
                        mContainer.addView(ad.getExpressAdView());
//                        onExpressAdLoad();
                    }
                }
        );
        ad.render();
    }
}
