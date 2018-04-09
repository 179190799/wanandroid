package com.chf.wanandroid.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.chf.wanandroid.R;
import com.chf.wanandroid.base.BaseToolBarActivity;
import com.chf.wanandroid.mvp.presenter.WebViewPresenter;
import com.chf.wanandroid.mvp.view.WebBaseView;
import com.chf.wanandroid.ui.base.BaseConfig;
import com.chf.wanandroid.ui.utils.SnackBarUtil;
import com.chf.wanandroid.ui.widget.ProgressWebView;

import butterknife.BindView;

/**
 * normal webView Activity
 *
 * @author Administrator
 * @date 2018/1/11
 */

@SuppressLint("Registered")
public class BaseWebViewActivity extends BaseToolBarActivity<WebViewPresenter> implements WebBaseView {

    @BindView(R.id.normal_web_view)
    ProgressWebView webView;

    private String url;

    @Override
    public void initView() {
        Intent intent = this.getIntent();
        if (intent != null) {
            if (intent.getStringExtra(BaseConfig.URL) != null) {
                url = intent.getStringExtra(BaseConfig.URL);
                setTitle(url);
                presenter.setWebViewSettings(webView, url);
            }
        }
    }

    /**
     *
     * @param context
     * @param url HTML链接
     */
    public static void actionStart(Context context, String url) {
        Intent intent = new Intent(context, BaseWebViewActivity.class);
        intent.putExtra(BaseConfig.URL, url);
        context.startActivity(intent);
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
    public void setWebTitle(String title) {
        setTitle(title);
    }

    @Override
    public void openFailed() {
        SnackBarUtil.showTipWithoutAction(webView, "打开网页失败");
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.base_web_view;
    }

    @Override
    protected void initPresenter() {
        presenter = new WebViewPresenter(this, this);
        presenter.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webview_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                presenter.refresh(webView);
                break;
            case R.id.action_copy_url:
                presenter.copyUrl(webView, webView.getUrl());
                break;
            case R.id.action_open_in_browser:
                presenter.openInBrowser(webView.getUrl());
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        if (webView != null) {
            webView.onResume();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (webView != null) {
            webView.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.removeView(webView);
            webView.removeAllViews();
            webView.destroy();
            webView = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
                default:
                    break;
            }
        }

        return super.onKeyDown(keyCode, event);
    }


}
