//package com.chrisbecca.doctorwhoiconpack;
//
//import android.app.ListActivity;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Color;
//import android.net.MailTo;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
//import android.os.Bundle;
//import android.util.SparseBooleanArray;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.os.Build;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//import android.widget.RelativeLayout;
//import android.widget.SimpleCursorAdapter;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.chrisbecca.doctorwhoiconpack.R;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//public class AppPicker extends ActionBarActivity {
//    ListView apkList ;
//    PackageManager packageManager;
//    List<PackageInfo> packageList;
//    SimpleCursorAdapter mAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_app_picker);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
//        packageManager = getPackageManager();
//        packageList = packageManager
//                .getInstalledPackages(PackageManager.GET_PERMISSIONS);
//
//        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();
//
////        for(PackageInfo pi : packageList) {
////            boolean b = isSystemPackage(pi);
////            if(!b) {
////                packageList1.add(pi);
////            }
////        }
//
//        apkList = (ListView) findViewById(R.id.applist);
//        Collections.sort(packageList, new Comparator<PackageInfo>() {
//            @Override
//            public int compare(PackageInfo packageInfo, PackageInfo packageInfo2) {
//                return packageInfo.packageName.compareToIgnoreCase(packageInfo2.packageName);
//            }
//
//        });
//        apkList.setAdapter(new ApkAdapter(AppPicker.this, packageList, packageManager));
//        apkList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
//        apkList.setSelector(R.drawable.selector);
//
//        apkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                if (apkList.isItemChecked(i)){
//                    apkList.setItemChecked(i, true);
//                }else {
//                    apkList.setItemChecked(i, false);
//                }
//
//                Toast.makeText(AppPicker.this, apkList.getCheckedItemPositions().toString(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//    }
//
//    private boolean isSystemPackage(PackageInfo pkgInfo) {
//        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
//                : false;
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.app_picker, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//
//        } else if (id == R.id.submit) {
//            //apkList.getItemAtPosition(apkList.getCheckedItemPosition());
//
//            SparseBooleanArray checked = apkList.getCheckedItemPositions();
//            ArrayList<String> check = new ArrayList<String>();
//            for (int i = 0; i < apkList.getAdapter().getCount(); i++) {
//                if (checked.get(i)) {
//                    check.add(apkList.getItemAtPosition(i).toString());
//                }
//            }
//
//            MailTo mt;
//            mt = MailTo.parse("mailto:becroserun@live.com");
//            Intent sendIntent = new Intent(Intent.ACTION_SEND);
//            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
//            sendIntent.putExtra(Intent.EXTRA_TEXT, check.toString());
//            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Icon Request");
//            sendIntent.setType("message/rfc822");
//            startActivity(Intent.createChooser(sendIntent, "Send a message: "));
//
//
//
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public Object getItem(int position) {
//        return packageList.get(position);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_app_picker, container, false);
//            return rootView;
//        }
//    }
//}
package com.chrisbecca.doctorwhoiconpack;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.MailTo;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chrisbecca.doctorwhoiconpack.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppPicker extends ActionBarActivity {
    ListView apkList ;
    PackageManager packageManager;
    List<ApplicationInfo> packages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_picker);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        packageManager = getPackageManager();
        packages = packageManager
                .getInstalledApplications(PackageManager.GET_META_DATA);

                Collections.sort(packages, new Comparator<ApplicationInfo>() {
                            @Override
                            public int compare(ApplicationInfo packageInfo, ApplicationInfo packageInfo2) {
                                return packageInfo.loadLabel(packageManager).toString().compareToIgnoreCase(packageInfo2.loadLabel(packageManager).toString());
                            }
                    });

        apkList = (ListView) findViewById(R.id.applist);
        apkList.setAdapter(new ApkAdapter(AppPicker.this, packages, packageManager));
        apkList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        apkList.setSelector(R.drawable.selector);
        apkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (apkList.isItemChecked(i)){
                    apkList.setItemChecked(i, true);
                }else {
                    apkList.setItemChecked(i, false);
                }

                Toast.makeText(AppPicker.this, apkList.getCheckedItemPositions().toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_picker, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {

        } else if (id == R.id.submit) {
            //apkList.getItemAtPosition(apkList.getCheckedItemPosition());
            SparseBooleanArray checked = apkList.getCheckedItemPositions();
            ArrayList<String> check = new ArrayList<String>();
            for (int i = 0; i < apkList.getAdapter().getCount(); i++) {
                if (checked.get(i)) {
                    ApplicationInfo app = (ApplicationInfo) getItem(i);
                    check.add(apkList.getItemAtPosition(i).toString());
                    check.add(packageManager.getApplicationLabel(app).toString());
                }
            }

            MailTo mt;
            mt = MailTo.parse("mailto:becroserun@live.com");
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mt.getTo()});
            sendIntent.putExtra(Intent.EXTRA_TEXT, check.toString());
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Icon Request");
            sendIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(sendIntent, "Send a message: "));
        }
        return super.onOptionsItemSelected(item);
    }

    public Object getItem(int position) {
        return packages.get(position);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_app_picker, container, false);
            return rootView;
        }
    }
}

