package ru.aisdev.worksafety.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

import com.google.android.gms.ads.AdView;

import ru.aisdev.worksafety.R;
import ru.aisdev.worksafety.constants.AppConstants;
import ru.aisdev.worksafety.listeners.WebListener;
import ru.aisdev.worksafety.utilities.AdsUtilities;
import ru.aisdev.worksafety.web.WebEngine;

public class CustomUrlActivity extends BaseActivity {

    private Activity activity;
    private Context context;
    private String pageTitle, pageUrl;

    private WebView webView;
    private WebEngine webEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initVar();
        initView();
        initFunctionality();

    }

    private void initVar() {
        activity = CustomUrlActivity.this;
        context = activity.getApplicationContext();

        Intent intent = getIntent();
        if (intent != null) {
            pageTitle = intent.getStringExtra(AppConstants.BUNDLE_KEY_TITLE);
            pageUrl = intent.getStringExtra(AppConstants.BUNDLE_KEY_URL);
        }
    }

    private void initView() {
        setContentView(R.layout.activity_custom_url);
        initWebEngine();
        initLoader();
        initToolbar(true);
        setToolbarTitle(pageTitle);
        enableUpButton();
    }

    public void initWebEngine() {
        webView = findViewById(R.id.webView);

        webEngine = new WebEngine(webView, activity);
        webEngine.initWebView();

        webEngine.initListeners(new WebListener() {
            @Override
            public void onStart() {
                showLoader();
            }

            @Override
            public void onLoaded() {
                hideLoader();
            }

            @Override
            public void onProgress(int progress) {

            }

            @Override
            public void onNetworkError() {
                showEmptyView();
            }

            @Override
            public void onPageTitle(String title) {

            }
        });
    }

    private void initFunctionality() {
        webEngine.loadPage(pageUrl);

        // show full-screen ads
        AdsUtilities.getInstance(context).showFullScreenAd();
        // show banner ads
        AdsUtilities.getInstance(context).showBannerAd((AdView) findViewById(R.id.adsView));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }


    }
}
