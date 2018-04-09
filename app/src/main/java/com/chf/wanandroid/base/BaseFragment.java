package com.chf.wanandroid.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chf.wanandroid.ui.utils.ToastUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Fragment基类
 *
 * @author bob
 * @date 17-5-2
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T presenter;
    private Unbinder mBinder;
    public boolean canLoading = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(provideViewLayout(), container, false);
        mBinder = ButterKnife.bind(this, view);
        initPresenter();
        return view;
    }

    protected abstract int provideViewLayout();

    protected abstract void initPresenter();

    public void showLogE(String msg) {
        Log.e("TAG", msg);
    }

    public void showMsg(String msg) {
        ToastUtil.showShort(getActivity(),msg);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.release();
    }
}
