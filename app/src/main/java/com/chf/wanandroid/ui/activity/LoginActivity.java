package com.chf.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.presenter.LoginPresenter;
import com.chf.wanandroid.mvp.view.LoginView;

import butterknife.BindView;

/**
 * @author 17919
 * @date 2018/4/8
 */

public class LoginActivity extends BaseToolBarActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.login_input_username)
    EditText loginInputUsername;
    @BindView(R.id.login_username_til)
    TextInputLayout loginUsernameTil;
    @BindView(R.id.login_input_password)
    EditText loginInputPassword;
    @BindView(R.id.login_password_til)
    TextInputLayout loginPasswordTil;

    @BindView(R.id.login_ll)
    LinearLayout mLoginLayout;
    @BindView(R.id.login_main_layout)
    LinearLayout mMainView;

    @Override
    public void initView() {
        //2、通过Resources获取
        //DisplayMetrics dm = getResources().getDisplayMetrics();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
            actionBar.setTitle("登陆");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.login_go_register:
                presenter.goRegister();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter() {
        presenter = new LoginPresenter(this, this);
        presenter.init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }


}
