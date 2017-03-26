package com.example.victor.ccflouncher;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppsListActivity extends AppCompatActivity {

    private PackageManager manager;
    List<AppDetail> apps;

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_list);

        list = (ListView)findViewById(R.id.apps_list);
        loadApps();
        loadListview();
        addClickListenar();
    }

    private void loadApps(){
        manager = getPackageManager();
        apps = new ArrayList<>();

        Intent i = new Intent(Intent.ACTION_MAIN,null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availablesActivities = manager.queryIntentActivities(i,0);

        for (ResolveInfo info: availablesActivities) {

            AppDetail detail = new AppDetail();
            detail.label = info.loadLabel(manager);
            detail.name = info.activityInfo.packageName;
            detail.icon = info.loadIcon(manager);

            apps.add(detail);

        }

    }

    private void loadListview(){

        ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,R.layout.list_item,apps){

            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null){

                    convertView = getLayoutInflater().inflate(R.layout.list_item,null);

                }

                ImageView icon = (ImageView)convertView. findViewById(R.id.item_wrap_icon);
                icon.setImageDrawable(apps.get(position).icon);

                TextView label = (TextView)convertView.findViewById(R.id.item_lab_label);
                label.setText(apps.get(position).label);

                TextView name = (TextView)convertView.findViewById(R.id.item_lab_name);
                name.setText(apps.get(position).label);



                return  convertView;
            }
        };

        list.setAdapter(adapter);
    }

    private void addClickListenar(){

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = manager.getLaunchIntentForPackage(apps.get(position).name.toString());
                AppsListActivity.this.startActivity(i);

            }
        });

    }

}
