package com.xxx.wordingtech.ui.collectionword;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.ItemCollectionWordBinding;
import com.xxx.wordingtech.model.word.Word;
import com.xxx.wordingtech.ui.base.commonlist.CommonListFragment;

public class CollectionWordFragment extends CommonListFragment<Word, CollectionWordViewModel, ItemCollectionWordBinding> {

    public CollectionWordFragment(String classify) {
        super(classify);
    }

    public CollectionWordFragment(String classify,Boolean canRefresh) {
        super(classify,canRefresh);
    }

    @Override
    protected Class<CollectionWordViewModel> getViewModelClazz() {
        return CollectionWordViewModel.class;
    }

    @Override
    public int getItemLayoutRes() { return R.layout.item_collection_word; }
}
