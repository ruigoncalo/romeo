package com.ruigoncalo.romeo.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by ruigoncalo on 28/02/16.
 */
public interface ImageLoader {
    void load(Context context, String urlResource, ImageView target);

    void load(Context context, int resource, ImageView target);
}
