package com.sag.library.view.image;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.signature.StringSignature;
import com.sag.library.R;

import java.util.Date;

/**
 * Created by SAG on 2017/6/21 0021
 */

public class SimpleImageView extends AppCompatImageView {

    private String url = "";
    private boolean cache = true;

    public SimpleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
        if (typedArray != null) {
            cache = typedArray.getBoolean(R.styleable.SimpleImageView_cache, true);
            typedArray.recycle();
        }
    }

    public void setUrl(String url) {
        if (this.url == null || !this.url.equals(url)) {
            Glide.with(getContext())
                    .load(url)
                    .centerCrop()
                    .error(R.drawable.default_img)
                    .placeholder(R.drawable.default_img)
                    .signature(new StringSignature(cache ? "cache" : (new Date().getTime() + "")))
                    .into(this);
            this.url = url;
        }
    }

    public void setHeader(String header) {
        if (this.url == null || !this.url.equals(header)) {
            Glide.with(getContext())
                    .load(header)
                    .asBitmap()
                    .centerCrop()
                    .error(R.drawable.default_img)
                    .placeholder(R.drawable.default_img)
                    .signature(new StringSignature(cache ? "cache" : (new Date().getTime() + "")))
                    .into(new BitmapImageViewTarget(this) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                    circularBitmapDrawable.setCircular(true);
                    setImageDrawable(circularBitmapDrawable);
                }
            });
            this.url = header;
        }
    }

}
