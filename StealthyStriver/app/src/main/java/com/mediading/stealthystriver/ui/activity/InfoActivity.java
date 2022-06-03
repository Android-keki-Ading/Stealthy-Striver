package com.mediading.stealthystriver.ui.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import dagger.hilt.android.AndroidEntryPoint;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.ActivityInfoBinding;
import com.mediading.stealthystriver.model.UserInfo;
import com.mediading.stealthystriver.utils.APKVersionInfoUtils;
import com.mediading.stealthystriver.viewmodel.InfoViewModel;

@AndroidEntryPoint
public class InfoActivity extends BaseActivity {
    private final String GITHUB_ADDR = "https://github.com/ldqsss/Stealthy-Striver";
    private ActivityInfoBinding dataBinding;
    private InfoViewModel infoViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_info);
        infoViewModel = new ViewModelProvider(this).get(InfoViewModel.class);
        dataBinding.setInfo(infoViewModel);
        initView();
    }

    private void initView(){

        dataBinding.tvId.setOnClickListener(e->{
            toastShort("id 是不能修改的哦");
        });

        dataBinding.tvName.setOnClickListener(e->{
            showInputDialog(dataBinding.tvPersonName);
        });

        dataBinding.tvSign.setOnClickListener(e->{
            showInputDialog(dataBinding.tvPersonSign);

        });

        dataBinding.tvSex.setOnClickListener(e->{
            showInputDialog(dataBinding.tvPersonSex);
        });

        dataBinding.tvVersion.setText(APKVersionInfoUtils.getVerName(context));
        dataBinding.tvCode.setOnClickListener(v -> jumpUrl(GITHUB_ADDR));

    }

    /**
     * 跳转URL
     *
     * @param url 地址
     */
    private void jumpUrl(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    private void showInputDialog(TextView tv){
        LayoutInflater layoutInflater = LayoutInflater.from(InfoActivity.this);
        View dialog = layoutInflater.inflate(R.layout.dialog_view,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(InfoActivity.this);
        builder.setView(dialog);

        final TextView textView = dialog.findViewById(R.id.dialog_tv);
        final EditText editText = dialog.findViewById(R.id.dialog_et);
        textView.setText(tv.getText());

        builder.setCancelable(false)
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    UserInfo userInfo = infoViewModel.getUserInfo().getValue();
                    String change = editText.getText().toString();
                    Log.i(TAG," before "+userInfo.toString());
                    switch (tv.getId()){
                        case R.id.tv_person_name:
                            userInfo.setNickName(change);
                            dataBinding.tvPersonNameShow.setText(change);
                            break;
                        case R.id.tv_person_sex:
                            userInfo.setSex(change);
                            dataBinding.tvPersonSexShow.setText(change);
                            break;
                        case R.id.tv_person_sign:
                            userInfo.setSign(change);
                            dataBinding.tvPersonSignShow.setText(change);
                            break;
                    }
                    infoViewModel.setUserInfo(userInfo);
//                    infoViewModel.refleshInfo();
                }
            });
        // create an alert dialog
        AlertDialog alert = builder.create();
        alert.show();
    }
}