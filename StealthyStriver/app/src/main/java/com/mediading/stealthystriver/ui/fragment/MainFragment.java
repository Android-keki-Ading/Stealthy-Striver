package com.mediading.stealthystriver.ui.fragment;

import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.MainFragmentBinding;
import com.mediading.stealthystriver.ui.adapter.TodoAdapter;
import com.mediading.stealthystriver.viewmodel.MainFragmentViewModel;

@AndroidEntryPoint
public class MainFragment extends BaseFragment{

    private MainFragmentBinding dataBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false);

        //Returns:
        //the outermost View in the layout file associated with the Binding
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainFragmentViewModel mainFragmentViewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
        dataBinding.recyclerviewTodoList.setLayoutManager(new LinearLayoutManager(dataBinding.getRoot().getContext()));

        dataBinding.recyclerviewTodoList.setHasFixedSize(true);

        TodoAdapter todoAdapter = new TodoAdapter(mainFragmentViewModel.getTodoList());

        dataBinding.recyclerviewTodoList.setAdapter(todoAdapter);

    }

}