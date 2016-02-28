package com.ruigoncalo.romeo.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public class ImageManager implements ImageLoader {
    private static ImageManager instance = new ImageManager();

    public static ImageManager getInstance(){
        return instance;
    }

    private ImageManager(){

    }

    @Override
    public void load(Context context, String urlResource, ImageView target){
        Picasso.with(context).load(urlResource).into(target);
    }

    @Override
    public void load(Context context, int resource, ImageView target){
        Picasso.with(context).load(resource).into(target);
    }

}
