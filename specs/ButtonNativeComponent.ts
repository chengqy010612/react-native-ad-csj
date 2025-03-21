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
  text?: string;
  // onClick?: (event: ButtonClickEvent) => void;
  // onClick?: DirectEventHandler<{ message: string }>;
}

export default codegenNativeComponent<NativeProps>(
  'CustomButton',
) as HostComponent<NativeProps>;

