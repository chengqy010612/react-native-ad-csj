import {
  Text,
  ScrollView,
  Image,
  View,
  TouchableOpacity,
  StyleSheet,
  Button,
} from 'react-native';
import {SafeAreaView} from 'react-native-safe-area-context';
import {globalStyles} from '../../style/style';
import Icon from 'react-native-vector-icons/AntDesign';
import { useNavigation } from '@react-navigation/native';
import { useDispatch } from 'react-redux';
import { loginOut } from '../../store/user';
export const UserPage = () => {
    const navigation = useNavigation();
    const dispatch = useDispatch()
  const onLoginOut = () => {
    dispatch(loginOut())
    // navigation.navigate('Login');
  };
  return (
    <SafeAreaView>
      <ScrollView>
        <View style={[globalStyles.flexCenter]}>
          <Image
            source={require('../../assets/testBg.jpg')}
            style={{width: 100, height: 100, borderRadius: 50}}></Image>
        </View>

        <TouchableOpacity>
          <View
            style={[
              globalStyles.flexRow,
              globalStyles.spaceBetween,
              styles.cellItem,
            ]}>
            <View style={{...globalStyles.flexRow, alignItems: 'center'}}>
              <Icon name="home" size={18}></Icon>
              <Text style={{marginLeft: 20}}>设置</Text>
            </View>
            <Icon name="right" size={18}></Icon>
          </View>
        </TouchableOpacity>

        <TouchableOpacity>
          <View
            style={[
              globalStyles.flexRow,
              globalStyles.spaceBetween,
              styles.cellItem,
            ]}>
            <View style={{...globalStyles.flexRow, alignItems: 'center'}}>
              <Icon name="home" size={18}></Icon>
              <Text style={{marginLeft: 20}}>设置</Text>
            </View>
            <Icon name="right" size={18}></Icon>
          </View>
        </TouchableOpacity>


        <Button onPress={onLoginOut} title="退出登录" />
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  cellItem: {
    borderBottomWidth: 2,
    borderBottomColor: '#eee',
    padding: 10,
    alignItems: 'center',
  },
});
