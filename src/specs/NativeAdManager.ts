import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';
import {Int32} from 'react-native/Libraries/Types/CodegenTypes';

// interface ReadableMap {
//   // 检查键是否存在
//   hasKey(key: string): boolean;
//   // 检查值是否为null
//   isNull(key: string): boolean;
//   // 获取基本类型
//   getBoolean(key: string): boolean;
//   getNumber(key: string): number;
//   getString(key: string): string;
//   // 获取嵌套结构
//   getMap(key: string): ReadableMap;
//   getArray(key: string): ReadableArray;
//   // 可选：获取所有键或动态类型值
//   keys(): string[];
//   getValue<T>(key: string): T; // 泛型方法示例
// }

// interface ReadableArray {
//   size(): number;
//   getBoolean(index: number): boolean;
//   getNumber(index: number): number;
//   getString(index: number): string;
//   getMap(index: number): ReadableMap;
//   getArray(index: number): ReadableArray;
// }


export interface Spec extends TurboModule {
  init(options: { [key: string]: unknown }): Promise<void>;
  loadSplashAd(codeid:string):void
  startTestActivity():void
  loadAndShowRewardAds(codeid:string):void
}

const NativeAdManager = TurboModuleRegistry.getEnforcing<Spec>(
  'NativeAdManager',
) as Spec;
export default NativeAdManager;
