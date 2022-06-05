package com.mediading.stealthystriver.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import dagger.hilt.android.AndroidEntryPoint;
import pl.droidsonroids.gif.GifImageView;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.ActivityActivateBinding;
import com.mediading.stealthystriver.utils.Constant;
import com.mediading.stealthystriver.utils.EasyAnimation;
import com.mediading.stealthystriver.utils.MVUtils;

import javax.inject.Inject;

@AndroidEntryPoint
public class ActivateActivity extends BaseActivity {
    private ActivityActivateBinding dataBinding;

    /**
     * Error: Dagger does not support injection into private fields
     * 所以这个mvUtils不能设置为私有
     */
    @Inject
    MVUtils mvUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(com.google.android.material.R.style.Widget_MaterialComponents_MaterialCalendar_HeaderSelection_Fullscreen);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_activate);
        EasyAnimation.appear(dataBinding.tvAppZh);
        EasyAnimation.appear(dataBinding.tvDevInfo);

        // 动画加载完毕,判断是否已登录
        new Handler().postDelayed(() -> jump2ActivityFinish(mvUtils.getBoolean(Constant.IS_LOGIN) ? MainActivity.class : LoginActivity.class),4000);

        // 加载完动画直接进入主页
//        new Handler().postDelayed(() -> jump2ActivityFinish(mvUtils.getBoolean(Constant.IS_LOGIN) ? LoginActivity.class : MainActivity.class),4000);


    }
}