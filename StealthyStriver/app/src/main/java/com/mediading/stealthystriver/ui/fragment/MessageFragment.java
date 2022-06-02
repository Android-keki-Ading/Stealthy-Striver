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
import com.mediading.stealthystriver.viewmodel.MessageFragmentViewModel;

public class MessageFragment extends Fragment {

    private MessageFragmentViewModel messageFragmentViewModel;

    public static MessageFragment newInstance() {
        return new MessageFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        messageFragmentViewModel = new ViewModelProvider(this).get(MessageFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}