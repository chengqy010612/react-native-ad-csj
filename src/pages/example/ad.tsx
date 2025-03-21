import {useNavigation} from '@react-navigation/native';
import {useEffect, useState} from 'react';
import {
  Alert,
  FlatList,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TouchableOpacity,
  View,
} from 'react-native';
import {Button, Tab, TabView} from 'react-native-elements';
import {ad} from 'react-native-ad';
import {DrawFeedView, FeedAdView, init, loadDrawFeedAd} from 'react-native-brayant-ad';
import {dyLoadSplashAd, startRewardVideo} from 'react-native-brayant-ad';
import MyCustomView from '../../components/MyCustomView';
import RCTImageView from '../../components/RCTImageView';
// import NativeLocalStorage from '../../../specs/NativeLocalStorage';
// import TestView from '../../components/TestView';
// import CustomButton from '../../../specs/ButtonNativeComponent';
//测试
//    const val ADS_CODE_ID = "5001121"
//    const val ADS_SPLASH_CODE = "801121648"
//    const val ADS_BANNER_CODE = "962771599"
//    const val ADS_REWARD_CODE = "901121430"
//    const val ADS_DRAWER_CODE = "103319273"
//    const val ADS_BANNER_PAUSE_CODE ="103320868"

export const AdPage = () => {
  // const navigation = useNavigation();
  // const appid = '5001121';
  const [showDrawFeedView, setShowDrawFeedView] = useState(false);

  let appid = '5643282';
  let feedDrawCodeid = '962822928';


  useEffect(() => {
    init({
      appid,
      app: '设备信息',
    });
    // loadDrawFeedAd({
    //   appid: '5001121',
    //   codeid: '103319273',
    // });

    //   .then((res) => {
    //     console.log(res);
    //     // setShowFeedView(true);
    //     // requestPermission();
    //   })
    //   .catch((e) => {
    //     console.log(e);
    //   });
  }, []);
  return (
    <>
      {/* <TestView style={styles.box} ></TestView> */}
      {/* <CustomButton text='点击1111' style={styles.box} onClick={(e) => { console.log(e.nativeEvent)}}></CustomButton> */}
      {/* <MyCustomView text="Hello Components111" style={styles.box} /> */}
      {/* <RCTImageView src={["https://imgv3.fotor.com.cn/images/side/feature-edit-step.png"]}></RCTImageView> */}
      <Button
        style={styles.button}
        onPress={() => {
          const splashAd = dyLoadSplashAd({
            codeid: '801121648',
            anim: 'default',
          });

          splashAd.subscribe('onAdClose', event => {
            console.log('广告关闭', event);
          });

          splashAd.subscribe('onAdSkip', i => {
            console.log('用户点击跳过监听', i);
          });

          splashAd.subscribe('onAdError', e => {
            console.log('开屏加载失败监听', e);
          });

          splashAd.subscribe('onAdClick', e => {
            console.log('开屏被用户点击了', e);
          });

          splashAd.subscribe('onAdShow', e => {
            console.log('开屏开始展示', e);
          });
        }}
        title="开屏"
      />
      <Button
        style={styles.button}
        onPress={() => {
          const rewardVideo = startRewardVideo({
            codeid: '901121430',
          });

          rewardVideo.result.then((val: any) => {
            console.log('RewardVideo 回调结果', val);
          });

          rewardVideo.subscribe('onAdLoaded', event => {
            console.log('广告加载成功监听', event);
          });

          rewardVideo.subscribe('onAdError', event => {
            console.log('广告加载失败监听', event);
          });

          rewardVideo.subscribe('onAdClose', event => {
            console.log('广告被关闭监听', event);
          });

          rewardVideo.subscribe('onAdClick', event => {
            console.log('广告点击查看详情监听', event);
          });
        }}
        title="激励"
      />
      <Button style={styles.button} onPress={() => {}} title="ad" />
      <TouchableOpacity onPress={() => setShowDrawFeedView(!showDrawFeedView)}>
        <Text>显示/隐藏DrawFeed</Text>
      </TouchableOpacity>
      <FeedAdView
        codeid={feedDrawCodeid}
        adWidth={400}
        visible={showDrawFeedView}
        onAdLayout={(data: any) => {
          console.log('Feed 广告加载成功！', data);
        }}
        onAdClose={(data: any) => {
          console.log('Feed 广告关闭！', data);
        }}
        onAdError={(err: any) => {
          console.log('Feed 广告加载失败！', err);
        }}
        onAdClick={(val: any) => {
          console.log('Feed 广告被用户点击！', val);
        }}
      />

      {/* <DrawFeedView
        appid={'5001121'}
        codeid={'945493687'}
        visible={true}
        onAdError={(e: any) => {
          console.log('DrawFeedAd 加载失败', e);
        }}
        onAdShow={(e: any) => {
          console.log('DrawFeedAd 开屏开始展示', e);
        }}
        onAdClick={(e: any) => {
          console.log('onAdClick DrawFeed', e.nativeEvent);
        }}
       />  */}
    </>
  );
};

const styles = StyleSheet.create({
  button: {
    marginBottom: 20,
  },
  box: {
    width: 200,
    height: 50,
  },
});
