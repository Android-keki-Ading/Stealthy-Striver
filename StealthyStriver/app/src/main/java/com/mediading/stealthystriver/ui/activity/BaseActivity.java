package com.mediading.stealthystriver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mediading.stealthystriver.view.LoadingDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 *  refer to https://cloud.tencent.com/developer/article/1905045
 *
 * @package com.mediading.stealthystriver.ui.activity
 * @className BaseActivity
 * @time 2022-05-24 22:11
 */
public class BaseActivity extends AppCompatActivity {
    public String TAG;

    protected AppCompatActivity context;

    /**
     *  自定义的view loadingDialog
     */
    private LoadingDialog loadingDialog;

    private void setTAG(){
        Log.i("BaseActivity","--> init tag"+this.getClass().getSimpleName());
        TAG = this.getClass().getSimpleName();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        setTAG();
    }

    protected void toastShort(CharSequence msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    protected void toastLong(CharSequence msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 跳转Activity
     * @param clazz 跳转的activity
     */
    protected void jump2Activity(final Class<?> clazz){
        startActivity(new Intent(context,clazz));
    }

    /**
     * 跳转页面并关闭当前页面
     *
     * @param clazz 目标页面
     */
    protected void jump2ActivityFinish(final Class<?> clazz){
        startActivity(new Intent(context,clazz));

        //Finishes the current activity
        finish();
    }

    /**
     * 默认弹窗显示loading
     */
    protected void showLoading(){
        loadingDialog = new LoadingDialog(context);

        //.show() Start the dialog and display it on screen.
        loadingDialog.show();
    }

    /**
     * 显示加载弹窗 msg
     * @param msg 弹窗msg
     */
    protected void showLoading(String  msg){
        loadingDialog = new LoadingDialog(context,msg);
        //.show() Start the dialog and display it on screen.
        loadingDialog.show();
    }

    /**
     * 显示加载弹窗
     * @param isClose true 则点击其他区域弹窗关闭， false 不关闭。
     */
    protected void showLoading(boolean isClose) {
        loadingDialog = new LoadingDialog(this, isClose);
        loadingDialog.show();
    }

    /**
     * Dismiss this dialog, removing it from the screen
     */
    protected void dissmissLoading(){
        if(loadingDialog!=null){
            loadingDialog.dismiss();
        }
    }

    /**
     * 状态栏文字图标颜色
     * @param dark 深色 false 为浅色
     */
    protected void setStatusBar(boolean dark) {
        View decor = getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }
}

