package com.sag.library.view.swipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.sag.library.R;
import com.sag.library.util.UIUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SAG on 16/1/7
 */
public class LoadingProgressView extends AppCompatImageView {
    private float degrees = 0f;
    private Matrix max;
    private int width;
    private int height;
    private Bitmap bitmap;

    public LoadingProgressView(Context context) {
        super(context);
        init();
    }

    public LoadingProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            degrees += 30f;
            max.setRotate(degrees, width, height);
            setImageMatrix(max);
            if (degrees == 360) {
                degrees = 0;
            }
        }
    };

    private void init() {
        setScaleType(ScaleType.MATRIX);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.refresh_loading);
        bitmap = Bitmap.createScaledBitmap(bitmap, UIUtils.dip2px(getContext(), 30), UIUtils.dip2px(getContext(), 30), true);
        setImageBitmap(bitmap);
        max = new Matrix();

        width = bitmap.getWidth() / 2;
        height = bitmap.getHeight() / 2;
        Timer time = new Timer();
        time.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 80);
    }

}
