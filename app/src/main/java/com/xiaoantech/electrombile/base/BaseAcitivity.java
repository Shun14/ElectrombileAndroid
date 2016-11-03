package com.xiaoantech.electrombile.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jk on 16-10-26.
 *
 * the base class of all activity
 */

public abstract class BaseAcitivity extends AppCompatActivity {


    protected abstract void initBefore();

    protected abstract void initView();

    protected abstract void bindData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
        bindData();
        initView();
    }

}
