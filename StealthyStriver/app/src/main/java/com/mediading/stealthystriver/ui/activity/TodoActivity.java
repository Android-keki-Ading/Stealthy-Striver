package com.mediading.stealthystriver.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.ActivityTodoBinding;
import com.mediading.stealthystriver.db.entity.Todo;
import com.mediading.stealthystriver.viewmodel.BaseViewModel;
import com.mediading.stealthystriver.viewmodel.TodoViewModel;

@AndroidEntryPoint
public class TodoActivity extends BaseActivity {
    private ActivityTodoBinding dataBinding;
    private TodoViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_todo);
        initView();
    }

    private void initView(){
        todoViewModel.getDataStatus().observe(this,todoStatus->{
            toastShort(todoStatus.getMsg());
        } );

        dataBinding.btnConfirm.setOnClickListener(e->{
            Log.i(TAG,"BTN_CONFIRM PRESSED");
            String title = dataBinding.titleEditText.getText().toString();
            String description = dataBinding.descriptiionEditText.getText().toString();
            String date;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                date = dataBinding.datePicker.getYear() +"-"
                        + dataBinding.datePicker.getMonth() +"-"
                        +dataBinding.datePicker.getDayOfMonth() + " "
                        +dataBinding.timePicker.getHour() +":"+dataBinding.timePicker.getMinute();
            }else{
                date = dataBinding.datePicker.getYear() +"-"
                        + dataBinding.datePicker.getMonth() +"-"
                        +dataBinding.datePicker.getDayOfMonth() + " ";
            }

            if(title.trim().isEmpty() || description.trim().isEmpty() ||date.trim().isEmpty()){
                toastShort("Please Fill all Field");
                return;
            }
            Log.i(TAG,"DATA"+date);
            todoViewModel.insert(new Todo(title,false,description,date));
            finish();
        });


    }
}