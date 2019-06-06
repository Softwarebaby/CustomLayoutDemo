package com.example.du.customlayoutdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du.customlayoutdemo.R;
import com.example.du.customlayoutdemo.custom.NewTVCustomLayout;
import com.example.du.customlayoutdemo.convert.CustomLayoutConvert;
import com.example.du.customlayoutdemo.manager.ModuleLayoutManager;
import com.example.du.customlayoutdemo.model.ModuleItem;
import com.example.du.customlayoutdemo.model.ProgramInfo;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {
    private Context mContext;
    private List<ModuleItem> mModuleList;
    private ModuleLayoutManager moduleLayoutManager;

    public ModuleAdapter(List<ModuleItem> moduleList) {
        mModuleList = moduleList;
        moduleLayoutManager = ModuleLayoutManager.getInstance();
    }

    @NonNull
    @Override
    public ModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(mContext == null) {
            mContext = viewGroup.getContext();
        }
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_module, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ModuleAdapter.ViewHolder viewHolder, int i) {
        ModuleItem moduleItem = mModuleList.get(i);
        viewHolder.moduleTitle.setText(moduleItem.getModuleTitle());
        String layoutCode = moduleItem.getLayoutCode();
        View contentView = null;
        if (layoutCode.split("_")[1].equals("custom")) {
            contentView = new NewTVCustomLayout(mContext);
            //不配置LayoutParams默认是wrap_content
//            FrameLayout.LayoutParams layoutParams = new NewTVCustomLayout.LayoutParams(400, 300).build();
            List<View> childrenViews = CustomLayoutConvert.getInstance(mContext, viewHolder.moduleContent)
                    .convert("ProgramInfo", layoutCode, moduleItem.getProgramInfos());
            //注册首行Cell (要求设置首行的y必须为0)
            ModuleLayoutManager.getInstance().setModuleFirstLineByViews(layoutCode, childrenViews);
            ((NewTVCustomLayout) contentView).addViewList(childrenViews);
//            contentView.setLayoutParams(layoutParams);
        } else {
            int layoutResId = moduleLayoutManager.getLayoutResId(layoutCode);
            contentView = LayoutInflater.from(mContext).inflate(layoutResId, viewHolder.moduleContent, false);
            List<ProgramInfo> programInfos = moduleItem.getProgramInfos();
            for (int j = 0; j< programInfos.size(); j++) {
                String content = programInfos.get(j).getContent();
                String cellName = "cell_" + (j+1);
                int cellId = mContext.getResources().getIdentifier(cellName, "id", "com.example.du.customlayoutdemo");
                FrameLayout cell = contentView.findViewById(cellId);
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_program_info, cell, false);
                TextView contentText = view.findViewById(R.id.program_content);
                contentText.setText(content);
                cell.addView(view);
            }
        }
        viewHolder.moduleContent.addView(contentView);
    }

    @Override
    public int getItemCount() {
        return mModuleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       TextView moduleTitle;
       FrameLayout moduleContent;

        public ViewHolder(View view) {
            super(view);
            moduleTitle = view.findViewById(R.id.module_title);
            moduleContent = view.findViewById(R.id.module_content);
        }
    }
}
