

# react-native-ad-cjs

穿山甲广告接入穿山甲demo，使用react-native新架构,集成了banner,插屏，激励，信息流广告



### demo快速启动

```
npm i
npm run android
```



### 使用

```js
import NativeAdManager from './specs/NativeAdManager';
import BannerView from './specs/BannerNativeComponent';
import DrawFeed from './specs/DrawFeedNativeComponent';


  let appid = '5643282';
  let feedDrawCodeid = '962822928';
  let bannerId = "962822923"
  let splashId = "890570138"
  let rewardId = "962822926"

//初始化  
useEffect(() => {
    NativeAdManager?.init({appid: appid, debug: true})
      .then(() => console.log('Success'))
      .catch(error => console.error('Error:', error));
  }, []);


//banner  需要组件设置宽高
<BannerView style={{ width: '100%',height: 100}} codeId={bannerId}></BannerView>
//信息流  需要组件设置宽高
<DrawFeed style={{ width: '100%',height: 100}} codeId={feedDrawCodeid}></DrawFeed>
//开屏
 NativeAdManager?.loadSplashAd(splashId);
//激励
NativeAdManager?.loadAndShowRewardAds(rewardId);



```

![1](https://gitee.com/chengqingyuan/img-store/raw/master/react-native-ad/1.jpg)

