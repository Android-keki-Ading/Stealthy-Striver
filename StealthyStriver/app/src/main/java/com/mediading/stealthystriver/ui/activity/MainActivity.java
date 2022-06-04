package com.mediading.stealthystriver.ui.activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import dagger.hilt.android.AndroidEntryPoint;

import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mediading.stealthystriver.R;
import com.mediading.stealthystriver.databinding.ActivityMainBinding;
import com.mediading.stealthystriver.viewmodel.MainViewModel;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    private NavController navController;
    private ActivityMainBinding dataBinding;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_main_graph);
        navController = navHostFragment.getNavController();
        fab = dataBinding.included.fab;
        initView();
    }

    private void initView(){
        fab.setOnClickListener(view->{
            jump2Activity(TodoActivity.class);
        });
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                setTitle(destination.getLabel());
            }
        });
        NavigationUI.setupWithNavController(dataBinding.bottomNavView, navController);

        dataBinding.included.fab.bringToFront();
    }

    private long timeMillis;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                toastShort("再次按下退出应用程序");
                timeMillis = System.currentTimeMillis();
            } else {
                exitTheProgram();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);

    }
}