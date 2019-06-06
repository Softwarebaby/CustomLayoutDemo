package com.example.du.customlayoutdemo.convert;

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

public class CustomLayoutConvert {
    private static CustomLayoutConvert INSTANCE;
    private List<View> childrenViews = null;
    private Context mContext;
    private ViewGroup mParent;
    private String mLayoutId;

    private List<ProgramInfo> programInfos;

    private CustomLayoutConvert(Context context, ViewGroup parent) {
        mContext = context;
        mParent = parent;
    }

    public static CustomLayoutConvert getInstance(Context context, ViewGroup parent) {
        if (INSTANCE == null) {
            INSTANCE = new CustomLayoutConvert(context, parent);
        }
        return INSTANCE;
    }

    public <T>List<View> convert(String beanName, String layoutCode, List<T> list) {
      if (beanName.equals("ProgramInfo")) {
          programInfos = (List<ProgramInfo>) list;
          mLayoutId = getLayoutId(layoutCode);
          childrenViews = getProgramInfoViews();
      }
      return childrenViews;
    }

    private String getLayoutId(String layoutCode) {
        return layoutCode.substring(layoutCode.indexOf("_") + 1);
    }

    private List<View> getProgramInfoViews() {
        List<View> programInfoViews = new ArrayList<>();
        int index = 1;
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
            view.setTag("cell_" + mLayoutId + (index++));
            programInfoViews.add(view);
        }
        return programInfoViews;
    }
}
