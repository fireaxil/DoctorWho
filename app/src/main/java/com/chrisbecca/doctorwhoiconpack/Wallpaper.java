package com.chrisbecca.doctorwhoiconpack;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.chrisbecca.doctorwhoiconpack.R;

import java.io.IOException;
import java.util.ArrayList;

public class Wallpaper extends ActionBarActivity {
    private ArrayList<Integer> mWall;
    private ArrayList<Integer> mThumbs;
    private int  position;
    private Gallery picGallery;
    private ImageView picView;
    private int currentPic =0;
    private final int PICKER = 1;
    private int pos =0;
    private PicAdapter imgAdapt;
    private Integer[] mImageIds ={
            R.drawable.wall_1_small,
            R.drawable.wall_3_small,
            R.drawable.wall_4_small,
            R.drawable.wall_5_small,
            R.drawable.wall_6_small,
            R.drawable.wall_7_small,
            R.drawable.wall_8_small,
            R.drawable.wall_9_small,
            R.drawable.wall_11_small
    };
    private Integer[] mImageIds2 ={
            R.drawable.wall_1,
            R.drawable.wall_3,
            R.drawable.wall_4,
            R.drawable.wall_5,
            R.drawable.wall_6,
            R.drawable.wall_7,
            R.drawable.wall_8,
            R.drawable.wall_9,
            R.drawable.wall_11
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        Button button = (Button) findViewById(R.id.apply);
        picView = (ImageView) findViewById(R.id.picture);
        picGallery = (Gallery) findViewById(R.id.gallery);

        imgAdapt = new PicAdapter(this);

        picGallery.setAdapter(imgAdapt);

        int targetWidth = 600;
        int targetHeight = 400;


        BitmapFactory.Options bmbOptions = new BitmapFactory.Options();

        for(int i =0; i < mImageIds.length; i++){
            Bitmap pic = null;
            bmbOptions.inJustDecodeBounds = true;
            Resources res = getResources();
            //Drawable drawable = res.getDrawable(mImageIds[i]);
            BitmapFactory.decodeResource(res, mImageIds2[i], bmbOptions);

            int currHeight = bmbOptions.outHeight;
            int currWidth = bmbOptions.outWidth;

            int sampleSize =1;
            if(currHeight > targetHeight || currWidth>targetWidth){
                if(currWidth> currHeight){
                    sampleSize = Math.round((float) currHeight/ (float) targetHeight);
                }else{
                    sampleSize = Math.round((float)currWidth/ (float) targetWidth);
                }
            }
            bmbOptions.inSampleSize = 4;
            bmbOptions.inJustDecodeBounds = false;
            pic =  BitmapFactory.decodeResource(res, mImageIds2[i], bmbOptions);
            imgAdapt.addPic(pic);
            picGallery.setAdapter(imgAdapt);
            if(i==0){
                picView.setImageBitmap(pic);
                picView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

        }

        picGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                picView.setImageBitmap(imgAdapt.getPic(i));
                pos = i;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                Toast.makeText(getBaseContext(), "Applying...", Toast.LENGTH_SHORT).show();
                try{
                    Toast.makeText(getBaseContext(), "Success!", Toast.LENGTH_SHORT).show();
                    wallpaperManager.setResource(mImageIds2[pos]);

                }catch(IOException e){
                    Toast.makeText(getBaseContext(), "Failed :(", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
//                Uri uri = Uri.parse("android.resource://com.chrisbecca.doctorwhoiconpack/" + "/drawable/" + R.drawable.wall_11);
//
//                Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setDataAndType(uri, "image/*" );
//                Toast.makeText(getBaseContext(), intent.getDataString(), Toast.LENGTH_SHORT).show();
////                intent.setData(uri);
////                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent, "Set as"), 2);

            }
        });
    }

    private void getWallpapers(){
        mWall = new ArrayList<Integer>(20);
        mThumbs = new ArrayList<Integer>(20);


    }

    private class PicAdapter extends BaseAdapter{
        int defaultItemBackground;
        private  Context galleryContext;
        private Bitmap[] imageBitmaps;
        Bitmap placeholder;

        public PicAdapter(Context c){
            galleryContext =c;
            imageBitmaps = new Bitmap[9];
            placeholder = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

            for (int i=0; i< imageBitmaps.length; i++){
                imageBitmaps[i]= placeholder;
            }
            TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);

            defaultItemBackground = styleAttrs.getResourceId(R.styleable.PicGallery_android_galleryItemBackground, 0);

            styleAttrs.recycle();
        }

        public int getCount(){
            return imageBitmaps.length;
        }
        public Object getItem(int position){
            return position;
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent){

            ImageView imageView = new ImageView(galleryContext);
            imageView.setImageBitmap(imageBitmaps[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setBackgroundResource(defaultItemBackground);

            return imageView;
        }

        public void addPic(Bitmap newPic){
            imageBitmaps[currentPic] = newPic;
            currentPic++;
        }

        public Bitmap getPic(int posn){
            return imageBitmaps[posn];
        }


    }


}
