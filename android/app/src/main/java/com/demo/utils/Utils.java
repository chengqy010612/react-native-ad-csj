package com.demo.utils;

import android.content.Intent;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;

public class Utils {

    // for fix addView not showing ====
    public static void setupLayoutHack(final ViewGroup view) {
        Choreographer.getInstance().postFrameCallback(new Choreographer.FrameCallback() {
            // 这个事件很关键，不然不能触发再次渲染，让 view 在RN里渲染成功!!
            @Override
            public void doFrame(long frameTimeNanos) {
                manuallyLayoutChildren(view);
                view.getViewTreeObserver().dispatchOnGlobalLayout();
                Choreographer.getInstance().postFrameCallback(this);
            }
        });
    }

    // 这个函数很关键，不然不能触发再次渲染，让 view 在RN里渲染成功!!
    public static void manuallyLayoutChildren(ViewGroup view) {
        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            child.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), View.MeasureSpec.EXACTLY));
            child.layout(0, 0, child.getMeasuredWidth(), child.getMeasuredHeight());
        }
    }


//    private void bindButton(@IdRes int id, final Class clz) {
//        findViewById(id).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(BannerActivity.this, clz);
//                startActivity(intent);
//            }
//        });
//    }
}
