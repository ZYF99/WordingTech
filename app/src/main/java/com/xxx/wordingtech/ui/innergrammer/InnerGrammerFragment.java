package com.xxx.wordingtech.ui.innergrammer;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ItemShopBinding;
import com.xxx.wordingtech.model.grammer.Grammer;
import com.xxx.wordingtech.ui.base.commonlist.CommonListFragment;

public class InnerGrammerFragment extends CommonListFragment<Grammer, InnerGrammerViewModel, ItemShopBinding> {

    public InnerGrammerFragment(String classify) {
        super(classify);
    }

    @Override
    protected Class<InnerGrammerViewModel> getViewModelClazz() {
        return InnerGrammerViewModel.class;
    }

    @Override
    public int getItemLayoutRes() { return R.layout.item_grammer; }

    @Override
    protected void initView() {
        super.initView();
        viewModel.classify = classify;
    }

}
