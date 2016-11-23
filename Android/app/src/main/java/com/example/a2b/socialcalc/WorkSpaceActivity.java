package com.example.a2b.socialcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WorkSpaceActivity extends AppCompatActivity {
        final private String[] mGroupList = new String[] { "Имена", "Айтемы", "Оплата" };
        private String[] mNames = new String[] {"Name1", "Name2"};
        private String[] mItems = new String[] {"item1", "item2"};
        private String[] mSummary = new String[] {};
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_work_space);
            setTitle("WorkSpace");

            Map<String, String> map;
            ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
            for (String group : mGroupList) {
                map = new HashMap<>();
                map.put("groupName", group);
                groupDataList.add(map);
            }

            String groupFrom[] = new String[] { "groupName" };
            int groupTo[] = new int[] { android.R.id.text1 };

            ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

            ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();

            for (String group : mNames) {
                map = new HashMap<>();
                map.put("Name", group);
                сhildDataItemList.add(map);
            }
            сhildDataList.add(сhildDataItemList);

            сhildDataItemList = new ArrayList<>();
            for (String items : mItems) {
                map = new HashMap<>();
                map.put("Item", items);
                сhildDataItemList.add(map);
            }
            сhildDataList.add(сhildDataItemList);

            сhildDataItemList = new ArrayList<>();
            for (String summary : mSummary) {
                map = new HashMap<>();
                map.put("Summary", summary);
                сhildDataItemList.add(map);
            }
            сhildDataList.add(сhildDataItemList);

            String childFrom[] = new String[] {"Name", "Item", "Summary"};
            int childTo[] = new int[] { android.R.id.text1 };

            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                    this, groupDataList,
                    android.R.layout.simple_expandable_list_item_1, groupFrom,
                    groupTo, сhildDataList, android.R.layout.simple_list_item_1,
                    childFrom, childTo);
            ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.ExpListView);
            expandableListView.setAdapter(adapter);


        /*final ArrayList<String> names = new ArrayList<>();
        final EditText editText = (EditText) findViewById(R.id.editText);
        final ArrayAdapter<String> adapter;

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        expandableListView.setAdapter(adapter);

        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        names.add(0, editText.getText().toString());
                        adapter.notifyDataSetChanged();
                        editText.setText("");
                        return true;
                    }
                return false;
            }
        });*/
        }
}
