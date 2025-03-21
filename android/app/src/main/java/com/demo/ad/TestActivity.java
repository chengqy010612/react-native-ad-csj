package com.demo.ad;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
import androidx.annotation.Nullable;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.DislikeInfo;
import com.bytedance.sdk.openadsdk.FilterWord;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdDislike;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAppDownloadListener;
import com.bytedance.sdk.openadsdk.TTImage;
import com.bytedance.sdk.openadsdk.TTNativeAd;
import com.demo.R;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;
//import com.union_test.toutiao.R;
//import com.union_test.toutiao.config.TTAdManagerHolder;
//import com.union_test.toutiao.dialog.DislikeDialog;
//import com.union_test.toutiao.utils.TToast;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("EmptyMethod")
public class TestActivity extends Activity {


    private TTAdNative mTTAdNative;
    private FrameLayout mBannerContainer;
    private Context mContext;
    private Button mButtonDownload;
    private Button mButtonLandingPage;
    private Button mCreativeButton;

    @SuppressLint("SetTextI18n")
    @SuppressWarnings("RedundantCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        mContext = this.getApplicationContext();
        mBannerContainer = (FrameLayout) findViewById(R.id.banner_container);
        mButtonDownload = (Button) findViewById(R.id.btn_banner_download);
        mButtonDownload.setText("展示原生Banner广告");
        mButtonLandingPage = (Button) findViewById(R.id.btn_banner_landingpage);
        mButtonLandingPage.setVisibility(View.GONE);
        mButtonDownload.setOnClickListener(mClickListener);
        mButtonLandingPage.setOnClickListener(mClickListener);
        //step2:创建TTAdNative对象

        mTTAdNative = TTAdManagerHolder.get().createAdNative(this);
        //step3:可选，强烈建议在合适的时机调用):申请部分权限，如read_phone_state,防止获取不了imei时候，下载类广告没有填充的问题。
        TTAdManagerHolder.get().requestPermissionIfNecessary(this);
        Button button = (Button)findViewById(R.id.btn_nb_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCreativeButton != null) {
            mCreativeButton = null;
        }
    }

    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_banner_download) {
                loadBannerAd("103370385");
            }
        }
    };

    @SuppressWarnings({"ALL", "SameParameterValue"})
    private void loadBannerAd(String codeId) {
        //step4:创建广告请求参数AdSlot,注意其中的setNativeAdtype方法，具体参数含义参考文档

        final AdSlot adSlot = new AdSlot.Builder()
                .setCodeId(codeId)
//                .setImageAcceptedSize(600, 257)
                //[start支持模板样式]:需要支持模板广告和原生广告样式的切换，需要调用supportRenderControl和setExpressViewAcceptedSize
                .supportRenderControl() //支持模板样式
//                .setExpressViewAcceptedSize(350,300)//设置模板宽高（dp）
                //[end支持模板样式]

                .setAdCount(1)
                .build();

        //step5:请求广告，对请求回调的广告作渲染处理

        mTTAdNative.loadNativeAd(adSlot, new TTAdNative.NativeAdListener() {
            @Override
            public void onError(int code, String message) {
                LogUtils.e( "load error : " + code + ", " + message);
            }

            @Override

            public void onNativeAdLoad(List<TTNativeAd> ads) {
                if (ads.get(0) == null) {
                    return;
                }

                final TTNativeAd ad = ads.get(0);
                //【注意】
                //如果打开了支持模板样式开关 supportRenderControl()：
                //则需要给广告对象设置ExpressRenderListener监听，
                //然后调用广告对象的render()方法开始渲染，在渲染成功的回调中再调用showAd(ad)
                //
                //如果没有打开支持模板样式开关 ：
                //这里向前兼容，则和以前版本sdk的使用保持一致，
                //不用设置监听以及调用render()
                //可以直接调用showAd(ad)

                ad.setExpressRenderListener(new TTNativeAd.ExpressRenderListener() {
                    @Override
                    public void onRenderSuccess(View view, float width, float height, boolean isExpress) {
                        LogUtils.e( "onRenderSuccess");
                        showAd(ad);
                    }
                });
                ad.render();
            }
        });
    }

    //广告展示和数据绑定

    private void showAd(TTNativeAd ad) {
        View bannerView = LayoutInflater.from(mContext).inflate(R.layout.native_ad, mBannerContainer, false);
        if (bannerView == null) {
            return;
        }
        if (mCreativeButton != null) {
            //防止内存泄漏
            mCreativeButton = null;
        }
        mBannerContainer.removeAllViews();
        mBannerContainer.addView(bannerView);
        //绑定原生广告的数据
//        setAdData(bannerView, ad);
    }

    @SuppressWarnings("RedundantCast")

    private void setAdData(final View nativeView, final TTNativeAd nativeAd) {
        ((TextView) nativeView.findViewById(R.id.tv_native_ad_title)).setText(nativeAd.getTitle());
        ((TextView) nativeView.findViewById(R.id.tv_native_ad_desc)).setText(nativeAd.getDescription());
        ImageView imgDislike = nativeView.findViewById(R.id.img_native_dislike);
        bindDislikeAction(nativeAd, imgDislike, false);
        List<View> imageViewList = new ArrayList<>();
        if (nativeAd.getImageList() != null && !nativeAd.getImageList().isEmpty()) {
            TTImage image = nativeAd.getImageList().get(0);
            if (image != null && image.isValid()) {
                final ImageView im = nativeView.findViewById(R.id.iv_native_image);
                imageViewList.add(im);
//                Glide.with(this).load(image.getImageUrl()).into(im);
            }
        }
        TTImage icon = nativeAd.getIcon();
        if (icon != null && icon.isValid()) {
            ImageView im = nativeView.findViewById(R.id.iv_native_icon);
//            Glide.with(this).load(icon.getImageUrl()).into(im);
        }
        mCreativeButton = (Button) nativeView.findViewById(R.id.btn_native_creative);

        //可根据广告类型，为交互区域设置不同提示信息
        switch (nativeAd.getInteractionType()) {

            case TTAdConstant.INTERACTION_TYPE_DOWNLOAD:
                //如果初始化ttAdManager.createAdNative(getApplicationContext())没有传入activity 则需要在此传activity，否则影响使用Dislike逻辑
                nativeAd.setActivityForDownloadApp(this);
                mCreativeButton.setVisibility(View.VISIBLE);
                nativeAd.setDownloadListener(mDownloadListener); // 注册下载监听器
                break;

            case TTAdConstant.INTERACTION_TYPE_DIAL:
                mCreativeButton.setVisibility(View.VISIBLE);
                mCreativeButton.setText("立即拨打");
                break;

            case TTAdConstant.INTERACTION_TYPE_LANDING_PAGE:

            case TTAdConstant.INTERACTION_TYPE_BROWSER:
                mCreativeButton.setVisibility(View.VISIBLE);
                mCreativeButton.setText("查看详情");
                break;
            default:
                mCreativeButton.setVisibility(View.GONE);
//                TToast.show(mContext, "交互类型异常");
        }

        //可以被点击的view, 也可以把nativeView放进来意味整个广告区域可被点击
        List<View> clickViewList = new ArrayList<>();
        clickViewList.add(nativeView);

        //触发创意广告的view（点击下载或拨打电话）
        List<View> creativeViewList = new ArrayList<>();

        //穿山甲logo
        ImageView ad_logo = nativeView.findViewById(R.id.iv_native_ad_logo);

        ad_logo.setImageBitmap(nativeAd.getAdLogo());
        //如果需要点击图文区域也能进行下载或者拨打电话动作，请将图文区域的view传入
        //creativeViewList.add(nativeView);
        creativeViewList.add(mCreativeButton);

        //重要! 这个涉及到广告计费，必须正确调用。convertView必须使用ViewGroup。

        nativeAd.registerViewForInteraction((ViewGroup) nativeView, imageViewList,clickViewList, creativeViewList, imgDislike, new TTNativeAd.AdInteractionListener() {
            @Override

            public void onAdClicked(View view, TTNativeAd ad) {
                if (ad != null) {
                    LogUtils.e( "广告" + ad.getTitle() + "被点击");
                }
            }

            @Override

            public void onAdCreativeClick(View view, TTNativeAd ad) {
                if (ad != null) {
                    LogUtils.e( "广告" + ad.getTitle() + "被创意按钮被点击");
                }
            }

            @Override

            public void onAdShow(TTNativeAd ad) {
                if (ad != null) {
                    LogUtils.e("广告" + ad.getTitle() + "展示");
                }
            }
        });

    }

    /**
     * /接入dislike 逻辑，有助于提示广告精准投放度
     *
     * @param ad
     * @param dislikeView
     * @param isCustomDislike 为true时 使用自定义dislike方法
     */

    private void bindDislikeAction(TTNativeAd ad, View dislikeView, boolean isCustomDislike) {
        if (isCustomDislike) {
            // 使用自定义Dislike
            final DislikeInfo dislikeInfo = ad.getDislikeInfo();
            if (dislikeInfo == null || dislikeInfo.getFilterWords() == null || dislikeInfo.getFilterWords().isEmpty()) {
                return;
            }
//            final DislikeDialog dislikeDialog = new DislikeDialog(this, dislikeInfo);
//            dislikeDialog.setOnDislikeItemClick(new DislikeDialog.OnDislikeItemClick() {
//                @Override
//                public void onItemClick(FilterWord filterWord) {
//                    mBannerContainer.removeAllViews();
//                }
//            });
//            ad.setDislikeDialog(dislikeDialog);
//            dislikeView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dislikeDialog.show();
//                }
//            });
        } else {
            // 使用默认Dislike

            final TTAdDislike ttAdDislike = ad.getDislikeDialog(this);
            if (ttAdDislike != null) {

                ttAdDislike.setDislikeInteractionCallback(new TTAdDislike.DislikeInteractionCallback() {
                    @Override
                    public void onShow() {

                    }

                    @Override
                    public void onSelected(int position, String value, boolean enforce) {
                        mBannerContainer.removeAllViews();
                        if (enforce) {
                            LogUtils.e( "NativeBannerActivity 原生banner sdk强制移除View ");
                        }
                    }

                    @Override
                    public void onCancel() {

                    }

                });
            }
            dislikeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ttAdDislike != null)
                        ttAdDislike.showDislikeDialog();
                }
            });
        }
    }

    private final TTAppDownloadListener mDownloadListener = new TTAppDownloadListener() {
        @Override
        public void onIdle() {
            if (mCreativeButton != null) {
                mCreativeButton.setText("开始下载");
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onDownloadActive(long totalBytes, long currBytes, String fileName, String appName) {
            if (mCreativeButton != null) {
                if (totalBytes <= 0L) {
                    mCreativeButton.setText("下载中 percent: 0");
                } else {
                    mCreativeButton.setText("下载中 percent: " + (currBytes * 100 / totalBytes));
                }
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onDownloadPaused(long totalBytes, long currBytes, String fileName, String appName) {
            if (mCreativeButton != null) {
                if (totalBytes <= 0L) {
                    mCreativeButton.setText("下载暂停 percent: 0");
                } else {
                    mCreativeButton.setText("下载暂停 percent: " + (currBytes * 100 / totalBytes));
                }
            }
        }

        @Override
        public void onDownloadFailed(long totalBytes, long currBytes, String fileName, String appName) {
            if (mCreativeButton != null) {
                mCreativeButton.setText("重新下载");
            }
        }

        @Override
        public void onInstalled(String fileName, String appName) {
            if (mCreativeButton != null) {
                mCreativeButton.setText("点击打开");
            }
        }

        @Override
        public void onDownloadFinished(long totalBytes, String fileName, String appName) {
            if (mCreativeButton != null) {
                mCreativeButton.setText("点击安装");
            }
        }
    };

}
