import {createNativeStackNavigator} from '@react-navigation/native-stack';
import {Text, View} from 'react-native';
import React from 'react';
import { HomePage } from '../pages/home/indes';
import { UserPage } from '../pages/user/indes';
import { ExamplePage } from '../pages/example';
import { AdPage } from '../pages/example/ad';
import Storage from '../pages/example/storage';
import WebView from '../pages/example/webview';

const Stack = createNativeStackNavigator();

const ExampleStack = () => {
  return (
    <Stack.Navigator initialRouteName="example" screenOptions={({route}) => ({
        headerShown: false,
      })}>
      <Stack.Screen
        name="example"
        component={ExamplePage}
      />
      <Stack.Screen
        name="ad"
        component={AdPage}
      />
      <Stack.Screen
        name="storage"
        component={Storage}
      />
      <Stack.Screen
        name="webview"
        component={WebView}
      />
    </Stack.Navigator>
  );
};

export default ExampleStack;
