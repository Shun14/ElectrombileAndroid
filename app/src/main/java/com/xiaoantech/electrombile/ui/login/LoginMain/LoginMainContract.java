package com.xiaoantech.electrombile.ui.login.LoginMain;

import com.xiaoantech.electrombile.base.BasePresenter;
import com.xiaoantech.electrombile.base.BaseView;

/**
 * Created by yangxu on 2016/10/28.
 */
public interface LoginMainContract {
    interface View extends BaseView<Presenter>{
    }
    interface Presenter extends BasePresenter{

        void register();

        int getBackgroundImage();
    }
}