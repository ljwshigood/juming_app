package com.github.jdsjlzx.interfaces;

import android.view.View;

/**
 */
public interface ILoadMoreFooter {
    /**
     */
    void onReset();

    /**
     */
    void onLoading();

    /**
     */
    void onComplete();

    /**
     */
    void onNoMore();

    /**
     */
    View getFootView();
}
