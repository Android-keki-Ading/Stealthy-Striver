package com.mediading.stealthystriver.ui.fragment;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.hilt.android.AndroidEntryPoint;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.MainFragmentBinding;
import com.mediading.stealthystriver.db.entity.Todo;
import com.mediading.stealthystriver.ui.adapter.TodoAdapter;
import com.mediading.stealthystriver.viewmodel.MainFragmentViewModel;

import java.util.List;

@AndroidEntryPoint
public class MainFragment extends BaseFragment{

    private MainFragmentBinding dataBinding;
    private MainFragmentViewModel mainFragmentViewModel;
    private TodoAdapter todoAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater,R.layout.main_fragment,container,false);
        mainFragmentViewModel = new ViewModelProvider(this).get(MainFragmentViewModel.class);
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

        todoAdapter = new TodoAdapter(mainFragmentViewModel.getTodoList());

        dataBinding.recyclerviewTodoList.setAdapter(todoAdapter);

        initView();
    }

    private void initView(){

        mainFragmentViewModel.getTodoList().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todoList) {
                Log.i(TAG, "todos--->"+ todoList.toString());
                MutableLiveData<List<Todo>> listLiveData = new MutableLiveData<>();
                listLiveData.setValue(todoList);
                todoAdapter.setTodoList(listLiveData);
            }
        });

        dataBinding.scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY > oldScrollY) {
                //上滑
            } else {
                //下滑
                toastShort("我也是有底线的~");
            }
        });

        todoAdapter.setOnItemClickListener(new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Todo todo) {
                toastShort("能感受到你点击了Todo任务:"+todo.getTodoTitle()+"\n但我还没开发完全,只能toast一下");
            }
        });
    }



}