package com.bawei.week2.model;

import android.util.Log;

import com.bawei.week2.conteact.IHomePageContract;
import com.bawei.week2.utils.VolleyUtils;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 * * 通常情况下，一个页面对应一个Model
 *  * 这一个Model里面包含当前页面下所有的接口
 *  *
 *  * 实现契约类中m层接口，重写方法
 *  *
 *  * 16.写一个具体的Model层，实现契约类中的Model接口，用来调用网络工具类、
 *  * 17.重写契约类中的方法，在方法中完成调用工具类，并接收回调数据
 *  * 18.将网络工具类回调的数据，通过方法传入的callback，回调至p层
 */
public class HomePageModel implements IHomePageContract.IModel {
    @Override
    public void getBanner(String url, final IModelCallback callback) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.CallBack() {
            @Override
            public void success(String json) {
                callback.onGetBannerSuccess(json);
            }

            @Override
            public void falied(String msg) {
                callback.onGetBannerFailure(msg);
            }
        });
    }

    @Override
    public void getList(final String url, final IModelGetListCallback callback) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.CallBack() {
            @Override
            public void success(String json) {
                callback.onGetListSuccess(url);
            }

            @Override
            public void falied(String msg) {
                callback.onGetListFailure(msg);
            }
        });
    }
}
