import { requireNativeComponent } from "react-native";

type Props = {
  text: string;
};

const MyCustomView = requireNativeComponent<Props>("MyCustomView");

export default MyCustomView;
