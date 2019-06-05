package com.example.du.customlayoutdemo.manager;

import com.example.du.customlayoutdemo.R;

import java.util.HashMap;
import java.util.Map;

public class ModuleLayoutManager {
    private static ModuleLayoutManager INSTANCE;
    private Map<String, Integer> layoutMap;
    private String[] layoutIds = new String[] {"layout_1", "layout_2"};
    private int[] layoutResIds = new int[] {R.layout.layout_module_1, R.layout.layout_module_2};

    private ModuleLayoutManager() {
        if (layoutMap == null) {
            layoutMap = new HashMap<>();
        }
        registerLayout();
    }

    public static ModuleLayoutManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ModuleLayoutManager();
        }
        return INSTANCE;
    }

    public int getLayoutResId(String layoutCode) {
        int layoutResId = layoutResIds[0];
        if (layoutMap.containsKey(layoutCode)) {
            layoutResId = layoutMap.get(layoutCode);
        }
        return layoutResId;
    }

    private void registerLayout() {
        int size = layoutIds.length;
        int i = 0;
        while (i < size) {
            String key = layoutIds[i];
            int value = layoutResIds[i];
            layoutMap.put(key, value);
            i++;
        }
    }
}
