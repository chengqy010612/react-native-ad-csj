import {Text, TouchableOpacity, View} from 'react-native';
// import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import {createBottomTabNavigator} from '@react-navigation/bottom-tabs';
import Icon from 'react-native-vector-icons/AntDesign';
import {NavigationContainer, useNavigation} from '@react-navigation/native';
import React, {useState} from 'react';
import {Colors} from '../types';
import HomeStack from './HomeStack';
import UserStack from './UserStack';
import {ExamplePage} from '../pages/example/index';
import ExampleStack from './Example';
import { Update } from '../pages/update';

const Tab = createBottomTabNavigator();

export function MyTabs(props: any) {
  // const navigation = useNavigation();
  const [typeList, setTypeList] = useState([
    {key: 'top', title: '头条'},
    {key: 'shehui', title: '社会'},
    {key: 'guonei', title: '国内'},
    {key: 'guoji', title: '国际'},
    {key: 'yule', title: '娱乐'},
    {key: 'tiyu', title: '体育'},
    {key: 'junshi', title: '军事'},
    {key: 'keji', title: '科技了'},
    {key: 'caijing', title: '财经'},
    {key: 'shishang', title: '时尚'},
  ]);


  return (
    <Tab.Navigator>
      <Tab.Screen
        name="Home"
        component={HomeStack}
        options={({route}) => ({
          tabBarLabel: '首页',
          headerStyle: {
            backgroundColor: Colors.primary,
            elevation: 0,
            shadowOpacity: 0,
          },
          headerTintColor: '#fff',
          title: '首页',
          tabBarIcon: ({color, size}) => {
            return <Icon name="home" size={size} color={color} />;
          },
          headerRight: () => (
            <TouchableOpacity
              onPress={() => {
                // navigation.navigate('picture', {
                //   itemId: 86,
                //   otherParam: 'anything you want here',
                // });
                // navigation.navigate('picture')
              }}>
              <Icon
                size={28}
                name="pluscircleo"
                color="#fff"
                style={{marginRight: 10}}
              />
            </TouchableOpacity>
          ),
        })}
      />
       <Tab.Screen
        name="update"
        component={Update}
        options={({route}) => ({
          tabBarLabel: '动态',
          title: '动态',
          tabBarIcon: ({color, size}) => {
            return <Icon name="home" size={size} color={color} />;
          },
        })}
      />
      <Tab.Screen
        name="Example"
        component={ExampleStack}
        options={({route}) => ({
          tabBarLabel: '例子',
          // tabBarStyle:"",
          title: '例子',
          tabBarIcon: ({color, size}) => {
            return <Icon name="menu" size={size} color={color} />;
          },
        })}
      />
      <Tab.Screen
        name="Profile"
        component={UserStack}
        options={({route}) => ({
          tabBarLabel: '我的',
          title: '我的',
          tabBarIcon: ({color, size}) => {
            return <Icon name="user" size={size} color={color} />;
          },
        })}
      />
    </Tab.Navigator>
  );
}

export default MyTabs;
