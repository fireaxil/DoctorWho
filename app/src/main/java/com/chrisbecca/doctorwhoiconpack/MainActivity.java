package com.chrisbecca.doctorwhoiconpack;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    public final static String PREFERENCE = "com.chrisbecca.pref";
    public final static String RUN = "com.chrisbecca.run";
    public final static String COUNT = "com.chrisbecca.count";
    public final static String COUNT_VAR = "com.chrisbecca.count_var";
    final String ACTION_SET_THEME_APEX = "com.anddoes.launcher.SET_THEME";
    final String EXTRA_PACKAGE_NAME_APEX = "com.anddoes.launcher.THEME_PACKAGE_NAME";
    final String ACTION_APPLY_ICON_THEME = "com.teslacoilsw.launcher.APPLY_ICON_THEME";
    final String NOVA_PACKAGE = "com.teslacoilsw.launcher";
    final String EXTRA_ICON_THEME_PACKAGE = "com.teslacoilsw.launcher.extra.ICON_THEME_PACKAGE";
    final String EXTRA_ICON_THEME_TYPE = "com.teslacoilsw.launcher.extra.ICON_THEME_TYPE";

    public int mCount;
    public boolean mDonate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        Toast.makeText(this, "Thank you for downloading!", Toast.LENGTH_SHORT).show();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        mCount= getSharedPreferences(COUNT, MODE_PRIVATE).getInt(COUNT_VAR,0 );


        boolean showPop = getSharedPreferences(PREFERENCE, MODE_PRIVATE)
                .getBoolean(RUN, false);


        if(showPop == false){
            if(mCount%5 == 0) {
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Hi!");
                alert.setMessage("Would you like to donate?");
                alert.setNegativeButton("Don't show again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setTrue();
                        getSharedPreferences(PREFERENCE, MODE_PRIVATE)
                                .edit().putBoolean(RUN, mDonate).commit();
                    }
                });
                alert.setNeutralButton("Remind me again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mCount++;
                        getSharedPreferences(COUNT, MODE_PRIVATE).edit().putInt(COUNT_VAR, mCount).commit();
                    }
                });
                alert.setPositiveButton("Donate", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setTrue();
                        getSharedPreferences(PREFERENCE, MODE_PRIVATE)
                                .edit().putBoolean(RUN, mDonate).commit();
                    }
                }).show();
            }
        }
        mCount++;
        getSharedPreferences(COUNT, MODE_PRIVATE).edit().putInt(COUNT_VAR, mCount).commit();

    }
    @Override
    public void onStart(){
        super.onStart();
    }
    private void setTrue(){
        mDonate= true;
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Won't bother ya again ;) ").show();
    }
    public void email(View view){
        Intent intent = new Intent(this, AppPicker.class);
        startActivity(intent);
    }
    public void quickSel(View view){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Apply to which theme").setItems(R.array.quick, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    Intent intent = new Intent(ACTION_SET_THEME_APEX);
                    intent.putExtra(EXTRA_PACKAGE_NAME_APEX, getPackageName());
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(), "Apex Launcher is not installed!", Toast.LENGTH_SHORT).show();
                    }
                    finish();
                } else if (i == 1) {
                    Intent nova = new Intent(ACTION_APPLY_ICON_THEME);
                    nova.putExtra(EXTRA_ICON_THEME_PACKAGE, getPackageName());
                    nova.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(nova);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(), "Nova Launcher is not installed!", Toast.LENGTH_SHORT).show();

                    }
                } else if (i == 2) {
                    Intent goApply = getPackageManager().getLaunchIntentForPackage(
                            "com.gau.go.launcherex");
                    Intent go = new Intent("com.gau.go.launcherex.MyThemes.mythemeaction");
                    go.putExtra("com.gau.go.launcherex", getPackageName());
                    go.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    go.putExtra("type", 1);
                    sendBroadcast(go);
                    try {
                        startActivity(goApply);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(), "Go Launcher is not installed!", Toast.LENGTH_SHORT).show();
                    }

                } else if (i == 3) {
                    Intent adw = new Intent("org.adw.launcher.SET_THEME");
                    adw.putExtra("org.adw.launcher.theme.NAME", getPackageName());
                    try {
                        startActivity(adw);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(), "ADW Launcher is not installed!", Toast.LENGTH_SHORT).show();
                    }
                } else if (i == 4) {
                    Intent smart = new Intent("ginlemon.smartlauncher.setGSLTHEME");
                    smart.putExtra("package", getPackageName());
                    smart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        startActivity(smart);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(), "Smart Launcher is not installed!", Toast.LENGTH_SHORT).show();
                    }
                } else if (i == 5) {
                    Intent holo = new Intent(Intent.ACTION_MAIN);
                    holo.setComponent(new ComponentName("com.mobint.hololauncher",
                            "com.mobint.hololauncher.SettingsActivity"));
                    try {
                        startActivity(holo);
                        Toast.makeText(getBaseContext(), "Go to Appearance Settings and click Icon Pack", Toast.LENGTH_LONG).show();


                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getBaseContext(), "Holo Launcher is not installed!", Toast.LENGTH_SHORT).show();
                    }

                } else if (i == 6) {
                    Intent al = getPackageManager().getLaunchIntentForPackage("com.actionlauncher.playstore");

                    al.putExtra("apply_icon_pack", getPackageName());
                    try {
                        startActivity(al);
                    }
                    catch (ActivityNotFoundException e){
                        Toast.makeText(getBaseContext(), "Action Launcher is not installed!", Toast.LENGTH_SHORT).show();

                    }
                    }

                }
            }

            ).

            show();

        }

    public void iconChange(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick a color").setItems(R.array.color_array, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i ==1 ){


                }
                else if(i ==2){

                }
                else{

                }

            }
        }).show();
        Toast.makeText(this, "Pressed", Toast.LENGTH_LONG).show();
    }


    public void wallpaper(View view){
        Intent intent = new Intent(this, Wallpaper.class);
        startActivity(intent);
    }
    public void customWall(View view){
        Intent intent = new Intent(this, CustomSubmit.class);
        startActivity(intent);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            //return true;
        }
        else if(id == R.id.about_us){
            LayoutInflater inflater =  getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.dialog, null);
            TextView textView = (TextView) findViewById(R.id.abouttext);
//            Dialog dialog = new Dialog(this);
//            dialog.setContentView(dialoglayout);
//            dialog.setTitle(null);
//            dialog.show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(dialoglayout);
            builder.show();

        }
        return super.onOptionsItemSelected(item);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
