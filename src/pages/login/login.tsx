import {Image, StyleSheet, Text, TextInput, View} from 'react-native';
import {Button, Input} from 'react-native-elements';
import Icon from 'react-native-vector-icons/AntDesign';
import {globalStyles} from '../../style/style';
import { useDispatch } from 'react-redux';
import { login } from '../../store/user';
export const LoginPage = () => {
    const dispatch = useDispatch()
    const onLogin = () => {
        dispatch(login({}))
    }

  return (
    <View>
      <Image
        style={{height: 200}}
        source={require('../../assets/testBg.jpg')}></Image>
      <View style={[styles.formWrap]}>
        <View style={[globalStyles.flexRow,globalStyles.alignCenter,styles.formItem]}>
          <Icon name={'user'} size={16} />
          <TextInput placeholder="用户名" />
        </View>
        <View style={[globalStyles.flexRow,globalStyles.alignCenter,,styles.formItem]}>
          <Icon name={'user'} size={16} />
          <TextInput placeholder="密码" />
        </View>
        {/* <Input placeholder="请输入手机号" leftIcon={{type: 'font-awesome', name: 'phone'}}/>
        <Input placeholder="请输入手机号" leftIcon={{type: 'font-awesome', name: 'phone'}}/> */}
        <Button title="登录" onPress={onLogin} style={{paddingTop: 40}} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  formWrap: {
    borderTopEndRadius: 20,
    borderTopStartRadius: 20,
    padding: 20,
  },
  formItem:{
    borderBottomWidth:1,
    borderColor:'#ccc',
    borderRadius:5,
    padding:10,
    marginBottom:10
  }
});
