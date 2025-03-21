/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './src/ad';
// import App from './src/App';
import {name as appName} from './app.json';
import { AdPage } from './src/pages/example/ad';

AppRegistry.registerComponent(appName, () => App);
