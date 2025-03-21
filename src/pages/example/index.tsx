import {useNavigation} from '@react-navigation/native';
import {useState} from 'react';
import {
  FlatList,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  View,
} from 'react-native';
import {Button, ListItem, Tab, TabView} from 'react-native-elements';
import {SafeAreaView} from 'react-native-safe-area-context';

const DATA = [
  {
    id: 'bd7acbea-c1b1-46c2-aed5-3ad53abb28ba',
    title: '视频',
  },
  {
    id: '3ac68afc-c605-48d3-a4f8-fbd91aa97f63',
    title: 'ad',
  },
  {
    id: '58694a0f-3da1-471f-bd96-145571e29d72',
    title: 'Third Item',
  },
];

type ItemProps = {title: string};

const Item = ({title}: ItemProps) => (
  <View style={styles.item}>
    <Text style={styles.title}>{title}</Text>
  </View>
);

export const ExamplePage = () => {
  const navigation = useNavigation();
  // navigation.navigate('ad');
  return (
    <>
      <SafeAreaView style={styles.container}>
        <ListItem onPress={() => navigation.navigate('ad')}>
          <ListItem.Content>
            <ListItem.Title>广告demo</ListItem.Title>
          </ListItem.Content>
        </ListItem>
        <ListItem onPress={() => navigation.navigate('storage')}>
          <ListItem.Content>
            <ListItem.Title>存储</ListItem.Title>
          </ListItem.Content>
        </ListItem>
        <ListItem onPress={() => navigation.navigate('webview')}>
          <ListItem.Content>
            <ListItem.Title>WebView</ListItem.Title>
          </ListItem.Content>
        </ListItem>
        <ListItem>
          <ListItem.Content>
            <ListItem.Title>视频</ListItem.Title>
          </ListItem.Content>
        </ListItem>
      </SafeAreaView>

      {/* <ScrollView
        horizontal
        contentContainerStyle={{height: 50}}
        style={{height: 50}}> */}
      {/* <Tab value={index} onChange={setIndex} variant="primary">
          {new Array(3).fill(2).map(item => (
            <Tab.Item title="recent" />
          ))}
        </Tab> */}
      {/* </ScrollView> */}
      {/* <Button title="Click me!" /> */}
      {/* <TabView value={index} onChange={setIndex} style={{height: 200}}>
        <TabView.Item style={{backgroundColor: 'red', width: '100%'}}>
          <Text h1>Recent</Text>
        </TabView.Item>
        <TabView.Item style={{backgroundColor: 'blue', width: '100%'}}>
          <Text h1>Favorite</Text>
        </TabView.Item>
        <TabView.Item style={{backgroundColor: 'green', width: '100%'}}>
          <Text h1>Cart</Text>
        </TabView.Item>
      </TabView> */}
    </>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    marginTop: StatusBar.currentHeight || 0,
  },
  item: {
    backgroundColor: '#f9c2ff',
    padding: 20,
    marginVertical: 8,
    marginHorizontal: 16,
  },
  title: {
    fontSize: 32,
  },
});
