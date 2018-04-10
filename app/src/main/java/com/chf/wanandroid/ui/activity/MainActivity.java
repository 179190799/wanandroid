package com.chf.wanandroid.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.presenter.MainActivityPresenter;
import com.chf.wanandroid.mvp.view.MainActivityView;
import com.chf.wanandroid.ui.base.BaseConfig;
import com.chf.wanandroid.ui.events.RefreshEvent;
import com.chf.wanandroid.ui.fragment.ClassFragment;
import com.chf.wanandroid.ui.fragment.HomeFragment;
import com.chf.wanandroid.ui.fragment.HotFragment;
import com.chf.wanandroid.ui.utils.SharedPreferencesUtil;
import com.chf.wanandroid.ui.utils.StringUtils;
import com.chf.wanandroid.ui.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 17919
 */
public class MainActivity extends BaseToolBarActivity<MainActivityPresenter> implements MainActivityView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.main_content_fl)
    FrameLayout mainContentFl;
    @BindView(R.id.main_home_iv)
    ImageView mainHomeIv;
    @BindView(R.id.main_home_tv)
    TextView mainHomeTv;

    @BindView(R.id.main_home_layout)
    LinearLayout mainHomeLayout;
    @BindView(R.id.main_class_fication_iv)
    ImageView mainClassFicationIv;
    @BindView(R.id.main_class_fication_tv)
    TextView mainClassFicationTv;
    @BindView(R.id.main_class_layout)
    LinearLayout mainClassLayout;
    @BindView(R.id.main_hot_iv)
    ImageView mainHotIv;
    @BindView(R.id.main_hot_tv)
    TextView mainHotTv;
    @BindView(R.id.main_hot_layout)
    LinearLayout mainHotLayout;
    @BindView(R.id.draw_my_loved_layout)
    LinearLayout drawMyLovedLayout;
    @BindView(R.id.draw_my_collection_layout)
    LinearLayout drawMyCollectionLayout;
    @BindView(R.id.draw_about_layout)
    LinearLayout drawAboutLayout;
    @BindView(R.id.draw_quit_login_tv)
    TextView drawQuitLoginTv;

    @BindView(R.id.main_draw_layout)
    DrawerLayout mDrawLayout;


    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        initFragment(0);
        mainHomeIv.setImageResource(R.drawable.icon_home_blue);
        mainHomeTv.setVisibility(View.VISIBLE);
        mainHomeTv.setTextColor(ContextCompat.getColor(this, R.color.tool_bar_color));

        initDrawLayout();
    }

    private void initDrawLayout() {

        if (!StringUtils.isLogin(this)) {
            drawQuitLoginTv.setText("登录");
        }

        mDrawLayout.addDrawerListener(presenter.mSimpleDrawerListener);

        toolbar.setNavigationIcon(R.drawable.icon_main_draw);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawLayout.isDrawerOpen(Gravity.LEFT)) {
//                    关闭
                    mDrawLayout.closeDrawer(Gravity.LEFT);
                } else {
//                    打开
                    mDrawLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        ActionBarDrawerToggle mToggle = new ActionBarDrawerToggle(MainActivity.this,
                mDrawLayout,
                toolbar,
                R.string.open,
                R.string.close);
        mToggle.syncState();
        mDrawLayout.addDrawerListener(mToggle);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        presenter = new MainActivityPresenter(this, this);
        presenter.init();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(canBack());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    private HomeFragment homeFragment;
    private ClassFragment classFragment;
    private HotFragment hotFragment;

    /**
     * 初始化Fragment
     *
     * @param index
     */
    public void initFragment(int index) {

        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.main_content_fl, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                if (classFragment == null) {
                    classFragment = new ClassFragment();
                    transaction.add(R.id.main_content_fl, classFragment);
                } else {
                    transaction.show(classFragment);
                }

                break;
            case 2:
                if (hotFragment == null) {
                    hotFragment = new HotFragment();
                    transaction.add(R.id.main_content_fl, hotFragment);
                } else {
                    transaction.show(hotFragment);
                }
                break;


            default:
                break;
        }

        // 提交事务
        transaction.commit();

    }

    /**
     * 隐藏Fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (classFragment != null) {
            transaction.hide(classFragment);
        }
        if (hotFragment != null) {
            transaction.hide(hotFragment);
        }

    }

    @OnClick({R.id.main_class_layout, R.id.main_home_layout, R.id.main_hot_layout})
    void onClick(View view) {
        initTab();
        switch (view.getId()) {
            case R.id.main_home_layout:
                tabClick(0);
                break;
            case R.id.main_class_layout:
                tabClick(1);
                break;
            case R.id.main_hot_layout:
                tabClick(2);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.draw_my_loved_layout,
            R.id.draw_my_collection_layout,
            R.id.draw_about_layout,
            R.id.draw_quit_login_tv})
    void onDrawLayoutClick(View view) {
        switch (view.getId()) {
            case R.id.draw_my_loved_layout:
//                onlyLoved
                break;
            case R.id.draw_my_collection_layout:
//                我的收藏
                if (!StringUtils.isLogin(this)) {
                    ToastUtil.showShort(mContext, "请先登录");
                    return;
                }
                MyCollectionActivity.actionStart(this);
                break;
            case R.id.draw_about_layout:
//                关于

                break;
            case R.id.draw_quit_login_tv:
                if (mDrawLayout.isDrawerOpen(Gravity.LEFT)) {
//                    关闭
                    mDrawLayout.closeDrawer(Gravity.LEFT);
                }
                if (!StringUtils.isLogin(this)) {
//                    登录
                    LoginActivity.actionStart(this);
                } else {
//                退出登录
//                    清除Cookie
                    CookieSyncManager.createInstance(getApplicationContext());
                    CookieManager.getInstance().removeAllCookie();
                    SharedPreferencesUtil.put(mContext, BaseConfig.IS_LOGIN, false);
                    EventBus.getDefault().post(new RefreshEvent(1));
                    drawQuitLoginTv.setText("登录");
                }
                break;
            default:
                break;
        }

    }

    /**
     * 初始化标签
     */
    private void initTab() {
        mainHomeIv.setImageResource(R.drawable.icon_home_gray);
        mainClassFicationIv.setImageResource(R.drawable.icon_classfication_gray);
        mainHotIv.setImageResource(R.drawable.icon_hot_gray);

        mainHomeTv.setVisibility(View.GONE);
        mainHomeTv.setTextColor(ContextCompat.getColor(this, R.color.bg_color));
        mainClassFicationTv.setVisibility(View.GONE);
        mainClassFicationTv.setTextColor(ContextCompat.getColor(this, R.color.bg_color));
        mainHotTv.setVisibility(View.GONE);
        mainHotTv.setTextColor(ContextCompat.getColor(this, R.color.bg_color));

    }

    /**
     * 点击切换
     *
     * @param position
     */
    private void tabClick(int position) {
        initFragment(position);
        switch (position) {
            case 0:
                mainHomeIv.setImageResource(R.drawable.icon_home_blue);
                mainHomeTv.setVisibility(View.VISIBLE);
                mainHomeTv.setTextColor(ContextCompat.getColor(this, R.color.tool_bar_color));

                break;
            case 1:
                mainClassFicationIv.setImageResource(R.drawable.icon_classfication_blue);
                mainClassFicationTv.setVisibility(View.VISIBLE);
                mainClassFicationTv.setTextColor(ContextCompat.getColor(this, R.color.tool_bar_color));
                break;
            case 2:
                mainHotIv.setImageResource(R.drawable.icon_hot_blue);
                mainHotTv.setVisibility(View.VISIBLE);
                mainHotTv.setTextColor(ContextCompat.getColor(this, R.color.tool_bar_color));
                break;
            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawLayout.isDrawerOpen(Gravity.LEFT)) {
            mDrawLayout.closeDrawer(Gravity.LEFT);
        }
        super.onBackPressed();
    }

    public static void actionStar(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Subscribe
    public void refreshEvent(RefreshEvent event) {
        presenter.refreshLayout(event, drawQuitLoginTv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mDrawLayout.removeDrawerListener(presenter.mSimpleDrawerListener);
    }
}
