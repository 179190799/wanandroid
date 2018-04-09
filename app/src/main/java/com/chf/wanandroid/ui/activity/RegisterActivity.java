package com.chf.wanandroid.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Menu;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.model.bean.UserBean;
import com.chf.wanandroid.mvp.presenter.RegisterPresenter;
import com.chf.wanandroid.mvp.view.RegisterView;
import com.chf.wanandroid.ui.utils.SnackBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册窗口
 *
 * @author 17919
 * @date 2018/4/9
 */

public class RegisterActivity extends BaseToolBarActivity<RegisterPresenter> implements RegisterView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.register_input_username)
    EditText registerInputUsername;
    @BindView(R.id.register_username_til)
    TextInputLayout registerUsernameTil;
    @BindView(R.id.register_input_password)
    EditText registerInputPassword;
    @BindView(R.id.register_password_til)
    TextInputLayout registerPasswordTil;
    @BindView(R.id.register_reinput_password)
    EditText registerReinputPassword;
    @BindView(R.id.register_repassword_til)
    TextInputLayout registerRepasswordTil;
    @BindView(R.id.register_tv)
    TextView registerTv;
    @BindView(R.id.register_ll)
    LinearLayout registerLl;
    @BindView(R.id.register_main_layout)
    LinearLayout registerMainLayout;

    /**
     * 注册成功
     *
     * @param userBean
     */
    @Override
    public void registerSuccess(UserBean userBean) {
        MainActivity.actionStar(this);
        SnackBarUtil.showTipWithoutAction(registerMainLayout,"注册成功");
        finish();
    }

    @Override
    public void showProgress() {
        setLoading(true);
    }

    @Override
    public void hideProgress() {
        setLoading(false);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (registerInputPassword.getText().length() > 0
                && registerInputUsername.getText().length() > 0
                && registerReinputPassword.getText().length() > 0) {
            registerTv.setEnabled(true);
        } else {
            registerTv.setEnabled(false);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void initView() {
        registerInputPassword.addTextChangedListener(this);
        registerInputUsername.addTextChangedListener(this);
        registerReinputPassword.addTextChangedListener(this);
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
            actionBar.setTitle("注册");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @OnClick(R.id.register_tv)
    void click() {
        String userName = registerInputUsername.getText().toString();
        String pwd = registerInputPassword.getText().toString();
        String rePwd = registerReinputPassword.getText().toString();
        if (presenter.checkParams(userName, pwd, rePwd, registerUsernameTil, registerPasswordTil, registerRepasswordTil)) {
            presenter.goRegister(userName, pwd, pwd);
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initPresenter() {
        presenter = new RegisterPresenter(this, this);
        presenter.init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }


}
