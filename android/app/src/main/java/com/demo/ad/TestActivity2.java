package com.demo.ad;

import static com.bytedance.sdk.openadsdk.TTAdLoadType.PRELOAD;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.brayantad.utils.TToast;
import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTNativeExpressAd;
import com.demo.R;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;

import java.util.List;

public class TestActivity2 extends Activity {
    FrameLayout bannerContainer;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        mContext = this.getApplicationContext();
        bannerContainer = findViewById(R.id.banner_container);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setCodeId("901121246");
                loadExpressAd("901121246",300,45);
//                loadAndShowBanner(bannerContainer);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTTAd.render();
            }
        });
//        setCodeId("901121246");
    }

    public void loadAndShowBanner( ViewGroup bannerContainer) {
        TTAdNative mTTAdNative = TTAdSdk.getAdManager().createAdNative(this);
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("103370385") //广告位id
                .setSupportDeepLink(true)
                .setAdCount(1) //请求广告数量为1
                .setExpressViewAcceptedSize(300,150) //期望模板广告view的size,单位dp
                .setAdLoadType(PRELOAD)//推荐使用，用于标注此次的广告请求用途为预加载（当做缓存）还是实时加载，方便后续为开发者优化相关策略
                .build();
        mTTAdNative.loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            //请求失败回调
            @Override
            public void onError(int code, String message) {

            }

            //请求成功回调
            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                mTTAd = ads.get(0);
                LogUtils.e( "onNativeExpressAdLoad: ");

                mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {

                    //广告点击回调
                    @Override
                    public void onAdClicked(View view, int type) {

                    }

                    //广告展示回调
                    @Override
                    public void onAdShow(View view, int type) {

                    }

                    //广告渲染失败回调
                    @Override
                    public void onRenderFail(View view, String msg, int code) {

                    }
                    //广告渲染成功回调
                    @Override
                    public void onRenderSuccess(View view, float width, float height) {
                        // 当前不建议直接使用 float width, float height
                        LogUtils.e( "onRenderSuccess: ");

                        bannerContainer.removeAllViews();
                        bannerContainer.addView(view);
                    }
                });
            }
        });
        //若设置了轮播功能，建议在onNativeExpressAdLoad回调之后调用mTTAd.destroy()

    }

    public void loadAndShowBannerAds( ViewGroup bannerContainer) {
        bannerContainer.removeAllViews();
        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId("962822923")
//                .setImageAcceptedSize(
//                        ScreenUtils.getScreenWidth(),
//                        UIUtils.dp2px(activity, 75f)
//                ) // 单位px
                .build();
        TTAdNative adNativeLoader = TTAdSdk.getAdManager().createAdNative(this);

        // 广告展示监听器
        TTNativeExpressAd.ExpressAdInteractionListener expressAdInteractionListener = new TTNativeExpressAd.ExpressAdInteractionListener() {
            @Override
            public void onAdClicked(View view, int type) {
                LogUtils.e( "banner clicked");
            }

            @Override
            public void onAdShow(View view, int type) {
                LogUtils.e( "banner show");
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                // 注意：使用融合功能时，无需调用render，load成功后可调用mBannerAd.getExpressAdView()进行展示。
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
//                bannerContainer.removeAllViews();
                LogUtils.e( "bannerView: " + view);
//                bannerContainer.addView(view);
                // 注意：使用融合功能时，无需调用render，load成功后可调用mBannerAd.getExpressAdView()获取view进行展示。
                // 如果调用了render，则会直接回调onRenderSuccess，***** 参数view为null，请勿使用。*****
            }
        };

        // dislike监听器，广告关闭时会回调onSelected
//        TTAdDislike.DislikeInteractionCallback dislikeInteractionCallback = new TTAdDislike.DislikeInteractionCallback() {
//            @Override
//            public void onShow() {
//                LogUtils.e( "banner dislike show");
//            }
//
//            @Override
//            public void onSelected(int position, String value, boolean enforce) {
//                LogUtils.e( "banner dislike closed");
//                bannerContainer.removeAllViews();
//            }
//
//            @Override
//            public void onCancel() {
//                LogUtils.e( "banner dislike cancel");
//            }
//        };

        TTAdNative.NativeExpressAdListener nativeExpressAdListener = new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                if (ads != null) {
                    LogUtils.e( "banner load success: " + ads.size());
                }
                if (ads != null && !ads.isEmpty()) {
                    TTNativeExpressAd bannerAd = ads.get(0);
                    bannerAd.setExpressInteractionListener(expressAdInteractionListener);
//                    bannerAd.setDislikeCallback(activity, dislikeInteractionCallback);
                    /** 注意：使用融合功能时，load成功后可直接调用getExpressAdView获取广告view展示，而无需调用render等onRenderSuccess后 */
                    View bannerView = bannerAd.getExpressAdView();
                    if (bannerView != null) {
                        bannerContainer.removeAllViews();
                        LogUtils.e( "bannerView: " + bannerView);
                        bannerContainer.addView(bannerView);
                    }
                }
            }

            @Override
            public void onError(int code, String message) {
                LogUtils.e( "banner load fail: " + code + ", " + message);
            }
        };
        adNativeLoader.loadBannerExpressAd(adSlot, nativeExpressAdListener);
    }


    private TTNativeExpressAd mTTAd;
    private void loadExpressAd(String codeId, int expressViewWidth, int expressViewHeight) {
        bannerContainer.removeAllViews();
        //step4:创建广告请求参数AdSlot,具体参数含义参考文档

        AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(codeId) //广告位id
                .setAdCount(1) //请求广告数量为1到3条
                .setExpressViewAcceptedSize(expressViewWidth, expressViewHeight) //期望模板广告view的size,单位dp
                .build();
        //step5:请求广告，对请求回调的广告作渲染处理

        TTAdManagerHolder.get().createAdNative(this).loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
            @Override
            public void onError(int code, String message) {
                LogUtils.e( "onError" + message );
//                TToast.show(BannerExpressActivity.this, "load error : " + code + ", " + message);
                bannerContainer.removeAllViews();
            }

            @Override
            public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                LogUtils.e( "onNativeExpressAdLoad"+ ads);
                if (ads == null || ads.size() == 0) {
                    return;
                }
                /*******************
                 * 如果旧广告对象不使用了，在替换成新广告对象前，必须进行销毁，否则可能导致多个广告对象同时存在，影响SSR
                 */
                if (mTTAd != null) {
                    mTTAd.destroy();
                }
                /********************/


                mTTAd = ads.get(0);
                mTTAd.setSlideIntervalTime(30 * 1000);
                bindAdListener(mTTAd);
                mTTAd.render();
//                startTime = System.currentTimeMillis();
//                TToast.show(mContext, "load success!");
            }
        });
    }


    private void bindAdListener(TTNativeExpressAd ad) {
        LogUtils.e( "bindAdListener" );

        mTTAd.setExpressInteractionListener(new TTNativeExpressAd.ExpressAdInteractionListener() {
            @Override
            public void onAdClicked(View view, int type) {
//                TToast.show(mContext, "广告被点击");
                LogUtils.e( "onAdClicked" );
            }

            @Override
            public void onAdShow(View view, int type) {
//                TToast.show(mContext, "广告展示");
                LogUtils.e( "onAdShow" );
            }

            @Override
            public void onRenderFail(View view, String msg, int code) {
                LogUtils.e( "render fail:" );
//                TToast.show(mContext, msg + " code:" + code);
            }

            @Override
            public void onRenderSuccess(View view, float width, float height) {
                LogUtils.e("render suc:"+ view);
                //返回view的宽高 单位 dp
                TToast.show(mContext, "渲染成功");
                LogUtils.e( "banner codeId: " );
                LogUtils.e("AdDebug AdView尺寸: " + view.getWidth() + "x" + view.getHeight());
                bannerContainer.removeAllViews();
                bannerContainer.addView(view);
            }
        });
        //dislike设置
//        bindDislike(ad, false);

        if (ad.getInteractionType() != TTAdConstant.INTERACTION_TYPE_DOWNLOAD) {
            return;
        }
//        ad.setDownloadListener(new TTAppDownloadListener() {
//            @Override
//            public void onIdle() {
//                TToast.show(BannerExpressActivity.this, "点击开始下载", Toast.LENGTH_LONG);
//            }
//
//            @Override
//            public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
//                if (!mHasShowDownloadActive) {
//                    mHasShowDownloadActive = true;
//                    TToast.show(BannerExpressActivity.this, "下载中，点击暂停", Toast.LENGTH_LONG);
//                }
//            }
//
//            @Override
//            public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
//                TToast.show(BannerExpressActivity.this, "下载暂停，点击继续", Toast.LENGTH_LONG);
//            }
//
//            @Override
//            public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
//                TToast.show(BannerExpressActivity.this, "下载失败，点击重新下载", Toast.LENGTH_LONG);
//            }
//
//            @Override
//            public void onInstalled(String fileName, String appName) {
//                TToast.show(BannerExpressActivity.this, "安装完成，点击图片打开", Toast.LENGTH_LONG);
//            }
//
//            @Override
//            public void onDownloadFinished(long totalBytes, String fileName, String appName) {
//                TToast.show(BannerExpressActivity.this, "点击安装", Toast.LENGTH_LONG);
//            }
//        });
    }

    public void setCodeId(String codeId) {
        LogUtils.e( "banner codeId: " + codeId);
        runOnUiThread(
                () -> {
                    bannerContainer.removeAllViews();
                    final AdSlot adSlot = new AdSlot.Builder()
                            .setCodeId(codeId)
//                            .setImageAcceptedSize(600, 257)
                            //[start支持模板样式]:需要支持模板广告和原生广告样式的切换，需要调用supportRenderControl和setExpressViewAcceptedSize
//                            .supportRenderControl() //支持模板样式
//                            .setExpressViewAcceptedSize(350, 300)//设置模板宽高（dp）
                            //[end支持模板样式]
                            .setAdCount(1)
                            .build();
                    TTAdManagerHolder.get().createAdNative(this).loadBannerExpressAd(adSlot, new TTAdNative.NativeExpressAdListener() {
                        @Override
                        public void onNativeExpressAdLoad(List<TTNativeExpressAd> ads) {
                            if (ads != null) {
                                LogUtils.e( "banner load success: " + ads.size());
                                if (!ads.isEmpty()) {
                                    TTNativeExpressAd bannerAd = ads.get(0);
                                    // 设置广告交互监听器
//                        bannerAd.setExpressInteractionListener(expressAdInteractionListener);

                                    // 设置不喜欢广告回调
                                    // 注意：dislikeInteractionCallback和activity需要在您的类中定义并初始化
//                        bannerAd.setDislikeCallback(activity, dislikeInteractionCallback);

                                    // 获取广告视图并添加到广告容器中
                                    View bannerView = bannerAd.getExpressAdView();
                                    if (bannerView != null) {
                                        // 假设bannerContainer是在您的类中定义的一个ViewGroup，用于展示广告
                                        bannerContainer.removeAllViews();
                                        LogUtils.e( "addView" );
                                        bannerContainer.addView(bannerView);
                                    }
                                }
                            }
                        }

                        @Override
                        public void onError(int code, String message) {
                            LogUtils.e("banner load fail: " + code + ", " + message);
                        }
                    });


                }
        );



//        TTAdManagerHolder.get().createAdNative(mContext).loadNativeAd(adSlot, new TTAdNative.NativeAdListener() {
//            @Override
//            public void onError(int code, String message) {
//                LogUtils.e("banner-onError:load error : " + code + ", " + message);
//            }
//
//            @Override
//
//            public void onNativeAdLoad(List<TTNativeAd> ads) {
//                if (ads.get(0) == null) {
//                    return;
//                }
//
//                final TTNativeAd ad = ads.get(0);
//                //【注意】
//                //如果打开了支持模板样式开关 supportRenderControl()：
//                //则需要给广告对象设置ExpressRenderListener监听，
//                //然后调用广告对象的render()方法开始渲染，在渲染成功的回调中再调用showAd(ad)
//                //
//                //如果没有打开支持模板样式开关 ：
//                //这里向前兼容，则和以前版本sdk的使用保持一致，
//                //不用设置监听以及调用render()
//                //可以直接调用showAd(ad)
//
//                ad.setExpressRenderListener(new TTNativeAd.ExpressRenderListener() {
//                    @Override
//                    public void onRenderSuccess(View view, float width, float height, boolean isExpress) {
//                        showAd(ad);
//                    }
//                });
//                ad.render();
//            }
//        });
    }


}