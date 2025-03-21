import { requireNativeComponent } from 'react-native';

/**
 * Composes `View`.
 *
 * - src: Array<{url: string}>
 * - borderRadius: number
 * - resizeMode: 'cover' | 'contain' | 'stretch'
 */
type Props = {
    url:string[]
  };
  
export default requireNativeComponent<Props>('RCTImageView1');