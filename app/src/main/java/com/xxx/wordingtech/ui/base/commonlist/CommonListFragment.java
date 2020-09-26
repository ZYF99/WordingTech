package com.xxx.wordingtech.ui.base.commonlist;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xxx.wordingtech.R;
import com.xxx.wordingtech.databinding.FragmentCommonListBinding;
import com.xxx.wordingtech.ui.base.BaseFragment;
import com.xxx.wordingtech.ui.widget.MyViewPagerBottomSheetBehavior;

import java.util.ArrayList;
import java.util.Objects;

public abstract class CommonListFragment<Bean, VM extends CommonListViewModel<Bean>, ItemBinding extends ViewDataBinding> extends BaseFragment<FragmentCommonListBinding, VM> {

    public static final String KEY_CLASSIFY = "key_classify";
    protected CommonListRecyclerAdapter<Bean, ItemBinding> commonListRecyclerAdapter;
    protected String classify = "";
    private Boolean isFirstInit = true;
    private Boolean canRefresh = true;
    private Boolean canSearch = false;

    private MyViewPagerBottomSheetBehavior behavior;

    public void setBehavior(MyViewPagerBottomSheetBehavior behavior) {
        this.behavior = behavior;
    }


    public CommonListFragment(String classify) {
        this.classify = classify;
    }

    public CommonListFragment(String classify, Boolean canRefresh) {
        this.classify = classify;
        this.canRefresh = canRefresh;
    }

    public CommonListFragment(String classify, Boolean canRefresh, Boolean canSearch) {
        this.classify = classify;
        this.canRefresh = canRefresh;
        this.canSearch = canSearch;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_common_list;
    }

    public abstract int getItemLayoutRes();

    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    @Override
    protected void initView() {

        if (canSearch)
            binding.etSearch.setVisibility(View.VISIBLE);
        else
            binding.etSearch.setVisibility(View.GONE);

        binding.refreshLayout.setEnabled(canRefresh);

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    viewModel.search(binding.etSearch.getText().toString());
                }
                return true;
            }
        });


        viewModel.isRefreshing.observe(this, aBoolean -> binding.refreshLayout.setRefreshing(aBoolean));

        viewModel.isLoadingMore.observe(this, aBoolean -> ((CommonListRecyclerAdapter) Objects.requireNonNull(binding.rvList.getAdapter())).onLoadMore.postValue(aBoolean));

        viewModel.commonListPageModelLiveData.observe(this, filmPageModel -> {
            commonListRecyclerAdapter.replaceData(filmPageModel.getDataList());
            binding.refreshLayout.setRefreshing(false);
        });

        viewModel.commonListLiveData.observe(this, filmList -> {
            commonListRecyclerAdapter.replaceData(filmList);
            binding.refreshLayout.setRefreshing(false);
        });

        //列表适配器
        commonListRecyclerAdapter = new CommonListRecyclerAdapter(this, getItemLayoutRes(), true, new ArrayList<>());
        binding.rvList.setLayoutManager(getLayoutManager());
        binding.rvList.setAdapter(commonListRecyclerAdapter);

        //下拉刷新监听
        binding.refreshLayout.setOnRefreshListener(() -> viewModel.refreshList(classify));

        //上拉加载
        binding.rvList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (viewModel.commonListPageModelLiveData.getValue() != null) {
                    if (!recyclerView.canScrollVertically(1)) {
                        if (!viewModel.isLoadingMore.getValue() && viewModel.commonListPageModelLiveData.getValue().getPages() > 1) {
                            viewModel.loadMoreShopList(classify);
                        }
                    }
                }
                if (behavior != null) {
                    Log.d("!!!!!!!!!", String.valueOf(!recyclerView.canScrollVertically(-1)) + dy);
                    if (dy == 0) {
                        //指尖未真实上下滑动（针对从viewpager其他页面切换过来时），不做任何操作
                        return;
                    }
                    if (!recyclerView.canScrollVertically(-1) && dy < 0) {
                        //不可以下拉，并且手势是下拉，通知behavior已经列表已经在顶部了
                        Log.d("~~~~~", "划不动了");
                        behavior.setCurrentScrollViewOnTop(true);
                    } else {
                        //可以下拉或者手势不是下拉，通知behavior已经列表不在顶部
                        behavior.setCurrentScrollViewOnTop(false);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                /*if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    behavior.setScrollViewOnTop(false);
                }*/
            }
        });
    }

    @Override
    protected void initData() {
        if (isFirstInit) {
            viewModel.refreshList(classify);
            isFirstInit = false;
        }

    }
}
