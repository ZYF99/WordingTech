package com.xxx.wordingtech.ui.login;

import android.content.Intent;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ActivityLoginBinding;
import com.xxx.wordingtech.ui.base.BaseActivity;
import com.xxx.wordingtech.ui.home.MainActivity;
import com.xxx.wordingtech.ui.register.RegisterActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected Class<LoginViewModel> getViewModelClazz() {
        return LoginViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        binding.btnServerLogin.setOnClickListener(v -> {
                    viewModel.login(() -> {
                        finish();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    });
                }
        );
        binding.btnGoRegister.setOnClickListener(v1 -> jumpToRegister());
    }

    @Override
    protected void initData() {

    }

    private void jumpToRegister() {
        finish();
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

}