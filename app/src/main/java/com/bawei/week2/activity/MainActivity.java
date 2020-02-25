package com.bawei.week2.activity;


import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bawei.week2.R;
import com.bawei.week2.adapter.ListAdapter;
import com.bawei.week2.base.BaseActivity;
import com.bawei.week2.base.BasePresenter;
import com.bawei.week2.bean.ListBean;
import com.bawei.week2.bean.XBannerBean;
import com.bawei.week2.conteact.IHomePageContract;
import com.bawei.week2.presenter.HomePagePresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * * 实现契约类中的v层接口，重写回调方法
 *  *
 *  *
 *  * 31.继承BaseActivity，实现契约类中V层接口
 *  * 32.在initPresenter中，new 一个具体的P层，传入this
 *  * 33.在发起请求的地方（可能是initData，可能是点击事件，根据需求来定），
 *  *    通过getPresenter，判断是契约类中P层接口，是则调用p层方法
 *  * 34.在重写的契约类中V层的方法里，实现对结果的处理
 */
public class MainActivity extends BaseActivity implements IHomePageContract.IView {


    private ListView lv;
    private XBanner xb;


    @Override
    protected int getlayout() {
        return R.layout.activity_main;
    }

    @Override
    public BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }
    @Override
    protected void initView() {
        xb = findViewById(R.id.xb);
        lv = findViewById(R.id.lv);
    }

    @Override
    protected void initData() {
        String url = "http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
        String urlGetList = "http://mobile.bwstudent.com/small/commodity/v1/commodityList";
        BasePresenter presenter = getPresenter();
        if(presenter!=null&&presenter instanceof IHomePageContract.IPresenter){
            ((IHomePageContract.IPresenter) presenter).getBanner(url);
            ((IHomePageContract.IPresenter) presenter).getList(urlGetList);
        }
    }


    @Override
    public void onGetBannerSuccess(String str) {
        Gson gson = new Gson();
        XBannerBean xBannerBean = gson.fromJson(str, XBannerBean.class);
        final List<XBannerBean.ResultBean> result = xBannerBean.getResult();

        //设置数据源
        xb.setBannerData(result);
        //加载图片
        xb.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                XBannerBean.ResultBean resultBean = result.get(position);
                String imageUrl = resultBean.getImageUrl();
                Glide.with(MainActivity.this).load(imageUrl).into((ImageView)view);
            }
        });
    }

    @Override
    public void onGetBannerFailure(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onGetListSuccess(String str) {

        Gson gson = new Gson();
        ListBean listBean = gson.fromJson(str, ListBean.class);
        ListBean.ResultBean result = listBean.getResult();
        ListBean.ResultBean.PzshBean pzsh = result.getPzsh();
        List<ListBean.ResultBean.PzshBean.CommodityListBeanX> commodityList = pzsh.getCommodityList();
         ListAdapter listAdapter = new ListAdapter(MainActivity.this, commodityList);
         lv.setAdapter(listAdapter);
    }

    @Override
    public void onGetListFailure(String str) {

    }
}
