package com.mediading.stealthystriver.ui.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import dagger.hilt.android.AndroidEntryPoint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.HomeFragmentBinding;
import com.mediading.stealthystriver.databinding.HomeFragmentBindingImpl;
import com.mediading.stealthystriver.ui.activity.InfoActivity;
import com.mediading.stealthystriver.ui.activity.LoginActivity;
import com.mediading.stealthystriver.utils.Constant;
import com.mediading.stealthystriver.utils.MVUtils;
import com.mediading.stealthystriver.viewmodel.HomeFragmentViewModel;

import javax.inject.Inject;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment {

    @Inject
    MVUtils mvUtils;

    private HomeFragmentBinding dataBinding;
    private HomeFragmentViewModel homeFragmentViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);
        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        dataBinding.setTotalFocus(homeFragmentViewModel);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView(){

        /**
         * 退出登录
         */
        dataBinding.btnLogout.setOnClickListener(e->{
            toastShort("退出登录");
            mvUtils.put(Constant.IS_LOGIN, false);
            jump2ActivityFinish(LoginActivity.class);
        });

        dataBinding.cvAvatar.setOnClickListener(e->{
            jump2Activity(InfoActivity.class);
        });

        homeFragmentViewModel.getFocusTotal().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                dataBinding.tvTotalFocusMinute.setText(s);
            }
        });
    }

}