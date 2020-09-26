package com.xxx.wordingtech.ui.home;

import android.content.Intent;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ActivityMainBinding;
import com.xxx.wordingtech.ui.base.BaseActivity;
import com.xxx.wordingtech.ui.find.FindFragment;
import com.xxx.wordingtech.ui.listen.ListenFragment;
import com.xxx.wordingtech.ui.mine.MineFragment;
import com.xxx.wordingtech.ui.shop.ShopFragment;
import com.xxx.wordingtech.ui.word.WordFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private Fragment currentFragment = new ShopFragment();

    @Override
    protected Class<MainViewModel> getViewModelClazz() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void setUpBottomNavigation() {
        //binding.bottomNavigation.
        //底部导航栏
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_shop:
                    replaceFragment("shop");
                    break;

                case R.id.action_listen:
                    replaceFragment("listen");
                    break;

                case R.id.action_word:
                    replaceFragment("word");
                    break;
                case R.id.action_find:
                    replaceFragment("find");
                    break;
                case R.id.action_mine:
                    replaceFragment("mine");
                    break;
            }
            return true;
        });
        binding.bottomNavigation.setSelectedItemId(R.id.action_shop);
    }


    @Override
    protected void initView() {
        setUpBottomNavigation();
    }

    @Override
    protected void initData() {

    }

    private void replaceFragment(String tag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(tag);

        if (currentFragment == null) {
            switch (tag) {
                case "shop":
                    currentFragment = new ShopFragment();
                    break;
                case "listen":
                    currentFragment = new ListenFragment();
                    break;
                case "word":
                    currentFragment = new WordFragment(false);
                    break;
                case "find":
                    currentFragment = new FindFragment();
                    break;
                case "mine":
                    currentFragment = new MineFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.maincontainer, currentFragment, tag).commit();
        } else getSupportFragmentManager().beginTransaction().show(currentFragment).commit();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getSupportFragmentManager().getFragments().forEach(fragment -> {
            fragment.onActivityResult(requestCode, resultCode, data);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}