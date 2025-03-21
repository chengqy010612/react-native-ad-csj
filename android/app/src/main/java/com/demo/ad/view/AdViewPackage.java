package com.demo.ad.view;

import androidx.annotation.NonNull;

import com.demo.ad.AdManager;
import com.facebook.react.BaseReactPackage;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdViewPackage extends BaseReactPackage {

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
//        return Collections.singletonList(new BannerViewManager(reactContext));
        return Arrays.<ViewManager>asList(
                new BannerViewManager(reactContext),
                new DrawFeedViewManager(reactContext)
        );
    }

    @Override
    public NativeModule getModule(String s, ReactApplicationContext reactApplicationContext) {
        if (BannerViewManager.REACT_CLASS.equals(s)) {
            return new BannerViewManager(reactApplicationContext);
        }
        if (DrawFeedViewManager.REACT_CLASS.equals(s)) {
            return new DrawFeedViewManager(reactApplicationContext);
        }
        return null;
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() {
            @Override
            public Map<String, ReactModuleInfo> getReactModuleInfos() {
                Map<String, ReactModuleInfo> map = new HashMap<>();
                map.put(BannerViewManager.REACT_CLASS, new ReactModuleInfo(
                        BannerViewManager.REACT_CLASS, // name
                        BannerViewManager.REACT_CLASS, // className
                        false,                           // canOverrideExistingModule
                        false,                           // needsEagerInit
                        false,                           // isCxxModule
                        true                             // isTurboModule
                ));
                map.put(DrawFeedViewManager.REACT_CLASS, new ReactModuleInfo(
                        DrawFeedViewManager.REACT_CLASS, // name
                        DrawFeedViewManager.REACT_CLASS, // className
                        false,                           // canOverrideExistingModule
                        false,                           // needsEagerInit
                        false,                           // isCxxModule
                        true                             // isTurboModule
                ));
                return map;
            }
        };
    }
}