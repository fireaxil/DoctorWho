package com.chrisbecca.doctorwhoiconpack;

import android.app.Application;
import android.content.pm.PackageInfo;

/**
 * Created by Christian on 6/25/2014.
 */
public class AppData extends Application {
    PackageInfo packageInfo;

    public PackageInfo getPackageInfo(){
        return packageInfo;
    }
    public void setPackageInfo(PackageInfo packageInfo){
        this.packageInfo = packageInfo;
    }
}
