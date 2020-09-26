package com.xxx.wordingtech.ui.base.commonlist;

import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LifecycleOwner;
import com.xxx.wordingtech.ui.base.BaseRecyclerAdapter;
import java.util.List;

public class CommonListRecyclerAdapter<T, B extends ViewDataBinding> extends BaseRecyclerAdapter<T, B> {
    public CommonListRecyclerAdapter(
            LifecycleOwner lifecycleOwner,
            int layoutRes,
            Boolean hasLoadMore,
            List<T> baseList
    ) {
        super(lifecycleOwner, layoutRes, hasLoadMore, baseList);
    }

    @Override
    public void bindData(B binding, int position) {
        binding.setVariable(BR.model, baseList.get(position));

    }
}
