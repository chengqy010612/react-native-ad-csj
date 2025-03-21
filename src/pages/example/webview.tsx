import React from 'react';
import {Alert, StyleSheet, View} from 'react-native';
import WebViewNativeComponent from '../../../specs/WebViewNativeComponent';

function WebView1(): React.JSX.Element {
  return (
    <View style={styles.container}>
      <WebViewNativeComponent
        sourceURL="https://react.dev/"
        style={styles.webview}
        onScriptLoaded={() => {
          Alert.alert('Page Loaded');
        }}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    alignContent: 'center',
  },
  webview: {
    width: '100%',
    height: '100%',
  },
});

export default WebView1;