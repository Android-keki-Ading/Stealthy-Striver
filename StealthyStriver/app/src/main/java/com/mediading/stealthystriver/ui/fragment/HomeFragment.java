package com.mediading.stealthystriver.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.viewmodel.HomeFragmentViewModel;

public class HomeFragment extends Fragment {

    private HomeFragmentViewModel homeFragmentViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homeFragmentViewModel = new ViewModelProvider(this).get(HomeFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}