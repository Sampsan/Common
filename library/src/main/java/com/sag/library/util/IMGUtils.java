package com.sag.library.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.sag.library.view.image.SimpleImageView;

/**
 * 图片加载工具
 * <p>
 * Created by SAG on 2017/5/17
 */

public class IMGUtils {

    @BindingAdapter({"url"})
    public static void setAdapter(SimpleImageView image, Object url) {
        image.setUrl((String) url);
    }

    @BindingAdapter({"res"})
    public static void setAdapter(ImageView image, int res) {
        if (image.getTag() == null || (int) image.getTag() != res) {
            image.setImageResource(res);
            image.setTag(res);
        }
    }

    @BindingAdapter({"header"})
    public static void setHeader(SimpleImageView image, Object header) {
        image.setHeader((String) header);
    }

}
