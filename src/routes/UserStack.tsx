import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {Text, View} from 'react-native';
import React from 'react';
import { HomePage } from '../pages/home/indes';
import { UserPage } from '../pages/user/indes';

const Stack = createNativeStackNavigator();

const UserStack = () => {
  return (
    <Stack.Navigator initialRouteName="user" screenOptions={({route}) => ({
        headerShown: false,
      })}>
      <Stack.Screen
        options={{title: 'user'}}
        name="user"
        component={UserPage}
      />
    </Stack.Navigator>
  );
};

export default UserStack;
