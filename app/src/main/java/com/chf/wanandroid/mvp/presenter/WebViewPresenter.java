package com.chf.wanandroid.mvp.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.chf.wanandroid.base.BasePresenter;
import com.chf.wanandroid.mvp.view.WebBaseView;
import com.chf.wanandroid.ui.utils.AppInfoUtil;
import com.chf.wanandroid.ui.widget.ProgressWebView;


/**
 * webView presenter
 *
 * @author Administrator
 * @date 2018/1/11
 */

public class WebViewPresenter extends BasePresenter<WebBaseView> {

    public WebViewPresenter(Context context, WebBaseView iView) {
        super(context, iView);
    }

    @Override
    public void release() {

    }


    @SuppressLint("SetJavaScriptEnabled")
    public void setWebViewSettings(ProgressWebView webView, String url) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        webView.setWebChromeClient(new ChromeClient());
        webView.setWebViewClient(new NormalWebViewClient());
        webView.loadUrl(url);
    }


    private class ChromeClient extends ProgressWebView.MyWebChromeClient {
        @Override
        public void onReceivedTitle(android.webkit.WebView view, String title) {
            super.onReceivedTitle(view, title);
            iView.setWebTitle(title);
        }
    }

    private class NormalWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, WebResourceRequest request) {
            if (request.getUrl() != null) {
                view.loadUrl(String.valueOf(request.getUrl()));
            }
            return true;
        }
    }

    /**
     * 刷新
     *
     * @param webView
     */
    public void refresh(android.webkit.WebView webView) {
        webView.reload();
    }

    /**
     * 复制链接
     *
     * @param view
     * @param text
     */
    public void copyUrl(View view, String text) {
        AppInfoUtil.copyToClipboard(view, text, "复制成功");
    }

    /**
     * 用浏览器打开
     *
     * @param url
     */
    public void openInBrowser(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(url);
        intent.setData(uri);
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        } else {
            iView.openFailed();
        }
    }
}
