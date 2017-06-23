package com.sag.library.view.swipe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;
import com.sag.library.R;
import com.sag.library.util.UIUtils;

/**
 * Created by SAG on 2017/5/9
 */

public class RefreshHeaderView extends FrameLayout implements SwipeRefreshTrigger, SwipeTrigger {
    private String TAG = getClass().getSimpleName();
    private LoadingProgressView mProgressBar;
    private ImageView mImageView;
    private TextView mTextView;

    public RefreshHeaderView(Context context) {
        super(context);
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_refresh_header, this);
        mProgressBar = (LoadingProgressView) view.findViewById(R.id.pb_stl_refresh_header);
        mImageView = (ImageView) view.findViewById(R.id.iv_stl_refresh_header);
        mTextView = (TextView) view.findViewById(R.id.tv_stl_refresh_header);
    }

    @Override
    public void onPrepare() {
        Log.d(TAG, "onPrepare: ");
        mProgressBar.setVisibility(GONE);
        mImageView.setImageResource(R.drawable.refresh_down);
        mImageView.setVisibility(VISIBLE);
        mTextView.setText("下拉刷新");
    }


    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        Log.d(TAG, "onMove: " + isComplete + "/" + automatic + "/" + yScrolled);
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                mTextView.setText("释放加载");
            } else {
                mTextView.setText("下拉刷新");
            }
        } else {
            mTextView.setText("正在加载");

        }
    }


    @Override
    public void onRelease() {
        Log.d(TAG, "onRelease: ");
        mImageView.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
        mTextView.setText("正在加载");
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "onRefresh: ");
        mImageView.setVisibility(GONE);
        mProgressBar.setVisibility(VISIBLE);
        mTextView.setText("正在加载");
    }


    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete: ");
        mProgressBar.setVisibility(GONE);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.refresh_done);
        bitmap = Bitmap.createScaledBitmap(bitmap, UIUtils.dip2px(getContext(), 30), UIUtils.dip2px(getContext(), 30), true);
        mImageView.setImageBitmap(bitmap);
        mImageView.setVisibility(VISIBLE);
        mTextView.setText("加载完成");
    }

    @Override
    public void onReset() {
        Log.d(TAG, "onReset: ");
        mProgressBar.setVisibility(GONE);
        mImageView.setImageResource(R.drawable.refresh_down);
        mImageView.setVisibility(VISIBLE);
        mTextView.setText("下拉刷新");
    }
}