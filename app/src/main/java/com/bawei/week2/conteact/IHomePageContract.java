package com.bawei.week2.conteact;

import com.bawei.week2.base.IBaseView;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 * * 契约类，作用是用来管理所有的接口
 *  * 他把我们V层，P层和M层所需要实现的所有接口统一管理
 *  * 然后，由我们具体的V层（某个Activity或者某个Fragment）实现其中的V层接口
 *  * 由我们具体的P层,实现其中的P层接口
 *  * 由我们具体的M层,实现其中的M层接口
 *  *
 *  * 注意：契约类实际上是一个接口，使用interface而不是class!!!!!!!!!!!!!!
 *  *
 *  *
 *  * 8.创建该页面的契约类，这个类是个接口
 *  * 9.在契约类中，实现V层的接口，继承IBaseView
 *  * 10.在V层接口中，添加需要实现的方法，一般是成功和失败两个
 *  * 11.在契约类中，实现P层的接口
 *  * 12.在P层接口中，添加需要实现的方法，一般是获取数据一个
 *  * 13.在契约类中，实现M层的接口
 *  * 14.在M层接口中，添加需要实现的方法，一般是获取数据一个
 *  * 15.在M层接口中，添加一个接口回调，内部实现成功和失败两个方法
 */
public interface IHomePageContract {
    //在view的接口中我们需要定义获取成功和失败的方法
    interface IView extends IBaseView {
        void onGetBannerSuccess(String str);
        void onGetBannerFailure(String str);

        void onGetListSuccess(String str);
        void onGetListFailure(String str);
    }

    //在presenter中，我们只需要定义获取数据的方法就可以了
    interface IPresenter {
        void getBanner(String url);

        void getList(String url);
    }

    //在model中，我们需要定义获取数据的方法，并再次创建一个接口回调，用来回调网络请求的数据给P层
    interface IModel {
        void getBanner(String url, IModelCallback callback);

        void getList(String url, IModelGetListCallback callback);

        interface IModelCallback {
            void onGetBannerSuccess(String str);

            void onGetBannerFailure(String str);
        }


        interface IModelGetListCallback {
            void onGetListSuccess(String str);
            void onGetListFailure(String str);
        }
    }
}
