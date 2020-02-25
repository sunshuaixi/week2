package com.bawei.week2.conteact;

import com.bawei.week2.base.IBaseView;

import java.util.HashMap;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 */
public interface ILoginContract {
    interface ILoginView extends IBaseView {
        void onLoginSuccess(String str);
        void onLoginFailure(String str);
    }

    interface ILoginPresenter{
        void doLogin(String url, HashMap<String, String> map);
    }

    interface ILoginModel{
        void doLogin(String url, HashMap<String, String> map, ILoginCallBack callBack);

        interface ILoginCallBack{
            void onLoginSuccess(String str);
            void onLoginFailure(String str);
        }
    }
}
