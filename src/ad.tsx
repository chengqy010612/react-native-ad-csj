import {View, StyleSheet, Button, TouchableOpacity, Text} from 'react-native';
import NativeAdManager from './specs/NativeAdManager';
import BannerView from './specs/BannerNativeComponent';
import DrawFeed from './specs/DrawFeedNativeComponent';
import {useEffect, useState} from 'react';
// import { CsjAdView } from 'react-native-csj-ad';
export default function App() {
  let appid = '5643282';
  let feedDrawCodeid = '962822928';
  let bannerId = "962822923"
  let splashId = "890570138"
  let rewardId = "962822926"


  useEffect(() => {
    NativeAdManager?.init({appid: appid, debug: true})
      .then(() => console.log('Success'))
      .catch(error => console.error('Error:', error));
  }, []);

  const [show, setShow] = useState(false);
  const [show2, setShow2] = useState(false);
  return (
    <View style={styles.container}>
      {
        show ? <BannerView style={styles.box} codeId={bannerId}></BannerView> : null
      }
      {show2 ? (
        <DrawFeed style={styles.box} codeId={feedDrawCodeid}></DrawFeed>
      ) : null}
      <TouchableOpacity   style={{
          marginVertical: 20,
          paddingHorizontal: 30,
          paddingVertical: 15,
          backgroundColor: '#F96',
          borderRadius: 5,
        }} onPress={() => setShow(!show)}>
        <Text>显示/隐藏banner</Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={{
          marginVertical: 20,
          paddingHorizontal: 30,
          paddingVertical: 15,
          backgroundColor: '#F96',
          borderRadius: 5,
        }}
        onPress={() => {
          NativeAdManager?.loadSplashAd(splashId);
        }}>
        <Text style={{textAlign: 'center'}}> 开屏</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={{
          marginVertical: 20,
          paddingHorizontal: 30,
          paddingVertical: 15,
          backgroundColor: '#F96',
          borderRadius: 5,
        }}
        onPress={() => {
          NativeAdManager?.loadAndShowRewardAds(rewardId);
        }}>
        <Text style={{textAlign: 'center'}}> 激励</Text>
      </TouchableOpacity>

      <TouchableOpacity
        style={{
          marginVertical: 20,
          paddingHorizontal: 30,
          paddingVertical: 15,
          backgroundColor: '#F96',
          borderRadius: 5,
        }}
        onPress={() => {
          setShow2(!show2);
        }}>
        <Text style={{textAlign: 'center'}}> 显示隐藏信息流</Text>
      </TouchableOpacity>

      {/* <TouchableOpacity
        style={{
          marginVertical: 20,
          paddingHorizontal: 30,
          paddingVertical: 15,
          backgroundColor: '#F96',
          borderRadius: 5,
        }}
        onPress={() => {
          NativeAdManager.startTestActivity();
        }}>
        <Text style={{textAlign: 'center'}}> 测试页面</Text>
      </TouchableOpacity> */}

      {/* <CsjAdView color="#32a852" style={styles.box} /> */}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: '100%',
    height: 100,
    marginVertical: 20,
  },
  button: {
    marginBottom: 20,
  },
});
