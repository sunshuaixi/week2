package com.bawei.week2.model;

import com.bawei.week2.conteact.ILoginContract;
import com.bawei.week2.utils.VolleyUtils;

import java.util.HashMap;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 */
public class LoginModel implements ILoginContract.ILoginModel {
    @Override
    public void doLogin(String url, HashMap<String, String> map, final ILoginCallBack callBack) {
        VolleyUtils.getInstance().doPost(url, map, new VolleyUtils.CallBack() {
            @Override
            public void success(String json) {
                callBack.onLoginSuccess(json);
            }

            @Override
            public void falied(String msg) {
                callBack.onLoginFailure(msg);
            }
        });
    }
}
