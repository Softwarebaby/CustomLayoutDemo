package com.example.du.customlayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.du.customlayoutdemo.adapter.ModuleAdapter;
import com.example.du.customlayoutdemo.model.ModuleItem;
import com.example.du.customlayoutdemo.model.ProgramInfo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ModuleItem> mModuleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        ModuleItem moduleItem1 = getStateModuleItem("layout_1", "测试静态布局1", 2);
        ModuleItem moduleItem2 = getStateModuleItem("layout_2", "测试静态布局2", 4);

        ModuleItem moduleItem3 = new ModuleItem("layout_custom_1", "测试动态布局1");
        List<ProgramInfo> programInfos1 = new ArrayList<>();
        ProgramInfo programInfo1 = new ProgramInfo(400, 200, 0, 0, "1");
        ProgramInfo programInfo2 = new ProgramInfo(400, 200, 450, 0, "2");
        ProgramInfo programInfo3 = new ProgramInfo(400, 200, 900, 0, "3");
        programInfos1.add(programInfo1);
        programInfos1.add(programInfo2);
        programInfos1.add(programInfo3);
        moduleItem3.setProgramInfos(programInfos1);

        ModuleItem moduleItem4 = new ModuleItem("layout_custom_2", "测试动态布局2");
        List<ProgramInfo> programInfos2 = new ArrayList<>();
        ProgramInfo programInfo11 = new ProgramInfo(400, 200, 100, 0, "1");
        ProgramInfo programInfo12 = new ProgramInfo(400, 200, 550, 0, "2");
        ProgramInfo programInfo13 = new ProgramInfo(400, 200, 1000, 0, "3");
        ProgramInfo programInfo14 = new ProgramInfo(600, 200, 100, 250, "4");
        ProgramInfo programInfo15 = new ProgramInfo(600, 200, 750, 250, "5");
        programInfos2.add(programInfo11);
        programInfos2.add(programInfo12);
        programInfos2.add(programInfo13);
        programInfos2.add(programInfo14);
        programInfos2.add(programInfo15);
        moduleItem4.setProgramInfos(programInfos2);

        ModuleItem moduleItem5 = new ModuleItem("layout_custom_3", "测试动态布局3");
        List<ProgramInfo> programInfos3 = new ArrayList<>();
        ProgramInfo programInfo21 = new ProgramInfo(400, 400, 200, 0, "1");
        ProgramInfo programInfo22 = new ProgramInfo(200, 200, 700, 100, "2");
        programInfos3.add(programInfo21);
        programInfos3.add(programInfo22);
        moduleItem5.setProgramInfos(programInfos3);

        mModuleList = new ArrayList<>();
        mModuleList.add(moduleItem1);
        mModuleList.add(moduleItem2);
        mModuleList.add(moduleItem3);
        mModuleList.add(moduleItem4);
        mModuleList.add(moduleItem5);
    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 1);
        ModuleAdapter moduleAdapter = new ModuleAdapter(mModuleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moduleAdapter);
    }

    private ModuleItem getStateModuleItem(String layoutCode, String moduleTitle, int count) {
        ModuleItem moduleItem = new ModuleItem(layoutCode, moduleTitle);
        List<ProgramInfo> programInfos = new ArrayList<>();
        int index = 1;
        while (index <= count) {
            ProgramInfo programInfo = new ProgramInfo(String.valueOf(index));
            programInfos.add(programInfo);
            index++;
        }
        moduleItem.setProgramInfos(programInfos);
        return moduleItem;
    }
}
