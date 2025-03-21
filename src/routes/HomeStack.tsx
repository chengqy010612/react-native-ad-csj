import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {Text, View} from 'react-native';
import React from 'react';
import { HomePage } from '../pages/home/indes';
import { PicturePage } from '../pages/home/take-picture';

const Stack = createNativeStackNavigator();

const HomeScreen = (props: any) => {
  return (
    <View>
      <Text>home</Text>
    </View>
  );
};

const HomeStack = () => {
  return (
    <Stack.Navigator initialRouteName="Home" screenOptions={({route}) => ({
      headerShown: false,
    })}>
      <Stack.Screen
        name="Home"
        component={HomePage}
      />
       <Stack.Screen
        name="picture"
        component={PicturePage}
      />
    </Stack.Navigator>
  );
};

export default HomeStack;
