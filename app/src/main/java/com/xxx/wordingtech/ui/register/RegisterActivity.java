package com.xxx.wordingtech.ui.register;

import android.content.Intent;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ActivityRegisterBinding;
import com.xxx.wordingtech.ui.base.BaseActivity;
import com.xxx.wordingtech.ui.home.MainActivity;
import com.xxx.wordingtech.ui.login.LoginActivity;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> {

    @Override
    protected Class<RegisterViewModel> getViewModelClazz() {
        return RegisterViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        binding.btnRegister.setOnClickListener(v -> viewModel.registerAndLogin(() -> {
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }));
        binding.rgSex.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.rb_femail) viewModel.sex.postValue("F");
            else viewModel.sex.postValue("M");
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

}