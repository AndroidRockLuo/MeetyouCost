package com.meiyou.meetyoucost.ui;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ScrollView;
import android.widget.TextView;

import com.meiyou.meetyoucost.R;

/**
 * Author: ice
 * Date: 17/12/18 11:39.
 */

public class LogView {

    private Context mContext;
    private StringBuilder mStringBuilder = new StringBuilder();
    public LogView(Context context){
       mContext = context;
    }
    public void appendLog(String log){
        initWindowManagerAndShow(mContext);
        mStringBuilder.append(log+"<br />");
        if(tvLog!=null){
            tvLog.setText(Html.fromHtml(mStringBuilder.toString()));
        }
        if(mScrollView!=null && tvLog!=null){
            int height = tvLog.getMeasuredHeight();
            mScrollView.scrollTo(0,height);
        }
    }

    public void setLog(String log){
        initWindowManagerAndShow(mContext);
        mStringBuilder  = new StringBuilder();
        mStringBuilder.append(log+"<br />");
        if(tvLog!=null){
            tvLog.setText(Html.fromHtml(mStringBuilder.toString()));
        }
        if(mScrollView!=null && tvLog!=null){
            int height = tvLog.getMeasuredHeight();
            mScrollView.scrollTo(0,height);
        }
    }

    public String getLog() {
        return mStringBuilder.toString();
    }


    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private ScrollView mScrollView;
    private TextView tvLog;
    private TextView tvClose;
    private void initWindowManagerAndShow(Context context){
        try {
            if(mWindowManager==null){
                mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                mLayoutParams = new WindowManager.LayoutParams(
                        WindowManager.LayoutParams.MATCH_PARENT,
                        WindowManager.LayoutParams.WRAP_CONTENT,
                        WindowManager.LayoutParams.TYPE_APPLICATION,
                        //WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE|
                                 WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                               // | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN ,
                        //| WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        PixelFormat.TRANSLUCENT);
                if (Build.VERSION.SDK_INT >= 24) {
                    mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
                    } else {
                        mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
                    }
                }
                mLayoutParams.gravity = Gravity.TOP | Gravity.RIGHT;
                mLayoutParams.y = 10;

                mView = LayoutInflater.from(context).inflate(R.layout.view_layout,null);
                mScrollView = (ScrollView)mView.findViewById(R.id.scrollView);
                tvLog = (TextView) mView.findViewById(R.id.tvLog);
                //tvLog.setMovementMethod(new ScrollingMovementMethod());
                tvClose = (TextView) mView.findViewById(R.id.tvClose);
                tvClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            mWindowManager.removeView(mView);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
                mWindowManager.addView(mView,mLayoutParams);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
