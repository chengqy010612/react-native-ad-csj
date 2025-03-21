import {StyleSheet} from 'react-native';
import {screenWidth} from '../utils';

export const globalStyles = StyleSheet.create({
  flexRow: {
    flexDirection: 'row',
  },
  spaceBetween: {
    justifyContent: 'space-between',
  },
  alignCenter: {
    alignItems: 'center',
  },
  widtFill: {
    width: screenWidth,
  },
  white: {
    color: '#fff',
  },
  flexCenter: {
    justifyContent: 'center',
    alignItems: 'center',
  },
});
