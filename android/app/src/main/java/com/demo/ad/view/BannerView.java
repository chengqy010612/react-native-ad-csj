package com.demo.ad.view;

import static com.facebook.react.bridge.UiThreadUtil.runOnUiThread;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.brayantad.utils.TToast;
import com.brayantad.utils.Utils;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.demo.R;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;
import com.facebook.react.bridge.ReactApplicationContext;

import java.util.List;

public class BannerView extends RelativeLayout {
    ReactApplicationContext mContext;

    protected final FrameLayout bannerContainer;

    public BannerView(ReactApplicationContext context) {
        super(context);
        mContext = context;

        inflate(mContext, R.layout.banner_view, this);
        bannerContainer = findViewById(R.id.tt_video_layout_hxb);

        Utils.setupLayoutHack(this);
        configureComponent();
    }

//    public BannerView(Context context, AttributeSet attrs, FrameLayout mContainer) {
//        super(context, attrs);
//        this.mContainer = mContainer;
//        configureComponent();
//    }
//
//    public BannerView(Context context, AttributeSet attrs, int defStyleAttr, FrameLayout mContainer) {
//        super(context, attrs, defStyleAttr);
//        this.mContainer = mContainer;
//        configureComponent();
//    }

    private void configureComponent() {
//        Button button = new android.widget.Button(context);
//        button.setText("Click Me");
//        return  new Button(context);
    }

//    public void setCodeId(String codeId) {
//        LogUtils.e( "banner codeId: " + codeId);
//        runOnUiThread(
//                () -> {
//                    bannerContainer.removeAllViews();
//                    final AdSlot adSlot = new AdSlot.Builder()
//                            .setCodeId(codeId)
////                            .setImageAcceptedSize(600, 257)
//                            //[start支持模板样式]:需要支持模板广告和原生广告样式的切换，需要调用supportRenderControl和setExpressViewAcceptedSize
////                            .supportRenderControl() //支持模板样式
//                            .setExpressViewAcceptedSize(350, 45)//设置模板宽高（dp）
//                            //[end支持模板样式]
//                            .setAdCount(1)
//                            .build();
//                    TTAdManagerHolder.get().createAdNative(mContext).loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
//                        @Override
//                        public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
//                            if (ads != null) {
//                                LogUtils.e( "banner load success: " + ads.size());
//                                if (!ads.isEmpty()) {
//                                    TTNativeExpressAd bannerAd = ads.get(0);
//                                    // 设置广告交互监听器
////                        bannerAd.setExpressInteractionListener(expressAdInteractionListener);
//
//                                    // 设置不喜欢广告回调
//                                    // 注意：dislikeInteractionCallback和activity需要在您的类中定义并初始化
////                        bannerAd.setDislikeCallback(activity, dislikeInteractionCallback);
//
//                                    // 获取广告视图并添加到广告容器中
//                                    View bannerView = bannerAd.getExpressAdView();
//                                    if (bannerView != null) {
//                                        // 假设bannerContainer是在您的类中定义的一个ViewGroup，用于展示广告
//                                        bannerContainer.removeAllViews();
//                                        bannerContainer.addView(bannerView);
//                                    }
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onError(int code, String message) {
//                            LogUtils.e("banner load fail: " + code + ", " + message);
//                        }
//                    });
//
//
//                }
//        );
//
//
//
////        TTAdManagerHolder.get().createAdNative(mContext).loadNativeAd(adSlot, new TTAdNative.NativeAdListener() {
////            @Override
////            public void onError(int code, String message) {
////                LogUtils.e("banner-onError:load error : " + code + ", " + message);
////            }
////
////            @Override
////
////            public void onNativeAdLoad(List<TTNativeAd> ads) {
////                if (ads.get(0) == null) {
////                    return;
////                }
////
////                final TTNativeAd ad = ads.get(0);
////                //【注意】
////                //如果打开了支持模板样式开关 supportRenderControl()：
////                //则需要给广告对象设置ExpressRenderListener监听，
////                //然后调用广告对象的render()方法开始渲染，在渲染成功的回调中再调用showAd(ad)
////                //
////                //如果没有打开支持模板样式开关 ：
////                //这里向前兼容，则和以前版本sdk的使用保持一致，
////                //不用设置监听以及调用render()
////                //可以直接调用showAd(ad)
////
////                ad.setExpressRenderListener(new TTNativeAd.ExpressRenderListener() {
////                    @Override
////                    public void onRenderSuccess(View view, float width, float height, boolean isExpress) {
////                        showAd(ad);
////                    }
////                });
////                ad.render();
////            }
////        });
//    }


    private TTNativeExpressAd mTTAd;
    public void setCodeId(String codeId) {
        runOnUiThread(() -> {
            bannerContainer.removeAllViews();
            AdSlot adSlot = new AdSlot.Builder()
                    .setCodeId(codeId) //广告位id
                    .setAdCount(1) //请求广告数量为1到3条
                    .setExpressViewAcceptedSize(300, 45) //期望模板广告view的size,单位dp
                    .build();
            TTAdManagerHolder.get().createAdNative(mContext).loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
                @Override
                public void onError(int code, String message) {
                    LogUtils.e("onError" + message);
//                TToast.show(BannerExpressActivity.this, "load error : " + code + ", " + message);
                    bannerContainer.removeAllViews();
                }

                @Override
                public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                    LogUtils.e("onNativeExpressAdLoad" + ads);
                    if (ads == null || ads.size() == 0) {
                        return;
                    }
                    if (mTTAd != null) {
                        mTTAd.destroy();
                    }
                    mTTAd = ads.get(0);
                    mTTAd.setSlideIntervalTime(30 * 1000);
                    bindAdListener(mTTAd);
                    mTTAd.render();
                }
            });
        });
    }

    private void bindAdListener(TTNativeExpressAd ad) {
        LogUtils.e("bindAdListener");

        mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {
            @Override
            public void onAdClicked(View view, int type) {
//                TToast.show(mContext, "广告被点击");
                LogUtils.e("onAdClicked");
            }

            @Override
            public void onAdShow(View view, int type) {
//                TToast.show(mContext, "广告展示");
                LogUtils.e("onAdShow");
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                LogUtils.e("render fail:");
//                TToast.show(mContext, msg + " code:" + code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                LogUtils.e("render suc:" + view);
                //返回view的宽高 单位 dp
                TToast.show(mContext, "渲染成功");
                LogUtils.e("banner codeId: ");
                LogUtils.e("AdDebug AdView尺寸: " + view.getWidth() + "x" + view.getHeight());
                bannerContainer.removeAllViews();
                bannerContainer.addView(view);
            }
        });
    }


    private void showAd(TTNativeExpressAd ad) {
//        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.native_ad, bannerContainer, false);
//        if (bannerView == null) {
//            return;
//        }
//        if (mCreativeButton != null) {
//            //防止内存泄漏
//            mCreativeButton = null;
//        }

//        bannerContainer.removeAllViews();
//        bannerContainer.addView(ad);
        //绑定原生广告的数据
//        setAdData(bannerView, ad);

        ad.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {

            @Override
            public void onAdClicked(View view, int type) {
                LogUtils.e("广告被点击");
            }

            @Override

            public void onAdShow(View view, int type) {
                LogUtils.e("广告展示");
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                LogUtils.e(msg + " code:" + code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                LogUtils.e("ExpressView render suc:");
                //返回view的宽高 单位 dp
                bannerContainer.removeAllViews();
                bannerContainer.addView(view);
            }
        });

    }
}