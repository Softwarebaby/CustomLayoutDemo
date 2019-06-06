package com.example.du.customlayoutdemo.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class NewTVCustomLayout extends FrameLayout {
    private Context mContext;
    private List<View> mChildViewList;
    private boolean isChildFocusable = true;
    private boolean isChildClickable = true;

    public NewTVCustomLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public NewTVCustomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewTVCustomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewTVCustomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setChildFocusable(boolean childFocusable) {
        isChildFocusable = childFocusable;
    }

    public boolean isChildFocusable() {
        return isChildFocusable;
    }

    public void setChildClickable(boolean childClickable) {
        isChildClickable = childClickable;
    }

    public boolean isChildClickable() {
        return isChildClickable;
    }

    public void addViewList(List<View> views) {
        mChildViewList = views;
        for (View view : views) {
            view.setFocusable(isChildFocusable);
            view.setClickable(isChildClickable);
            addView(view);
        }
    }

    public void removeAllFocus() {
        if (mChildViewList != null) {
            for (View view : mChildViewList) {
                view.setFocusable(false);
            }
            isChildFocusable = false;
        }
    }

    public void removeAllClick() {
        if (mChildViewList != null) {
            for (View view : mChildViewList) {
                view.setClickable(false);
            }
            isChildClickable = true;
        }
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        private int width;
        private int height;
        private int gravity = LayoutParams.UNSPECIFIED_GRAVITY;
        private int marginLeft = 0;
        private int marginTop = 0;
        private int marginRight = 0;
        private int marginBottom = 0;

        public LayoutParams(int width, int height) {
            super(width, height);
            this.width = width;
            this.height = height;
        }

        public LayoutParams(int width, int height, int gravity) {
            super(width, height, gravity);
            this.width = width;
            this.height = height;
            this.gravity = gravity;
        }

        public LayoutParams left(int marginLeft) {
            this.marginLeft = marginLeft;
            return this;
        }

        public LayoutParams top(int marginTop) {
            this.marginTop = marginTop;
            return this;
        }

        public LayoutParams right(int marginRight) {
            this.marginRight = marginRight;
            return this;
        }

        public LayoutParams Bottom(int marginBottom) {
            this.marginBottom = marginBottom;
            return this;
        }

        public FrameLayout.LayoutParams build() {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width, height);
            layoutParams.leftMargin = marginLeft;
            layoutParams.topMargin = marginTop;
            layoutParams.rightMargin = marginRight;
            layoutParams.bottomMargin = marginBottom;
            layoutParams.gravity = gravity;
            return layoutParams;
        }
    }
}
