package com.example.du.customlayoutdemo.manager;

import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.example.du.customlayoutdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleLayoutManager {
    private static ModuleLayoutManager INSTANCE;
    private Map<String, Integer> mLayoutMap;
    private Map<String, List<String>> mModulesFirstLine;
    private String[] layoutIds = new String[] {"layout_1", "layout_2"};
    private int[] layoutResIds = new int[] {R.layout.layout_module_1, R.layout.layout_module_2};

    private ModuleLayoutManager() {
        if (mLayoutMap == null) {
            mLayoutMap = new HashMap<>();
        }
        if (mModulesFirstLine == null) {
            mModulesFirstLine = new HashMap<>();
        }
        registerLayout();
        setModulesFirstLine();
    }

    public static ModuleLayoutManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleLayoutManager();
        }
        return INSTANCE;
    }

    public int getLayoutResId(String layoutCode) {
        int layoutResId = layoutResIds[0];
        if (mLayoutMap.containsKey(layoutCode)) {
            layoutResId = mLayoutMap.get(layoutCode);
        }
        return layoutResId;
    }

    public void setModuleFirstLine(String layoutId, List<String> firstLineCellCodes) {
        mModulesFirstLine.put(layoutId, firstLineCellCodes);
    }

    public void setModuleFirstLineByViews(String layoutId, List<View> childViews) {
        List<String> firstLineCellCodes = new ArrayList<>();
        for (View childView : childViews) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childView.getLayoutParams();
            if (layoutParams.topMargin == 0) {
                firstLineCellCodes.add((String) childView.getTag());
            }
        }
        mModulesFirstLine.put(layoutId, firstLineCellCodes);
    }

    public boolean isNeedInterceptKeyEvent(String layoutId, String cellCode) {
        if (mModulesFirstLine == null) {
            return false;
        }
        List<String> firstLineCells = mModulesFirstLine.get(layoutId);
        if (firstLineCells == null || firstLineCells.size() == 0) {
            return false;
        }
        for (String firstLineCellCode : firstLineCells) {
            if (TextUtils.equals(firstLineCellCode, cellCode)) {
                return true;
            }
        }
        return false;
    }

    private void registerLayout() {
        int size = layoutIds.length;
        int i = 0;
        while (i < size) {
            String key = layoutIds[i];
            int value = layoutResIds[i];
            mLayoutMap.put(key, value);
            i++;
        }
    }

    private void setModulesFirstLine() {
        List<String> firstLineCellCodes;
        for (String layoutId : layoutIds) {
            switch (layoutId) {
                case "layout_1":
                    firstLineCellCodes = new ArrayList<>();
                    firstLineCellCodes.add("cell_1_1");
                    firstLineCellCodes.add("cell_1_2");
                    mModulesFirstLine.put(layoutId, firstLineCellCodes);
                    break;
                case "layout_2":
                    firstLineCellCodes = new ArrayList<>();
                    firstLineCellCodes.add("cell_2_1");
                    firstLineCellCodes.add("cell_2_2");
                    firstLineCellCodes.add("cell_2_3");
                    firstLineCellCodes.add("cell_2_4");
                    mModulesFirstLine.put(layoutId, firstLineCellCodes);
                default:
                    break;
            }
        }
    }
}
