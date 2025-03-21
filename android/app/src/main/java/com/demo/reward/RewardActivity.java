package com.demo.reward;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bytedance.sdk.openadsdk.AdSlot;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.TTAdNative;
import com.bytedance.sdk.openadsdk.TTAdSdk;
import com.bytedance.sdk.openadsdk.TTRewardVideoAd;
import com.demo.R;
import com.demo.utils.LogUtils;
import com.demo.utils.TTAdManagerHolder;

import java.lang.ref.WeakReference;

public class RewardActivity extends Activity {
    private String code_id;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        activity = this;

        Bundle extras = getIntent().getExtras();
        code_id = extras.getString("codeid");

        loadAndShowRewardAds(code_id);
        //边界---退出
    }

    public void loadAndShowRewardAds(String codeid) {
        AdSlot rewardAdSlot = new AdSlot.Builder()
                .setCodeId(codeid)
                .setOrientation(TTAdConstant.VERTICAL)
                .build();
        TTAdNative adRewardNativeLoader = TTAdSdk.getAdManager().createAdNative(this);
        TTAdNative.RewardVideoAdListener rewardVideoAdListener = new TTAdNative.RewardVideoAdListener() {
            @Override
            public void onError(int code, String message) {
                finish();
                LogUtils.e("onError code = " + code + " msg = " + message);
            }

            //@[classname]
            @Override
            public void onRewardVideoAdLoad(TTRewardVideoAd rewardVideoAd) {
                LogUtils.e("onRewardVideoAdLoad");
                if (rewardVideoAd != null) {
//                    rewardVideoAd.showRewardVideoAd(this);
//                    if (rewardVideoAd.mediationManager.isReady()) {
//                        rewardVideoAd.setRewardAdInteractionListener(callback);
                        rewardVideoAd.showRewardVideoAd(activity);
//                    }
                }
            }

            @Override
            public void onRewardVideoCached() {
                LogUtils.e("onRewardVideoCached");
            }

            //@[classname]
            @Override
            public void onRewardVideoCached(TTRewardVideoAd rewardVideoAd) {
                LogUtils.e("onRewardVideoCached");
                if (rewardVideoAd != null) {
//                    rewardVideoAd.showRewardVideoAd(this);
//                    if (rewardVideoAd.mediationManager.isReady()) {
                    rewardVideoAd.setRewardAdInteractionListener(new AdLifeListener(activity));
                    rewardVideoAd.showRewardVideoAd(activity);
//                    }
                }
            }
        };
        adRewardNativeLoader.loadRewardVideoAd(rewardAdSlot, rewardVideoAdListener);
    }


    private class AdLifeListener implements TTRewardVideoAd.RewardAdInteractionListener {

        private final WeakReference<Context> mContextRef;

        public AdLifeListener(Context context) {
            mContextRef = new WeakReference<>(context);
        }

        @Override

        public void onAdShow() {
            // 广告展示
            LogUtils.e("Callback --> rewardVideoAd show");
        }

        @Override

        public void onAdVideoBarClick() {
            // 广告中产生了点击行为
            LogUtils.e("Callback --> rewardVideoAd bar click");
        }

        @Override

        public void onAdClose() {
            fisish();
            // 广告整体关闭
            LogUtils.e("Callback --> rewardVideoAd close");

        }

        //视频播放完成回调
        @Override
        public void onVideoComplete() {
            // 广告素材播放完成，例如视频未跳过，完整的播放了
            LogUtils.e("Callback --> rewardVideoAd complete");
        }

        @Override
        public void onVideoError() {
            fisish();
            // 广告素材展示时出错
            LogUtils.e("Callback --> rewardVideoAd error");
        }

        @Override
        public void onRewardVerify(boolean b, int i, String s, int i1, String s1) {

        }

//        @Override
//
//        public void onRewardVerify(boolean rewardVerify, int rewardAmount, String rewardName, int errorCode, String errorMsg) {
//            // 已废弃 请使用 onRewardArrived(boolean isRewardValid, int rewardType, Bundle extraInfo)
//        }

        @Override

        public void onRewardArrived(boolean isRewardValid, int rewardType, Bundle extraInfo) {
            fisish();
            // 用户的观看行为满足了奖励条件
//            RewardBundleModel rewardBundleModel = new RewardBundleModel(extraInfo);
//             LogUtils.e("Callback --> rewardVideoAd has onRewardArrived " +
//                    "\n奖励是否有效：" + isRewardValid +
//                    "\n奖励类型：" + rewardType +
//                    "\n奖励名称：" + rewardBundleModel.getRewardName() +
//                    "\n奖励数量：" + rewardBundleModel.getRewardAmount() +
//                    "\n建议奖励百分比：" + rewardBundleModel.getRewardPropose());
//                    " type:" + rewardType + " errorCode:" + rewardBundleModel.getServerErrorCode());
//            if (!isRewardValid) {
//                 LogUtils.e( "发送奖励失败 code：" + rewardBundleModel.getServerErrorCode() +
//                        "\n msg：" + rewardBundleModel.getServerErrorMsg());
//                return;
//            }
//
//
//            if (rewardType == TTRewardVideoAd.REWARD_TYPE_DEFAULT) {
//                 LogUtils.e( "普通奖励发放，name:" + rewardBundleModel.getRewardName() +
//                        "\namount:" + rewardBundleModel.getRewardAmount());
//            }
        }

        @Override
        public void onSkippedVideo() {
            // 用户在观看素材时点击了跳过
            LogUtils.e("Callback --> rewardVideoAd has onSkippedVideo");
        }
    }

    public void fisish(){
        finish();
    }


//    public  void loadAd(String codeId) {
////        if (codeId.isEmpty()) {
////            // 广告位 CodeId 未传, 抛出error
////            DyADCore.getRewardResult();
//////            fireEvent("onAdError", 1001, "广告位 CodeId 未传");
////            return;
////        }
//        // 创建广告请求参数 AdSlot, 具体参数含义参考文档
//        AdSlot adSlot = new AdSlot.Builder()
//                .setCodeId(codeId)
//                .setExpressViewAcceptedSize(500, 500)
//                .setRewardName(DyADCore.rewardName) // 奖励的名称
//                .setRewardAmount(DyADCore.rewardAmount) // 奖励的数量
//                .setUserID(DyADCore.userId) // 用户id,必传参数
//                .setMediaExtra("media_extra") // 附加参数，可选
//                .setOrientation(TTAdConstant.VERTICAL) // 必填参数，期望视频的播放方向：TTAdConstant.HORIZONTAL 或 TTAdConstant.VERTICAL
//                .build();
//
//        //FIXME:  穿山甲需要全面替换 express 模式
//        // 请求广告
//
//        TTAdManagerHolder.get().createAdNative(this).loadRewardVideoAd(
//                adSlot,
//                new TTAdNative.RewardVideoAdListener() {
//                    @Override
//                    public void onError(int code, String message) {
//                        LogUtils.e("reward onError ", message);
////                        fireEvent("onAdError", 1002, message);
//                        if (DyADCore.rewardActivity != null) {
//                            DyADCore.rewardActivity.finish();
//                        }
//                    }
//                    @Override
//                    public void onRewardVideoCached() {}
//
//                    // 视频广告加载后，视频资源缓存到本地的回调，在此回调后，播放本地视频，流畅不阻塞。
//                    @Override
//                    public void onRewardVideoCached(TTRewardVideoAd ad) {
//                        LogUtils.e("reward Cached ", "穿山甲激励视频缓存成功");
////                        fireEvent("onAdVideoCached", 201, "穿山甲激励视频缓存成功");
//                    }
//                    // 视频广告的素材加载完毕，比如视频url等，在此回调后，可以播放在线视频，网络不好可能出现加载缓冲，影响体验。
//                    @Override
//                    public void onRewardVideoAdLoad(TTRewardVideoAd ad) {
//                        LogUtils.e("reward AdLoad ", ad.toString());
//                        sendEvent("AdLoaded", null);
////                        fireEvent("onAdLoaded", 200, "视频广告的素材加载完毕");
//                        // 缓存的更新为最新加载成功的广告
//                        DyADCore.rewardAd = ad;
//                        callback.run();
//                    }
//                }
//        );
//    }
}