package com.hudawei.dialogsample.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.hudawei.dialogsample.R;

/**
 * Created by hudawei on 2017/2/10.
 * 固定style,接收外部布局
 */

public abstract class BaseDialog extends Dialog {
    private View mContentView;
    private int mAnimationRes;
    private int mGravity;
    private int mWidth;
    private int mHeight;
    private int mOffsetX;
    private int mOffsetY;
    private int mAlpha;

    public BaseDialog(Context context, int layoutId) {
        super(context, R.style.BaseDialog);
        mContentView = getLayoutInflater().inflate(layoutId, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(true);
        if (mContentView != null)
            setContentView(mContentView);
        initWindow(getWindow());
        initView();
        initData();
        initEvent();

    }

    private void initWindow(Window window) {
        window.setWindowAnimations(mAnimationRes);
        WindowManager.LayoutParams lp = window.getAttributes();
        window.setGravity(mGravity);

        if (mOffsetX > 0)
            lp.x = mWidth; // 新位置X坐标
        if (mOffsetY > 0)
            lp.y = mHeight; // 新位置Y坐标
        if (mWidth > 0)
            lp.width = mWidth; // 宽度
        if (mHeight > 0)
            lp.height = mHeight; // 高度
        if (mAlpha > 0)
            lp.alpha = mAlpha; // 透明度
        window.setAttributes(lp);
    }


    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initEvent();

    public View findViewById(int id) {
        return mContentView.findViewById(id);
    }

    public BaseDialog animation(int animationRes) {
        mAnimationRes = animationRes;
        return this;
    }

    public BaseDialog gravity(int gravity) {
        mGravity = gravity;
        return this;
    }

    public BaseDialog width(int width) {
        mWidth = width;
        return this;
    }

    public BaseDialog height(int height) {
        mHeight = height;
        return this;
    }

    public BaseDialog offsetX(int offsetX) {
        mOffsetX = offsetX;
        return this;
    }

    public BaseDialog offsetY(int offsetY) {
        mOffsetY = offsetY;
        return this;
    }

    public BaseDialog alpha(int alpha) {
        mAlpha = alpha;
        return this;
    }

}
