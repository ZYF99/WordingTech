package com.xxx.wordingtech.ui.setting;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import androidx.annotation.Nullable;
import com.luck.picture.lib.PictureSelector;
import com.xxx.wordingtech.MainApplication;
import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ActivitySettingBinding;
import com.xxx.wordingtech.model.mine.UserProfile;
import com.xxx.wordingtech.ui.base.BaseActivity;
import com.xxx.wordingtech.ui.util.PictureSelectUtil;
import com.xxx.wordingtech.util.PermissionUtil;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import io.reactivex.functions.Action;

public class SettingActivity extends BaseActivity<ActivitySettingBinding, SettingViewModel> {

    @Override
    protected Class<SettingViewModel> getViewModelClazz() {
        return SettingViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        viewModel.userProfileLiveData.observe(this, userProfile -> updateUserInfoUI(userProfile));

        viewModel.birthdayLongLiveData.observe(this, aLong -> {
            Date date = new Date(aLong);
            int year = 1900 + date.getYear();
            binding.tvBirthday.setText(year + "年" + date.getMonth() + "月" + date.getDay() + "日");
        });

        viewModel.genderLiveData.observe(this, s -> {
            if (s.equals("F")) binding.tvSex.setText("女");
            else binding.tvSex.setText("男");
        });

        binding.toolbar.setNavigationOnClickListener(v -> finish());

        binding.llAvatar.setOnClickListener(v -> PermissionUtil.rxCheckPermission(SettingActivity.this, new Action() {
            @Override
            public void run() {
                PictureSelectUtil.showAvatarAlbum(SettingActivity.this);
            }
        }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe());

        binding.llBg.setOnClickListener(v -> PermissionUtil.rxCheckPermission(SettingActivity.this, () -> PictureSelectUtil.showBackgroundAlbum(SettingActivity.this), Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE).subscribe());

        binding.llSex.setOnClickListener(v -> {
            if (viewModel.genderLiveData.getValue().equals("F"))
                viewModel.genderLiveData.postValue("M");
            else viewModel.genderLiveData.postValue("F");
        });

        binding.llBirthday.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(SettingActivity.this, (view, year, month, dayOfMonth) -> {
                Date chooseDate = new Date(year - 1900, month, dayOfMonth);
                viewModel.birthdayLongLiveData.postValue(chooseDate.getTime());
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)).show();

        });

        binding.btnUpdate.setOnClickListener(v -> viewModel.updateUserProfile(
                () -> {
                    MainApplication.getApplication().showToast("更新成功");
                    finish();
                }
        ));
    }

    @Override
    protected void initData() {
        viewModel.getUserProfile();
    }

    //更新个人信息ui
    private void updateUserInfoUI(UserProfile userProfile) {
        if (userProfile.getGender().equals("")) binding.tvSex.setText("女");
        else binding.tvSex.setText("男");
        binding.etNickname.setText(userProfile.getNikeName());
        binding.etSignature.setText(userProfile.getSignature());
        viewModel.birthdayLongLiveData.postValue(userProfile.getBirthday());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureSelectUtil.REQUESTCODE_AVATAR) {
                assert data != null;
                viewModel.uploadAvatar(new File(PictureSelector.obtainMultipleResult(data).get(0).getPath()));
            } else if (requestCode == PictureSelectUtil.REQUESTCODE_BACKGROUND) {
                assert data != null;
                viewModel.uploadBackground(new File(PictureSelector.obtainMultipleResult(data).get(0).getPath()));
            }
        }
    }
}