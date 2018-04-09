package com.chf.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.model.bean.UserBean;
import com.chf.wanandroid.mvp.presenter.LoginPresenter;
import com.chf.wanandroid.mvp.view.LoginView;
import com.chf.wanandroid.ui.base.BaseConfig;
import com.chf.wanandroid.ui.utils.SharedPreferencesUtil;
import com.chf.wanandroid.ui.utils.SnackBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 17919
 * @date 2018/4/8
 */

public class LoginActivity extends BaseToolBarActivity<LoginPresenter> implements LoginView, TextWatcher {
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
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.login_ll)
    LinearLayout mLoginLayout;
    @BindView(R.id.login_main_layout)
    LinearLayout mMainView;

    @Override
    public void initView() {
        loginInputUsername.addTextChangedListener(this);
        loginInputPassword.addTextChangedListener(this);
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

    @OnClick(R.id.login_tv)
    void click() {
        String pwd = loginInputPassword.getText().toString();
        String userName = loginInputUsername.getText().toString();
        if (presenter.checkParams(pwd, userName, loginPasswordTil, loginUsernameTil)) {
            presenter.goLogin(pwd, userName);
        }
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

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (loginInputPassword.getText().length() > 0 && loginInputUsername.getText().length() > 0) {
            loginTv.setEnabled(true);
        } else {
            loginTv.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 登陆成功
     *
     * @param userBean
     */
    @Override
    public void loginSuccess(UserBean userBean) {
        SharedPreferencesUtil.put(this, BaseConfig.IS_LOGIN, true);
        SnackBarUtil.showTipWithoutAction(mMainView, "登录成功");
        MainActivity.actionStar(this);
        this.finish();
    }

    @Override
    public void showProgress() {
        setLoading(true);
    }

    @Override
    public void hideProgress() {
        setLoading(false);
    }
}
