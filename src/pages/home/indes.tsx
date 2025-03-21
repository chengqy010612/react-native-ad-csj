import {
  ScrollView,
  StyleSheet,
  Text,
  View,
  TouchableOpacity,
  Image,
} from 'react-native';
import Icon from 'react-native-vector-icons/AntDesign';
import {screenHeight, screenWidth} from '../../utils';
import {Colors} from '../../types';
import Swiper from 'react-native-swiper';
import LinearGradient from 'react-native-linear-gradient';

export const HomePage = () => {
  return (
    <>
      <View>
        <ScrollView>
          <View style={[styles.gridWrap]}>
            {[1, 2, 3, 4].map(item => (
              <TouchableOpacity key={'grid' + item}>
                <View style={[styles.gridItem]}>
                  <Icon name="home" size={40} color={'#fff'} />
                  <Text style={[styles.gridItemFont]}>扫一扫</Text>
                </View>
              </TouchableOpacity>
            ))}
          </View>
          {/* swiper */}
          <Swiper style={styles.swiperWrap} showsButtons={true} autoplay={true}>
            {[1, 2, 3, 4].map(item => (
              <Image
              key={'grid' + item}
                style={[styles.swiperImage]}
                source={require('../../assets/testBg.jpg')}></Image>
            ))}
          </Swiper>
          {/*  */}
          <View style={[styles.dailyWrap]}>
            {[1,2,3].map((item, index) => (
              <LinearGradient
              style={[styles.dailyItem]}
                start={{x: 0, y: 0}}
                end={{x: 1, y: 0}}
                colors={['#ddd', '#333']}
                key={'weather' + index}>
                <Text style={[styles.white]}>2024-05-01</Text>
                <View style={[styles.flexRow,styles.spaceBetween,styles.widtFill]}>
                  <View style={{...styles.flexRow,marginLeft:20}}>
                    <Image
                      style={{width: 50, height: 50}}
                      source={require('../../assets/testBg.jpg')}></Image>
                    <Text style={[styles.white]}>晴15°C</Text>
                  </View>
                  <View style={{...styles.flexRow,marginRight:20}}>
                    <Image
                      style={{width: 50, height: 50}}
                      source={require('../../assets/testBg.jpg')}></Image>
                    <Text style={[styles.white]}>晴15°C</Text>
                  </View>
                </View>
              </LinearGradient> 
            ))}
          </View>
        </ScrollView>
      </View>
    </>
  );
};

const styles = StyleSheet.create({
  flexRow: {
    flexDirection: 'row',
  },
  spaceBetween: {
    justifyContent: 'space-between',
  },
  widtFill: {
    width: screenWidth,
  },
  white: {
    color: "#fff",
  },


  gridWrap: {
    flexDirection: 'row',
  },
  gridItem: {
    width: screenWidth / 4,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: Colors.primary,
    height: 90,
  },
  gridItemFont: {
    color: '#fff',
  },
  text: {
    color: '#fff',
    fontSize: 30,
    fontWeight: 'bold',
  },
  swiperWrap: {
    height: 200,
  },
  swiperImage: {
    height: 200,
    width: screenHeight,
  },
  dailyWrap: {
  },
  dailyItem: {
    alignItems: 'center',
    // width: '95%',
    margin: 10,
    borderRadius: 20,
    paddingBottom: 20,
  },
});
