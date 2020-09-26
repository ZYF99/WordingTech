package com.xxx.wordingtech.ui.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.DialogHelpBinding;
import com.xxx.wordingtech.databinding.FragmentMineBinding;
import com.xxx.wordingtech.ui.WordTestActivity;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.collection.CollectionFragment;
import com.xxx.wordingtech.ui.practiceword.PracticeWordActivity;
import com.xxx.wordingtech.ui.setting.SettingActivity;
import com.xxx.wordingtech.ui.widget.MyViewPagerBottomSheetBehavior;

public class MineFragment extends BaseFragment<FragmentMineBinding, MineViewModel> {

    @Override
    protected Class<MineViewModel> getViewModelClazz() {
        return MineViewModel.class;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        CollectionFragment collectionFragment = new CollectionFragment();
        collectionFragment.setBehavior(MyViewPagerBottomSheetBehavior.from(binding.flCollection));
        fragmentTransaction.add(R.id.fl_collection, collectionFragment, "collection").commit();
        binding.imSetting.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingActivity.class)));
        binding.imSetting.setOnClickListener(v -> startActivity(new Intent(getContext(), SettingActivity.class)));
        binding.tvHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogHelpBinding dialogHelpBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_help, null, false);

                new AlertDialog.Builder(getContext()).setView(dialogHelpBinding.getRoot())
                        .setPositiveButton("提交", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viewModel.uploadAdvance(dialogHelpBinding.etAdvance.getText().toString());
                            }
                        })
                        .setNegativeButton("取消", null)
                        .create().show();
            }
        });
        binding.tvWordTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), PracticeWordActivity.class);
                intent.putExtra("isTest",true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchUserProfile();
    }
}