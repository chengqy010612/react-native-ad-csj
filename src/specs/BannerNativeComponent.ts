import type {HostComponent, NativeSyntheticEvent, ViewProps} from 'react-native';
import type {BubblingEventHandler, DirectEventHandler} from 'react-native/Libraries/Types/CodegenTypes';
import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';

// type WebViewScriptLoadedEvent = {
//   result: 'success' | 'error';
// };
type ClickEventPayload = Readonly<{
  message: string;
}>;

export interface NativeProps extends ViewProps {
  codeId?: string;
  onError?: DirectEventHandler<{ message: string }>;
}

// let NativeBannerView = codegenNativeComponent<NativeProps>(
//   'NativeBannerView',
// ) as HostComponent<NativeProps>;

// export const BannerView =  codegenNativeComponent<NativeProps>(
//   'BannerView',
// ) as HostComponent<NativeProps>;

export default codegenNativeComponent<NativeProps>(
  'BannerView',
) as HostComponent<NativeProps>;

