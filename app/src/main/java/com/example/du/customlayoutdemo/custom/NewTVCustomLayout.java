package com.example.du.customlayoutdemo.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.du.customlayoutdemo.R;

import java.util.List;

public class NewTVCustomLayout extends LinearLayout {
    private static final String MODULE_TITLE_TEXT = "module_title_text";
    private static final String MODULE_TITLE_IMAGE = "module_title_image";
    private Context mContext;
    private TextView mTitleText;
    private ImageView mTitleImage;
    private FrameLayout mContentView;
    private List<View> mChildViewList;
    private boolean isChildFocusable = true;
    private boolean isChildClickable = true;

    public NewTVCustomLayout(@NonNull Context context) {
        super(context);
        this.mContext = context;
        initView();
        setTag();
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

    public void setTitle(String title) {
        mTitleImage.setVisibility(View.GONE);
        mTitleText.setVisibility(View.VISIBLE);
        mTitleText.setText(title);
    }

    public void setTitle(Bitmap bitmap) {
        mTitleText.setVisibility(View.GONE);
        mTitleImage.setVisibility(View.VISIBLE);
        mTitleImage.setImageBitmap(bitmap);
    }

    public ViewGroup getContentView() {
        return mContentView;
    }

    public void addViewList(List<View> views) {
        mChildViewList = views;
        for (View view : views) {
            view.setFocusable(isChildFocusable);
            view.setClickable(isChildClickable);
            mContentView.addView(view);
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

    public void setLayoutParams(LayoutParams layoutParams) {
        mContentView.setLayoutParams(layoutParams);
    }

    private void initView() {
        setOrientation(VERTICAL);
        if (mTitleText == null) {
            mTitleText = new TextView(mContext);
            mTitleText.setTextColor(mContext.getResources().getColor(R.color.colorBlack));
            mTitleText.setTextSize(35);
            addView(mTitleText);
        }
        if (mTitleImage == null) {
            mTitleImage = new ImageView(mContext);
            addView(mTitleImage);
        }
        if (mContentView == null) {
            mContentView = new FrameLayout(mContext);
            addView(mContentView);
        }
    }

    private void setTag() {
        mTitleText.setTag(MODULE_TITLE_TEXT);
        mTitleImage.setTag(MODULE_TITLE_IMAGE);
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
