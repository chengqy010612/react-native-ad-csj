package com.demo.ad.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.viewmanagers.BannerViewManagerDelegate;
import com.facebook.react.viewmanagers.BannerViewManagerInterface;
import com.facebook.react.viewmanagers.DrawFeedManagerDelegate;
import com.facebook.react.viewmanagers.DrawFeedManagerInterface;

//public class DrawFeedViewManager extends ViewGroupManager<DrawFeedView> {
//    public static final String TAG = "DrawFeedAdViewManager";
//    public static final String REACT_CLASS = "DrawFeed";
//    @NonNull
//    @Override
//    public String getName() {
//        return TAG;
//    }
//
//    public DrawFeedViewManager(ReactApplicationContext reactApplicationContext) {
//    }
//
//    @NonNull
//    @Override
//    protected DrawFeedView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
//        return new DrawFeedView(themedReactContext);
//    }
//
//
//    @ReactProp(name = "codeId")
//    public void setCodeId(@NonNull com.brayantad.dy.drawFeed.view.DrawFeedView view, @NonNull String codeid) {
//        view.setCodeId(codeid);
//    }
//}
//


@ReactModule(name = DrawFeedViewManager.REACT_CLASS)
class DrawFeedViewManager extends SimpleViewManager<DrawFeedView> implements DrawFeedManagerInterface<DrawFeedView> {
    private final DrawFeedManagerDelegate<DrawFeedView, DrawFeedViewManager> delegate =
            new  DrawFeedManagerDelegate<>(this);
    public static final String REACT_CLASS = "DrawFeed";

    public DrawFeedViewManager(ReactApplicationContext reactApplicationContext) {
    }

    @Override
    public ViewManagerDelegate<DrawFeedView> getDelegate() {
        return delegate;
    }

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    public DrawFeedView createViewInstance(ThemedReactContext context) {
//        Button button = new android.widget.Button(context);
//        button.setText("Click Me");
//        return button;
        return new DrawFeedView(context.getReactApplicationContext());
    }

    @Override
    public void setCodeId(DrawFeedView view, @Nullable String codeId) {
        view.setCodeId(codeId);
    }
}
