package com.example.du.customlayoutdemo.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.du.customlayoutdemo.R;
import com.example.du.customlayoutdemo.model.ProgramInfo;

import java.util.ArrayList;
import java.util.List;

public class CustomLayoutManager {
    private static CustomLayoutManager INSTANCE;
    private List<View> childrenViews = null;
    private Context mContext;
    private ViewGroup mParent;

    private static List<ProgramInfo> programInfos;

    private CustomLayoutManager(Context context, ViewGroup parent) {
        mContext = context;
        mParent = parent;
    }

    public static CustomLayoutManager getInstance(Context context, ViewGroup parent) {
        if (INSTANCE == null) {
            INSTANCE = new CustomLayoutManager(context, parent);
        }
        return INSTANCE;
    }

    public <T>List<View> transform(String beanName, List<T> list) {
      if (beanName.equals("ProgramInfo")) {
          programInfos = (List<ProgramInfo>) list;
          childrenViews = getProgramInfoViews();
      }
      return childrenViews;
    }

    private List<View> getProgramInfoViews() {
        List<View> programInfoViews = new ArrayList<>();
        for (ProgramInfo programInfo : programInfos) {
            FrameLayout view = (FrameLayout) LayoutInflater.from(mContext).inflate(R.layout.item_program_info, mParent, false);
            TextView programContent = view.findViewById(R.id.program_content);
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            programContent.setText(programInfo.getContent());
            params.width = programInfo.getWidth();
            params.height = programInfo.getHeight();
            params.leftMargin = programInfo.getX();
            params.topMargin = programInfo.getY();
            view.setLayoutParams(params);
            programInfoViews.add(view);
        }
        return programInfoViews;
    }
}
