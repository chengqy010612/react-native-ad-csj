package com.demo.ad;

import com.facebook.react.BaseReactPackage;
import com.facebook.react.TurboReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdModulePackage extends TurboReactPackage  {
    @Override
    public NativeModule getModule(String s, ReactApplicationContext reactApplicationContext) {
        if (AdManager.NAME.equals(s)) {
            return new AdManager(reactApplicationContext);
        }
        return null;
    }

    @Override
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return new ReactModuleInfoProvider() {
            @Override
            public Map<String, ReactModuleInfo> getReactModuleInfos() {
                Map<String, ReactModuleInfo> map = new HashMap<>();
                map.put(AdManager.NAME, new ReactModuleInfo(
                        AdManager.NAME, // name
                        AdManager.NAME, // className
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