//package com.chrisbecca.doctorwhoiconpack;
//
//import android.app.Activity;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.graphics.drawable.Drawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//import java.util.List;
//
///**
// * Created by Christian on 6/25/2014.
// */
//public class ApkAdapter extends BaseAdapter {
//    List<PackageInfo> packageList;
//    Activity context;
//    PackageManager packageManager;
//
//
//    public ApkAdapter(Activity context, List<PackageInfo> packageList, PackageManager packageManager){
//        super();
//        this.context = context;
//        this.packageList = packageList;
//        this.packageManager = packageManager;
//    }
//    private class ViewHolder{
//        TextView apkName;
//        TextView text;
//    }
//    public int getCount(){
//        return packageList.size();
//    }
//    public Object getItem(int position) {
//        return packageList.get(position);
//    }
//    public long getItemId(int position) {
//        return 0;
//    }
//    public View getView(int position, View convertView, ViewGroup parent){
//        ViewHolder holder;
//        LayoutInflater inflater = context.getLayoutInflater();
//
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.apklist_item, null);
//            holder = new ViewHolder();
//
//            holder.apkName = (TextView) convertView.findViewById(R.id.appname);
//            holder.text = (TextView) convertView.findViewById(R.id.text);
//
//            convertView.setTag(holder);
//        } else{
//            holder =(ViewHolder) convertView.getTag();
//        }
//
//        PackageInfo packageInfo =  (PackageInfo) getItem(position);
//        Drawable appIcon = packageManager
//                .getApplicationIcon(packageInfo.applicationInfo);
//        String appName = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
//        //String appName = packageManager.getApplicationLabel(packageInfo.packageName);
//        appIcon.setBounds(0,0,80,80);
//        holder.apkName.setCompoundDrawables(appIcon, null, null, null);
//        holder.apkName.setCompoundDrawablePadding(15);
//        holder.apkName.setText(appName);
//        holder.text.setText(packageInfo.packageName);
//        return convertView;
//    }
//
//
//}
package com.chrisbecca.doctorwhoiconpack;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Christian on 6/25/2014.
 */
public class ApkAdapter extends BaseAdapter {
    List<ApplicationInfo> packageList;
    Activity context;
    PackageManager packageManager;


    public ApkAdapter(Activity context, List<ApplicationInfo> packageList, PackageManager packageManager){
        super();
        this.context = context;
        this.packageList = packageList;
        this.packageManager = packageManager;
    }
    private class ViewHolder{
        TextView apkName;
        TextView text;
    }
    public int getCount(){
        return packageList.size();
    }
    public Object getItem(int position) {
        return packageList.get(position);
    }
    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        LayoutInflater inflater = context.getLayoutInflater();


        if(convertView == null){
            convertView = inflater.inflate(R.layout.apklist_item, null);
            holder = new ViewHolder();

            holder.apkName = (TextView) convertView.findViewById(R.id.appname);
            holder.text = (TextView) convertView.findViewById(R.id.text);

            convertView.setTag(holder);
        } else{
            holder =(ViewHolder) convertView.getTag();
        }

        ApplicationInfo packageInfo =  (ApplicationInfo) getItem(position);
        Drawable appIcon = packageInfo.loadIcon(packageManager);

        String appName = (String) packageInfo.loadLabel(packageManager);
        //String appName = packageManager.getApplicationLabel(packageInfo.packageName);
        appIcon.setBounds(0,0,80,80);
        holder.apkName.setCompoundDrawables(appIcon, null, null, null);
        holder.apkName.setCompoundDrawablePadding(15);
        holder.apkName.setText(appName);
        holder.text.setText(packageInfo.packageName);
        return convertView;
    }


}
