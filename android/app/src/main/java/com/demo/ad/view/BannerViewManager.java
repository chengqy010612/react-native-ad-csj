package com.demo.ad.view;

import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.brayantad.dy.drawFeed.view.DrawFeedView;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.BannerViewManagerDelegate;
import com.facebook.react.viewmanagers.BannerViewManagerInterface;

@ReactModule(name = BannerViewManager.REACT_CLASS)
class BannerViewManager extends SimpleViewManager<BannerView> implements BannerViewManagerInterface<BannerView> {
    private final BannerViewManagerDelegate<BannerView, BannerViewManager> delegate =
            new BannerViewManagerDelegate<>(this);
    public static final String REACT_CLASS = "BannerView";

    public BannerViewManager(ReactApplicationContext reactApplicationContext) {
    }

    @Override
    public ViewManagerDelegate<BannerView> getDelegate() {
        return delegate;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public BannerView createViewInstance(ThemedReactContext context) {
//        Button button = new android.widget.Button(context);
//        button.setText("Click Me");
//        return button;
        return new BannerView(context.getReactApplicationContext());
    }

    @Override
    public void setCodeId(BannerView view, @Nullable String codeId) {
        view.setCodeId(codeId);
    }
}