package com.mediading.stealthystriver.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mediading.stealthystriver.view.LoadingDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    protected AppCompatActivity context;
    public String TAG;
    private LoadingDialog loadingDialog;

    private void setTAG(){
        Log.i("BaseFragment","--> init tag: "+this.getClass().getSimpleName());
        TAG = this.getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AppCompatActivity) {
            this.context = (AppCompatActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    protected void toastShort(CharSequence msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    protected void toastLong(CharSequence msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示加载弹窗
     */
    protected void showLoading() {
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
    }

    /**
     * 显示加载弹窗
     *
     * @param isClose true 则点击其他区域弹窗关闭， false 不关闭。
     */
    protected void showLoading(boolean isClose) {
        loadingDialog = new LoadingDialog(context, isClose);
    }

    /**
     * 隐藏加载弹窗
     */
    protected void dismissLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    protected void jump2ActivityFinish(final Class<?> clazz){
        startActivity(new Intent(context,clazz));
        //Finishes the associated activity
        getActivity().finish();
    }

    protected void jump2Activity(final Class<?> clazz){
        startActivity(new Intent(context,clazz));
    }


}
