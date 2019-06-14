package com.example.du.customlayoutdemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du.customlayoutdemo.R;
import com.example.du.customlayoutdemo.convert.CustomLayoutConvert;
import com.example.du.customlayoutdemo.custom.NewTVCustomLayout;
import com.example.du.customlayoutdemo.manager.ModuleLayoutManager;
import com.example.du.customlayoutdemo.model.ModuleItem;
import com.example.du.customlayoutdemo.model.ProgramInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {
    private static final String TAG = "ModuleAdapter";
    private Context mContext;
    private List<ModuleItem> mModuleList;
    private ModuleLayoutManager moduleLayoutManager;

    public ModuleAdapter(List<ModuleItem> moduleList) {
        mModuleList = moduleList;
        moduleLayoutManager = ModuleLayoutManager.getInstance();
    }

    @NonNull
    @Override
    public ModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(mContext == null) {
            mContext = parent.getContext();
        }
        View view;
        if (viewType > 0) {  //静态布局
            Log.d(TAG, "viewType: " + viewType);
            int layoutResId = moduleLayoutManager.getLayoutResByViewType(viewType);
            view = LayoutInflater.from(mContext).inflate(layoutResId, parent, false);
        } else {  //动态布局
            view = new NewTVCustomLayout(mContext);
        }
        final ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.ViewHolder viewHolder, int i) {
        ModuleItem moduleItem = mModuleList.get(i);
        TextView moduleTitle = (TextView) viewHolder.getViewByTag("module_title_text");
        moduleTitle.setText(moduleItem.getModuleTitle());
        String layoutCode = moduleItem.getLayoutCode();
        if (layoutCode.split("_")[1].equals("custom")) {
            //不配置LayoutParams默认是wrap_content
//            FrameLayout.LayoutParams layoutParams = new NewTVCustomLayout.LayoutParams(1000, 800).build();
            List<View> childrenViews = CustomLayoutConvert.getInstance(mContext, ((NewTVCustomLayout)viewHolder.itemView).getContentView())
                    .convert("ProgramInfo", layoutCode, moduleItem.getProgramInfos());
            //注册首行Cell (要求设置首行的y必须为0)
            moduleLayoutManager.setModuleFirstLineByViews(layoutCode, childrenViews);
            ((NewTVCustomLayout)viewHolder.itemView).addViewList(childrenViews);
//            ((NewTVCustomLayout)viewHolder.itemView).setLayoutParams(layoutParams);
        } else {
            List<ProgramInfo> programInfos = moduleItem.getProgramInfos();
            for (int j = 0; j< programInfos.size(); j++) {
                String cellCode = programInfos.get(j).getCellCode();
                String cellContent = programInfos.get(j).getContent();
                Log.d(TAG, "cellCode: " + cellCode);
                FrameLayout cell = (FrameLayout) viewHolder.getViewByTag(cellCode);
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_program_info, cell, false);
                TextView contentText = view.findViewById(R.id.program_content);
                contentText.setText(cellContent);
                cell.addView(view);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mModuleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ModuleItem moduleItem = mModuleList.get(position);
        if (moduleItem != null) {
            String layoutCode = moduleItem.getLayoutCode();
            if (layoutCode != null) {
                return moduleLayoutManager.getViewType(layoutCode);
            }
        }
        return super.getItemViewType(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected View mView;
        protected Map<String, View> mTagAndViewMap;  //缓存组件

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTagAndViewMap = new HashMap<>();
        }

        public View getViewByTag(String tag) {
            View targetView = null;
            if (!TextUtils.isEmpty(tag)) {
                targetView = mTagAndViewMap.get(tag);
                if (targetView == null) {
                    targetView = mView.findViewWithTag(tag);
                    mTagAndViewMap.put(tag, targetView);
                }
            }
            return targetView;
        }
    }
}
